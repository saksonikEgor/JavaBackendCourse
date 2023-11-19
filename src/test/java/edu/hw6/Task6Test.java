package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task6Test {
    @DisplayName("Сканирование портов")
    @Test
    void scanPorts() {
        assertFalse(Task6.isPortBusy(8080));

        assertDoesNotThrow(Task6::scanPorts);
    }
}
