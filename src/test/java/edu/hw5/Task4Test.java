package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    private static Stream<Arguments> providePasswordIsValid() {
        return Stream.of(
            Arguments.of(false, "398hd29h81h"),
            Arguments.of(false, "7857236013"),
            Arguments.of(false, "flf-0fk2-0--"),
            Arguments.of(true, "123@321"),
            Arguments.of(true, "123!321"),
            Arguments.of(true, "123~321"),
            Arguments.of(true, "123#321"),
            Arguments.of(true, "123$321"),
            Arguments.of(true, "123%321"),
            Arguments.of(true, "123^321"),
            Arguments.of(true, "123&321"),
            Arguments.of(true, "123*321"),
            Arguments.of(true, "123|321"),
            Arguments.of(true, "12@3|3%@!!$@@21")
        );
    }

    @ParameterizedTest
    @DisplayName("Проверка пароля на валидность")
    @MethodSource("providePasswordIsValid")
    void passwordIsValid(boolean expected, String input) {
        assertEquals(expected, Task4.passwordIsValid(input));

        assertThatThrownBy(() -> Task4.passwordIsValid(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task4.NULL_POINTER_EXCEPTION_MESSAGE);
    }
}
