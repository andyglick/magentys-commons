package io.magentys.commons.adapt.string;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map.Entry;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MapEntryAdapterTest {
    private final MapEntryAdapter<String> cut = new MapEntryAdapter<String>(StringAdapters.string());

    @Parameter(0)
    public String sourceStr;

    @Parameter(1)
    public String expectedKey;

    @Parameter(2)
    public String expectedValue;

    @Parameter(3)
    public boolean expectedValid;

    @Parameters
    public static Collection<Object[]> params() {
        //@formatter:off
        
        return Arrays.asList(new Object[][] {
                { "key:value", "key", "value", true }, 
                { "    key:value", "key", "value", true }, 
                { "key     :value", "key", "value", true }, 
                { "key:    value", "key", "value", true }, 
                { "key:value    ", "key", "value", true }, 
                { "    key    :    value    ", "key", "value", true }, 
                { "", null,null, false }, 
                { "key", null,null, false }, 
                { ":value", null,null, false }, 
                { ":", null,null, false }, 
                { "key:value:somethingElse", null,null, false }, 
                { "key:", "key","", true }, 
        });
        //@formatter:on
    }

    @Test
    public void testValidParse() {

        Assume.assumeTrue(expectedValid);

        final Entry<String, String> actual = cut.fromString(sourceStr);
        assertEquals(expectedKey, actual.getKey());
        assertEquals(expectedValue, actual.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidParse() {
        Assume.assumeFalse(expectedValid);
        cut.fromString(sourceStr);
    }

}
