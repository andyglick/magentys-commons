package io.magentys.commons.datevariant;

import java.util.ArrayList;
import java.util.List;

public class DateTypeInternal {
    public interface IDateType {
        public DateTypeInternal getDateTypeInternal();
    }

    public enum DateType implements IDateType {
        VALID_DATE(null),
        STRING_VALUE(null);

        private DateTypeInternal dateTypeInternal;

        DateType(String match) {
            this.dateTypeInternal = new DateTypeInternal(match);
            DateTypeInternal.register(this);
        }

        public DateTypeInternal getDateTypeInternal() {
            return dateTypeInternal;
        }
    }

    private static List<IDateType> iDateTypeList = new ArrayList<IDateType>();

    public static void register(IDateType iDateType) {
        if (!iDateTypeList.contains(iDateType))
            iDateTypeList.add(iDateType);
    }

    public static void deregister(IDateType iDateType) {
        if (iDateTypeList.contains(iDateType))
            iDateTypeList.remove(iDateType);
    }

    public static IDateType fromLabel(String label) {
        for (IDateType idt : iDateTypeList) {
            if (idt.getDateTypeInternal().testMatch(label))
                return idt;
        }
        return DateType.STRING_VALUE;
    }

    private String match;

    public DateTypeInternal(String match) {
        this.match = match;
    }

    public boolean testMatch(String label) {
        return (match != null && match.equals(label));
    }

}
