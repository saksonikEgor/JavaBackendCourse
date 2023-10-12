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
        private static final String CONNECTION_EXCEPTION_MESSAGE = "Faulty execute";
        private static final int UPPER_BOUND = 2;
        private final Random random;

        public FaultyConnection() {
            random = new Random();
        }

        public FaultyConnection(Random random) {
            this.random = random;
        }

        @Override
        public void execute(String command) {
            if (random.nextInt(UPPER_BOUND) == 0) {
                throw new ConnectionException(CONNECTION_EXCEPTION_MESSAGE);
            }
        }

        @Override
        public void close() throws Exception {

        }
    }
}
