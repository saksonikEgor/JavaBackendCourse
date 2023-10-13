package edu.hw2.task3;

import java.util.Random;

public sealed interface Connection extends AutoCloseable {
    void execute(String command);

    final class StableConnection implements Connection {
        @Override
        public void execute(String command) {
        }

        @Override
        public void close() throws Exception {
        }
    }

    final class FaultyConnection implements Connection {
        private final String connectionExceptionMessage;
        private final int connectionUpperBound;
        private final Random connectionRandom;

        public FaultyConnection(int connectionUpperBound, String connectionExceptionMessage) {
            this.connectionUpperBound = connectionUpperBound;
            this.connectionExceptionMessage = connectionExceptionMessage;
            connectionRandom = new Random();
        }

        public FaultyConnection(int connectionUpperBound, String connectionExceptionMessage, Random connectionRandom) {
            this.connectionUpperBound = connectionUpperBound;
            this.connectionExceptionMessage = connectionExceptionMessage;
            this.connectionRandom = connectionRandom;
        }

        @Override
        public void execute(String command) {
            if (connectionRandom.nextInt(connectionUpperBound) == 0) {
                throw new ConnectionException(connectionExceptionMessage);
            }
        }

        @Override
        public void close() throws Exception {
        }
    }
}
