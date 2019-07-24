package it.mathiasmah.junik.client;

import it.mathiasmah.junik.client.exceptions.UnikException;
import it.mathiasmah.junik.client.models.CreateImage;
import it.mathiasmah.junik.client.models.Image;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class holding all requests related to images
 */
public class Images extends Requests {

    private static final String IMAGES_BASE = "/images";

    Images(HttpClient client, String baseUrl) throws UnikException {
        super(client, baseUrl);
    }

    /**
     * Get all available unikernel images across providers.
     * <p/>
     * Includes important information for running and managing an instance of the images,
     * for example the required mount points for the image.
     *
     * @return a {@link List} of {@link Image} describing all images
     * @throws UnikException if the request was not successful
     * @see Image
     */
    public List<Image> getAllAvailable() throws UnikException {
        return Arrays.asList(get(IMAGES_BASE, Image[].class));
    }

    /**
     * Get the information about one image, identified by its name.
     *  <p/>
     * Includes important information for running and managing an instance of the image,
     * for example the required mount points for the image.
     *
     *
     * @param name the name of an image
     * @return a {@link Image} describing the requested image
     * @throws UnikException if the request was not successful
     * @see Image
     */
    public Image describe(final String name) throws UnikException {
        return get(String.format(IMAGES_BASE + "/%s", name), Image.class);
    }

    /**
     * Builds a new image on the targeted unik backend, with the specified name.
     *
     * @param image an object of {@link CreateImage} holding all the information needed to create the new image
     * @return a {@link Image} describing the newly created image
     * @throws UnikException if the request was not successful
     * @see CreateImage
     * @see Image
     */
    public Image create(final CreateImage image) throws UnikException {

        MultipartEntityBuilder builder = MultipartEntityBuilder
                .create()
                .addPart("no_cleanup", new StringBody(String.valueOf(image.isNoCleanup()), ContentType.TEXT_PLAIN))
                .addPart("force", new StringBody(String.valueOf(image.isForce()), ContentType.TEXT_PLAIN))
                .addPart("provider", new StringBody(image.getProvider(), ContentType.TEXT_PLAIN))
                .addPart("base", new StringBody(image.getBase(), ContentType.TEXT_PLAIN))
                .addPart("lang", new StringBody(image.getLanguage(), ContentType.TEXT_PLAIN))
                .addPart("args", new StringBody(String.join(",", image.getArgs()), ContentType.TEXT_PLAIN))
                .addPart("mounts", new StringBody(String.join(",", image.getMounts()), ContentType.TEXT_PLAIN))
                .addPart("tarfile", new FileBody(new File(image.getTarFile())));


        return post(String.format(IMAGES_BASE + "/%s/create", image.getName()), builder.build(), Image.class);
    }

    /**
     * Remove an image identified by its name.
     *
     * @param name the name of an image
     * @param force the removal of the image will be enforced
     * @throws UnikException if the request was not successful
     */
    public void delete(final String name, final boolean force) throws UnikException {
        Map<String, String> params = new HashMap<>();
        params.put("force", String.valueOf(force));

        delete(String.format(IMAGES_BASE + "/%s", name), params);
    }

}
