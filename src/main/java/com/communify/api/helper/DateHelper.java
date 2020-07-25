package com.communify.api.helper;

import com.google.api.services.classroom.model.Date;

public class DateHelper {

    public static String transformDateToString(Date dueDate) {
        return new StringBuilder()
            .append(dueDate.getDay())
            .append("/")
            .append(setLeftZeroToMonth(dueDate.getMonth()))
            .append("/")
            .append(dueDate.getYear())
            .toString();
    }
    
    private static String setLeftZeroToMonth(Integer month) {
        return month < 10 ? "0" + month.toString() : month.toString();
    }
}
