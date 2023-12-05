package edu.hw5.task3.util;

import java.time.LocalDate;
import java.util.Optional;

public class PeriodDateParser extends DateParser {
    private static final String LATER_PATTERN = "\\d+ (day|days) later";
    private static final String AGO_PATTERN = "\\d+ (day|days) ago";

    public PeriodDateParser(DateParser next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> parse(String s) {
        if (s.matches(LATER_PATTERN)) {
            return Optional.of(LocalDate.now().plusDays(Integer.parseInt(s.split(" ")[0])));
        } else if (s.matches(AGO_PATTERN)) {
            return Optional.of(LocalDate.now().minusDays(Integer.parseInt(s.split(" ")[0])));
        } else {
            return next == null ? Optional.empty() : next.parse(s);
        }
    }
}
