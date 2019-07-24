package it.mathiasmah.junik.client;

import it.mathiasmah.junik.client.exceptions.UnikException;
import org.apache.http.client.HttpClient;

import java.util.Arrays;
import java.util.List;

/**
 * A class holding all requests related to providers
 */
public class Providers extends Requests {

    private static String GET_ALL_AVAILABLE = "/available_providers";

    Providers(HttpClient client, String baseUrl) throws UnikException {
        super(client, baseUrl);
    }

    /**
     * Get all available providers from the targeted unik instance
     *
     * @return a {@link List} containing all available providers
     * @throws UnikException if the request was not successful
     */
    public List<String> getAllAvailable() throws UnikException {
        return Arrays.asList(get(GET_ALL_AVAILABLE, String[].class));
    }


}
