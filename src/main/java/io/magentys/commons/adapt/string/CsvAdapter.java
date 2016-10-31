package io.magentys.commons.adapt.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import io.magentys.commons.adapt.string.StringAdapters.StringAdapter;

class CsvAdapter implements StringAdapter<List<String>> {

    private static final String CSV_REGEX = " *, *";

    @Override
    public String toString(final List<String> t) {
        // JDK8
        // return t.stream().reduce((a, b) -> a + "," + b).orElse("");

        if (t == null || t.isEmpty()) {
            return "";
        }

        final StringBuilder sb = new StringBuilder();
        for (final String string : t) {
            sb.append(string.trim()).append(',');
        }
        // remove trailing ','
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    @Override
    public List<String> fromString(final String s) {
        if (s == null || s.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.asList(s.trim().split(CSV_REGEX));
    }

}
