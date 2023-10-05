package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Исправление строки")
    void fixString() {
        assertThat(Task4.fixString("123456")).isEqualTo("214365");
        assertThat(Task4.fixString("оПомигети псаривьтс ртко!и")).isEqualTo("Помогите исправить строки!");
        assertThat(Task4.fixString("hTsii  s aimex dpus rtni.g")).isEqualTo("This is a mixed up string.");
        assertThat(Task4.fixString("badce")).isEqualTo("abcde");
        assertThat(Task4.fixString("55555")).isEqualTo("55555");
        assertThat(Task4.fixString("1234567")).isEqualTo("2143657");
        assertThat(Task4.fixString("qwertyu")).isEqualTo("wqreytu");
        assertThat(Task4.fixString("")).isEqualTo("");
        assertThat(Task4.fixString("  ")).isEqualTo("  ");

        Assertions.assertEquals(
            "Cannot invoke \"String.toCharArray()\" because \"str\" is null",
            Assertions.assertThrows(NullPointerException.class,
                () -> Task4.fixString(null), "NullPointerException was expected"
            ).getMessage()
        );
    }
}
