package io.magentys.commons.memory;

import io.magentys.commons.typemap.TypedKey;
import io.magentys.commons.typemap.TypedKeyMapVersioned;
import io.magentys.commons.typemap.Versions;

public class MemoryVersioned {

    private final TypedKeyMapVersioned values = new TypedKeyMapVersioned();

    public <T> MemoryVersioned commit(TypedKey<T> key, T value) {
        values.put(key, value);
        return this;
    }

    public <T> T recall(TypedKey<T> key) {
        return values.get(key);
    }

    public <T> T recall(TypedKey<T> key, T defaultValue) {
        T value = recall(key);
        return (value == null) ? defaultValue : value;
    }

    public <T> Versions<T> recallAll(TypedKey<T> key) {
        return values.getVersions(key);
    }

    public <T> int size(TypedKey<T> key) {
        return values.getVersions(key).size();
    }

}