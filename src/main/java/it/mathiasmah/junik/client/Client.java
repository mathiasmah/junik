package it.mathiasmah.junik.client;

import it.mathiasmah.junik.client.exceptions.UnikException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * A wrapper class that holds the base URL and the different groups of requests
 */
public class Client {

    private final HttpClient client;
    private final String baseUrl;

    private Images images;
    private Instances instances;
    private Volumes volumes;
    private Providers providers;
    private Compilers compilers;
    private Hubs hubs;

    /**
     * Create a new instance of junik client
     *
     * @param baseUrl The base URL of the unik target, including http or https
     */
    public Client(final String baseUrl) {
        this.client = HttpClients.createDefault();
        this.baseUrl = baseUrl;
    }

    /**
     * All request related images
     *
     * @return An instance of {@link Images}, holding all request types related to images
     * @throws UnikException if the request is not successful
     */
    public Images images() throws UnikException {
        if (images == null) {
            images = new Images(client, baseUrl);
        }
        return images;
    }

    /**
     * All request related instances
     *
     * @return An instance {@link Instances}, holding all request types related to instances
     * @throws UnikException if the request is not successful
     */
    public Instances instances() throws UnikException {
        if (instances == null) {
            instances = new Instances(client, baseUrl);
        }
        return instances;
    }

    /**
     * All request related volumes
     *
     * @return An instance {@link Volumes}, holding all request types related to volumes
     * @throws UnikException if the request is not successful
     */
    public Volumes volumes() throws UnikException {
        if (volumes == null) {
            volumes = new Volumes(client, baseUrl);
        }
        return volumes;
    }

    /**
     * All request related providers
     *
     * @return An instance {@link Providers}, holding all request types related to providers
     * @throws UnikException if the request is not successful
     */
    public Providers providers() throws UnikException {
        if (providers == null) {
            providers = new Providers(client, baseUrl);
        }
        return providers;
    }

    /**
     * All request related compilers
     *
     * @return An instance {@link Compilers}, holding all request types related to compilers
     * @throws UnikException if the request is not successful
     */
    public Compilers compilers() throws UnikException {
        if (compilers == null) {
            compilers = new Compilers(client, baseUrl);
        }
        return compilers;
    }

    /**
     * All request related hubs
     *
     * @return An instance {@link Hubs}, holding all request types related to hubs
     * @throws UnikException if the request is not successful
     */
    public Hubs hubs() throws UnikException {
        if (hubs == null) {
            hubs = new Hubs(client, baseUrl);
        }
        return hubs;
    }
}
