package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Подсчет общей длины видео в секундах")
    void minutesToSeconds() {
        assertThat(Task1.minutesToSeconds("01:00")).isEqualTo(60);
        assertThat(Task1.minutesToSeconds("13:56")).isEqualTo(836);
        assertThat(Task1.minutesToSeconds("0000:00")).isEqualTo(0);
        assertThat(Task1.minutesToSeconds("0:0")).isEqualTo(0);
        assertThat(Task1.minutesToSeconds("99:59")).isEqualTo(5999);
        assertThat(Task1.minutesToSeconds("10:60")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("10:60:12")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("1060")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("   ")).isEqualTo(-1);
        assertThat(Task1.minutesToSeconds("9223372036854775807:22")).isEqualTo(-1);

        Assertions.assertEquals(
            "For input string: \"3.1\"",
            Assertions.assertThrows(NumberFormatException.class,
                () -> Task1.minutesToSeconds("3.1:3"), "NumberFormatException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            "For input string: \"3.4\"",
            Assertions.assertThrows(NumberFormatException.class,
                () -> Task1.minutesToSeconds("3:3.4"), "NumberFormatException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            "For input string: \"3  \"",
            Assertions.assertThrows(NumberFormatException.class,
                () -> Task1.minutesToSeconds("21:3  "), "NumberFormatException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            "Cannot invoke \"String.split(String)\" because \"s\" is null",
            Assertions.assertThrows(NullPointerException.class,
                () -> Task1.minutesToSeconds(null), "NullPointerException was expected"
            ).getMessage()
        );
    }
}
