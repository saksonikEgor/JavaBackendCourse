package edu.hw1;

import java.util.Arrays;
import java.util.Collections;

public class Task6 {
    private static final int LOWER_BOUND = 1000;
    private static final int UPPER_BOUND = 9999;
    private static final int DECIMAL_BASE = 10;
    private static final int[] TARGET = new int[] {6, 1, 7, 4};
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "It is impossible to execute the Kaprekar "
        + "function because the \"num\" is not valid";

    private Task6() {
    }

    /**
     * Checks the input number for correctness and counts the number of steps that need to be taken to get 6174.
     * The input number is invalid if it is negative or not four-digit or all digits in the number are the same.
     *
     * @param num four-digit positive number in which not all digits are the same,
     *            for which you need to count the number of steps until get the number 6174
     * @return number of steps required to get number 6174
     * @throws IllegalArgumentException if the input number is invalid
     */
    public static int countK(int num) {
        if (num <= LOWER_BOUND || num >= UPPER_BOUND) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
        }

        boolean correctInput = false;
        for (int i = DECIMAL_BASE; i <= LOWER_BOUND; i *= DECIMAL_BASE) {
            if (num % DECIMAL_BASE != (num / i) % DECIMAL_BASE) {
                correctInput = true;
                break;
            }
        }

        if (!correctInput) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
        }

        int[] digits = String.valueOf(num).chars().map(c -> c - '0').toArray();

        return countK(digits);
    }

    /**
     * Counts the number of steps that need to be taken to get target.
     *
     * @param digits an array of numbers for which it is necessary to count the number of
     *               steps before obtaining the target
     * @return number of steps required to get number target
     * @throws NullPointerException if the input arrays of digits are null
     */
    private static int countK(int[] digits) {
        if (Arrays.equals(digits, TARGET)) {
            return 0;
        }
        return 1 + countK(kaprecarFunc(digits));
    }

    /**
     * Kaprekar function. Arranges the numbers first in ascending order, then in descending order,
     * and then subtracts the smaller from the larger.
     *
     * @param digits is array of digits for which it is necessary to perform the Kaprekar function
     * @return an array of digits, the result of subtracting a smaller number from a larger number,
     *     which are composed of elements of the input array
     * @throws NullPointerException if the input array of digits is null
     */
    private static int[] kaprecarFunc(int[] digits) {
        int[] incr = digits.clone();
        int[] desc = Arrays.stream(digits).boxed()
            .sorted(Collections.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();
        Arrays.sort(incr);

        int larger = 0;
        for (int el : desc) {
            larger *= DECIMAL_BASE;
            larger += el;
        }

        int smaller = 0;
        for (int el : incr) {
            smaller *= DECIMAL_BASE;
            smaller += el;
        }

        int diff = larger - smaller;
        int[] result = new int[digits.length];

        int idx = result.length - 1;
        while (diff > 0) {
            result[idx--] = diff % DECIMAL_BASE;
            diff /= DECIMAL_BASE;
        }
        return result;
    }
}
