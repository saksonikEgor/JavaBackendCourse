package edu.project1.exception;

public abstract sealed class WrongGameParamsException extends RuntimeException {
    protected WrongGameParamsException(String message) {
        super(message);
    }

    public static final class WrongDictionaryException extends WrongGameParamsException {
        public WrongDictionaryException(String message) {
            super(message);
        }
    }

    public static final class WrongGiveUpCharException extends WrongGameParamsException {
        public WrongGiveUpCharException(String message) {
            super(message);
        }
    }

    public static final class WrongMaxAttemptsException extends WrongGameParamsException {
        public WrongMaxAttemptsException(String message) {
            super(message);
        }
    }
}
