package edu.project3.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WriterUtilsTest {
    @Test
    @DisplayName("Получение описания по коду")
    void getNameByCode() {
        assertEquals(
            "OK",
            WriterUtils.getNameByCode(200)
        );

        assertEquals(
            "Requested Range Not Satisfiable",
            WriterUtils.getNameByCode(416)
        );

        assertNull(WriterUtils.getNameByCode(1245));
    }
}
