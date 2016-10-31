package io.magentys.commons.adapt.string;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonAdapter<T> implements StringAdapters.StringAdapter<T> {

    private final Class<T> type;
    private final Gson json = new GsonBuilder().create();

    public JsonAdapter(Class<T> type) {
        this.type = type;
    }

    @Override
    public String toString(T t) {
        return json.toJson(t, type);
    }

    @Override
    public T fromString(String s) {
        T object = json.fromJson(s, type);
        return object;
    }

}
