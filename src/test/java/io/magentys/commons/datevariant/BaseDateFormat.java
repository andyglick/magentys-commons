package io.magentys.commons.datevariant;

public enum BaseDateFormat implements DateFormatInternal.IDateFormat {
    ddMMyyyy("dd/MM/yyyy");

    private DateFormatInternal dateFormatInternal;

    BaseDateFormat(String match) {
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
