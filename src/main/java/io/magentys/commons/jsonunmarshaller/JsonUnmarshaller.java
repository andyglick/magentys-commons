package io.magentys.commons.jsonunmarshaller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUnmarshaller {
    private final Gson gson = new GsonBuilder().create();

    public <T> T fromJson(String resourceName, Class<T> clazz) {
        Reader reader = null;
        try {
            reader = new InputStreamReader(JsonUnmarshaller.class.getResourceAsStream(resourceName));
            return gson.fromJson(reader, clazz);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }
}
