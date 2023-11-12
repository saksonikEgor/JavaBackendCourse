package edu.hw5;

public class Task5 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "String \"s\" cant be null";
    private static final String PATTERN = "[А-Я][1-9]\\d{2}[А-Я]{2}[1-9]\\d{2}";

    private Task5() {
    }

    public static boolean isValidRussianLicensePlates(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(PATTERN);
    }
}
