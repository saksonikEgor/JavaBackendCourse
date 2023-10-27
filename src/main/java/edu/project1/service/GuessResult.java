package edu.project1.service;

import org.jetbrains.annotations.NotNull;

public sealed interface GuessResult {
    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

    record Defeat(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }

    record Win(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
        @Override public @NotNull String message() {
            return message + ", mistake " + attempt + " out of " + maxAttempts + ".";
        }
    }

    record WrongInput(char[] state, int attempt, int maxAttempts, String message) implements GuessResult {
    }
}
