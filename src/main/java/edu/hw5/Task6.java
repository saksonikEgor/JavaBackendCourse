package edu.hw5;

public class Task6 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "Strings cant be null";

    private Task6() {
    }

    public static boolean isSubstring(String s, String t) {
        if (s == null || t == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        return t.matches(".*" + s + ".*");
    }
}
