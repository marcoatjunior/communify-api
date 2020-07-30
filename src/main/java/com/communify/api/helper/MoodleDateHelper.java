package com.communify.api.helper;

import static com.communify.api.helper.DateHelper.build;
import static com.communify.api.helper.DateHelper.cast;
import static java.time.Instant.ofEpochSecond;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MoodleDateHelper {
    
    private MoodleDateHelper() {
    }

    public static java.util.Date toDate(Long time) {
        LocalDateTime date = ofInstant(ofEpochSecond(time), systemDefault());
        return cast(build(date.getDayOfMonth(), date.getMonthValue(), date.getYear()));
    }
    
    public static LocalDate toLocalDate(Long time) {
        return toDate(time).toInstant().atZone(systemDefault()).toLocalDate();
    }
}
