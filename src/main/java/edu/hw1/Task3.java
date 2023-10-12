package edu.hw1;

public class Task3 {
    private Task3() {
    }

    /**
     * Checks the possibility of nesting the first array in the second.
     *
     * @param arr1 the first array of integers
     * @param arr2 the second array of integers
     * @return true if the arr1 array can be nested in the arr2, false otherwise
     * @throws NullPointerException if the input arrays are null
     */
    public static boolean isNestable(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            throw new NullPointerException("Arrays cannot be null");
        }

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : arr1) {
            max1 = Math.max(num, max1);
            min1 = Math.min(num, min1);
        }

        for (int num : arr2) {
            max2 = Math.max(num, max2);
            min2 = Math.min(num, min2);
        }
        return min1 > min2 && max1 < max2;
    }
}
