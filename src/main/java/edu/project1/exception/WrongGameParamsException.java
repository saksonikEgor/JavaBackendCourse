package edu.project1.exception;

public abstract sealed class WrongGameParamsException extends RuntimeException
    permits WrongMaxAttemptsException, WrongDictionaryException {
    protected WrongGameParamsException(String message) {
        super(message);
    }
}
