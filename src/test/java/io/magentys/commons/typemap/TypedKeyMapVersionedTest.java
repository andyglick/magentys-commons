package io.magentys.commons.typemap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TypedKeyMapVersionedTest {

    private final TypedKeyMapVersioned map = new TypedKeyMapVersioned();

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

    @Test
    public void shouldAllowRetrievalOfVersionsOfValues() {
        final List<Integer> listV1 = Arrays.asList(1, 2, 3);
        final List<Integer> listV2 = Arrays.asList(4, 5, 6);
        final List<Integer> listV3 = Arrays.asList(7, 8, 9);
        final List<Integer> listV4 = Arrays.asList(10, 11, 12);
        map.put(Keys.BAR, listV1);
        map.put(Keys.BAR, listV2);
        map.put(Keys.BAR, listV3);
        map.put(Keys.BAR, listV4);

        // Strongly typed
        final List<Integer> integerList = map.get(Keys.BAR);
        Assert.assertSame(listV4, integerList);

        final Versions<List<Integer>> integerListAllVersions = map.getVersions(Keys.BAR);
        Assert.assertSame(listV1, integerListAllVersions.getFirst());
        Assert.assertSame(listV2, integerListAllVersions.getVersion(1));
        Assert.assertSame(listV3, integerListAllVersions.getPrevious());
        Assert.assertSame(listV4, integerListAllVersions.getLatest());
        Assert.assertSame(4, integerListAllVersions.size());
    }

}
