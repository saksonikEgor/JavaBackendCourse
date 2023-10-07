package edu.project1.util;

import edu.project1.params.GameParams;
import edu.project1.service.GuessResult;
import edu.project1.service.Session;
import java.util.Scanner;

public class ConsoleHangman {
    private boolean gameOver = false;

    public void run() {
        Session session = new Session();
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            displayASuggestionForEnteringACharacter();
            printState(tryGuess(session, scanner.nextLine()));
        }
        printFarewellMessage();
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void displayASuggestionForEnteringACharacter() {
        System.out.println(GameParams.GUESS_MESSAGE);
    }

    private GuessResult tryGuess(Session session, String input) {
        if (input == null || input.length() != 1 || input.charAt(0) == ' ') {
            return new GuessResult.WrongInput(session.getState(), 0, 0, GameParams.WRONG_INPUT_MESSAGE);
        }
        return session.guess(Character.toLowerCase(input.charAt(0)));
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void printState(GuessResult guess) {
        if (guess.getClass().equals(GuessResult.Defeat.class)) {
            printState(new GuessResult.FailedGuess(
                guess.state(),
                guess.attempt(),
                guess.maxAttempts(),
                GameParams.FAILED_GUESS_MESSAGE
            ));

            printWordState(guess);
            System.out.println(guess.message());
            gameOver = true;
        } else if (guess.getClass().equals(GuessResult.Win.class)) {
            printWordState(new GuessResult.SuccessfulGuess(
                guess.state(),
                guess.attempt(),
                guess.maxAttempts(),
                GameParams.SUCCESSFUL_GUESS_MESSAGE
            ));

            printWordState(guess);
            System.out.println(guess.message());
            gameOver = true;
        } else {
            System.out.println(guess.message() + "\n");
            printWordState(guess);
        }
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void printWordState(GuessResult guess) {
        System.out.println(GameParams.PRINT_WORD_STATE_MESSAGE + " " + String.valueOf(guess.state()) + "\n");
    }

    @SuppressWarnings("RegexpSinglelineJava")
    private void printFarewellMessage() {
        System.out.println(GameParams.FAREWELL_MESSAGE);
    }
}
