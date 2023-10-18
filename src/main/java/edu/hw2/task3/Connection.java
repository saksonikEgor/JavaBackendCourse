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
        public static final String CONNECTION_EXCEPTION_MESSAGE = "Faulty execute";
        public static final int CONNECTION_UPPER_BOUND = 2;
        private final Random connectionRandom;

        public FaultyConnection() {
            connectionRandom = new Random();
        }

        public FaultyConnection(Random connectionRandom) {
            this.connectionRandom = connectionRandom;
        }

        @Override
        public void execute(String command) {
            if (connectionRandom.nextInt(CONNECTION_UPPER_BOUND) == 0) {
                throw new ConnectionException(CONNECTION_EXCEPTION_MESSAGE);
            }
        }

        @Override
        public void close() throws Exception {
        }
    }
}
