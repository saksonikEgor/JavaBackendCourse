package edu.hw2.task3Test;

import edu.hw2.task3.Connection;
import edu.hw2.task3.ConnectionManager;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultConnectionManagerTest {
    @Test
    @DisplayName("Получение \"StableConnection\"")
    void getStableConnection() {
        assertEquals(
            Connection.StableConnection.class,
            new ConnectionManager
                .DefaultConnectionManager(new Random(0), new Random())
                .getConnection().getClass()
        );
    }

    @Test
    @DisplayName("Получение \"FaultyConnection\"")
    void getFaultyConnection() {
        assertEquals(
            Connection.FaultyConnection.class,
            new ConnectionManager
                .DefaultConnectionManager(new Random(-231), new Random())
                .getConnection().getClass()
        );
    }
}
