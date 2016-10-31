package io.magentys.commons.adapt.string;

import io.magentys.commons.adapt.string.StringAdapters.StringAdapter;

public class IntegerAdapter implements StringAdapter<Integer> {

    @Override
    public String toString(Integer t) {
        return t.toString();
    }

    @Override
    public Integer fromString(String s) {
        return Integer.parseInt(s);
    }

}
