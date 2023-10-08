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
        switch (guess) {
            case GuessResult.Defeat defeat -> {
                printState(new GuessResult.FailedGuess(
                    defeat.state(),
                    defeat.attempt(),
                    defeat.maxAttempts(),
                    GameParams.FAILED_GUESS_MESSAGE
                ));

                System.out.println(defeat.message());
                gameOver = true;
            }
            case GuessResult.Win win -> {
                printWordState(new GuessResult.SuccessfulGuess(
                    win.state(),
                    win.attempt(),
                    win.maxAttempts(),
                    GameParams.SUCCESSFUL_GUESS_MESSAGE
                ));

                System.out.println(win.message());
                gameOver = true;
            }
            default -> {
                System.out.println(guess.message() + "\n");
                printWordState(guess);
            }
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
