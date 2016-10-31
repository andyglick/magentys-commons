package io.magentys.commons.adapt.string;

import io.magentys.commons.adapt.string.StringAdapters.StringAdapter;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapAdapterTest {

    @Test
    public void test() {

        final Map<String, MyObject> sample = new HashMap<String, MyObject>();

        sample.put("key1", new MyObject("one", "yan"));
        sample.put("key2", new MyObject("two", "tan"));
        sample.put("key3", new MyObject("three", "tethera"));

        final MapAdapter<MyObject> adapter = new MapAdapter<MyObject>(StringAdapters.listOf(StringAdapters.csv(), StringAdapters.string()),
                myObjectAdapter());

        final String adapted = adapter.toString(sample);

        final Map<String, MyObject> result = adapter.fromString(adapted);

        Assert.assertEquals(sample, result);

    }

    private StringAdapter<MyObject> myObjectAdapter() {
        return new StringAdapter<MyObject>() {

            @Override
            public String toString(final MyObject t) {
                return t.foo + "@" + t.bar;
            }

            @Override
            public MyObject fromString(final String s) {
                final String[] parts = s.split("@");
                return new MyObject(parts[0], parts[1]);
            }
        };
    }

    private static class MyObject {
        String foo;
        String bar;

        public MyObject(final String foo, final String bar) {
            super();
            this.foo = foo;
            this.bar = bar;
        }

        @Override
        public String toString() {
            return foo + "@" + bar;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((bar == null) ? 0 : bar.hashCode());
            result = prime * result + ((foo == null) ? 0 : foo.hashCode());
            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            final MyObject other = (MyObject) obj;
            if (bar == null) {
                if (other.bar != null)
                    return false;
            } else if (!bar.equals(other.bar))
                return false;
            if (foo == null) {
                if (other.foo != null)
                    return false;
            } else if (!foo.equals(other.foo))
                return false;
            return true;
        }
    }

}
