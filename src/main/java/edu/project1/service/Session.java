package edu.project1.service;

import edu.project1.params.GameParams;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class Session {
    private final String answer;
    private final char[] state;
    private final int maxAttempts;
    private int attempts;
    private int unguessedCharactersLeft;
    private final Set<Character> previousCharacters;

    public Session() {
        answer = Dictionary.getRandomWord();
        state = new char[answer.length()];

        Arrays.fill(state, GameParams.UNGUESSED_CHAR);

        maxAttempts = GameParams.MAX_ATTEMPTS;
        attempts = 0;
        unguessedCharactersLeft = answer.length();
        previousCharacters = new HashSet<>();
    }

    public @NotNull GuessResult guess(char guess) {
        if (guess == GameParams.GIVE_UP_CHAR) {
            return giveUp();
        }

        if (previousCharacters.contains(guess)) {
            return new GuessResult.WrongInput(state, attempts, maxAttempts,
                GameParams.THIS_CHARACTER_HAS_ALREADY_BEEN_ENTERED_MESSAGE
            );
        }

        GuessResult guessResult;
        previousCharacters.add(guess);

        if (answer.contains(String.valueOf(guess))) {
            int index = -1;
            while (true) {
                index = answer.indexOf(guess, index + 1);

                if (index == -1) {
                    break;
                }

                state[index] = guess;
                unguessedCharactersLeft--;
            }

            if (unguessedCharactersLeft == 0) {
                guessResult = new GuessResult.Win(state, attempts, maxAttempts, GameParams.WIN_MASSAGE);
            } else {
                guessResult =
                    new GuessResult.SuccessfulGuess(state, attempts, maxAttempts, GameParams.SUCCESSFUL_GUESS_MESSAGE);
            }
        } else {
            if (++attempts == maxAttempts) {
                guessResult = new GuessResult.Defeat(state, attempts, maxAttempts, GameParams.DEFEAT_MESSAGE);
            } else {
                guessResult =
                    new GuessResult.FailedGuess(state, attempts, maxAttempts, GameParams.FAILED_GUESS_MESSAGE);
            }
        }
        return guessResult;
    }

    private @NotNull GuessResult giveUp() {
        return new GuessResult.Defeat(state, attempts, maxAttempts, GameParams.DEFEAT_MESSAGE);
    }

    public char[] getState() {
        return state;
    }
}
