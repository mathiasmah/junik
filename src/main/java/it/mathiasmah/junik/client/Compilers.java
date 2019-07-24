package it.mathiasmah.junik.client;

import it.mathiasmah.junik.client.exceptions.UnikException;
import org.apache.http.client.HttpClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class holding all requests related to compilers
 */
public class Compilers extends Requests {

    private static final String GET_ALL_AVAILABLE = "/available_compilers";
    private static final String DESCRIBE = "/describe_compiler";

    Compilers(HttpClient client, String baseUrl) throws UnikException {
        super(client, baseUrl);
    }

    /**
     * Get retrieve all available compilers from the unik target
     *
     * @return a {@link List} with the names of all available compilers
     * @throws UnikException if the requests is not successful
     */
    public List<String> getAllAvailable() throws UnikException {
        return Arrays.asList(get(GET_ALL_AVAILABLE, String[].class));
    }

    /**
     * Get the description of one compiler, identified by {@param provider}, {@param base} and {@param language}
     *
     * @param provider the name of a provider, e.g. virtualbox
     * @param base the name of a unikernel base, e.g. osv
     * @param language the name of a programming language, e.g. go
     * @return A formatted {@link String} describing the compiler
     * @throws UnikException if the request is not successful
     */
    public String describe(final String provider, final String base, final String language) throws UnikException {

        Map<String, String> params = new HashMap<>();
        params.put("provider", provider);
        params.put("base", base);
        params.put("lang", language);

        return get(DESCRIBE, params, String.class);
    }

}
