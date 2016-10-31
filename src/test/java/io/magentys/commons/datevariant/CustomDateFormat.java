package io.magentys.commons.datevariant;

public enum CustomDateFormat implements DateFormatInternal.IDateFormat {
    ddMMyy("dd/MM/yy"),
    ddMMyyyyNoSlashes("ddMMyyyy"),
    ddMMyyNoSlashes("ddMMyy"),
    yyyyMMddDashes("yyyy-MM-dd"),
    dMMMMy("d MMMM y");

    private DateFormatInternal dateFormatInternal;

    CustomDateFormat(String match) {
        this.dateFormatInternal = new DateFormatInternal(match);
    }

    public DateFormatInternal getDateFormatInternal() {
        return dateFormatInternal;
    }

    public static void register() {
        for (DateFormatInternal.IDateFormat format : values())
            DateFormatInternal.register(format);
    }

    public static void deregister() {
        for (DateFormatInternal.IDateFormat format : values())
            DateFormatInternal.deregister(format);
    }
}
