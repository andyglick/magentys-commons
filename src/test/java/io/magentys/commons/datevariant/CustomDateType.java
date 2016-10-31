package io.magentys.commons.datevariant;

import io.magentys.commons.datevariant.DateTypeInternal.IDateType;

public enum CustomDateType implements IDateType {
    TEXT_NA("n/a"),
    TEXT_INVALID("INVALID");

    private DateTypeInternal dateTypeInternal;

    CustomDateType(String match) {
        this.dateTypeInternal = new DateTypeInternal(match);
        //DateTypeInternal.register(this);
    }

    @Override
    public DateTypeInternal getDateTypeInternal() {
        return dateTypeInternal;
    }

    public static void register() {
        for (CustomDateType type : values())
            DateTypeInternal.register(type);
    }

    public static void deregister() {
        for (CustomDateType type : values())
            DateTypeInternal.deregister(type);
    }
}
