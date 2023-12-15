package edu.hw10.task2;

import edu.hw10.task2.annotations.Cache;
import edu.hw10.task2.utils.CacheProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    private static long getDuration(Runnable task) {
        long start = System.nanoTime();
        task.run();
        return System.nanoTime() - start;
    }

    @Test
    @DisplayName("Кеширование возвращаемого значения функции")
    void proxyCashing() {
        Calculator calculator = new Calculator.FibCalculator();
        Calculator calculatorProxy = CacheProxy.create(calculator, Calculator.class);

        assertTrue(getDuration(() -> calculatorProxy.calculate(100))
            > getDuration(() -> calculatorProxy.calculate(100)));
        assertTrue(getDuration(() -> calculatorProxy.calculate(Integer.MAX_VALUE))
            > getDuration(() -> calculatorProxy.calculate(Integer.MAX_VALUE)));
    }

    private interface Calculator {
        @Cache(persist = true)
        long calculate(int number);

        class FibCalculator implements Calculator {
            @Override
            public long calculate(int number) {
                long prev = 0;
                long next = 1;

                for (int i = 0; i < number; i++) {
                    long tmp = next;
                    next = prev + next;
                    prev = tmp;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                return prev;
            }
        }
    }
}
