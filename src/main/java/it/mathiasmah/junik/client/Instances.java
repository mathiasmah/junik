package it.mathiasmah.junik.client;

import it.mathiasmah.junik.client.exceptions.UnikException;
import it.mathiasmah.junik.client.models.Instance;
import it.mathiasmah.junik.client.models.RunInstance;
import org.apache.http.client.HttpClient;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class holding all requests related to instances
 **/
public class Instances extends Requests {

    private static final String INSTANCES_BASE = "/instances";

    Instances(HttpClient client, String baseUrl) throws UnikException {
        super(client, baseUrl);
    }

    /**
     * Lists all available unikernel instances across providers.
     * <p/>
     * Includes important information about the state of the instances.
     *
     * @return a  {@link List} of {@link Instance} holding the information about the instances
     * @throws UnikException if the request was not successful
     * @see Instance
     */
    public List<Instance> getAllAvailable() throws UnikException {
        return Arrays.asList(get(INSTANCES_BASE, Instance[].class));
    }

    /**
     * Describe an instance identified by its ID or name.
     * <p/>
     * Includes important information about the state of the instance.
     *
     * @param name the name or the id of an existing instance
     * @return a {@link Instance} holding the information about the instance
     * @throws UnikException if the request was not successful
     * @see Instance
     */
    public Instance describe(final String name) throws UnikException {
        return get(String.format(INSTANCES_BASE + "/%s", name), Instance.class);
    }

    /**
     * Powers of an instance identified by its ID or name.
     *
     * @param name the name or the id of an existing instance
     * @throws UnikException if the request was not successful
     */
    public void stop(final String name) throws UnikException {
        post(String.format(INSTANCES_BASE + "/%s/stop", name));
    }

    /**
     * Powers on an existing instance identified by its ID or name.
     *
     * @param name the name or the id of an existing instance
     * @throws UnikException if the request was not successful
     */
    public void start(final String name) throws UnikException {
        post(String.format(INSTANCES_BASE + "/%s/start", name));
    }

    /**
     * Deploys a running instance from a unik-compiled unikernel disk image.
     * <p/>
     * The instance will be deployed on the provider the image was compiled for. e.g. if the image was compiled for virtualbox,
     * unik will attempt to deploy the image on the configured virtualbox environment.
     *
     * @param runInstance a {@link RunInstance} holding all the relevant information to run an new instance
     * @return a {@link Instance} describing the newly created instance
     * @throws UnikException if the request was not successful
     * @see RunInstance
     * @see Instance
     */
    public Instance run(final RunInstance runInstance) throws UnikException {
        JsonBodyBuilder builder = new JsonBodyBuilder().addObject(runInstance);

        try {
            return post(INSTANCES_BASE + "/run", builder.build(), Instance.class);
        } catch (IOException e) {
            throw new UnikException(e);
        }
    }

    /**
     * Deletes an instance identified by its ID or name.
     *
     * @param name  the name or ID of an existing instance
     * @param force the removal of the instance will be enforced
     * @throws UnikException if the request was not successful
     */
    public void delete(final String name, final boolean force) throws UnikException {
        Map<String, String> params = new HashMap<>();
        params.put("force", String.valueOf(force));

        delete(String.format(INSTANCES_BASE + "/%s", name), params);
    }

    /**
     * Retrieves logs from a running unikernel instance as a snapshot containing all logs up until the time of the request.
     *
     * @param name the name or ID of a running instance
     * @return All logs of this instance up until the time of the request
     * @throws UnikException if the request was not successful
     */
    public String logsAsString(final String name) throws UnikException {
        return get(String.format(INSTANCES_BASE + "/%s/logs", name), String.class);
    }

    /**
     * Write logs form a running unikernel instance to a stream.
     * <p/>
     * The connection will stay open and all new logs will be delivered until the stream is closed by the client or the instance is terminated.
     *
     * @param name                the name or ID of a running instance
     * @param deleteOnTermination specify if the instance should be removed as soon as the stream is closed.
     * @param outputStream        the stream the log messages are written to
     * @throws UnikException if the request was not successful
     * @see InputStream
     */
    public void logToStream(final String name, final boolean deleteOnTermination, final OutputStream outputStream) throws UnikException {

        Map<String, String> params = new HashMap<>();
        params.put("follow", String.valueOf(true));
        params.put("delete", String.valueOf(deleteOnTermination));

        String line;
        HttpURLConnection httpConn = null;
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));
        try {
            URL url = buildURI(String.format("/instances/%s/logs", name), params).toURL();
            httpConn = (HttpURLConnection) url.openConnection();

            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.setReadTimeout(50 * 1000);

            BufferedReader is = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

            while ((line = is.readLine()) != null) {

                out.write(line);
                out.newLine();
                out.flush();

            }
        } catch (MalformedURLException | SocketTimeoutException | URISyntaxException e) {
            throw new UnikException("Could not connnect", e);
        } catch (IOException e) {
            if (httpConn != null) {

                BufferedReader is = new BufferedReader(new InputStreamReader(httpConn.getErrorStream()));

                try {
                    while ((line = is.readLine()) != null) {
                        out.write(line);
                        out.flush();
                    }
                } catch (IOException ignore) {
                }
            }

        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
    }
}
