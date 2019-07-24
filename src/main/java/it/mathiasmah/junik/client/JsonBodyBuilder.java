package it.mathiasmah.junik.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class JsonBodyBuilder {

    private List<Object> objects = new ArrayList<>();
    private ObjectMapper objectMapper;

    JsonBodyBuilder() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.findAndRegisterModules();
    }

    JsonBodyBuilder addObject(Object obj) {
        objects.add(obj);
        return this;
    }

    HttpEntity build() throws IOException {
        String json;

        if (objects.isEmpty()) {
            json = "{}";
        } else if (objects.size() == 1) {
            json = objectMapper.writeValueAsString(objects.get(0));
        } else {
            json = objectMapper.writeValueAsString(objects);
        }

        return new StringEntity(json, ContentType.APPLICATION_JSON);
    }
}