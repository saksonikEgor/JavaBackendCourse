package edu.hw2.task3Test;

import edu.hw2.task3.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StableConnectionTest {
    @Test
    @DisplayName("Выполнение команды")
    void executeWithOutException() {
        for (int i = 0; i < 50; i++) {
            try (Connection connection = new Connection.StableConnection()) {
                assertDoesNotThrow(() -> connection.execute("rm -rf /"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
