package edu.hw5;

public class Task7 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "String \"s\" cant be null";
    private static final String FIRST_PATTERN = "[0-1]{2}0[0-1]*";
    private static final String SECOND_PATTERN = "([0-1])[0-1]*\\1";
    private static final String THIRD_PATTERN = "[0-1]{1,3}";

    private Task7() {
    }

    public static boolean isStringValid(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(FIRST_PATTERN) && s.matches(SECOND_PATTERN) && s.matches(THIRD_PATTERN);
    }
}
