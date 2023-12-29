package edu.hw5.task3.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class SlashDateParser extends DateParser {
    private static final String PATTERN = "d/M/y";

    public SlashDateParser(DateParser next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> parse(String s) {
        try {
            return Optional.of(LocalDate.parse(s, DateTimeFormatter.ofPattern(PATTERN)));
        } catch (DateTimeParseException e) {
            return next == null ? Optional.empty() : next.parse(s);
        }
    }
}
