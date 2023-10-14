package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task0Test {
    @Test
    @DisplayName("Проверка вывода строки \"Привет, мир!\"")
    void printHelloWord() throws Exception {
        assertTrue(tapSystemOut(Task0::printHelloWorld)
            .contains("Привет, мир!"));
    }
}
