package edu.hw3;

public class Task1 {
    public static final String NULL_POINTER_EXCEPTION_MESSAGE = "String \"str\" cant be null";
    private static final int LATIN_LOWERCASE_ASCII_LOWER_BOUND = 97;
    private static final int LATIN_LOWERCASE_ASCII_UPPER_BOUND = 122;
    private static final int LATIN_UPPERCASE_ASCII_LOWER_BOUND = 65;
    private static final int LATIN_UPPERCASE_ASCII_UPPER_BOUND = 90;

    private Task1() {
    }

    public static String atbash(String str) {
        if (str == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }

        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            int ascii = sb.charAt(i);

            if (isLowerCaseLatinCharacter(ascii)) {
                sb.setCharAt(i, (char) (LATIN_LOWERCASE_ASCII_UPPER_BOUND - (ascii - LATIN_LOWERCASE_ASCII_LOWER_BOUND)));
            } else if (isUppercaseLatinCharacter(ascii)) {
                sb.setCharAt(i, (char) (LATIN_UPPERCASE_ASCII_UPPER_BOUND - (ascii - LATIN_UPPERCASE_ASCII_LOWER_BOUND)));
            }
        }
        return sb.toString();
    }

    private static boolean isLowerCaseLatinCharacter(int ascii) {
        return LATIN_LOWERCASE_ASCII_LOWER_BOUND <= ascii && ascii <= LATIN_LOWERCASE_ASCII_UPPER_BOUND;
    }

    private static boolean isUppercaseLatinCharacter(int ascii) {
        return LATIN_UPPERCASE_ASCII_LOWER_BOUND <= ascii && ascii <= LATIN_UPPERCASE_ASCII_UPPER_BOUND;
    }
}
