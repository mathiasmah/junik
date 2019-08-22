package it.mathiasmah.junik.client;

import org.apache.http.HttpEntity;
import org.apache.http.impl.client.AbstractResponseHandler;

import java.io.IOException;
import java.io.OutputStream;

class StreamResponseHandler extends AbstractResponseHandler<Void> {

    private final OutputStream outputStream;

    public StreamResponseHandler(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public Void handleEntity(HttpEntity entity) throws IOException {
        if (entity != null) {
            entity.writeTo(outputStream);
        }
        return null;
    }
}
