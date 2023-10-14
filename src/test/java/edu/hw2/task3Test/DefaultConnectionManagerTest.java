package edu.hw2.task3Test;

import edu.hw2.task3.Connection;
import edu.hw2.task3.ConnectionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultConnectionManagerTest {
    @Test
    @DisplayName("Получение \"StableConnection\"")
    void getStableConnection() {
        assertEquals(
            Connection.StableConnection.class,
            new ConnectionManager
                .DefaultConnectionManager(new Random(0))
                .getConnection(2, "some message", new Random()).getClass()
        );
    }

    @Test
    @DisplayName("Получение \"FaultyConnection\"")
    void getFaultyConnection() {
        assertEquals(
            Connection.FaultyConnection.class,
            new ConnectionManager
                .DefaultConnectionManager(new Random(-231))
                .getConnection(2, "some message", new Random()).getClass()
        );
    }
}
