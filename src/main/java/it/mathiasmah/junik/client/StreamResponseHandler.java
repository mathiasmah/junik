package it.mathiasmah.junik.client;

import org.apache.http.HttpEntity;
import org.apache.http.impl.client.AbstractResponseHandler;

import java.io.IOException;
import java.io.InputStream;

class StreamResponseHandler extends AbstractResponseHandler<InputStream> {

    @Override
    public InputStream handleEntity(HttpEntity entity) throws IOException {
        return entity.getContent();
    }
}
