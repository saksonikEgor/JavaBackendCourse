package edu.project1;

import edu.project1.exception.WrongGameParamsException;
import edu.project1.parameter.GameParams;
import edu.project1.service.RandomDictionary;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DictionaryTest {
    @Test
    @DisplayName("Получение случайного слова из словаря")
    void selectRandomWord() {
        String[] stringPool = new String[] {"first", "second", "third", "forth"};
        Random random = new Random();

        try {
            random.setSeed(-385);
            assertThat(new RandomDictionary(stringPool, random).getRandomWord()).isEqualTo("first");

            random.setSeed(-50);
            assertThat(new RandomDictionary(stringPool, random).getRandomWord()).isEqualTo("second");

            random.setSeed(0);
            assertThat(new RandomDictionary(stringPool, random).getRandomWord()).isEqualTo("third");

            random.setSeed(256);
            assertThat(new RandomDictionary(stringPool, random).getRandomWord()).isEqualTo("forth");
        } catch (WrongGameParamsException.WrongDictionaryException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Передача \"stringPull\" = null")
    void selectRandomWordFromNullableStringPool() {
        Assertions.assertEquals(
            "Cannot read the array length because \"this.wordPool\" is null",
            Assertions.assertThrows(NullPointerException.class,
                () -> new RandomDictionary(null, new Random()).getRandomWord(), "NullPointerException was expected"
            ).getMessage()
        );
    }

    @Test
    @DisplayName("Передача \"random\" = null")
    void selectRandomWordUsingNullableRandom() {
        Assertions.assertEquals(
            "Cannot invoke \"java.util.Random.nextInt(int)\" because \"this.random\" is null",
            Assertions.assertThrows(
                NullPointerException.class,
                () -> new RandomDictionary(new String[] {"first", "second"}, null).getRandomWord(),
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
                assertThat(new RandomDictionary(wordPool, random).getRandomWord()).isEqualTo("valid");
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
                () -> new RandomDictionary(new String[] {}, new Random()).getRandomWord(),
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
                () -> new RandomDictionary(new String[] {null, null, "", "", null}, new Random()).getRandomWord(),
                "WrongDictionaryException was expected"
            ).getMessage()
        );
    }
}
