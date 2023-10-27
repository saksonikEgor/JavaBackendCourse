package edu.project1;

import edu.project1.exception.WrongGameParamsException;
import edu.project1.service.Session;
import edu.project1.util.ConsoleHangman;
import java.util.Scanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleHangmanTest {
    @Test
    @DisplayName("Пользователь выйграл без единого промаха")
    void winningWithoutAMistake() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("f", "i", "r", "s", "t")
                .execute(() -> assertEquals("""
                    Press '0' to give up
                    Guess a letter:
                    Hit!

                    The word: f****

                    Guess a letter:
                    Hit!

                    The word: fi***

                    Guess a letter:
                    Hit!

                    The word: fir**

                    Guess a letter:
                    Hit!

                    The word: firs*

                    Guess a letter:
                    The word: first

                    You won!
                    Bye!
                    """, tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("Пользователь выйграл, но промахнулся 1 раз из 5")
    void winningWithOneOutOfFiveMistake() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("f", "i", "r", "s", "q", "t")
                .execute(() -> assertEquals(
                    """
                        Press '0' to give up
                        Guess a letter:
                        Hit!

                        The word: f****

                        Guess a letter:
                        Hit!

                        The word: fi***

                        Guess a letter:
                        Hit!

                        The word: fir**

                        Guess a letter:
                        Hit!

                        The word: firs*

                        Guess a letter:
                        Missed, mistake 1 out of 5.

                        The word: firs*

                        Guess a letter:
                        The word: first

                        You won!
                        Bye!
                        """,
                    tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)
                ));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("Пользователь выйграл, но промахнулся 4 раза из 5")
    void winningWithForthOutOfFiveMistake() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("f", "i", "r", "s", "q", "w", "b", "z", "t")
                .execute(() -> assertEquals("""
                    Press '0' to give up
                    Guess a letter:
                    Hit!

                    The word: f****

                    Guess a letter:
                    Hit!

                    The word: fi***

                    Guess a letter:
                    Hit!

                    The word: fir**

                    Guess a letter:
                    Hit!

                    The word: firs*

                    Guess a letter:
                    Missed, mistake 1 out of 5.

                    The word: firs*

                    Guess a letter:
                    Missed, mistake 2 out of 5.

                    The word: firs*

                    Guess a letter:
                    Missed, mistake 3 out of 5.

                    The word: firs*

                    Guess a letter:
                    Missed, mistake 4 out of 5.

                    The word: firs*

                    Guess a letter:
                    The word: first

                    You won!
                    Bye!
                    """, tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("Пользователь выйграл, но промахнулся 4 раза из 5 и 3 раза опечатался")
    void winningWithForthOutOfFiveMistakeAndMistypedTreeTimes() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("f", "i", "r", "miss", "miss", "s", "q", "w", "b", "z", "miss", "t")
                .execute(() -> assertEquals("""
                    Press '0' to give up
                    Guess a letter:
                    Hit!

                    The word: f****

                    Guess a letter:
                    Hit!

                    The word: fi***

                    Guess a letter:
                    Hit!

                    The word: fir**

                    Guess a letter:
                    The number of characters must be 1, try again.

                    The word: fir**

                    Guess a letter:
                    The number of characters must be 1, try again.

                    The word: fir**

                    Guess a letter:
                    Hit!

                    The word: firs*

                    Guess a letter:
                    Missed, mistake 1 out of 5.

                    The word: firs*

                    Guess a letter:
                    Missed, mistake 2 out of 5.

                    The word: firs*

                    Guess a letter:
                    Missed, mistake 3 out of 5.

                    The word: firs*

                    Guess a letter:
                    Missed, mistake 4 out of 5.

                    The word: firs*

                    Guess a letter:
                    The number of characters must be 1, try again.

                    The word: firs*

                    Guess a letter:
                    The word: first

                    You won!
                    Bye!
                    """, tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("Пользователь отгадал 4 символа из 5 и сдался")
    void guessingFourCharactersOutOfFiveAndGiveUp() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("f", "i", "r", "t", "0")
                .execute(() -> assertEquals("""
                    Press '0' to give up
                    Guess a letter:
                    Hit!

                    The word: f****

                    Guess a letter:
                    Hit!

                    The word: fi***

                    Guess a letter:
                    Hit!

                    The word: fir**

                    Guess a letter:
                    Hit!

                    The word: fir*t

                    Guess a letter:
                    Missed, mistake 0 out of 5.

                    The word: fir*t

                    You lost!
                    Bye!
                    """, tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("Пользователь промахнулся 5 раз из 5")
    void missingFiveTimesOutOfFive() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("q", "w", "e", "y", "u")
                .execute(() -> assertEquals("""
                    Press '0' to give up
                    Guess a letter:
                    Missed, mistake 1 out of 5.

                    The word: *****

                    Guess a letter:
                    Missed, mistake 2 out of 5.

                    The word: *****

                    Guess a letter:
                    Missed, mistake 3 out of 5.

                    The word: *****

                    Guess a letter:
                    Missed, mistake 4 out of 5.

                    The word: *****

                    Guess a letter:
                    Missed, mistake 5 out of 5.

                    The word: *****

                    You lost!
                    Bye!
                    """, tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("Пользователь проиграл и ввел еще символы")
    void loosingAndEnteringMoreCharacters() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("q", "w", "e", "y", "u", "f", "i", "r", "q", "t", "miss")
                .execute(() -> assertEquals("""
                    Press '0' to give up
                    Guess a letter:
                    Missed, mistake 1 out of 5.

                    The word: *****

                    Guess a letter:
                    Missed, mistake 2 out of 5.

                    The word: *****

                    Guess a letter:
                    Missed, mistake 3 out of 5.

                    The word: *****

                    Guess a letter:
                    Missed, mistake 4 out of 5.

                    The word: *****

                    Guess a letter:
                    Missed, mistake 5 out of 5.

                    The word: *****

                    You lost!
                    Bye!
                    """, tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("Пользователь сдался и ввел еще символы")
    void givingUpAndEnteringMoreCharacters() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("0", "w", "e", "y", "u", "f", "i", "r", "q", "t", "miss")
                .execute(() -> assertEquals("""
                    Press '0' to give up
                    Guess a letter:
                    Missed, mistake 0 out of 5.

                    The word: *****

                    You lost!
                    Bye!
                    """, tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("Пользователь выйграл и ввел еще символы")
    void winningAndEnteringMoreCharacters() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("f", "s", "t", "i", "r", "f", "i", "r", "q", "t", "miss")
                .execute(() -> assertEquals("""
                    Press '0' to give up
                    Guess a letter:
                    Hit!

                    The word: f****

                    Guess a letter:
                    Hit!

                    The word: f**s*

                    Guess a letter:
                    Hit!

                    The word: f**st

                    Guess a letter:
                    Hit!

                    The word: fi*st

                    Guess a letter:
                    The word: first

                    You won!
                    Bye!
                    """, tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("Пользователь выйграл, введя ранее введенный символ")
    void winningEnteringThePreviouslyEnteredCharacter() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("f", "s", "t", "t", "s", "s", "t", "i", "r")
                .execute(() -> assertEquals("""
                    Press '0' to give up
                    Guess a letter:
                    Hit!

                    The word: f****

                    Guess a letter:
                    Hit!

                    The word: f**s*

                    Guess a letter:
                    Hit!

                    The word: f**st

                    Guess a letter:
                    This character has already been entered, try again.

                    The word: f**st

                    Guess a letter:
                    This character has already been entered, try again.

                    The word: f**st

                    Guess a letter:
                    This character has already been entered, try again.

                    The word: f**st

                    Guess a letter:
                    This character has already been entered, try again.

                    The word: f**st

                    Guess a letter:
                    Hit!

                    The word: fi*st

                    Guess a letter:
                    The word: first

                    You won!
                    Bye!
                    """, tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }

    @Test
    @DisplayName("Пользователь проиграл, введя ранее введенный символ")
    void loosingEnteringThePreviouslyEnteredCharacter() throws Exception {
        try {
            Session session = new Session(5, new String[] {"first"}, '*', '0');
            withTextFromSystemIn("f", "s", "t", "t", "s", "s", "t", "q", "w", "a", "v", "l")
                .execute(() -> assertEquals("""
                    Press '0' to give up
                    Guess a letter:
                    Hit!

                    The word: f****

                    Guess a letter:
                    Hit!

                    The word: f**s*

                    Guess a letter:
                    Hit!

                    The word: f**st

                    Guess a letter:
                    This character has already been entered, try again.

                    The word: f**st

                    Guess a letter:
                    This character has already been entered, try again.

                    The word: f**st

                    Guess a letter:
                    This character has already been entered, try again.

                    The word: f**st

                    Guess a letter:
                    This character has already been entered, try again.

                    The word: f**st

                    Guess a letter:
                    Missed, mistake 1 out of 5.

                    The word: f**st

                    Guess a letter:
                    Missed, mistake 2 out of 5.

                    The word: f**st

                    Guess a letter:
                    Missed, mistake 3 out of 5.

                    The word: f**st

                    Guess a letter:
                    Missed, mistake 4 out of 5.

                    The word: f**st

                    Guess a letter:
                    Missed, mistake 5 out of 5.

                    The word: f**st

                    You lost!
                    Bye!
                    """, tapSystemOutNormalized(new ConsoleHangman(new Scanner(System.in), session)::run)));
        } catch (WrongGameParamsException e) {
            throw new RuntimeException();
        }
    }
}
