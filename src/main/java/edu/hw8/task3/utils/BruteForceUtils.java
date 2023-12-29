package edu.hw8.task3.utils;

import edu.hw8.task3.configuration.Params;

public class BruteForceUtils {
    private BruteForceUtils() {
    }

    public static String nextPassword(long index, int length) {
        StringBuffer password = new StringBuffer();
        long idx = index;

        for (int i = 0; i < length; i++) {
            password.append(Params.ALPHABET[(int) idx % Params.ALPHABET.length]);
            idx /= Params.ALPHABET.length;
        }

        return password.reverse().toString();
    }
}
