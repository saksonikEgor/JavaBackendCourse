package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static final String YEAR_NULL_POINTER_EXCEPTION_MESSAGE = "Year cant be null";
    public static final String DATE_NULL_POINTER_EXCEPTION_MESSAGE = "Date cant be null";

    private Task2() {
    }

    public static LocalDate[] getAllFridays13thInTheCurrentYear(Year year) {
        if (year == null) {
            throw new NullPointerException(YEAR_NULL_POINTER_EXCEPTION_MESSAGE);
        }

        final int FRIDAY_DATE = 13;
        List<LocalDate> fridays = new ArrayList<>();

        LocalDate cur13th = year.atDay(FRIDAY_DATE);
        LocalDate lastDayInYear = cur13th.with(TemporalAdjusters.lastDayOfYear());

        while (cur13th.isBefore(lastDayInYear)) {
            if (cur13th.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(cur13th);
            }

            cur13th = cur13th.plusMonths(1);
        }

        return fridays.toArray(new LocalDate[] {});
    }

    public static LocalDate getNextFriday13th(LocalDate date) {
        if (date == null) {
            throw new NullPointerException(DATE_NULL_POINTER_EXCEPTION_MESSAGE);
        }

        final int FRIDAY_DATE = 13;

        return date.with(temporal -> {
            Temporal curFriday = temporal.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

            while (curFriday.get(ChronoField.DAY_OF_MONTH) != FRIDAY_DATE) {
                curFriday = curFriday.plus(Period.ofWeeks(1));
            }
            return curFriday;
        });
    }
}
