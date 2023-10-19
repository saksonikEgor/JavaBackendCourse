package edu.hw2.task3Test;

import edu.hw2.task3.Connection;
import edu.hw2.task3.ConnectionManager;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FaultyConnectionManagerTest {
    @Test
    @DisplayName("Получение \"FaultyConnection\"")
    void getFaultyConnection() {
        for (int i = 0; i < 50; i++) {
            assertEquals(
                Connection.FaultyConnection.class,
                new ConnectionManager
                    .FaultyConnectionManager(new Random(i))
                    .getConnection().getClass()
            );
        }
    }
}
