package edu.hw2.task3Test;

import edu.hw2.task3.Connection;
import edu.hw2.task3.ConnectionException;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FaultyConnectionTest {
    @Test
    @DisplayName("Невыполнение команды")
    void executeWithException() {
        try (Connection connection = new Connection.FaultyConnection(new Random(-312))) {
            Assertions.assertEquals(
                Connection.FaultyConnection.CONNECTION_EXCEPTION_MESSAGE,
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
        try (Connection connection = new Connection.FaultyConnection(new Random(2))) {
            assertDoesNotThrow(() -> connection.execute("rm -rf /"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
