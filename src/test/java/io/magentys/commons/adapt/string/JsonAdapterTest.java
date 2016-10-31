package io.magentys.commons.adapt.string;

import org.junit.Assert;
import org.junit.Test;

public class JsonAdapterTest {

    private static final String SAMPLE_JSON = "{\"key\":\"foo\",\"value\":\"bar\"}";

    @Test
    public void testFromString() {

        JsonAdapter<SampleObject> adapter = new JsonAdapter<SampleObject>(SampleObject.class);

        SampleObject object = adapter.fromString(SAMPLE_JSON);
        Assert.assertEquals("foo", object.key);
        Assert.assertEquals("bar", object.value);

    }

    @Test
    public void testToString() {
        JsonAdapter<SampleObject> adapter = new JsonAdapter<SampleObject>(SampleObject.class);

        SampleObject object = new SampleObject("foo", "bar");

        Assert.assertEquals(SAMPLE_JSON, adapter.toString(object));
    }

    private static final class SampleObject {
        public SampleObject(String key, String value) {
            super();
            this.key = key;
            this.value = value;
        }

        private String key;
        private String value;
    }

}
