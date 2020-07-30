package com.communify.api.helper;

import static com.communify.api.helper.DateHelper.build;
import static com.communify.api.helper.DateHelper.cast;
import static java.time.ZoneId.systemDefault;

import java.time.LocalDate;

import com.google.api.services.classroom.model.Date;

public class ClassroomDateHelper {
    
    private ClassroomDateHelper() {
    }

    public static java.util.Date toDate(Date date) {
        return cast(build(date.getDay(), date.getMonth(), date.getYear()));
    }
    
    public static LocalDate toLocalDate(Date dueDate) {
        return toDate(dueDate).toInstant().atZone(systemDefault()).toLocalDate();
    }
}
