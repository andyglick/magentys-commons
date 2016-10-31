package io.magentys.commons.typemap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypedKeyMapTest {

    private final TypedKeyMap map = new TypedKeyMap();

    /**
     * Example keys.
     *
     */
    private static class Keys {
        private static final StringKey FOO = new StringKey("foo");
        private static final ListKey<Integer> BAR = new ListKey<Integer>("bar");
        private static final MapKey<Integer, Object> BAZ = new MapKey<Integer, Object>("baz");
    }

    @Before
    public void setup() {

    }

    @Test
    public void shouldForceStronglyTypedValuesOnPut() {

        // A string
        map.put(Keys.FOO, "one");
        // list of ints
        map.put(Keys.BAR, Arrays.asList(1, 2, 3));

        // map

        Assert.assertTrue(map.containsKey(Keys.FOO));

    }

    @Test
    public void shouldAllowNullValues() {
        map.put(Keys.FOO, null);

        Assert.assertTrue(map.containsKey(Keys.FOO));
    }

    @Test
    public void shouldOnlyContainAKeyOnceAdded() {
        Assert.assertFalse(map.containsKey(Keys.FOO));

        map.put(Keys.FOO, "one");

        Assert.assertTrue(map.containsKey(Keys.FOO));
    }

    @Test
    public void shouldAllowRetrievalOfValues() {
        final List<Integer> list = Arrays.asList(1, 2, 3);
        map.put(Keys.BAR, list);

        // Strongly typed
        final List<Integer> integerList = map.get(Keys.BAR);

        Assert.assertSame(list, integerList);

        final Map<Integer, Object> myMapValue = new HashMap<Integer, Object>();
        myMapValue.put(1, "one");
        map.put(Keys.BAZ, myMapValue);
        Assert.assertSame(myMapValue, map.get(Keys.BAZ));
    }

}
