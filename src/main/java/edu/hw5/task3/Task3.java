package edu.hw5.task3;

import edu.hw5.task3.util.DateParser;
import edu.hw5.task3.util.MinusDateParser;
import edu.hw5.task3.util.PeriodDateParser;
import edu.hw5.task3.util.SlashDateParser;
import edu.hw5.task3.util.WordDateParser;
import java.time.LocalDate;
import java.util.Optional;

public class Task3 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "String \"s\" cant be null";

    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }
        return initDateParsers().parse(s);
    }

    private static DateParser initDateParsers() {
        DateParser periodDateParser = new PeriodDateParser(null);
        DateParser wordDateParser = new WordDateParser(periodDateParser);
        DateParser slashDateParser = new SlashDateParser(wordDateParser);
        return new MinusDateParser(slashDateParser);
    }
}
