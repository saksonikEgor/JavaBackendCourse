package edu.hw2.task3;

import java.util.Random;

public sealed interface ConnectionManager {
    Connection getConnection();

    final class DefaultConnectionManager implements ConnectionManager {
        private static final int CONNECTION_MANAGER_UPPER_BOUND = 2;
        private final Random randomForConnectionManager;
        private final Random randomForConnection;

        public DefaultConnectionManager() {
            randomForConnectionManager = new Random();
            randomForConnection = new Random();
        }

        public DefaultConnectionManager(Random randomForConnectionManager, Random randomForConnection) {
            this.randomForConnectionManager = randomForConnectionManager;
            this.randomForConnection = randomForConnection;
        }

        @Override
        public Connection getConnection() {
            return this.randomForConnectionManager.nextInt(CONNECTION_MANAGER_UPPER_BOUND) == 0
                ? new Connection.FaultyConnection(randomForConnection)
                : new Connection.StableConnection();
        }
    }

    final class FaultyConnectionManager implements ConnectionManager {
        private final Random randomForConnection;

        public FaultyConnectionManager(Random randomForConnection) {
            this.randomForConnection = randomForConnection;
        }

        @Override
        public Connection getConnection() {
            return new Connection.FaultyConnection(randomForConnection);
        }
    }
}
