package edu.hw5.task3.util;

import java.time.LocalDate;
import java.util.Optional;

public class WordDateParser extends DateParser {
    private static final String TODAY_STRING = "today";
    private static final String TOMMOROW_STRING = "tomorrow";
    private static final String YESTERDAY_STRING = "yesterday";

    public WordDateParser(DateParser next) {
        super(next);
    }

    @Override
    public Optional<LocalDate> parse(String s) {
        return switch (s) {
            case TODAY_STRING -> Optional.of(LocalDate.now());
            case TOMMOROW_STRING -> Optional.of(LocalDate.now().plusDays(1));
            case YESTERDAY_STRING -> Optional.of(LocalDate.now().minusDays(1));
            default -> next == null ? Optional.empty() : next.parse(s);
        };
    }
}
