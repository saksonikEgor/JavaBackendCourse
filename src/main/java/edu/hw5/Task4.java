package edu.hw5;

public class Task4 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "String \"s\" cant be null";
    private static final String PATTERN = "(.*)(~|!|@|#|\\$|%|\\^|&|\\*|\\|)(.*)";

    private Task4() {
    }

    public static boolean passwordIsValid(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(PATTERN);
    }
}
