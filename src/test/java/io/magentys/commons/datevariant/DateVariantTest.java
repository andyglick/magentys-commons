package io.magentys.commons.datevariant;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DateVariantTest {

    @Before
    public void setup() {
        BaseDateFormat.register();
        CustomDateFormat.register();
    }

    @Test
    public void dateTypeDate() {
        DateVariant dateIn = DateVariant.from("10/05/2016");
        Assert.assertTrue(dateIn.isType(DateTypeInternal.DateType.VALID_DATE));
    }

    @Test
    public void invalidDate() {
        DateVariant dateIn = DateVariant.from("36/05/2016");
        Assert.assertFalse(dateIn.isType(DateTypeInternal.DateType.VALID_DATE));
        Assert.assertTrue(dateIn.isType(DateTypeInternal.DateType.STRING_VALUE));
        Assert.assertTrue(dateIn.getOriginalDateString().equals("36/05/2016"));
    }

    @Test
    public void getYear() {
        DateVariant dateIn = DateVariant.from("10/05/2016");
        Assert.assertTrue(dateIn.getYear() == 2016);
    }

    @Test
    public void getYearYY() {
        DateVariant dateIn = DateVariant.from("10/05/16", CustomDateFormat.ddMMyy);
        Assert.assertTrue(dateIn.getYear() == 2016);
    }

    @Test
    public void getDay() {
        DateVariant dateIn = DateVariant.from("10/05/2016");
        Assert.assertTrue(dateIn.getDay() == 10);
    }

    @Test
    public void getMonthName() {
        DateVariant dateIn = DateVariant.from("10/11/2016");
        Assert.assertTrue(dateIn.getMonthName().equals("November"));
    }

    @Test
    public void dateOffsetTwoDays() {
        DateVariant dateIn = DateVariant.from("10/05/2016");
        DateVariant dateOut = dateIn.offsetBy(DateVariant.DateUnit.DAY, DateVariant.DateOffset.AFTER, 2);
        Assert.assertTrue(dateOut.toString(BaseDateFormat.ddMMyyyy).equals("12/05/2016"));
    }

    @Test
    public void dateOffsetTwoMonths() {
        DateVariant dateIn = DateVariant.from("10/05/2016");
        DateVariant dateOut = dateIn.offsetBy(DateVariant.DateUnit.MONTH, DateVariant.DateOffset.AFTER, 2);
        Assert.assertTrue(dateOut.toString(BaseDateFormat.ddMMyyyy).equals("10/07/2016"));
    }

    @Test
    public void dateOffsetTwoYears() {
        DateVariant dateIn = DateVariant.from("10/05/2016");
        DateVariant dateOut = dateIn.offsetBy(DateVariant.DateUnit.YEAR, DateVariant.DateOffset.AFTER, 2);
        Assert.assertTrue(dateOut.toString(BaseDateFormat.ddMMyyyy).equals("10/05/2018"));
    }

    @Test
    public void dateOffsetTwoMonthsFormatTwoDigitYear() {
        DateVariant dateIn = DateVariant.from("10/05/2016");
        DateVariant dateOut = dateIn.offsetBy(DateVariant.DateUnit.MONTH, DateVariant.DateOffset.AFTER, 2);
        Assert.assertTrue(dateOut.toString(CustomDateFormat.ddMMyy).equals("10/07/16"));
    }

    @Test
    public void dateOffsetTwoMonthsFormatTwoDigitYearNoSlashes() {
        DateVariant dateIn = DateVariant.from("10/05/2016");
        DateVariant dateOut = dateIn.offsetBy(DateVariant.DateUnit.MONTH, DateVariant.DateOffset.AFTER, 2);
        Assert.assertTrue(dateOut.toString(CustomDateFormat.ddMMyyNoSlashes).equals("100716"));
    }

    @Test
    public void dateSameDay() {
        DateVariant date1 = DateVariant.from("10/05/2016");
        DateVariant date2 = DateVariant.from("11/05/2016").offsetBy(DateVariant.DateUnit.DAY, DateVariant.DateOffset.BEFORE, 1);
        Assert.assertTrue(date1.isSameDay(date2));
    }

    @Test
    public void dateNotSameDay() {
        DateVariant date1 = DateVariant.from("10/05/2016");
        DateVariant date2 = DateVariant.from("11/05/2016").offsetBy(DateVariant.DateUnit.DAY, DateVariant.DateOffset.BEFORE, 2);
        Assert.assertFalse(date1.isSameDay(date2));
    }

    @Test
    public void dateAfter() {
        DateVariant date1 = DateVariant.from("10/05/2016");
        DateVariant date2 = DateVariant.from("11/05/2016");
        Assert.assertTrue(date2.isAfter(date1));
    }

    @Test
    public void dateNotAfter() {
        DateVariant date1 = DateVariant.from("10/05/2016");
        DateVariant date2 = DateVariant.from("11/05/2016");
        Assert.assertFalse(date1.isAfter(date2));
    }

    @Test
    public void dateBefore() {
        DateVariant date1 = DateVariant.from("10/05/2016");
        DateVariant date2 = DateVariant.from("11/05/2016");
        Assert.assertTrue(date1.isBefore(date2));
    }

    @Test
    public void dateNotBefore() {
        DateVariant date1 = DateVariant.from("10/05/2016");
        DateVariant date2 = DateVariant.from("11/05/2016");
        Assert.assertFalse(date2.isBefore(date1));
    }

    @Test
    public void dateNotEqualRegisteredType() {
        CustomDateType.register();
        DateVariant date1 = DateVariant.from("10/05/2016");
        DateVariant date2 = DateVariant.from("INVALID");
        Assert.assertFalse(date1.compare(date2));
        CustomDateType.deregister();
    }

    @Test
    public void dateNotEqualNonRegisteredType() {
        DateVariant date1 = DateVariant.from("10/05/2016");
        DateVariant date2 = DateVariant.from("SOMESTRING");
        Assert.assertFalse(date1.compare(date2));
    }

    @Test
    public void nonRegisteredEqualNonRegistered() {
        DateVariant date1 = DateVariant.from("SOMESTRING1");
        DateVariant date2 = DateVariant.from("SOMESTRING1");
        Assert.assertTrue(date1.compare(date2));
    }

    @Test
    public void nonRegisteredNotEqualNonRegistered() {
        DateVariant date1 = DateVariant.from("SOMESTRING1");
        DateVariant date2 = DateVariant.from("SOMESTRING2");
        Assert.assertFalse(date1.compare(date2));
    }

    @Test
    public void blankDate() {
        DateVariant date1 = DateVariant.from("");
        Assert.assertTrue(date1.toString(BaseDateFormat.ddMMyyyy).equals(""));
    }

    @Test
    public void naDate() {
        CustomDateType.register();
        DateVariant date1 = DateVariant.from("n/a", BaseDateFormat.ddMMyyyy);
        Assert.assertTrue(date1.toString(BaseDateFormat.ddMMyyyy).equals("n/a"));
        CustomDateType.deregister();
    }

    @Test
    public void nonRegisteredInvalidStringDateType() {
        DateVariant dateIn = DateVariant.from("INVALID");
        Assert.assertTrue(dateIn.isType(DateTypeInternal.DateType.STRING_VALUE));
        Assert.assertFalse(dateIn.isType(CustomDateType.TEXT_INVALID));
        Assert.assertTrue(dateIn.getOriginalDateString().equals("INVALID"));
    }

    @Test
    public void registeredInvalidStringDateType() {
        CustomDateType.register();
        DateVariant dateIn = DateVariant.from("INVALID");
        Assert.assertFalse(dateIn.isType(DateTypeInternal.DateType.STRING_VALUE));
        Assert.assertTrue(dateIn.isType(CustomDateType.TEXT_INVALID));
        Assert.assertTrue(dateIn.getOriginalDateString().equals("INVALID"));
        CustomDateType.deregister();
    }

    @Test
    public void dateTypesEqual() {
        CustomDateType.register();
        DateVariant dateInvalid = DateVariant.from("INVALID");
        DateVariant dateInvalid2 = DateVariant.from("INVALID");
        Assert.assertTrue(dateInvalid.isType(CustomDateType.TEXT_INVALID));
        Assert.assertTrue(dateInvalid2.isType(CustomDateType.TEXT_INVALID));
        Assert.assertTrue(dateInvalid.compare(dateInvalid2));
        CustomDateType.deregister();
    }

    @Test
    public void dateTypesNotEqual() {
        CustomDateType.register();
        DateVariant dateInvalid = DateVariant.from("INVALID");
        DateVariant dateNA = DateVariant.from("n/a");
        Assert.assertTrue(dateInvalid.isType(CustomDateType.TEXT_INVALID));
        Assert.assertTrue(dateNA.isType(CustomDateType.TEXT_NA));
        Assert.assertFalse(dateInvalid.compare(dateNA));
        CustomDateType.deregister();
    }

    @Test
    public void dateTypesRegisteredNotEqualNonRegistered() {
        CustomDateType.register();
        DateVariant dateNA1 = DateVariant.from("n/a");
        DateVariant dateNA2 = DateVariant.from("na");
        Assert.assertTrue(dateNA1.isType(CustomDateType.TEXT_NA));
        Assert.assertFalse(dateNA2.isType(CustomDateType.TEXT_NA));
        Assert.assertFalse(dateNA1.compare(dateNA2));
        CustomDateType.deregister();
    }

    @Test
    public void dateTypesNotRegisteredEqualsNonRegistered() {
        DateVariant dateNA1 = DateVariant.from("na");
        DateVariant dateNA2 = DateVariant.from("na");
        Assert.assertFalse(dateNA1.isType(CustomDateType.TEXT_NA));
        Assert.assertFalse(dateNA2.isType(CustomDateType.TEXT_NA));
        Assert.assertTrue(dateNA1.isType(DateTypeInternal.DateType.STRING_VALUE));
        Assert.assertTrue(dateNA2.isType(DateTypeInternal.DateType.STRING_VALUE));
        Assert.assertTrue(dateNA1.compare(dateNA2));
    }

}
