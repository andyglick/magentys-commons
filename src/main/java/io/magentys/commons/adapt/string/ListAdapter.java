package io.magentys.commons.adapt.string;

import io.magentys.commons.adapt.string.StringAdapters.StringAdapter;

import java.util.LinkedList;
import java.util.List;

public class ListAdapter<X> implements StringAdapter<List<X>> {

    StringAdapter<X> itemAdapter;
    StringAdapter<List<String>> listAdapter;

    public ListAdapter(final StringAdapter<List<String>> listAdapter, final StringAdapter<X> adapter1) {
        this.listAdapter = listAdapter;
        this.itemAdapter = adapter1;
    }

    @Override
    public String toString(final List<X> t) {

        // JDK8
        // List<String> stringList = t.stream().map(itemAdapter::toString)
        // .collect(Collectors.toList());
        final List<String> stringList = new LinkedList<String>();

        for (final X item : t) {
            stringList.add(itemAdapter.toString(item));
        }
        return listAdapter.toString(stringList);
    }

    @Override
    public List<X> fromString(final String s) {

        // return
        // listAdapter.fromString(s).stream().map(itemAdapter::fromString)
        // .collect(Collectors.toList());
        final List<String> stringList = listAdapter.fromString(s);

        final List<X> adapted = new LinkedList<X>();
        for (final String string : stringList) {
            adapted.add(itemAdapter.fromString(string));
        }
        return adapted;
    }

}
