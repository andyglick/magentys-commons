package io.magentys.commons.adapt.string;

import io.magentys.commons.adapt.string.StringAdapters.StringAdapter;

public class IdentityAdapter implements StringAdapter<String> {

    @Override
    public String toString(String t) {
        return t;
    }

    @Override
    public String fromString(String s) {
        return s;
    }

}
