package edu.hw1;

import java.util.Arrays;
import java.util.Collections;

public class Task6 {
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
        if (num <= 1000 || num >= 9999) {
            throw new IllegalArgumentException("It is impossible to execute the Kaprekar " +
                "function because the \"num\" is not valid");
        }

        boolean correctInput = false;
        for (int i = 10; i <= 1000; i *= 10) {
            if (num % 10 != (num / i) % 10) {
                correctInput = true;
                break;
            }
        }

        if (!correctInput) {
            throw new IllegalArgumentException("It is impossible to execute the Kaprekar " +
                "function because the \"num\" is not valid");
        }

        int[] digits = String.valueOf(num).chars().map(c -> c - '0').toArray();
        int[] target = new int[] {6, 1, 7, 4};

        return countK(digits, target);
    }

    /**
     * Counts the number of steps that need to be taken to get target.
     *
     * @param digits an array of numbers for which it is necessary to count the number of steps before obtaining the target
     * @param target an array of digits which as a result must be equal to the input array
     * @return number of steps required to get number target
     * @throws NullPointerException if the input arrays of digits are null
     */
    private static int countK(int[] digits, int[] target) {
        if (Arrays.equals(digits, target)) {
            return 0;
        }
        return 1 + countK(kaprecarFunc(digits), target);
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
            larger *= 10;
            larger += el;
        }

        int smaller = 0;
        for (int el : incr) {
            smaller *= 10;
            smaller += el;
        }

        int diff = larger - smaller;
        int[] result = new int[digits.length];

        int idx = result.length - 1;
        while (diff > 0) {
            result[idx--] = diff % 10;
            diff /= 10;
        }
        return result;
    }
}
