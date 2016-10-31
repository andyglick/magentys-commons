package io.magentys.commons.typemap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Provides a mechanism for storing key/value pairs of different types, where there is a strong correlation between the
 * key and the value it represents.
 * 
 * Useful for creating complex data objects, and storing scenario context information in a flexible way.
 * 
 * See TypedKeyMapTest for example usage.
 * 
 *
 */
public class TypedKeyMap {

    protected final Map<TypedKey<?>, Object> entries = new HashMap<>();

    public <V> void put(final TypedKey<V> key, final V value) {
        entries.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <V> V get(final TypedKey<V> key) {
        return (V) entries.get(key);
    }

    public boolean containsKey(final TypedKey<?> key) {
        return entries.containsKey(key);
    }

    public Set<TypedKey<?>> keySet() {
        return entries.keySet();
    }

    public void remove(final TypedKey<?> key) {
        entries.remove(key);
    }

}