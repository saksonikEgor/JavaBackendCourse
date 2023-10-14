package edu.hw2.task3Test;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.ConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PopularCommandExecutorTest {
    @Test
    @DisplayName("Выполнение команды с \"DefaultConnectionManager\" и \"StableConnection\"")
    void executeCommandWithDefaultConnectionManagerAndStableConnection() {
        for (int i = 0; i < 50; i++) {
            assertDoesNotThrow(() -> new PopularCommandExecutor(
                    new ConnectionManager.DefaultConnectionManager(new Random(2)),
                    1,
                    new Random()
                ).tryExecute("command")
            );
        }
    }

    @Test
    @DisplayName("Выполнение команды с \"DefaultConnectionManager\" и \"FaultyConnection\"")
    void executeCommandWithDefaultConnectionManagerAndFaultyConnection() {
        for (int i = 0; i < 50; i++) {
            assertDoesNotThrow(() -> new PopularCommandExecutor(
                    new ConnectionManager.DefaultConnectionManager(new Random(-300)),
                    1,
                    new Random(2)
                ).tryExecute("command")
            );
        }
    }

    @Test
    @DisplayName("Невыполнение команды с \"DefaultConnectionManager\" и \"FaultyConnection\"")
    void failureExecuteCommandWithDefaultConnectionManagerAndFaultyConnection() {
        for (int i = 0; i < 50; i++) {
            assertEquals(
                "All executions failed",
                assertThrows(
                    ConnectionException.class,
                    () -> new PopularCommandExecutor(
                        new ConnectionManager.DefaultConnectionManager(new Random(-300)),
                        1,
                        new Random(-300)
                    ).tryExecute("command"), "ConnectionException was expected"
                ).getMessage()
            );
        }
    }

    @Test
    @DisplayName("Выполнение команды с \"FaultyConnectionManager\" и \"FaultyConnection\"")
    void executeCommandWithFaultyConnectionManagerAndFaultyConnection() {
        for (int i = 0; i < 50; i++) {
            assertDoesNotThrow(() -> new PopularCommandExecutor(
                    new ConnectionManager.FaultyConnectionManager(),
                    1,
                    new Random(2)
                ).tryExecute("command")
            );
        }
    }

    @Test
    @DisplayName("Неполнение команды с \"FaultyConnectionManager\" и \"FaultyConnection\"")
    void failureExecuteCommandWithFaultyConnectionManagerAndFaultyConnection() {
        for (int i = 0; i < 50; i++) {
            assertEquals(
                "All executions failed",
                assertThrows(
                    ConnectionException.class,
                    () -> new PopularCommandExecutor(
                        new ConnectionManager.FaultyConnectionManager(),
                        1,
                        new Random(-300)
                    ).tryExecute("command"), "ConnectionException was expected"
                ).getMessage()
            );
        }
    }
}
