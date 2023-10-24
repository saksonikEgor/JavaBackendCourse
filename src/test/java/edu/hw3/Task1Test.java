package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Шифрация Атбаша")
    void atbashEncryption() {
        assertEquals("Svool dliow!", Task1.atbash("Hello world!"));
        assertEquals(
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv "
                + "xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
            Task1.atbash("Any fool can write code that a computer can understand. "
                + "Good programmers write code that humans can understand. ― Martin Fowler")
        );
        assertEquals("!@#^$%!", Task1.atbash("!@#^$%!"));
        assertEquals("!@#nvhhztv^$%!", Task1.atbash("!@#message^$%!"));
        assertEquals(
            "nvhhztv сообщение …њaџџ‘љan∆ђіawѕƒ љ'љљљљ‘w'јј‘''ћƒ∆љeƒћі∆•",
            Task1.atbash("message сообщение …њzџџ‘љzm∆ђіzdѕƒ љ'љљљљ‘d'јј‘''ћƒ∆љvƒћі∆•")
        );
        assertEquals("12345gzitvg54321", Task1.atbash("12345target54321"));

        assertThatThrownBy(() -> Task1.atbash(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task1.NULL_POINTER_EXCEPTION_MESSAGE);
    }
}
