package edu.hw7.task4.util;

import edu.hw7.task4.model.CounterResponse;
import edu.hw7.task4.model.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class PiCounter {
    private static final double RADIUS = 0.5;
    private static final Point CIRCLE_CENTER = new Point(RADIUS, RADIUS);
    private static final int RATIO_OF_THE_AREA_OF_A_SQUARE_TO_THE_CIRCLE = 4;

    private PiCounter() {
    }

    private static boolean isInCircle(Point point) {
        return (Math.pow(CIRCLE_CENTER.x() - point.x(), 2) + Math.pow(CIRCLE_CENTER.y() - point.y(), 2))
            <= Math.pow(RADIUS, 2);
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static double countPiMultiThreading(int threadCount, int iterCount) {
        if (threadCount <= 0) {
            throw new IllegalArgumentException("\"threadCount\" must be greater than 0");
        }
        if (iterCount <= 0) {
            throw new IllegalArgumentException("\"iterCount\" must be greater than 0");
        }

        List<MonteCarloWorker> workers = new ArrayList<>(threadCount);

        IntStream.range(1, threadCount)
            .forEach(i -> workers.add(new MonteCarloWorker(iterCount / threadCount)));
        workers.add(new MonteCarloWorker(iterCount % threadCount));

        workers.forEach(Thread::start);
        workers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread is interrupted");
            }
        });

        return countPiByWorkers(workers);
    }

    private static double countPiByWorkers(List<MonteCarloWorker> workers) {
        int inCircleCount = 0;
        int totalCount = 0;

        for (var w : workers) {
            inCircleCount += w.getInCircleCount();
            totalCount += w.getTotalCount();
        }

        return RATIO_OF_THE_AREA_OF_A_SQUARE_TO_THE_CIRCLE * ((double) inCircleCount / totalCount);
    }

    public static double countPiSingleThreading(int iterCount) {
        if (iterCount <= 0) {
            throw new IllegalArgumentException("\"iterCount\" must be greater than 0");
        }

        Random random = new Random();
        int inCircleCount = 0;

        for (int i = 0; i < iterCount; i++) {
            if (isInCircle(new Point(random.nextDouble(), random.nextDouble()))) {
                inCircleCount++;
            }
        }

        return RATIO_OF_THE_AREA_OF_A_SQUARE_TO_THE_CIRCLE * ((double) inCircleCount / iterCount);
    }

    public static CounterResponse countPiMultiThreadingMetric(int threadCount, int iterCount) {
        if (threadCount <= 0) {
            throw new IllegalArgumentException("\"threadCount\" must be greater than 0");
        }
        if (iterCount <= 0) {
            throw new IllegalArgumentException("\"iterCount\" must be greater than 0");
        }

        List<MonteCarloWorker> workers = new ArrayList<>(threadCount);

        IntStream.range(1, threadCount)
            .forEach(i -> workers.add(new MonteCarloWorker(iterCount / threadCount)));
        workers.add(new MonteCarloWorker(iterCount % threadCount));

        long start = System.nanoTime();

        workers.forEach(Thread::start);
        workers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread is interrupted");
            }
        });

        double pi = countPiByWorkers(workers);

        long stop = System.nanoTime();

        return new CounterResponse(pi, stop - start, Math.abs(Math.PI - pi), threadCount, iterCount);
    }

    //-----------------METRICS

    public static CounterResponse countPiSingleThreadingMetric(int iterCount) {
        if (iterCount <= 0) {
            throw new IllegalArgumentException("\"iterCount\" must be greater than 0");
        }

        long start = System.nanoTime();

        Random random = new Random();
        int inCircleCount = 0;

        for (int i = 0; i < iterCount; i++) {
            if (isInCircle(new Point(random.nextDouble(), random.nextDouble()))) {
                inCircleCount++;
            }
        }

        double pi = RATIO_OF_THE_AREA_OF_A_SQUARE_TO_THE_CIRCLE * ((double) inCircleCount / iterCount);

        long stop = System.nanoTime();

        return new CounterResponse(pi, stop - start, Math.abs(Math.PI - pi), 1, iterCount);
    }

    static class MonteCarloWorker extends Thread {
        private final int iterCount;
        private int inCircleCount = 0;
        private int totalCount = 0;

        MonteCarloWorker(int iterCount) {
            this.iterCount = iterCount;
        }

        @Override
        public void run() {
            Random random = ThreadLocalRandom.current();
            final double diameter = RADIUS * 2;

            for (int i = 0; i < iterCount; i++) {
                if (isInCircle(new Point(random.nextDouble(diameter), random.nextDouble(diameter)))) {
                    inCircleCount++;
                }
            }

            totalCount = iterCount;
        }

        public int getInCircleCount() {
            return inCircleCount;
        }

        public int getTotalCount() {
            return totalCount;
        }
    }

}
