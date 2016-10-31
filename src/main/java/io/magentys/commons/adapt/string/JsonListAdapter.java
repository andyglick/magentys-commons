package io.magentys.commons.adapt.string;

import com.google.gson.*;
import io.magentys.commons.adapt.string.StringAdapters.StringAdapter;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class JsonListAdapter<T> implements StringAdapter<List<T>> {

    private final Type collectionType;
    private final Gson json = new GsonBuilder().create();

    private JsonListAdapter(final Type collectionType) {
        this.collectionType = collectionType;
    }

    @Override
    public String toString(final List<T> t) {

        return json.toJson(t);
    }

    @Override
    public List<T> fromString(final String s) {
        if (s == null || s.isEmpty()) {
            return Collections.emptyList();
        }

        final JsonArray jsonArray = new JsonParser().parse(s).getAsJsonArray();

        final List<T> deserialised = new LinkedList<T>();

        for (final JsonElement jsonElement : jsonArray) {
            final T fromJson = json.fromJson(jsonElement, collectionType);
            deserialised.add(fromJson);
        }

        return deserialised;
    }

    public static <T> JsonListAdapter<T> of(final Class<T> type) {

        final Type collectionType = type;
        return new JsonListAdapter<T>(collectionType);
    }

}
