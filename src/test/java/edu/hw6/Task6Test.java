package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @DisplayName("Сканирование портов")
    @Test
    void scanPorts() {
        assertTrue(Task6.isPortBusy(135));
        assertTrue(Task6.isPortBusy(143));

        assertDoesNotThrow(Task6::scanPorts);
    }
}
