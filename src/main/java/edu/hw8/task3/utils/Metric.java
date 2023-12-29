package edu.hw8.task3.utils;

import edu.hw8.task3.solving.MultiThreadingPasswordBruteForcer;
import edu.hw8.task3.solving.PasswordBruteForcer;
import edu.hw8.task3.solving.SingleThreadingPasswordBruteForcer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;

public class Metric {
    private static final List<Integer> THREAD_COUNTS = List.of(1, 2, 3, 4, 5, 6);
    private static final Map<String, String> HASH_TO_LOGIN = Map.of(
        DigestUtils.md5Hex("h84").toLowerCase(), "Tom",
        DigestUtils.md5Hex("hcr1").toLowerCase(), "Jane",
        DigestUtils.md5Hex("1").toLowerCase(), "Will",
        DigestUtils.md5Hex("873").toLowerCase(), "Kate"
    );

    private Metric() {
    }

    public static String getMetric() {
        return THREAD_COUNTS.stream()
            .map(count -> count + " threads: " + getDuration(count) + "\n")
            .collect(Collectors.joining());
    }

    private static long getDuration(int threadCount) {
        Map<String, String> copyOfHashToLogin = new HashMap<>(HASH_TO_LOGIN);
        PasswordBruteForcer bruteForcer = threadCount == 1
            ? new SingleThreadingPasswordBruteForcer()
            : new MultiThreadingPasswordBruteForcer(threadCount);

        long start = System.currentTimeMillis();
        bruteForcer.bruteForce(copyOfHashToLogin);
        long end = System.currentTimeMillis();

        return end - start;
    }

//    public static void main(String[] args) {
//        System.out.println(getMetric());
//    }


    /*
    1 threads: 2381
    2 threads: 2050
    3 threads: 2178
    4 threads: 305
    5 threads: 801
    6 threads: 1483
     */
}
