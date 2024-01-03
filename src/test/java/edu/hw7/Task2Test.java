package edu.hw7;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    private static Stream<Arguments> provideMultithreadingFactorial() {
        return Stream.of(
            Arguments.of(1, 1),
            Arguments.of(2, 2),
            Arguments.of(6, 3),
            Arguments.of(24, 4),
            Arguments.of(6227020800L, 13)
        );
    }

    @ParameterizedTest
    @MethodSource("provideMultithreadingFactorial")
    @DisplayName("Многопоточное вычисление факториала")
    void multithreadingFactorial(long expected, int input) {
        assertEquals(expected, Task2.factorial(input));
    }

    @Test
    @DisplayName("Передача невалидного значения")
    void givingWrongInput() {
        assertThatThrownBy(() -> Task2.factorial(0))
            .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Task2.factorial(-4765))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
