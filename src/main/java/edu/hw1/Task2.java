package edu.hw1;

public class Task2 {
    private static final int NUMBER_OF_DIGITS_IN_LONG_MIN_VALUE = 19;
    private static final int DECIMAL_BASE = 10;

    private Task2() {
    }

    /**
     * Counts the count of digits in an input number.
     *
     * @param num is input number
     * @return the count of digits in an input number
     */
    public static int countDigits(long num) {
        if (num == 0) {
            return 1;
        }

        long n = num;
        if (num < 0) {
            if (num == Long.MIN_VALUE) {
                return NUMBER_OF_DIGITS_IN_LONG_MIN_VALUE;
            }

            n *= -1;
        }

        int result = 0;
        while (n > 0) {
            n /= DECIMAL_BASE;
            result++;
        }
        return result;
    }
}
