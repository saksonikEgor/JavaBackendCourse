package edu.project1.service;

import edu.project1.exception.WrongGameParamsException;
import edu.project1.parameter.GameParams;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class RandomDictionary implements Dictionary {
    private final String[] wordPool;
    private final Random random;

    public RandomDictionary(String[] wordPool, Random random) {
        this.wordPool = wordPool;
        this.random = random;
    }

    private static boolean stringLengthIsCorrect(String str) {
        return str != null && !str.isEmpty();
    }

    @Override
    public @NotNull String getRandomWord()
        throws WrongGameParamsException.WrongDictionaryException {
        if (wordPool.length == 0) {
            throw new WrongGameParamsException.WrongDictionaryException(GameParams.WRONG_DICTIONARY_SIZE_EXCEPTION);
        }

        int refIndex = random.nextInt(wordPool.length);
        int curIndex = refIndex;
        while (!stringLengthIsCorrect(wordPool[curIndex])) {
            curIndex = (curIndex + 1) % wordPool.length;

            if (curIndex == refIndex) {
                throw new WrongGameParamsException
                    .WrongDictionaryException(GameParams.WRONG_DICTIONARY_EXCEPTION_MESSAGE);
            }
        }
        return wordPool[curIndex];
    }
}
