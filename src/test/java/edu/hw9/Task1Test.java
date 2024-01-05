package edu.hw9;

import edu.hw9.task1.Metric;
import edu.hw9.task1.StatsCollector;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task1Test {
    @Test
    @DisplayName("Добавление данных и получение статистики")
    void statsCollection() {
        StatsCollector statsCollector = new StatsCollector();
        final double value = 7;
        final int pushTimes = 20;

        assertNull(statsCollector.getStats());

        var pushingFutures = Stream.generate(() -> CompletableFuture.runAsync(() -> statsCollector.pushData(value)))
            .limit(pushTimes)
            .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(pushingFutures).join();

        assertEquals(Map.of(
            Metric.SUM, value * pushTimes,
            Metric.AVG, (value * pushTimes) / pushTimes,
            Metric.MAX, value,
            Metric.MIN, value
        ), statsCollector.getStats());
    }
}
