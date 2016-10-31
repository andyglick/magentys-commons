package io.magentys.commons.adapt.string;

import org.apache.commons.collections.keyvalue.DefaultMapEntry;

import java.util.Map.Entry;

public class MapEntryAdapter<X> implements StringAdapters.StringAdapter<Entry<String, X>> {

    private final StringAdapters.StringAdapter<X> valueAdapter;

    public MapEntryAdapter(final StringAdapters.StringAdapter<X> valueAdapter) {
        this.valueAdapter = valueAdapter;
    }

    @Override
    public String toString(final Entry<String, X> t) {
        return t.getKey() + ":" + valueAdapter.toString(t.getValue());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Entry<String, X> fromString(final String s) {
        if (s == null || s.isEmpty() || !s.contains(":")) {
            throw new IllegalArgumentException("expected <key>:<value>. Got: " + s);
        }
        final String[] keyValuePair = s.split(":");

        if (!(keyValuePair.length == 1 || keyValuePair.length == 2)) {
            throw new IllegalArgumentException("expected <key>:<value>. Got: " + s);
        }
        final String key = keyValuePair[0].trim();
        final String value = keyValuePair.length == 2 ? keyValuePair[1].trim() : "";

        if (key.isEmpty()) {
            throw new IllegalArgumentException("Key must be defined. expected <key>:<value>. Got: " + s);
        }
        return new DefaultMapEntry(key, valueAdapter.fromString(value));
    }
}
