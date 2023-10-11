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
        if (str == null) {
            throw new NullPointerException("String \"str\" cannot be null");
        }

        StringBuilder sb = new StringBuilder(str);

        for (int i = 1; i < sb.length(); i += 2) {
            char tmp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(i - 1));
            sb.setCharAt(i - 1, tmp);
        }
        return sb.toString();
    }
}
