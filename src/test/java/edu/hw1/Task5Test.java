package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Проверка на то, является ли число или любой из его потомков длиной > 1 палиндромом")
    void isPalindromeDescendant() {
        for (int i = 0; i < 10; i++) {
            assertThat(Task5.isPalindromeDescendant(i)).isEqualTo(true);
        }

        assertThat(Task5.isPalindromeDescendant(11211230)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(13001120)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(23336014)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(11)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(7022137)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(10)).isEqualTo(false);
        assertThat(Task5.isPalindromeDescendant(-1331)).isEqualTo(true);
        assertThat(Task5.isPalindromeDescendant(-677645)).isEqualTo(false);
    }
}
