package edu.hw8.task3.solving;

import edu.hw8.task3.configuration.Params;
import edu.hw8.task3.utils.BruteForceUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.codec.digest.DigestUtils;

public class MultiThreadingPasswordBruteForcer implements PasswordBruteForcer {
    private final int threadCount;
    private final ExecutorService executorService;

    public MultiThreadingPasswordBruteForcer(int threadCount) {
        this.threadCount = threadCount;
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    @Override
    public Map<String, String> bruteForce(Map<String, String> hashToLogin) {
        Map<String, String> loinToPassword = new ConcurrentHashMap<>();

        for (int length = 1; length <= Params.PASSWORD_MAX_LENGTH; length++) {
            List<WorkRange> segments = distributeTheWork((long) Math.pow(Params.ALPHABET.length, length));

            for (var segment : segments) {
                final int finalLength = length;

                executorService.submit(() -> {
                    for (
                        long idx = segment.lowerBound();
                        idx < segment.upperBound() && !hashToLogin.isEmpty();
                        idx++
                    ) {
                        String password = BruteForceUtils.nextPassword(idx, finalLength);
                        String hash = DigestUtils.md5Hex(password).toLowerCase();
                        String login = hashToLogin.remove(hash);

                        if (login != null) {
                            loinToPassword.put(login, password);
                        }
                    }
                });
            }
        }

        shutdownAndAwaitTermination();
        return loinToPassword;
    }

    private List<WorkRange> distributeTheWork(long passwordCount) {
        List<WorkRange> ranges = new ArrayList<>();
        long delta = passwordCount / threadCount;
        long curIdx = 0;

        for (int i = 0; i < threadCount - 1; i++) {
            ranges.add(new WorkRange(curIdx, curIdx + delta));
            curIdx += delta;
        }

        ranges.add(new WorkRange(curIdx, passwordCount));

        return ranges;
    }

    private void shutdownAndAwaitTermination() {
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(Params.THREADS_WAITING_TIME, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(Params.THREADS_WAITING_TIME, TimeUnit.SECONDS)) {
                    throw new TimeoutException("The limit for waiting for the end of operations has been exceeded");
                }
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private record WorkRange(long lowerBound, long upperBound) {
    }
}
