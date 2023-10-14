package edu.hw2.task3Test;

import edu.hw2.task3.Connection;
import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Random;

public class FaultyConnectionTest {
    @Test
    @DisplayName("Невыполнение команды")
    void executeWithException() {
        try (Connection connection = new Connection.FaultyConnection(2, "Faulty execute", new Random(-312))) {
            Assertions.assertEquals(
                "Faulty execute",
                Assertions.assertThrows(
                    ConnectionException.class,
                    () -> connection.execute("rm -rf /"), "ConnectionException was expected"
                ).getMessage()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Выполнение команды")
    void executeWithOutException() {
        try (Connection connection = new Connection.FaultyConnection(2, "Faulty execute", new Random(2))) {
            assertDoesNotThrow(() -> connection.execute("rm -rf /"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
