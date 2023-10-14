package edu.hw2.task3;

import java.util.Random;

public sealed interface ConnectionManager {
    Connection getConnection(int connectionUpperBound, String connectionExceptionMessage, Random connectionRandom);

    final class DefaultConnectionManager implements ConnectionManager {
        private static final int CONNECTION_MANAGER_UPPER_BOUND = 2;
        private final Random random;

        public DefaultConnectionManager() {
            random = new Random();
        }

        public DefaultConnectionManager(Random random) {
            this.random = random;
        }

        @Override
        public Connection getConnection(
            int connectionUpperBound,
            String connectionExceptionMessage,
            Random connectionRandom
        ) {
            return this.random.nextInt(CONNECTION_MANAGER_UPPER_BOUND) == 0
                ? new Connection.FaultyConnection(connectionUpperBound, connectionExceptionMessage, connectionRandom)
                : new Connection.StableConnection();
        }
    }

    final class FaultyConnectionManager implements ConnectionManager {
        @Override
        public Connection getConnection(
            int connectionUpperBound,
            String connectionExceptionMessage,
            Random connectionRandom
        ) {
            return new Connection.FaultyConnection(connectionUpperBound, connectionExceptionMessage, connectionRandom);
        }
    }
}
