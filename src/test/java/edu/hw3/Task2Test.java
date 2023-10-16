package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    @DisplayName("Кластеризация скобок")
    void atbashEncryption() {
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

        assertEquals(Task2.NULL_POINTER_EXCEPTION_MESSAGE, Assertions.assertThrows(NullPointerException.class,
            () -> Task2.clusterize(null), "NullPointerException was expected"
        ).getMessage());

        assertEquals(Task2.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE, Assertions.assertThrows(IllegalArgumentException.class,
            () -> Task2.clusterize("(]"), "IllegalArgumentException was expected"
        ).getMessage());

        assertEquals(Task2.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE, Assertions.assertThrows(IllegalArgumentException.class,
            () -> Task2.clusterize("(())[]{(wrong)}"), "IllegalArgumentException was expected"
        ).getMessage());

        assertEquals(Task2.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE, Assertions.assertThrows(IllegalArgumentException.class,
            () -> Task2.clusterize("(())[]{(1)}"), "IllegalArgumentException was expected"
        ).getMessage());
    }
}
