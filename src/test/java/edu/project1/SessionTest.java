package edu.project1;

import edu.project1.exception.WrongGameParamsException;
import edu.project1.parameter.GameParams;
import edu.project1.service.GuessResult;
import edu.project1.service.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {
    @Test
    @DisplayName("Создание сессии с невалидным \"maxAttempts\"")
    void createSessionWithWrongMaxAttempts() {
        Assertions.assertEquals(
            GameParams.WRONG_MAX_ATTEMPTS_EXCEPTION_MESSAGE,
            Assertions.assertThrows(
                WrongGameParamsException.WrongMaxAttemptsException.class,
                () -> new Session(-2, new String[] {"word"}, '*', '0'),
                "WrongMaxAttemptsException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            GameParams.WRONG_MAX_ATTEMPTS_EXCEPTION_MESSAGE,
            Assertions.assertThrows(
                WrongGameParamsException.WrongMaxAttemptsException.class,
                () -> new Session(0, new String[] {"word"}, '*', '0'),
                "WrongMaxAttemptsException was expected"
            ).getMessage()
        );
    }

    @Test
    @DisplayName("Создание сессии с невалидным \"giveUpChar\"")
    void createSessionWithWrongGiveUpChar() {
        Assertions.assertEquals(
            GameParams.WRONG_GIVE_UP_CHAR_EXCEPTION_MESSAGE,
            Assertions.assertThrows(
                WrongGameParamsException.WrongGiveUpCharException.class,
                () -> new Session(10, new String[] {"word"}, '*', 'A'),
                "WrongGiveUpCharException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            GameParams.WRONG_GIVE_UP_CHAR_EXCEPTION_MESSAGE,
            Assertions.assertThrows(
                WrongGameParamsException.WrongGiveUpCharException.class,
                () -> new Session(10, new String[] {"word"}, '*', 'U'),
                "WrongGiveUpCharException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            GameParams.WRONG_GIVE_UP_CHAR_EXCEPTION_MESSAGE,
            Assertions.assertThrows(
                WrongGameParamsException.WrongGiveUpCharException.class,
                () -> new Session(10, new String[] {"word"}, '*', 'Ю'),
                "WrongGiveUpCharException was expected"
            ).getMessage()
        );
    }

    @Test
    @DisplayName("Ввод \"giveUpChar\"")
    void enterGiveUpCharacter() {
        Session session;
        GuessResult guessResult;
        try {
            session = new Session(10, new String[] {"first"}, '*', '0');
            guessResult = session.guess('0');
            assertThat(guessResult.state()).isEqualTo(new char[] {'*', '*', '*', '*', '*'});
            assertThat(guessResult.attempt()).isEqualTo(0);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.DEFEAT_MESSAGE);

            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('a');
            session.guess('b');
            guessResult = session.guess('0');
            assertThat(guessResult.state()).isEqualTo(new char[] {'*', '*', '*', '*', '*'});
            assertThat(guessResult.attempt()).isEqualTo(2);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.DEFEAT_MESSAGE);

            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('f');
            session.guess('s');
            session.guess('a');
            guessResult = session.guess('0');
            assertThat(guessResult.state()).isEqualTo(new char[] {'f', '*', '*', 's', '*'});
            assertThat(guessResult.attempt()).isEqualTo(1);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.DEFEAT_MESSAGE);
        } catch (WrongGameParamsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Ввод одинаковых символов")
    void enterEqualCharacters() {
        Session session;
        GuessResult guessResult;
        try {
            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('a');
            guessResult = session.guess('a');
            assertThat(guessResult.state()).isEqualTo(new char[] {'*', '*', '*', '*', '*'});
            assertThat(guessResult.attempt()).isEqualTo(1);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.THIS_CHARACTER_HAS_ALREADY_BEEN_ENTERED_MESSAGE);

            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('a');
            session.guess('a');
            session.guess('b');
            session.guess('a');
            guessResult = session.guess('a');
            assertThat(guessResult.state()).isEqualTo(new char[] {'*', '*', '*', '*', '*'});
            assertThat(guessResult.attempt()).isEqualTo(2);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.THIS_CHARACTER_HAS_ALREADY_BEEN_ENTERED_MESSAGE);

            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('f');
            session.guess('s');
            guessResult = session.guess('s');
            assertThat(guessResult.state()).isEqualTo(new char[] {'f', '*', '*', 's', '*'});
            assertThat(guessResult.attempt()).isEqualTo(0);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.THIS_CHARACTER_HAS_ALREADY_BEEN_ENTERED_MESSAGE);
        } catch (WrongGameParamsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Пользователь угадал и выйграл")
    void successfulGuessingAndWin() {
        Session session;
        GuessResult guessResult;
        try {
            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('f');
            session.guess('i');
            session.guess('r');
            session.guess('s');
            guessResult = session.guess('t');
            assertThat(guessResult.state()).isEqualTo(new char[] {'f', 'i', 'r', 's', 't'});
            assertThat(guessResult.attempt()).isEqualTo(0);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.WIN_MASSAGE);

            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('f');
            session.guess('i');
            session.guess('r');
            session.guess('r');
            session.guess('q');
            session.guess('a');
            session.guess('s');
            guessResult = session.guess('t');
            assertThat(guessResult.state()).isEqualTo(new char[] {'f', 'i', 'r', 's', 't'});
            assertThat(guessResult.attempt()).isEqualTo(2);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.WIN_MASSAGE);
        } catch (WrongGameParamsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Пользователь угадал и не выйграл")
    void successfulGuessing() {
        Session session;
        GuessResult guessResult;
        try {
            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('f');
            session.guess('i');
            session.guess('r');
            guessResult = session.guess('s');
            assertThat(guessResult.state()).isEqualTo(new char[] {'f', 'i', 'r', 's', '*'});
            assertThat(guessResult.attempt()).isEqualTo(0);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.SUCCESSFUL_GUESS_MESSAGE);

            session = new Session(10, new String[] {"first"}, '*', '0');
            guessResult = session.guess('f');
            assertThat(guessResult.state()).isEqualTo(new char[] {'f', '*', '*', '*', '*'});
            assertThat(guessResult.attempt()).isEqualTo(0);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.SUCCESSFUL_GUESS_MESSAGE);

            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('f');
            session.guess('i');
            session.guess('r');
            session.guess('1');
            session.guess('2');
            session.guess('3');
            session.guess('4');
            session.guess('5');
            session.guess('6');
            session.guess('7');
            session.guess('8');
            session.guess('9');
            guessResult = session.guess('t');
            assertThat(guessResult.state()).isEqualTo(new char[] {'f', 'i', 'r', '*', 't'});
            assertThat(guessResult.attempt()).isEqualTo(9);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.SUCCESSFUL_GUESS_MESSAGE);
        } catch (WrongGameParamsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Пользователь не угадал и проиграл")
    void failedGuessingAndDefeat() {
        Session session;
        GuessResult guessResult;
        try {
            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('1');
            session.guess('2');
            session.guess('3');
            session.guess('4');
            session.guess('5');
            session.guess('6');
            session.guess('7');
            session.guess('8');
            session.guess('9');
            guessResult = session.guess('q');
            assertThat(guessResult.state()).isEqualTo(new char[] {'*', '*', '*', '*', '*'});
            assertThat(guessResult.attempt()).isEqualTo(10);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.DEFEAT_MESSAGE);

            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('f');
            session.guess('s');
            session.guess('t');
            session.guess('1');
            session.guess('2');
            session.guess('3');
            session.guess('4');
            session.guess('5');
            session.guess('6');
            session.guess('7');
            session.guess('8');
            session.guess('9');
            guessResult = session.guess('q');
            assertThat(guessResult.state()).isEqualTo(new char[] {'f', '*', '*', 's', 't'});
            assertThat(guessResult.attempt()).isEqualTo(10);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo(GameParams.DEFEAT_MESSAGE);
        } catch (WrongGameParamsException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Пользователь не угадал и не проиграл")
    void failedGuessing() {
        Session session;
        GuessResult guessResult;
        try {
            session = new Session(10, new String[] {"first"}, '*', '0');
            guessResult = session.guess('q');
            assertThat(guessResult.state()).isEqualTo(new char[] {'*', '*', '*', '*', '*'});
            assertThat(guessResult.attempt()).isEqualTo(1);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo("Missed, mistake 1 out of 10.");

            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('f');
            session.guess('s');
            session.guess('t');
            guessResult = session.guess('a');
            assertThat(guessResult.state()).isEqualTo(new char[] {'f', '*', '*', 's', 't'});
            assertThat(guessResult.attempt()).isEqualTo(1);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo("Missed, mistake 1 out of 10.");

            session = new Session(10, new String[] {"first"}, '*', '0');
            session.guess('f');
            session.guess('a');
            session.guess('b');
            session.guess('c');
            guessResult = session.guess('k');
            assertThat(guessResult.state()).isEqualTo(new char[] {'f', '*', '*', '*', '*'});
            assertThat(guessResult.attempt()).isEqualTo(4);
            assertThat(guessResult.maxAttempts()).isEqualTo(10);
            assertThat(guessResult.message()).isEqualTo("Missed, mistake 4 out of 10.");
        } catch (WrongGameParamsException e) {
            throw new RuntimeException(e);
        }
    }
}
