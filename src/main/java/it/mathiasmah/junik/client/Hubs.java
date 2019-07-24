package it.mathiasmah.junik.client;

import it.mathiasmah.junik.client.exceptions.UnikException;
import it.mathiasmah.junik.client.models.Hub;
import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  A class holding all requests related to unik hubs
 */
public class Hubs extends Requests {

    private static final String IMAGES_BASE = "/images";

    Hubs(HttpClient client, String baseUrl) throws UnikException {
        super(client, baseUrl);
    }

    /**
     * Pushes an image from the unik target to the specified unik hub
     *
     * @param hub a {@link Hub} containing the information about the unik hub
     * @param imageName the name of an image
     * @throws UnikException if the request was not successful
     * @see Hub
     */
    public void push(Hub hub, String imageName) throws UnikException {
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
     * @param hub a {@link Hub} containing the information about the unik hub
     * @param imageName the name of an image
     * @param provider the name of a provider
     * @param force the pulling of the image will be enforced, e.g. an existing image will be overwritten
     * @throws UnikException if the request was not successful
     * @see Hub
     */
    public void pull(Hub hub, String imageName, String provider, boolean force) throws UnikException {
        JsonBodyBuilder builder = new JsonBodyBuilder().addObject(hub);

        Map<String, String> params = new HashMap<>();
        params.put("provider", provider);
        params.put("force", String.valueOf(force));

        try {
            post(String.format(IMAGES_BASE + "pull/%s", imageName),params, builder.build());
        } catch (IOException e) {
            throw new UnikException(e);
        }

    }

    /**
     * Remove an image from the specified unik hub.
     *
     * @param hub a {@link Hub} containing the information about the unik hub
     * @param imageName the name of an image
     * @throws UnikException if the request was not successful
     * @see Hub
     */
    public void remove(Hub hub, String imageName) throws UnikException {
        JsonBodyBuilder builder = new JsonBodyBuilder().addObject(hub);

        try {
            post(String.format(IMAGES_BASE + "remote-delete/%s", imageName), builder.build());
        } catch (IOException e) {
            throw new UnikException(e);
        }
    }
}
