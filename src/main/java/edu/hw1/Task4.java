package edu.hw1;

public class Task4 {
    private Task4() {
    }

    /**
     * Corrects lines in which each pair of characters has swapped places.
     *
     * @param str is the input string in which each pair of characters is swapped
     * @return string with correct character order
     * @throws NullPointerException if the input string is null
     */
    public static String fixString(String str) {
        char[] chars = str.toCharArray();

        for (int i = 1; i < chars.length; i += 2) {
            char tmp = chars[i];
            chars[i] = chars[i - 1];
            chars[i - 1] = tmp;
        }
        return new String(chars);
    }
}
