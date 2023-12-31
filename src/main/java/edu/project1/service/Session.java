package edu.project1.service;

import edu.project1.exception.WrongGameParamsException;
import edu.project1.parameter.GameParams;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class Session {
    private final String answer;
    private final char[] state;
    private final int maxAttempts;
    private final Set<Character> previousCharacters;
    private final Dictionary dictionary;
    private int attempts;
    private int unguessedCharactersLeft;

    public Session(int maxAttempts, String[] wordPool, char unguessedChar, char giveUpChar)
        throws WrongGameParamsException {
        dictionary = new RandomDictionary(wordPool, new Random());
        answer = dictionary.getRandomWord();
        state = new char[answer.length()];

        Arrays.fill(state, unguessedChar);

        this.maxAttempts = maxAttempts;
        if (maxAttempts < 1) {
            throw new WrongGameParamsException
                .WrongMaxAttemptsException(GameParams.WRONG_MAX_ATTEMPTS_EXCEPTION_MESSAGE);
        }

        if (Character.isUpperCase(giveUpChar)) {
            throw new WrongGameParamsException
                .WrongGiveUpCharException(GameParams.WRONG_GIVE_UP_CHAR_EXCEPTION_MESSAGE);
        }

        attempts = 0;
        unguessedCharactersLeft = answer.length();
        previousCharacters = new HashSet<>();
    }

    public @NotNull GuessResult guess(char guess) {
        if (guess == GameParams.GIVE_UP_CHAR) {
            return giveUp();
        }

        if (!previousCharacters.add(guess)) {
            return new GuessResult.WrongInput(state, attempts, maxAttempts,
                GameParams.THIS_CHARACTER_HAS_ALREADY_BEEN_ENTERED_MESSAGE
            );
        }

        GuessResult guessResult;

        if (answer.contains(String.valueOf(guess))) {
            upgradeState(guess);

            guessResult = unguessedCharactersLeft == 0
                ? new GuessResult.Win(state, attempts, maxAttempts, GameParams.WIN_MASSAGE)
                : new GuessResult.SuccessfulGuess(state, attempts, maxAttempts, GameParams.SUCCESSFUL_GUESS_MESSAGE);
        } else {
            guessResult = ++attempts == maxAttempts
                ? new GuessResult.Defeat(state, attempts, maxAttempts, GameParams.DEFEAT_MESSAGE)
                : new GuessResult.FailedGuess(state, attempts, maxAttempts, GameParams.FAILED_GUESS_MESSAGE);
        }
        return guessResult;
    }

    private @NotNull GuessResult giveUp() {
        return new GuessResult.Defeat(state, attempts, maxAttempts, GameParams.DEFEAT_MESSAGE);
    }

    private void upgradeState(char guess) {
        int index = answer.indexOf(guess, -1);

        while (index != -1) {
            state[index++] = guess;
            unguessedCharactersLeft--;
            index = answer.indexOf(guess, index + 1);
        }
    }

    public char[] getState() {
        return state;
    }
}
