package edu.hw8.task2;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> blockingQueue;

    public FixedThreadPool(int threadCount) {
        blockingQueue = new ArrayBlockingQueue<>(threadCount);
        threads = Stream.generate(
                () -> new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                blockingQueue.take().run();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                )
            ).limit(threadCount)
            .toArray(Thread[]::new);
    }

    @Override
    public void start() {
        Arrays.stream(threads).forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
