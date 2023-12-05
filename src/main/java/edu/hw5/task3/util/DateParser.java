package edu.hw5.task3.util;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    protected final DateParser next;

    protected DateParser(DateParser next) {
        this.next = next;
    }

    public abstract Optional<LocalDate> parse(String s);
}
