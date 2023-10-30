package edu.hw3;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    private static Stream<Arguments> provideConvertToRoman() {
        return Stream.of(
            Arguments.of("II", 2),
            Arguments.of("XII", 12),
            Arguments.of("XVI", 16),
            Arguments.of("I", 1),
            Arguments.of("MMM", 3000),
            Arguments.of("MMMCMXCIX", 3999)
        );
    }

    @ParameterizedTest
    @MethodSource("provideConvertToRoman")
    @DisplayName("Преобразование арабских чисел в римские")
    void convertToRoman(String expected, int input) {
        assertEquals(expected, Task4.convertToRoman(input));

        assertThatThrownBy(() -> Task4.convertToRoman(0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task4.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task4.convertToRoman(-23))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task4.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task4.convertToRoman(4000))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task4.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
    }
}
