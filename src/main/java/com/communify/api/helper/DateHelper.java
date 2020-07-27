package com.communify.api.helper;

import static java.time.Instant.ofEpochSecond;
import static java.time.LocalDate.now;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;
import static java.time.temporal.ChronoUnit.DAYS;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.api.services.classroom.model.Date;

public class DateHelper {
    
    private static final Long WEEK_DAYS = 4L;
    private static final String DEFAULT_DATE = "dd/MM/yyyy";

    public static java.util.Date transform(Date date) {
        return cast(build(date.getDay(), date.getMonth(), date.getYear()));
    }
    
    public static java.util.Date transform(Long time) {
        LocalDateTime date = ofInstant(ofEpochSecond(time), systemDefault());
        return cast(build(date.getDayOfMonth(), date.getMonthValue(), date.getYear()));
    }
    
    public static String transform(java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }
    
    public static Boolean compare(Date dueDate) {
        LocalDate date = convertToLocalDate(transform(dueDate));
        return DAYS.between(now(), date) <= WEEK_DAYS && date.isAfter(now());
    }
    
    public static Boolean compare(Long time) {
        LocalDate date = convertToLocalDate(transform(time));
        return DAYS.between(now(), date) <= WEEK_DAYS && date.isAfter(now());
    }
    
    private static java.util.Date cast(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE);
            return simpleDateFormat.parse(date);
        }catch (Exception e) {
            e.getStackTrace();
        }
        return new java.util.Date();
    }
    
    private static String build(Integer day, Integer month, Integer year) {
        return new StringBuilder()
            .append(day)
            .append("/")
            .append(setZero(month))
            .append("/")
            .append(year)
            .toString();
    }
    
    private static String setZero(Integer month) {
        return month < 10 ? "0" + month.toString() : month.toString();
    }
    
    private static LocalDate convertToLocalDate(java.util.Date date) {
        return date.toInstant().atZone(systemDefault()).toLocalDate();
    }
}
