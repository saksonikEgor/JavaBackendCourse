package edu.hw6;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task6Test {
    @DisplayName("Сканирование портов")
    @Test
    void scanPorts() throws IOException {
        Task6.scanPorts();
//        assertTrue(Task6.isPortBusy(135));
    }
}
