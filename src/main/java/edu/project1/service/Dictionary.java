package edu.project1.service;

import edu.project1.exception.WrongGameParamsException;
import edu.project1.parameter.GameParams;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Dictionary {
    private Dictionary() {
    }

    public static @NotNull String getRandomWord() {
        String[] wordPool = GameParams.WORD_POOL;

        int refIndex = new Random().nextInt(wordPool.length);
        int curIndex = refIndex;
        while (!stringLengthIsCorrect(wordPool[curIndex])) {
            curIndex = (curIndex + 1) % wordPool.length;

            if (curIndex == refIndex) {
                throw new WrongGameParamsException.WrongDictionaryException(GameParams.WRONG_DICTIONARY_EXCEPTION_MESSAGE);
            }
        }
        return wordPool[curIndex];
    }

    private static boolean stringLengthIsCorrect(String str) {
        return str != null && !str.isEmpty();
    }
}
