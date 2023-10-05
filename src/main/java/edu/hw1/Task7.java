package edu.hw1;

public class Task7 {
    private Task7() {
    }

    /**
     * Cyclically shifts a number to the left.
     *
     * @param n     the number to be shifted to the left
     * @param shift number of positions by which the number needs to be shifted to the left
     * @return shifted to the left by a specified number of positions number
     */
    public static long rotateLeft(int n, int shift) {
        if (shift < 0) {
            return rotateRight(n, -shift);
        }

        char[] binary = Integer.toBinaryString(n).toCharArray();
        int length = binary.length;
        char[] result = new char[length];
        shift %= length;

        for (int i = 0; i < length; i++) {
            result[(length + i - shift) % length] = binary[i];
        }
        return Long.parseLong(String.join("", new String(result)), 2);
    }

    /**
     * Cyclically shifts a number to the right.
     *
     * @param n     the number to be shifted to the right
     * @param shift number of positions by which the number needs to be shifted to the right
     * @return shifted to the right by a specified number of positions number
     */
    public static long rotateRight(int n, int shift) {
        if (shift < 0) {
            return rotateLeft(n, -shift);
        }

        char[] binary = Integer.toBinaryString(n).toCharArray();
        int length = binary.length;
        char[] result = new char[length];

        for (int i = 0; i < length; i++) {
            result[(i + shift) % length] = binary[i];
        }
        return Long.parseLong(String.join("", new String(result)), 2);
    }
}
