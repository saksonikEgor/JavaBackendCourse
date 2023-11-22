package edu.hw7;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    private static Stream<Arguments> provideMultithreadingIncrementation() {
        return Stream.of(
            Arguments.of(1),
            Arguments.of(0),
            Arguments.of(15),
            Arguments.of(1520)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMultithreadingIncrementation")
    @DisplayName("Многопоточное увеличение счетчика")
    void multithreadingIncrementation(int input) {
        assertEquals(input, new Task1().incrementAndGet(input));
    }

    @Test
    @DisplayName("Передача отрицательного числа")
    void givingNegativeInput() {
        assertThatThrownBy(() -> new Task1().incrementAndGet(-1))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Task1().incrementAndGet(-1874503))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Task1().incrementAndGet(Integer.MIN_VALUE))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
