package edu.hw8;

import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    private static final int THREAD_COUNT = 3;

    private static Stream<Arguments> provideFibonacciCalculating() {
        return Stream.of(
            Arguments.of(
                List.of(1, 1), List.of(new FibonacciWorker(1), new FibonacciWorker(2)),
                List.of(1, 1, 55, 55, 34, 10946), List.of(
                    new FibonacciWorker(1), new FibonacciWorker(2), new FibonacciWorker(10),
                    new FibonacciWorker(10), new FibonacciWorker(9), new FibonacciWorker(21)
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideFibonacciCalculating")
    @DisplayName("Вычисление чисел фиббоначи")
    void fibonacciCalculating(List<Integer> expectedList, List<FibonacciWorker> workers) throws InterruptedException {
        ThreadPool pool = new FixedThreadPool(THREAD_COUNT);
        pool.start();

        workers.forEach(pool::execute);

        Thread.sleep(1000);

        IntStream.range(0, expectedList.size())
            .forEach(i -> assertEquals(expectedList.get(i), workers.get(i).getResult()));
    }

    private static class FibonacciWorker extends Thread {
        private final int n;
        private int result;

        public FibonacciWorker(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            result = fibonacciCalculating(n);
        }

        private int fibonacciCalculating(int n) {
            return n <= 2 ? 1 : fibonacciCalculating(n - 1) + fibonacciCalculating(n - 2);
        }

        public int getResult() {
            return result;
        }
    }
}
