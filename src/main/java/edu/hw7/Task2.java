package edu.hw7;

import java.util.stream.LongStream;

public class Task2 {
    private Task2() {
    }

    public static long factorial(int n) {
        return LongStream.range(1, n + 1)
            .boxed()
            .toList()
            .parallelStream()
            .reduce((n1, n2) -> n1 * n2)
            .orElseThrow(() -> new IllegalArgumentException("n mast be greater than 0"));
    }
}
