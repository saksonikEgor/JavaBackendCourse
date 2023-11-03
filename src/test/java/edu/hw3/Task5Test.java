package edu.hw3;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task5Test {
    private static Stream<Arguments> provideConvertToRoman() {
        return Stream.of(
            Arguments.of(new Task5.Contact[] {
                    new Task5.Contact("Thomas", "Aquinas"), new Task5.Contact("Rene", "Descartes"),
                    new Task5.Contact("David", "Hume"), new Task5.Contact("John", "Locke")
                },
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC"
            ),
            Arguments.of(new Task5.Contact[] {
                    new Task5.Contact("Carl", "Gauss"), new Task5.Contact("Leonhard", "Euler"),
                    new Task5.Contact("Paul", "Erdos")
                },
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC"
            ),
            Arguments.of(new Task5.Contact[] {}, new String[] {}, "DESC"),
            Arguments.of(new Task5.Contact[] {}, null, "DESC"),
            Arguments.of(new Task5.Contact[] {}, new String[] {}, "ASC"),
            Arguments.of(new Task5.Contact[] {}, null, "ASC")
        );
    }

    @ParameterizedTest
    @MethodSource("provideConvertToRoman")
    @DisplayName("Формирование массива контактов")
    void parseContacts(Task5.Contact[] expected, String[] input, String inputSortKey) {
        assertArrayEquals(expected, Task5.parseContacts(input, inputSortKey));

        assertThatThrownBy(() -> Task5.parseContacts(new String[] {}, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task5.KEY_WORD_IS_NULL_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task5.parseContacts(new String[] {}, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task5.KEY_WORD_IS_NULL_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task5.parseContacts(new String[] {"Sample Name", "I Am A Wrong Name"}, "DESC"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task5.FULLNAME_IS_INVALID_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task5.parseContacts(new String[] {"Sample Name", "I Am A Wrong Name"}, "DESC"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task5.FULLNAME_IS_INVALID_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task5.parseContacts(new String[] {"Sample Name"}, "WRONG"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("No enum constant edu.hw3.Task5.Key.");
    }
}

