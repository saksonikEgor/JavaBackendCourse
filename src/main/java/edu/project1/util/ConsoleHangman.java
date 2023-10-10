package edu.project1.util;

import edu.project1.exception.WrongGameParamsException;
import edu.project1.parameter.GameParams;
import edu.project1.service.GuessResult;
import edu.project1.service.Session;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private boolean gameOver = false;
    private final Logger logger = LogManager.getLogger();

    public void run() {
        try {
            Session session = new Session(
                GameParams.MAX_ATTEMPTS, GameParams.WORD_POOL,
                GameParams.UNGUESSED_CHAR, GameParams.GIVE_UP_CHAR
            );
            Scanner scanner = new Scanner(System.in);

            printGiveUpCharIsMessage();

            while (!gameOver) {
                displayASuggestionForEnteringACharacter();
                printState(tryGuess(session, scanner.nextLine()));
            }
            printFarewellMessage();
        } catch (WrongGameParamsException e) {
            logger.error(GameParams.WRONG_GAME_PARAMS_MESSAGE, e);
        }
    }

    private GuessResult tryGuess(Session session, String input) {
        if (input == null || input.length() != 1 || input.charAt(0) == ' ') {
            return new GuessResult.WrongInput(session.getState(), 0, 0, GameParams.WRONG_INPUT_MESSAGE);
        }
        return session.guess(Character.toLowerCase(input.charAt(0)));
    }

    private void printState(GuessResult guess) {
        switch (guess) {
            case GuessResult.Defeat defeat -> {
                printState(new GuessResult.FailedGuess(
                    defeat.state(),
                    defeat.attempt(),
                    defeat.maxAttempts(),
                    GameParams.FAILED_GUESS_MESSAGE
                ));

                logger.info(defeat.message());
                gameOver = true;
            }
            case GuessResult.Win win -> {
                printWordState(new GuessResult.SuccessfulGuess(
                    win.state(),
                    win.attempt(),
                    win.maxAttempts(),
                    GameParams.SUCCESSFUL_GUESS_MESSAGE
                ));

                logger.info(win.message());
                gameOver = true;
            }
            default -> {
                logger.info(guess.message() + "\n");
                printWordState(guess);
            }
        }
    }

    private void displayASuggestionForEnteringACharacter() {
        logger.info(GameParams.GUESS_MESSAGE);
    }

    private void printWordState(GuessResult guess) {
        logger.info(GameParams.PRINT_WORD_STATE_MESSAGE + " " + String.valueOf(guess.state()) + "\n");
    }

    private void printGiveUpCharIsMessage() {
        logger.info(GameParams.GIVE_UP_CHAR_IS_MESSAGE);
    }

    private void printFarewellMessage() {
        logger.info(GameParams.FAREWELL_MESSAGE);
    }
}
