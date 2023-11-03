package edu.hw3;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task2Test {
    private static Stream<Arguments> provideClusterize() {
        return Stream.of(
            Arguments.of(new String[] {"()", "()", "()"}, "()()()"),
            Arguments.of(new String[] {"((()))"}, "((()))"),
            Arguments.of(new String[] {"((())())", "(()(()()))"}, "((())())(()(()()))"),
            Arguments.of(new String[] {""}, ""),
            Arguments.of(new String[] {"((()))", "(())", "()", "()", "(()())"}, "((()))(())()()(()())")
        );
    }

    @ParameterizedTest
    @MethodSource("provideClusterize")
    @DisplayName("Кластеризация скобок")
    void clusterize(String[] expected, String input) {
        assertArrayEquals(expected, Task2.clusterize(input));

        assertThatThrownBy(() -> Task2.clusterize(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task2.NULL_POINTER_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task2.clusterize("(("))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task2.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task2.clusterize("(())(wrong)"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task2.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task2.clusterize("(())(1)"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task2.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
    }
}
