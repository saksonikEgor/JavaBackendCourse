package edu.hw1;

public class Task1 {
    private static final int SECONDS_IN_MINUTES = 60;

    private Task1() {
    }

    /**
     * Counts the total length of the video in seconds.
     *
     * @param s string with video length in "mm:ss" format
     * @return the total video length in seconds or - 1 if the input string is invalid.
     *     The input string is invalid
     *     if the number of ":" characters is not 2 or
     *     if the number of seconds is not in the range [0, 60) or
     *     if the number of minutes is less than 0 or
     *     if the resulting video length in seconds does not fit into the range of long values
     * @throws NumberFormatException if the number of minutes or seconds in the input string is not long type
     * @throws NullPointerException  if the input string is null
     */
    public static long minutesToSeconds(String s) {
        String[] params = s.split(":");

        if (params.length != 2) {
            return -1;
        }

        long min = Long.parseLong(params[0]);
        long sec = Long.parseLong(params[1]);

        if (sec >= SECONDS_IN_MINUTES || sec < 0 || min < 0) {
            return -1;
        }

        long result = sec + min * SECONDS_IN_MINUTES;
        if (result < min) {
            return -1;
        }
        return result;
    }
}
