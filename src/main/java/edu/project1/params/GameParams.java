package edu.project1.params;

public class GameParams {
    public static final int MAX_ATTEMPTS = 10;
    public static final String[] WORD_POOL = new String[] {"hello", "help", "road", "helicopter", "cucumber"};
    public static final String DEFEAT_MESSAGE = "You lost!";
    public static final String WIN_MASSAGE = "You won!";
    public static final String SUCCESSFUL_GUESS_MESSAGE = "Hit!";
    public static final String FAILED_GUESS_MESSAGE = "Missed";
    public static final String GUESS_MESSAGE = "Guess a letter:";
    public static final String PRINT_WORD_STATE_MESSAGE = "The word:";
    public static final String WRONG_INPUT_MESSAGE = "The number of characters must be 1, try again.";
    public static final String THIS_CHARACTER_HAS_ALREADY_BEEN_ENTERED_MESSAGE = "This character has "
        + "already been entered, try again.";
    public static final String FAREWELL_MESSAGE = "Bye!";
    public static final char UNGUESSED_CHAR = '*';
    public static final char GIVE_UP_CHAR = '0';

    private GameParams() {
    }
}
