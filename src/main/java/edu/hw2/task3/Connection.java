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
        public static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "Seed must be greater than 0";
        public static final int CONNECTION_UPPER_BOUND = 2;
        private final Random connectionRandom;

        public FaultyConnection() {
            if (CONNECTION_UPPER_BOUND < 1) {
                throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
            }
            connectionRandom = new Random();
        }

        public FaultyConnection(Random connectionRandom) {
            if (CONNECTION_UPPER_BOUND < 1) {
                throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
            }
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
