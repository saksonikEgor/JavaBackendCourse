package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StatsCollector {
    private final List<Double> data = new ArrayList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(Metric.values().length);
    private final Lock lock = new ReentrantLock(true);
    private int firstUncountedIndex = 0;
    private double sum;
    private double avg;
    private double max = Double.MIN_VALUE;
    private double min = Double.MAX_VALUE;

    public void pushData(double... values) {
        try {
            lock.lock();

            Arrays.stream(values)
                .boxed()
                .forEach(data::add);
        } finally {
            lock.unlock();
        }
    }

    public Map<Metric, Double> getStats() {
        try {
            int valueCount = data.size();

            if (valueCount == 0) {
                return null;
            }
            if (valueCount == firstUncountedIndex) {
                return makeResponse();
            }

            List<Double> subList;
            try {
                lock.lock();
                subList = data.subList(firstUncountedIndex, valueCount);
            } finally {
                lock.unlock();
            }

            var sumFuture = executorService.submit(() -> Metric.SUM.getFunction()
                .update(sum, valueCount, subList));
            var avgFuture = executorService.submit(() -> Metric.AVG.getFunction()
                .update(avg, valueCount, subList));
            var maxFuture = executorService.submit(() -> Metric.MAX.getFunction()
                .update(max, valueCount, subList));
            var minFuture = executorService.submit(() -> Metric.MIN.getFunction()
                .update(min, valueCount, subList));

            sum = sumFuture.get();
            avg = avgFuture.get();
            max = maxFuture.get();
            min = minFuture.get();

            firstUncountedIndex = valueCount;

            return makeResponse();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<Metric, Double> makeResponse() {
        return Map.ofEntries(
            Map.entry(Metric.SUM, sum),
            Map.entry(Metric.AVG, avg),
            Map.entry(Metric.MAX, max),
            Map.entry(Metric.MIN, min)
        );
    }

}
