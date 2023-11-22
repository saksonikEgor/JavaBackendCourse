package edu.hw7;

import edu.hw7.task4.util.PiCounter;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    private static Stream<Arguments> provideFaultDESCMultithreading() {
        return Stream.of(
            Arguments.of(2),
            Arguments.of(3),
            Arguments.of(4),
            Arguments.of(5),
            Arguments.of(6)
        );
    }

    private static Stream<Arguments> provideDurationDESCMultithreading() {
        return Stream.of(
            Arguments.of(2),
            Arguments.of(3),
            Arguments.of(4),
            Arguments.of(5),
            Arguments.of(6)
        );
    }

    @Test
    @DisplayName("Проверка на убывание погрешности при увеличении количества испытаний для одного потока")
    void faultDESCSingleThread() {
        assertTrue(PiCounter.countPiSingleThreadingMetric(10).fault()
            > PiCounter.countPiSingleThreadingMetric(1000000).fault());
    }

    @ParameterizedTest
    @MethodSource("provideFaultDESCMultithreading")
    @DisplayName("Проверка на убывание погрешности при увеличении количества испытаний для нескольких потоков")
    void faultDESCMultithreading(int threadCount) {
        assertTrue(PiCounter.countPiMultiThreadingMetric(threadCount, 10).fault()
            > PiCounter.countPiMultiThreadingMetric(threadCount, 1000000).fault());
    }

    @Test
    @DisplayName("Проверка на увеличение врмени выполнения при повышении количества испытаний для одного потока")
    void durationDESCSingleThread() {
        assertTrue(PiCounter.countPiSingleThreadingMetric(1000000).duration()
            > PiCounter.countPiSingleThreadingMetric(10).duration());
    }

    @ParameterizedTest
    @MethodSource("provideDurationDESCMultithreading")
    @DisplayName("Проверка на увеличение врмени выполнения при повышении количества испытаний для нескольких потоков")
    void durationDESCMultithreading(int threadCount) {
        assertTrue(PiCounter.countPiMultiThreadingMetric(threadCount, 1000000).duration()
            > PiCounter.countPiMultiThreadingMetric(threadCount, 10).duration());
    }
}
