package io.magentys.commons.datevariant;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateFormatInternal {
    public interface IDateFormat {
        public DateFormatInternal getDateFormatInternal();
    }

    private static List<IDateFormat> iDateFormatList = new ArrayList<IDateFormat>();

    public static void register(IDateFormat iDateFormat) {
        if (!iDateFormatList.contains(iDateFormat))
            iDateFormatList.add(iDateFormat);
    }

    public static void deregister(IDateFormat iDateFormat) {
        if (iDateFormatList.contains(iDateFormat))
            iDateFormatList.remove(iDateFormat);
    }

    public static List<IDateFormat> registeredFormats() {
        List<IDateFormat> formats = new ArrayList<IDateFormat>();
        formats.addAll(iDateFormatList);
        return formats;
    }

    private String format;
    private DateTimeFormatter dtf;

    public DateFormatInternal(String format) {
        this.format = format;
        this.dtf = DateTimeFormat.forPattern(format);
    }

    public String getFormat() {
        return format;
    }

    public DateTimeFormatter getDtf() {
        return dtf;
    }

    public String dateToString(DateTime date) {
        return date.toString(dtf);
    }

    public DateTime stringToDate(String dateString) {
        try {
            return dtf.parseDateTime(dateString);
        } catch (Exception e) {
            return null;
        }
    }

}
