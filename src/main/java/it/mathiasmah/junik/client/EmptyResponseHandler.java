package it.mathiasmah.junik.client;

import org.apache.http.HttpEntity;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.util.EntityUtils;

class EmptyResponseHandler extends AbstractResponseHandler<Void> {

    @Override
    public Void handleEntity(HttpEntity entity) {
        EntityUtils.consumeQuietly(entity);
        return null;
    }
}
