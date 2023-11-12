package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {
    private static Stream<Arguments> provideIsValidRussianLicensePlates() {
        return Stream.of(
            Arguments.of(true, "А123ВЕ777"),
            Arguments.of(true, "О777ОО177"),
            Arguments.of(false, "123АВЕ777"),
            Arguments.of(false, "А123ВГ77"),
            Arguments.of(false, "А123ВЕ7777")
        );
    }

    @ParameterizedTest
    @DisplayName("Проверка номерных знаков на валидность")
    @MethodSource("provideIsValidRussianLicensePlates")
    void isValidRussianLicensePlates(boolean expected, String input) {
        assertEquals(expected, Task5.isValidRussianLicensePlates(input));

        assertThatThrownBy(() -> Task5.isValidRussianLicensePlates(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task5.NULL_POINTER_EXCEPTION_MESSAGE);
    }
}
