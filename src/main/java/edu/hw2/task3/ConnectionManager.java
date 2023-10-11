package edu.hw2.task3;

import java.util.Random;

public sealed interface ConnectionManager {
    Connection getConnection();

    final class DefaultConnectionManager implements ConnectionManager {
        private final static int UPPER_BOUND = 2;
        private final Random random;

        public DefaultConnectionManager() {
            random = new Random();
        }

        public DefaultConnectionManager(Random random) {
            this.random = random;
        }

        @Override
        public Connection getConnection() {
            return random.nextInt(UPPER_BOUND) == 0
                ? new Connection.FaultyConnection()
                : new Connection.StableConnection();
        }
    }

    final class FaultyConnectionManager implements ConnectionManager {
        @Override
        public Connection getConnection() {
            return new Connection.FaultyConnection();
        }
    }
}
