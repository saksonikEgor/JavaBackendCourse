package edu.hw5;

import edu.hw5.task3.Task3;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private static Stream<Arguments> provideParseDate() {
        return Stream.of(
            Arguments.of(Optional.of(LocalDate.of(2020, 10, 10)), "2020-10-10"),
            Arguments.of(Optional.of(LocalDate.of(2020, 12, 2)), "2020-12-2"),
            Arguments.of(Optional.of(LocalDate.of(1976, 3, 1)), "1/3/1976"),
            Arguments.of(Optional.of(LocalDate.of(20, 3, 1)), "1/3/20"),
            Arguments.of(Optional.of(LocalDate.now().plusDays(1)), "tomorrow"),
            Arguments.of(Optional.of(LocalDate.now()), "today"),
            Arguments.of(Optional.of(LocalDate.now().minusDays(1)), "yesterday"),
            Arguments.of(Optional.of(LocalDate.now().minusDays(1)), "1 day ago"),
            Arguments.of(Optional.of(LocalDate.now().minusDays(2234)), "2234 days ago"),
            Arguments.of(Optional.empty(), "1234 da4ys la321ter"),
            Arguments.of(Optional.empty(), "2023 13 4"),
            Arguments.of(Optional.empty(), "2132-23/32"),
            Arguments.of(Optional.empty(), "2537513"),
            Arguments.of(Optional.empty(), "1:10:2201")
        );
    }

    @ParameterizedTest
    @DisplayName("Валидация даты")
    @MethodSource("provideParseDate")
    void parseDate(Optional<LocalDate> expected, String input) {
        assertEquals(expected, Task3.parseDate(input));

        assertThatThrownBy(() -> Task3.parseDate(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task3.NULL_POINTER_EXCEPTION_MESSAGE);
    }
}
