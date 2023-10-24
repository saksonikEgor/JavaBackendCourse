package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task2Test {
    @Test
    @DisplayName("Кластеризация скобок")
    void clusterize() {
        assertArrayEquals(new String[] {"()", "()", "()"}, Task2.clusterize("()()()"));
        assertArrayEquals(new String[] {"((()))"}, Task2.clusterize("((()))"));
        assertArrayEquals(new String[] {"((())())", "(()(()()))"}, Task2.clusterize("((())())(()(()()))"));
        assertArrayEquals(new String[] {""}, Task2.clusterize(""));
        assertArrayEquals(
            new String[] {"((()))", "(())", "()", "()", "(()())"},
            Task2.clusterize("((()))(())()()(()())")
        );
        assertArrayEquals(
            new String[] {"((())())", "(()(()()))", "{}", "[{()}]"},
            Task2.clusterize("((())())(()(()())){}[{()}]")
        );
        assertArrayEquals(
            new String[] {"[(()){}]", "[]", "{}", "{}", "((())({}))", "(()(()()))", "{}", "[{()}]"},
            Task2.clusterize("[(()){}][]{}{}((())({}))(()(()())){}[{()}]")
        );

        assertThatThrownBy(() -> Task2.clusterize(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task2.NULL_POINTER_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task2.clusterize("(]"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task2.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task2.clusterize("(())[]{(wrong)}"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task2.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task2.clusterize("(())[]{(1)}"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task2.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
    }
}
