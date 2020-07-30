package com.communify.api.helper;

import static com.communify.api.factory.DateHelperTestFactory.addDays;
import static com.communify.api.helper.DateHelper.isGreaterOrEqualThanNow;
import static com.communify.api.helper.DateHelper.isRemainingAtLeastFourDays;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;

import com.communify.api.CommunifyApplicationTests;

import lombok.Getter;

@Getter
public class DateHelperTest extends CommunifyApplicationTests {

    @Test
    public void shouldReturnTrueWhenDateIsGreaterThanNow() {
        Date date = addDays(1).getTime();
        Boolean returned = isGreaterOrEqualThanNow(convertToLocalDate(date));
        assertTrue(returned);
    }
    
    @Test
    public void shouldReturnFalseWhenDateIsLessThanNow() {
        Date date = addDays(-1).getTime();
        Boolean returned = isGreaterOrEqualThanNow(convertToLocalDate(date));
        assertFalse(returned);
    }
    
    @Test
    public void shouldReturnTrueWhenDateIsLessOfFourDaysThanNow() {
        Date date = addDays(2).getTime();
        Boolean returned = isRemainingAtLeastFourDays(convertToLocalDate(date));
        assertTrue(returned);
    }
    
    @Test
    public void shouldReturnFalseWhenDateIsGreaterOfFourDaysThanNow() {
        Date date = addDays(5).getTime();
        Boolean returned = isRemainingAtLeastFourDays(convertToLocalDate(date));
        assertFalse(returned);
    }
    
    @Test
    public void shouldSetZeroWhenMonthIsMinusThanTen() {
        assertNotNull(DateHelper.setZero(6));
    }
    
    @Test
    public void shouldNotSetZeroWhenMonthIsGreaterOrEqualTen() {
        assertNotNull(DateHelper.setZero(12));
    }
    
    private static LocalDate convertToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }
}
