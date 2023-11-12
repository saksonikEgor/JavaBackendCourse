package edu.hw5;

public class Task4 {
    private static final String PATTERN = "(.*)(~|!|@|#|\\$|%|\\^|&|\\*|\\|)(.*)";
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "String cant be null";

    private Task4() {
    }

    public static boolean passwordIsValid(String s) {
        if (s == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return s.matches(PATTERN);
    }
}
