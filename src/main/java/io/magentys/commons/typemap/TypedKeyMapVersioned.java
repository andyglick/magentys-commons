package io.magentys.commons.typemap;

/**
 * Extends the TypedKeyMap to allow versioning of data values per key.
 * 
 * Useful for repeatedly storing multiple versions of a value to the same key, and being able to request latest or any version
 * 
 * Can act exactly like a normal TypedKeyMap, but use 'getVersions' method to access the underlying versions handler
 * 
 * See TypedKeyMapVersionedTest for example usage.
 */
public class TypedKeyMapVersioned extends TypedKeyMap {

    @Override
    @SuppressWarnings("unchecked")
    public <V> void put(final TypedKey<V> key, final V value) {
        if (entries.containsKey(key))
            ((Versions<V>) entries.get(key)).addVersion(value);
        else {
            Versions<V> versions = new Versions<>(value);
            entries.put(key, versions);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V> V get(final TypedKey<V> key) {
        return (V) ((Versions<V>) entries.get(key)).getLatest();
    }

    @SuppressWarnings("unchecked")
    public <V> Versions<V> getVersions(final TypedKey<V> key) {
        return (Versions<V>) entries.get(key);
    }

}