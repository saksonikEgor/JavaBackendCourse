package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Подсчет количества шагов до получения 6174")
    void countK() {
        assertThat(Task6.countK(6621)).isEqualTo(5);
        assertThat(Task6.countK(6554)).isEqualTo(4);
        assertThat(Task6.countK(1234)).isEqualTo(3);
        assertThat(Task6.countK(6174)).isEqualTo(0);
        assertThat(Task6.countK(9998)).isEqualTo(5);

        Assertions.assertEquals(
            "It is impossible to execute the Kaprekar function because the \"num\" is not valid",
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> Task6.countK(4444), "IllegalArgumentException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            "It is impossible to execute the Kaprekar function because the \"num\" is not valid",
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> Task6.countK(5847263), "IllegalArgumentException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            "It is impossible to execute the Kaprekar function because the \"num\" is not valid",
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> Task6.countK(-5736), "IllegalArgumentException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            "It is impossible to execute the Kaprekar function because the \"num\" is not valid",
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> Task6.countK(0), "IllegalArgumentException was expected"
            ).getMessage()
        );
    }
}
