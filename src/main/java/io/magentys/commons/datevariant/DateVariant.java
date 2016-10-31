package io.magentys.commons.datevariant;

import io.magentys.commons.datevariant.DateFormatInternal.IDateFormat;
import io.magentys.commons.datevariant.DateTypeInternal.DateType;
import io.magentys.commons.datevariant.DateTypeInternal.IDateType;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;

import java.util.ArrayList;
import java.util.List;

public class DateVariant {

    public enum DateUnit {
        DAY(DurationFieldType.days()),
        MONTH(DurationFieldType.months()),
        YEAR(DurationFieldType.years());

        private DurationFieldType interval;

        DateUnit(DurationFieldType interval) {
            this.interval = interval;
        }

        public DurationFieldType getInterval() {
            return interval;
        }
    }

    public enum DateOffset {
        BEFORE(-1),
        ON(0),
        AFTER(1);

        private int offset;

        DateOffset(int offset) {
            this.offset = offset;
        }

        public int getOffset() {
            return offset;
        }

        public int getOffset(int multiplier) {
            return offset * multiplier;
        }

        public static DateOffset getByLabel(String label) {
            for (DateOffset d : values()) {
                if (d.name().toString().equalsIgnoreCase(label))
                    return d;
            }
            return null;
        }
    }

    public static DateVariant today() {
        return from(new DateTime());
    }

    public static DateVariant todayOffsetBy(DateUnit dateUnit, DateOffset dateOffset, int multiplier) {
        return offsetBy(DateTime.now(), dateUnit, dateOffset, multiplier);
    }

    private static DateVariant offsetBy(DateTime dateTime, DateUnit dateUnit, DateOffset dateOffset, int multiplier) {
        return from(dateTime.withFieldAdded(dateUnit.getInterval(), dateOffset.getOffset(multiplier)));
    }

    public static DateVariant from(String dateIn, IDateFormat format) {
        return new DateVariant(dateIn, format);
    }

    public static DateVariant from(String dateString) {
        return new DateVariant(dateString, null);
    }

    public static DateVariant from(DateTime date) {
        return new DateVariant(date);
    }

    //public static DateVariant fromInvalidString(String dateIn) {
    //    return new DateVariant(dateIn);
    //}

    private DateTime date;
    private String originalDateString;
    private IDateType type;

    //private DateVariant(String dateString) {
    //    this(dateString, null);
    //}

    private DateVariant(String dateString, IDateFormat format) {
        originalDateString = dateString;
        try {
            date = parseDate(dateString, format);
        } catch (Exception e) {
            date = null;
        }
        type = (date != null) ? DateType.VALID_DATE : DateTypeInternal.fromLabel(dateString);
    }

    public DateVariant(DateTime date) {
        this.originalDateString = null;
        this.date = date;
        this.type = DateType.VALID_DATE;
    }

    private DateTime parseDate(String dateString, IDateFormat requiredFormat) {
        List<IDateFormat> formats = new ArrayList<IDateFormat>();
        if (requiredFormat == null) {
            formats = DateFormatInternal.registeredFormats();
            return parseDateWithFormats(dateString, formats);
        }
        formats.add(requiredFormat);
        return parseDateWithFormats(dateString, formats);
    }

    private DateTime parseDateWithFormats(String dateString, List<IDateFormat> formats) {
        DateTime date = null;

        for (IDateFormat df : formats) {
            date = df.getDateFormatInternal().stringToDate(dateString);
            if (date != null)
                break;
        }

        return date;
    }

    public DateTime getDate() {
        return date;
    }

    // tested
    public String getOriginalDateString() {
        return originalDateString;
    }

    public IDateType getType() {
        return type;
    }

    // tested
    public boolean compare(DateVariant other) {
        if (type.equals(DateType.VALID_DATE))
            return (other.type.equals(DateType.VALID_DATE) && isSameDay(other));
        else if (type.equals(DateType.STRING_VALUE))
            return other.type.equals(DateType.STRING_VALUE) && originalDateString.equals(other.getOriginalDateString());
        else
            return other.type.equals(type);
    }

    // tested
    public String toString(IDateFormat format) {
        return (type.equals(DateType.VALID_DATE)) ? format.getDateFormatInternal().dateToString(date) : originalDateString;
    }

    // tested
    public boolean isAfter(DateVariant other) {
        return date.isAfter(other.getDate());
    }

    // tested
    public boolean isBefore(DateVariant other) {
        return date.isBefore(other.getDate());
    }

    // tested
    public boolean isSameDay(DateVariant other) {
        return date.toLocalDate().equals(other.getDate().toLocalDate());
    }

    // tested
    public boolean isType(IDateType type) {
        return getType().equals(type);
    }

    // tested
    public DateVariant offsetBy(DateUnit dateUnit, DateOffset dateOffset, int days) {
        return offsetBy(getDate(), dateUnit, dateOffset, days);
    }

    // tested
    public int getYear() {
        return date.getYear();
    }

    // tested
    public int getDay() {
        return date.getDayOfMonth();
    }

    // tested
    public String getMonthName() {
        return date.monthOfYear().getAsText();
    }
}
