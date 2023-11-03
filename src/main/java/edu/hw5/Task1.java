package edu.hw5;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Task1 {
    private static final String INPUT_DATA_PATTERN = "yyyy-MM-dd, HH:mm";
    private static final String OUTPUT_AVR_DATA_FORMAT = "hh:mm";
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "String \"s\" cant be null";
    public static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "String \"s\" cant be empty";

    private Task1() {
    }

    public static String calculateAvg(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }
        if (s.isEmpty()) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
        }

        String[] ranges = s.split("\n");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(INPUT_DATA_PATTERN);
        long sum = 0;

        for (String range : ranges) {
            String[] dates = range.split(" - ");
            LocalDateTime start = LocalDateTime.parse(dates[0].trim(), dateTimeFormatter);
            LocalDateTime end = LocalDateTime.parse(dates[1].trim(), dateTimeFormatter);
            sum += Duration.between(start, end).toMinutes();
        }


        Duration duration  = Duration.ofMinutes(sum / ranges.length);
        return String.format("%02d:%02d", duration.toHours(), duration.toMinutesPart());
    }
}
