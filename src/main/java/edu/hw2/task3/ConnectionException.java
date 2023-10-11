package edu.hw2.task3;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(ConnectionException exception) {
        super(exception);
    }
}
