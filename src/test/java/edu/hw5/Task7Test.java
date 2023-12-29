package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {
    private static Stream<Arguments> provideIsSubstring() {
        return Stream.of(
            Arguments.of(true, "010"),
            Arguments.of(true, "000"),
            Arguments.of(false, "110"),
            Arguments.of(false, "1310"),
            Arguments.of(false, "13e10"),
            Arguments.of(false, "1w0"),
            Arguments.of(false, "10")
        );
    }

    @ParameterizedTest
    @DisplayName("Проверка строки на валидность")
    @MethodSource("provideIsSubstring")
    void isSubstring(boolean expected, String input) {
        assertEquals(expected, Task7.isStringValid(input));

        assertThatThrownBy(() -> Task7.isStringValid(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task7.NULL_POINTER_EXCEPTION_MESSAGE);

    }
}
