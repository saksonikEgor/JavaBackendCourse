package edu.hw5;

public class Task7 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "String \"s\" cant be null";
    private static final String PATTERN = "0[0-1]0";

    private Task7() {
    }

    public static boolean isStringValid(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(PATTERN);
    }
}
