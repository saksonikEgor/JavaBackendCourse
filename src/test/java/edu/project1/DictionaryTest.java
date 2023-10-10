package edu.project1;

import edu.project1.exception.WrongGameParamsException;
import edu.project1.parameter.GameParams;
import edu.project1.service.Dictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryTest {
    @Test
    @DisplayName("Получение случайного слова из словаря")
    void selectRandomWord() {
        String[] stringPool = new String[] {"first", "second", "third", "forth"};
        Random random = new Random();

        try {
            random.setSeed(-385);
            assertThat(Dictionary.getRandomWord(stringPool, random)).isEqualTo("first");

            random.setSeed(-50);
            assertThat(Dictionary.getRandomWord(stringPool, random)).isEqualTo("second");

            random.setSeed(0);
            assertThat(Dictionary.getRandomWord(stringPool, random)).isEqualTo("third");

            random.setSeed(256);
            assertThat(Dictionary.getRandomWord(stringPool, random)).isEqualTo("forth");
        } catch (WrongGameParamsException.WrongDictionaryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Передача \"stringPull\" = null")
    void selectRandomWordFromNullableStringPool() {
        Assertions.assertEquals(
            "Cannot read the array length because \"wordPool\" is null",
            Assertions.assertThrows(NullPointerException.class,
                () -> Dictionary.getRandomWord(null, new Random()), "NullPointerException was expected"
            ).getMessage()
        );
    }

    @Test
    @DisplayName("Передача \"random\" = null")
    void selectRandomWordUsingNullableRandom() {
        Assertions.assertEquals(
            "Cannot invoke \"java.util.Random.nextInt(int)\" because \"random\" is null",
            Assertions.assertThrows(
                NullPointerException.class,
                () -> Dictionary.getRandomWord(new String[] {"first", "second"}, null),
                "NullPointerException was expected"
            ).getMessage()
        );
    }

    @Test
    @DisplayName("Получение случайного слова из словаря с один валидным словом")
    void selectRandomWordFromDictionaryWithOneValidWord() {
        String[] wordPool = new String[] {"", null, null, "", "", "valid", "", null};
        Random random = new Random();
        try {
            for (int i = 0; i < 100; i++) {
                assertThat(Dictionary.getRandomWord(wordPool, random)).isEqualTo("valid");
            }
        } catch (WrongGameParamsException.WrongDictionaryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Получение случайного слова из пустого словаря")
    void selectRandomWordFromWrongEmptyDictionary() {
        Assertions.assertEquals(
            GameParams.WRONG_DICTIONARY_SIZE_EXCEPTION,
            Assertions.assertThrows(
                WrongGameParamsException.WrongDictionaryException.class,
                () -> Dictionary.getRandomWord(new String[] {}, new Random()),
                "WrongDictionaryException was expected"
            ).getMessage()
        );
    }

    @Test
    @DisplayName("Получение случайного слова из словаря, в котором все слова невалидны")
    void selectRandomWordFromDictionaryWithAllInvalidWords() {
        Assertions.assertEquals(
            GameParams.WRONG_DICTIONARY_EXCEPTION_MESSAGE,
            Assertions.assertThrows(
                WrongGameParamsException.WrongDictionaryException.class,
                () -> Dictionary.getRandomWord(new String[] {null, null, "", "", null}, new Random()),
                "WrongDictionaryException was expected"
            ).getMessage()
        );
    }
}
