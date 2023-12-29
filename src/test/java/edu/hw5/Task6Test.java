package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {
    private static Stream<Arguments> provideIsSubstring() {
        return Stream.of(
            Arguments.of(true, "abc", "achfdbaabgabcaabg"),
            Arguments.of(true, "abc", "abc"),
            Arguments.of(false, "doijwqdoqw", "doi"),
            Arguments.of(false, "abc", "Abc"),
            Arguments.of(true, "", "achfdbaabgabcaabg"),
            Arguments.of(false, "dfknows", "")
        );
    }

    @ParameterizedTest
    @DisplayName("Поиск подстроки в строке")
    @MethodSource("provideIsSubstring")
    void isSubstring(boolean expected, String inputS, String inputT) {
        assertEquals(expected, Task6.isSubstring(inputS, inputT));

        assertThatThrownBy(() -> Task6.isSubstring(null, "fweiou"))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task6.NULL_POINTER_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task6.isSubstring("dw", null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task6.NULL_POINTER_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task6.isSubstring(null, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task6.NULL_POINTER_EXCEPTION_MESSAGE);
    }
}
