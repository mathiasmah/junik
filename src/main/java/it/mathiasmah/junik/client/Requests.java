package it.mathiasmah.junik.client;

import it.mathiasmah.junik.client.exceptions.UnikException;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

abstract class Requests {

    private final HttpClient client;
    private final String host;
    private final String schema;

    Requests(final HttpClient client, final String baseUrl) throws UnikException {
        this.client = client;

        try {
            String[] base = baseUrl.split("://");
            schema = base[0];
            host = base[1];
        } catch (Exception e) {
            throw new UnikException("The base url is not valid", e);
        }

    }


    <T> T get(final String url, final Class<T> returnType) throws UnikException {
        return get(url, Collections.emptyMap(), returnType);
    }

    <T> T get(final String url, final Map<String, String> params, final Class<T> returnType) throws UnikException {

        HttpGet request;
        try {
            request = new HttpGet(buildURI(url, params));
        } catch (URISyntaxException e) {
            throw new UnikException(e);
        }

        return send(request, returnType);
    }

    void post(final String url) throws UnikException {
        post(url, Collections.emptyMap(), null, null);
    }

    void post(final String url, final Map<String, String> params) throws UnikException {
        post(url, params, null, null);
    }

    void post(final String url, final HttpEntity entity) throws UnikException {
        post(url, Collections.emptyMap(), entity, null);
    }

    void post(final String url, final Map<String, String> params, final HttpEntity entity) throws UnikException {
        post(url, params, entity, null);
    }

    <T> T post(final String url, final HttpEntity entity, final Class<T> returnType) throws UnikException {
        return post(url, Collections.emptyMap(), entity, returnType);
    }

    <T> T post(final String url, final Map<String, String> params, final HttpEntity entity, final Class<T> returnType) throws UnikException {

        HttpPost request;
        try {
            request = new HttpPost(buildURI(url, params));
            request.setEntity(entity);

        } catch (URISyntaxException e) {
            throw new UnikException(e);
        }

        return send(request, returnType);
    }

    void delete(final String url, final Map<String, String> params) throws UnikException {

        HttpDelete request;
        try {
            request = new HttpDelete(buildURI(url, params));
        } catch (URISyntaxException e) {
            throw new UnikException(e);
        }

        send(request, null);
    }

    @SuppressWarnings("unchecked")
    private <T> T send(final HttpUriRequest request, Class<T> returnType) throws UnikException {

        try {
            if (returnType == null) {
                client.execute(request, new EmptyResponseHandler());
                return null;
            } else if (String.class.equals(returnType)) {
                return (T) client.execute(request, new StringResponseHandler());
            } else if (InputStream.class.equals(returnType)) {
                return (T) client.execute(request, new StreamResponseHandler());
            } else {
                return client.execute(request, new JsonResponseHandler<>(returnType));
            }

        } catch (HttpResponseException e) {
            throw new UnikException("Request was not successful, status code [" + e.getStatusCode() + "], error response [" + e.getReasonPhrase() + "]");
        } catch (IOException e) {
            throw new UnikException(e);
        }
    }

    private URI buildURI(String url, final Map<String, String> queries) throws URISyntaxException {
        URIBuilder builder = new URIBuilder()
                .setScheme(schema)
                .setHost(host)
                .setPath(url);

        queries.forEach(builder::setParameter);

        return builder.build();
    }
}
