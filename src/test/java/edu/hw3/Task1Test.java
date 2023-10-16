package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Шифрация Атбаша")
    void atbashEncryption() {
        assertEquals(Task1.atbash("Hello world!"), "Svool dliow!");
        assertEquals(
            Task1.atbash("Any fool can write code that a computer can understand. "
                + "Good programmers write code that humans can understand. ― Martin Fowler"),
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv "
                + "xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
        );
        assertEquals(Task1.atbash("!@#^$%!"), "!@#^$%!");
        assertEquals(Task1.atbash("!@#message^$%!"), "!@#nvhhztv^$%!");
        assertEquals(
            Task1.atbash("message сообщение …њzџџ‘љzm∆ђіzdѕƒ љ'љљљљ‘d'јј‘''ћƒ∆љvƒћі∆•"),
            "nvhhztv сообщение …њaџџ‘љan∆ђіawѕƒ љ'љљљљ‘w'јј‘''ћƒ∆љeƒћі∆•"
        );
        assertEquals(Task1.atbash("12345target54321"), "12345gzitvg54321");


        assertEquals(Task1.NULL_POINTER_EXCEPTION_MESSAGE, Assertions.assertThrows(NullPointerException.class,
            () -> Task1.atbash(null), "NullPointerException was expected"
        ).getMessage());
    }
}
