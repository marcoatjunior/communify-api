package com.communify.api.factory;

import java.util.Calendar;
import java.util.Date;

public class DateHelperTestFactory {

    private DateHelperTestFactory() {
    }
    
    public static Calendar addDays(Integer days) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date); 
        calendar.add(Calendar.DATE, days);
        return calendar;
    }
}
