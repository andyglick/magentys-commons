package io.magentys.commons.typemap;

import java.util.ArrayList;
import java.util.List;

public class Versions<V> {
    private List<Object> versions;

    public Versions(Object object) {
        this.versions = new ArrayList<Object>();
        versions.add(object);
    }

    public Versions<V> addVersion(Object object) {
        versions.add(object);
        return this;
    }

    @SuppressWarnings("unchecked")
    public V getFirst() {
        return (V) versions.get(0);
    }

    @SuppressWarnings("unchecked")
    public V getVersion(int i) {
        return (V) versions.get(i);
    }

    @SuppressWarnings("unchecked")
    public V getPrevious() {
        return (V) versions.get(versions.size() - 2);
    }

    @SuppressWarnings("unchecked")
    public V getLatest() {
        return (V) versions.get(versions.size() - 1);
    }

    public int size() {
        return versions.size();
    }
}
