package io.magentys.commons.adapt.string;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CsvAdapterFromStringTest {

    @Parameter(0)
    public static String source;
    @Parameter(1)
    public static List<String> destination;

    @Parameters
    public static Collection<Object[]> params() {
        //@formatter:off
        
        return Arrays.asList(new Object[][] {
                { "one,two,three", Arrays.asList("one","two","three") },
                { "   one   ,   two   ,   three   ", Arrays.asList("one","two","three") },
                { "one", Arrays.asList("one") },
                { null, Arrays.asList() },
                { "", Arrays.asList() },
        });
        //@formatter:on
    }

    @Test
    public void fromString() {
        final List<String> actual = new CsvAdapter().fromString(source);

        Assert.assertEquals(destination, actual);
    }

}
