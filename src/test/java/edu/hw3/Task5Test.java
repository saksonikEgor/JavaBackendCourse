package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task5Test {
    @Test
    @DisplayName("Формирование массива контактов")
    void parseContacts() {
        assertArrayEquals(
            new Task5.Contact[] {
                new Task5.Contact("Thomas Aquinas"), new Task5.Contact("Rene Descartes"),
                new Task5.Contact("David Hume"), new Task5.Contact("John Locke")
            },
            Task5.parseContacts(
                new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"}, "ASC"
            )
        );

        assertArrayEquals(
            new Task5.Contact[] {
                new Task5.Contact("Carl Gauss"), new Task5.Contact("Leonhard Euler"),
                new Task5.Contact("Paul Erdos")
            },
            Task5.parseContacts(
                new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"}, "DESC"
            )
        );

        assertArrayEquals(
            new Task5.Contact[] {
                new Task5.Contact("Aboba"), new Task5.Contact("Adam"), new Task5.Contact("Adolf")
            },
            Task5.parseContacts(
                new String[] {"Adam", "Aboba", "Adolf"}, "ASC"
            )
        );

        assertArrayEquals(
            new Task5.Contact[] {
                new Task5.Contact("Alex Zelko"), new Task5.Contact("Walter White"),
                new Task5.Contact("Max"), new Task5.Contact("Anton")
            },
            Task5.parseContacts(
                new String[] {"Alex Zelko", "Anton", "Max", "Walter White"}, "DESC"
            )
        );

        assertArrayEquals(new Task5.Contact[] {}, Task5.parseContacts(new String[] {}, "DESC"));
        assertArrayEquals(new Task5.Contact[] {}, Task5.parseContacts(null, "DESC"));
        assertArrayEquals(new Task5.Contact[] {}, Task5.parseContacts(new String[] {}, "ASC"));
        assertArrayEquals(new Task5.Contact[] {}, Task5.parseContacts(null, "ASC"));

        assertThatThrownBy(() -> Task5.parseContacts(new String[] {}, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task5.KEY_WORD_IS_NULL_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task5.parseContacts(new String[] {}, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining(Task5.KEY_WORD_IS_NULL_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task5.parseContacts(new String[] {"Sample Name", "I Am A Wrong Name"}, "DESC"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task5.NAME_IS_INVALID_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task5.parseContacts(new String[] {"Sample Name", "I Am A Wrong Name"}, "DESC"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Task5.NAME_IS_INVALID_EXCEPTION_MESSAGE);

        assertThatThrownBy(() -> Task5.parseContacts(new String[] {"Sample Name"}, "WRONG"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("No enum constant edu.hw3.Task5.Key.");
    }
}

