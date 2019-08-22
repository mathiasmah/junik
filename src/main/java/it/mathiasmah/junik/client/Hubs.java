package it.mathiasmah.junik.client;

import it.mathiasmah.junik.client.exceptions.UnikException;
import it.mathiasmah.junik.client.models.Hub;
import org.apache.http.client.HttpClient;
import org.apache.http.util.Asserts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A class holding all requests related to unik hubs
 */
public class Hubs extends Requests {

    private static final String IMAGES_BASE = "/images";

    Hubs(HttpClient client, String baseUrl) throws UnikException {
        super(client, baseUrl);
    }

    /**
     * Pushes an image from the unik target to the specified unik hub
     *
     * @param hub       a {@link Hub} containing the information about the unik hub
     * @param imageName the name of an image, cannot be blank
     * @throws UnikException if the request was not successful
     * @see Hub
     */
    public void push(Hub hub, String imageName) throws UnikException {
        Asserts.notBlank("image name", imageName);

        JsonBodyBuilder builder = new JsonBodyBuilder().addObject(hub);

        try {
            post(String.format(IMAGES_BASE + "push/%s", imageName), builder.build());
        } catch (IOException e) {
            throw new UnikException(e);
        }

    }

    /**
     * Pulls an image from a specified unik hub to the unik target
     *
     * @param hub       a {@link Hub} containing the information about the unik hub
     * @param imageName the name of an image, cannot be blank
     * @param provider  the name of a provider, cannot be blank
     * @param force     the pulling of the image will be enforced, e.g. an existing image will be overwritten
     * @throws UnikException if the request was not successful
     * @see Hub
     */
    public void pull(Hub hub, String imageName, String provider, boolean force) throws UnikException {
        Asserts.notBlank("image name", imageName);
        Asserts.notBlank("provider", provider);

        JsonBodyBuilder builder = new JsonBodyBuilder().addObject(hub);

        Map<String, String> params = new HashMap<>();
        params.put("provider", provider);
        params.put("force", String.valueOf(force));

        try {
            post(String.format(IMAGES_BASE + "pull/%s", imageName), params, builder.build());
        } catch (IOException e) {
            throw new UnikException(e);
        }

    }

    /**
     * Remove an image from the specified unik hub.
     *
     * @param hub       a {@link Hub} containing the information about the unik hub
     * @param imageName the name of an image, cannot be blank
     * @throws UnikException if the request was not successful
     * @see Hub
     */
    public void remove(Hub hub, String imageName) throws UnikException {
        Asserts.notBlank("image name", imageName);

        JsonBodyBuilder builder = new JsonBodyBuilder().addObject(hub);

        try {
            post(String.format(IMAGES_BASE + "remote-delete/%s", imageName), builder.build());
        } catch (IOException e) {
            throw new UnikException(e);
        }
    }

    private void validateHub(Hub hub) {
        Asserts.notNull(hub, "Hub definition");
        Asserts.notBlank(hub.getUrl(), "Hub URL");
    }
}