package edu.project1.service;

import edu.project1.params.GameParams;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Dictionary {
    private Dictionary() {
    }

    public static @NotNull String getRandomWord() {
        String[] wordPool = GameParams.WORD_POOL;
        return wordPool[new Random().nextInt(wordPool.length)];
    }
}
