package com.communify.api.helper;

import static java.math.BigDecimal.ZERO;
import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.isNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateHelper {
    
    private DateHelper() {
    }
    
    private static final Long WEEK_DAYS = 4L;
    private static final String DEFAULT_DATE = "dd/MM/yyyy";
    
    public static String format(Date date) {
        if (isNull(date)) {
            return null;
        }
        return new SimpleDateFormat(DEFAULT_DATE).format(date);
    }

    public static boolean isGreaterOrEqualThanNow(LocalDate date) {
        return date.isAfter(now()) || date.isEqual(now());
    }
    
    public static boolean isRemainingAtLeastFourDays(LocalDate date) {
        return DAYS.between(now(), date) <= WEEK_DAYS;
    }
    
    protected static Date cast(String date) {
        try {
            return new SimpleDateFormat(DEFAULT_DATE).parse(date);
        }catch (Exception e) {
            e.getStackTrace();
        }
        return new Date();
    }
    
    protected static String build(Integer day, Integer month, Integer year) {
        return new StringBuilder()
            .append(day)
            .append("/")
            .append(setZero(month))
            .append("/")
            .append(year)
            .toString();
    }
    
    protected static String setZero(Integer month) {
        if (isNull(month)) {
            return "".toString();
        }
        return month < 10 ? ZERO + month.toString() : month.toString();
    }
}
