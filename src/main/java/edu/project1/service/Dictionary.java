package edu.project1.service;

import edu.project1.exception.WrongGameParamsException;
import org.jetbrains.annotations.NotNull;

public interface Dictionary {
    @NotNull String getRandomWord() throws WrongGameParamsException.WrongDictionaryException;
}
