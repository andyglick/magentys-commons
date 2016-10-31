package io.magentys.commons.typemap;

import java.util.Map;

/**
 * A {@link TypedKey} where the value the key maps to is a Map itself.
 * 
 *
 * @param <K> The type of the keys in the value map
 * @param <V> The type of the values in the value map
 */
public class MapKey<K, V> extends TypedKey<Map<K, V>> {

    public MapKey(final String name) {
        super(name);
    }

}
