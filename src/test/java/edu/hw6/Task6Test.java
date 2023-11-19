package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @DisplayName("Сканирование портов")
    @Test
    void scanPorts() {
        assertTrue(Task6.isPortBusy(445));

        assertDoesNotThrow(Task6::scanPorts);
    }
}
