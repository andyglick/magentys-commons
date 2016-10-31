package io.magentys.commons.adapt.string;

import org.junit.Ignore;
import org.junit.Test;

public class YamlMappingOfMappingTest {

    @Test
    public void test() {

        YamlMappingOfMapping mom = new YamlMappingOfMapping("Foo: {Bar: Alpha, Baz: Bravo}");
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore
    public void testInvalid() {
        // TODO - implement
        new YamlMappingOfMapping("Foo: Bar");
    }

}
