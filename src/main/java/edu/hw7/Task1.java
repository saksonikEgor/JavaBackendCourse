package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Task1 {
    private final AtomicInteger count = new AtomicInteger(0);

    public int incrementAndGet(int times) {
        if (times < 0) {
            throw new IllegalArgumentException("times mast be greater than 0");
        }

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < times; i++) {
            threads.add(new Thread(count::getAndIncrement));
        }

        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return count.get();
    }
}
