package io.magentys.commons.adapt.string;

import io.magentys.commons.adapt.string.StringAdapters.StringAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MapAdapter<X> implements StringAdapter<Map<String, X>> {

    private final StringAdapter<List<String>> listAdapter;
    private final MapEntryAdapter<X> entryAdapter;

    public MapAdapter(final StringAdapter<List<String>> listAdapter, final StringAdapter<X> valueAdapter) {
        this.listAdapter = listAdapter;
        entryAdapter = new MapEntryAdapter<X>(valueAdapter);
    }

    @Override
    public String toString(final Map<String, X> t) {
        // Java 8
        // List<String> stringList = t.entrySet().stream()
        // .map(entryAdapter::toString).collect(Collectors.toList());
        final List<String> stringList = new LinkedList<String>();

        for (final Entry<String, X> entry : t.entrySet()) {
            stringList.add(entryAdapter.toString(entry));
        }

        return listAdapter.toString(stringList);
    }

    @Override
    public Map<String, X> fromString(final String s) {

        // Java 8
        // return
        // listAdapter.fromString(s).stream().map(entryAdapter::fromString)
        // .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));

        final Map<String, X> adapted = new HashMap<String, X>();

        final List<String> items = listAdapter.fromString(s);
        for (final String item : items) {
            final Entry<String, X> e = entryAdapter.fromString(item);
            adapted.put(e.getKey(), e.getValue());
        }

        return adapted;
    }
}
