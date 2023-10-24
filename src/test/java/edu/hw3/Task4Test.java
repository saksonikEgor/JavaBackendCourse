package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    @DisplayName("Преобразование арабских чисел в римские")
    void convertToRoman() {
        assertEquals("II", Task4.convertToRoman(2));
        assertEquals("XII", Task4.convertToRoman(12));
        assertEquals("XVI", Task4.convertToRoman(16));
        assertEquals("I", Task4.convertToRoman(1));
        assertEquals("MMM", Task4.convertToRoman(3000));
        assertEquals("MMMCMXCIX", Task4.convertToRoman(3999));

        assertThatThrownBy(() -> Task4.convertToRoman(0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task4.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task4.convertToRoman(-23))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task4.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task4.convertToRoman(4000))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task4.ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
    }
}
