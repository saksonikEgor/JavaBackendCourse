package edu.hw5;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static Stream<Arguments> provideGetAllFridays13thInTheCurrentYear() {
        return Stream.of(
            Arguments.of(
                parseStringToLocalDateArray("1925-02-13, 1925-03-13, 1925-11-13"),
                Year.of(1925)
            ),
            Arguments.of(
                parseStringToLocalDateArray("2024-09-13, 2024-12-13"),
                Year.of(2024)
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Поиск всех пятниц 13")
    @MethodSource("provideGetAllFridays13thInTheCurrentYear")
    void getAllFridays13thInTheCurrentYear(LocalDate[] expected, Year input) {
        assertArrayEquals(expected, Task2.getAllFridays13thInTheCurrentYear(input));

        assertThatThrownBy(() -> Task2.getAllFridays13thInTheCurrentYear(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task2.YEAR_NULL_POINTER_EXCEPTION_MESSAGE);
    }

    private static Stream<Arguments> provideGetNextFridays13th() {
        return Stream.of(
            Arguments.of(
                parseStringToLocalDate("1925-02-13"),
                parseStringToLocalDate("1925-02-12")
            ),
            Arguments.of(
                parseStringToLocalDate("1925-02-13"),
                parseStringToLocalDate("1925-02-13")
            ),
            Arguments.of(
                parseStringToLocalDate("1925-11-13"),
                parseStringToLocalDate("1925-08-22")
            ),
            Arguments.of(
                parseStringToLocalDate("2024-09-13"),
                parseStringToLocalDate("2024-01-01")
            )
        );
    }

    @ParameterizedTest
    @DisplayName("Поиск ближайшей пятницы 13")
    @MethodSource("provideGetNextFridays13th")
    void getNextFridays13th(LocalDate expected, LocalDate input) {
        assertEquals(expected, Task2.getNextFriday13th(input));

        assertThatThrownBy(() -> Task2.getNextFriday13th(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task2.DATE_NULL_POINTER_EXCEPTION_MESSAGE);
    }

    private static LocalDate[] parseStringToLocalDateArray(String s) {
        return Arrays.stream(s.split(", "))
            .map(Task2Test::parseStringToLocalDate)
            .toArray(LocalDate[]::new);
    }

    private static LocalDate parseStringToLocalDate(String s) {
        return LocalDate.parse(s, formatter);
    }
}
