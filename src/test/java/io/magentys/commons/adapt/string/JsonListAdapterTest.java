package io.magentys.commons.adapt.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JsonListAdapterTest {

    private static final String SAMPLE_JSON = "[{\"key\":\"foo\",\"value\":\"bar\"},{\"key\":\"baz\",\"value\":\"bat\"}]";
    private static final String EMPTY_JSON = "[]";
    private static final String EMPTY_STRING = "";

    public JsonListAdapter<SampleObject> adapter = JsonListAdapter.of(SampleObject.class);

    @Test
    public void testFromString() {

        final List<SampleObject> objects = adapter.fromString(SAMPLE_JSON);

        Assert.assertEquals(2, objects.size());
        SampleObject object = objects.get(0);
        Assert.assertEquals("foo", object.key);
        Assert.assertEquals("bar", object.value);
        object = objects.get(1);
        Assert.assertEquals("baz", object.key);
        Assert.assertEquals("bat", object.value);

    }

    @Test
    public void testFromEmptyJSonArrayString() {
        final List<SampleObject> objects = adapter.fromString(EMPTY_JSON);
        Assert.assertEquals(0, objects.size());
    }

    @Test
    public void testFromEmptyString() {
        final List<SampleObject> objects = adapter.fromString(EMPTY_STRING);
        Assert.assertEquals(0, objects.size());
    }

    @Test
    public void testFromNullString() {
        final List<SampleObject> objects = adapter.fromString(null);
        Assert.assertEquals(0, objects.size());
    }

    @Test
    public void testToString() {

        final SampleObject object = new SampleObject("foo", "bar");
        final SampleObject object2 = new SampleObject("baz", "bat");

        final List<SampleObject> objects = Arrays.asList(object, object2);
        Assert.assertEquals(SAMPLE_JSON, adapter.toString(objects));
    }

    @Test
    public void testToEmptyJsonArrayString() {
        Assert.assertEquals(EMPTY_JSON, adapter.toString(new LinkedList<SampleObject>()));
    }

    private static final class SampleObject {
        public SampleObject(final String key, final String value) {
            super();
            this.key = key;
            this.value = value;
        }

        private final String key;
        private final String value;
    }

}
