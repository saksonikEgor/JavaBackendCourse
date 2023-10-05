package edu.hw1;

public class Task2 {
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

        if (num < 0) {
            if (num == Long.MIN_VALUE) {
                return 19;
            }

            num *= -1;
        }

        int result = 0;
        while (num > 0) {
            num /= 10;
            result++;
        }
        return result;
    }
}
