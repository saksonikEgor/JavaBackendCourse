package edu.hw3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Test
    @DisplayName("Подсчет частоты слов в списке")
    void freqDict() {
        assertEquals(
            Map.of(
                "bb", 2,
                "a", 2
            ),
            Task3.freqDict(List.of("a", "bb", "a", "bb"))
        );

        assertEquals(
            Map.of(
                "that", 1,
                "and", 2,
                "this", 1
            ),
            Task3.freqDict(List.of("this", "and", "that", "and"))
        );

        assertEquals(
            Map.of(
                "код", 3,
                "bug", 1
            ),
            Task3.freqDict(List.of("код", "код", "код", "bug"))
        );

        assertEquals(
            Map.of(
                1, 2,
                2, 2
            ),
            Task3.freqDict(List.of(1, 1, 2, 2))
        );

        assertEquals(
            Map.of(
                1, 5,
                "ds", 1
            ),
            Task3.freqDict(List.of(1, 1, 1, 1, 1, "ds"))
        );

        assertEquals(
            Map.of(
                1, 2,
                5, 1,
                2.2, 2,
                -0.2, 1,
                "ds", 2
            ),
            Task3.freqDict(List.of(1, 1, 5, 2.2, 2.2, -0.2, "ds", "ds"))
        );

        assertThatThrownBy(() -> Task3.freqDict(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task3.NULL_POINTER_EXCEPTION_MESSAGE);
    }
}
