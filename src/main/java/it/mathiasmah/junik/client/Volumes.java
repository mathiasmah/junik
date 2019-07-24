package it.mathiasmah.junik.client;

import it.mathiasmah.junik.client.exceptions.UnikException;
import it.mathiasmah.junik.client.models.CreateVolume;
import it.mathiasmah.junik.client.models.Volume;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.*;

/**
 * A class holding all requests related to volumes
 */
public class Volumes extends Requests {

    private static final String VOLUMES_BASE = "/volumes";

    Volumes(HttpClient client, String baseUrl) throws UnikException {
        super(client, baseUrl);
    }

    /**
     * Get all available unik-managed volumes across providers.
     *
     * @return a {@link List} of {@link Volume} describing all available volumes.
     * @throws UnikException if the request was not successful
     * @see Volume
     */
    public List<Volume> getAllAvailable() throws UnikException {
        return Arrays.asList(get(VOLUMES_BASE, Volume[].class));
    }

    /**
     * Describe a volume identified by its name.
     *
     * @param name the name of a volume
     * @return a {@link Volume} describing the volume
     * @throws UnikException of the request was not successful
     * @see Volume
     */
    public Volume describe(final String name) throws UnikException {
        return get(String.format(VOLUMES_BASE + "/%s", name), Volume.class);
    }

    /**
     * Attaches an existing volume, identified by its name, to a running instance, identified by its ID or name.
     *
     * @param name the name of an existing volume
     * @param instanceId the name or ID of an existing volume
     * @param mountPoint the mount point of the instance where the volume should be attached
     * @throws UnikException if the request was not successful
     */
    public void attach(final String name, final String instanceId, final String mountPoint) throws UnikException {
        Map<String, String> params = new HashMap<>();
        params.put("mount", mountPoint);

        post(String.format(VOLUMES_BASE + "/%s/attach/%s", name, instanceId), params);
    }

    /**
     * Detaches a volume from its instance.
     *
     * @param name the name of an existing volume
     * @throws UnikException if the request was not successful
     */
    public void detach(final String name) throws UnikException {
        post(String.format(VOLUMES_BASE + "/%s/detach", name));
    }

    /**
     * Creates a data volume which can be attached to and detached from unik-managed instances.
     *
     * @param createVolume a {@link CreateVolume} holding all information needed to create a volume
     * @return a {@link Volume} describing the newly created volume
     * @throws UnikException if the request was not successful
     * @see CreateVolume
     * @see Volume
     */
    public Volume create(final CreateVolume createVolume) throws UnikException {
        if (createVolume.getTarFile() != null) {
            return createFormData(createVolume);
        }
        return createQueryData(createVolume);
    }

    /**
     * Delete a volume identified by its name.
     *
     * @param name the name of a volume
     * @param force the removal of the volume will be enforced
     * @throws UnikException  if the request was not successful
     */
    public void delete(final String name, final boolean force) throws UnikException {
        Map<String, String> params = new HashMap<>();
        params.put("force", String.valueOf(force));

        delete(String.format(VOLUMES_BASE + "/%s", name), params);
    }

    private Volume createFormData(final CreateVolume createVolume) throws UnikException {
        MultipartEntityBuilder builder = MultipartEntityBuilder
                .create()
                .addPart("no_cleanup", new StringBody(String.valueOf(createVolume.isNoCleanup()), ContentType.TEXT_PLAIN))
                .addPart("raw", new StringBody(String.valueOf(createVolume.isRaw()), ContentType.TEXT_PLAIN))
                .addPart("type", new StringBody(createVolume.getType(), ContentType.TEXT_PLAIN))
                .addPart("provider", new StringBody(createVolume.getProvider(), ContentType.TEXT_PLAIN))
                .addPart("tarfile", new FileBody(new File(createVolume.getTarFile())));

        Map<String, String> params = new HashMap<>();
        params.put("size", String.valueOf(createVolume.getSize()));

        return post(String.format(VOLUMES_BASE + "/%s", createVolume.getName()), params, builder.build(), Volume.class);
    }

    private Volume createQueryData(final CreateVolume createVolume) throws UnikException {
        Map<String, String> params = new HashMap<>();
        params.put("no_cleanup", String.valueOf(createVolume.isNoCleanup()));
        params.put("size", String.valueOf(createVolume.getSize()));
        params.put("provider", createVolume.getProvider());

        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("type", createVolume.getType()));
        HttpEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        return post(String.format(VOLUMES_BASE + "/%s", createVolume.getName()), params, entity, Volume.class);
    }
}
