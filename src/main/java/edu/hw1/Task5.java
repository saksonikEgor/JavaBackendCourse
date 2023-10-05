package edu.hw1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Task5 {
    private Task5() {
    }

    /**
     * Checks whether a number or any of its descendants of length > 1 is a palindrome.
     *
     * @param num is input number
     * @return true if the input number or any of its descendants of length > 1 is a palindrome, false otherwise
     */
    public static boolean isPalindromeDescendant(long num) {
        if (num < 0) {
            num *= -1;
        }

        List<Integer> digits = String.valueOf(num).chars().map(c -> c - '0').boxed().collect(Collectors.toList());

        if (isPalindrome(digits)) {
            return true;
        }
        return isPalindromeDescendant(calculateDescendant(digits));
    }

    /**
     * Tests whether the size of a list of digits is greater than 1 and whether it
     * or any of its descendants is a palindrome of length > 1.
     *
     * @param digits is input list of digits
     * @return true if the size of the input list of digits is greater than 1 and it
     *     or any of its descendants of length > 1 is a palindrome, false otherwise
     * @throws NullPointerException if the input list of digits is null
     */
    private static boolean isPalindromeDescendant(List<Integer> digits) {
        if (digits.size() < 2) {
            return false;
        }

        if (isPalindrome(digits)) {
            return true;
        }
        return isPalindromeDescendant(calculateDescendant(digits));
    }

    /**
     * Calculates the number descendant.
     *
     * @param parent a list of digits for which the descendant needs to be calculated
     * @return a list of digits that is a descendant of the input parent
     * @throws NullPointerException if the input list of digits is null
     */
    private static List<Integer> calculateDescendant(List<Integer> parent) {
        List<Integer> descendant = new ArrayList<>();

        for (int i = 1; i < parent.size(); i += 2) {
            int curSum = parent.get(i - 1) + parent.get(i);
            if (curSum < 9) {
                descendant.add(curSum);
            } else {
                descendant.add(curSum / 10);
                descendant.add(curSum % 10);
            }
        }

        if (parent.size() % 2 == 1) {
            descendant.add(parent.get(parent.size() - 1));
        }
        return descendant;
    }

    /**
     * Checks whether a list of digits is a palindrome.
     *
     * @param nums a list of digits that is a putative palindrome. List size must be greater than 1
     * @return true if the input list of digits a palindrome, false otherwise
     * @throws NullPointerException if the input list of digits is null
     */
    private static boolean isPalindrome(List<Integer> nums) {
        int l = 0;
        int r = nums.size() - 1;

        while (l < r) {
            if (!Objects.equals(nums.get(l), nums.get(r))) {
                return false;
            }

            l++;
            r--;
        }
        return true;
    }
}
