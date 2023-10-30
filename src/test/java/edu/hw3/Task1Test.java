package edu.hw3;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    private static Stream<Arguments> provideAtbash() {
        return Stream.of(
            Arguments.of("Svool dliow!", "Hello world!"),
            Arguments.of(
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv "
                    + "xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
                "Any fool can write code that a computer can understand. "
                    + "Good programmers write code that humans can understand. ― Martin Fowler"
            ),
            Arguments.of("!@#^$%!", "!@#^$%!"),
            Arguments.of("!@#nvhhztv^$%!", "!@#message^$%!"),
            Arguments.of(
                "nvhhztv сообщение …њaџџ‘љan∆ђіawѕƒ љ'љљљљ‘w'јј‘''ћƒ∆љeƒћі∆•",
                "message сообщение …њzџџ‘љzm∆ђіzdѕƒ љ'љљљљ‘d'јј‘''ћƒ∆љvƒћі∆•"
            ),
            Arguments.of("12345gzitvg54321", "12345target54321")
        );
    }

    @ParameterizedTest
    @DisplayName("Шифрация Атбаша")
    @MethodSource("provideAtbash")
    void atbashEncryption(String expected, String input) {
        assertEquals(expected, Task1.atbash(input));

        assertThatThrownBy(() -> Task1.atbash(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task1.NULL_POINTER_EXCEPTION_MESSAGE);
    }
}
