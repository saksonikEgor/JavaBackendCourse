package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Определение возможности вложения первого массива во второй")
    void isNestable() {
        assertThat(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6})).isEqualTo(true);
        assertThat(Task3.isNestable(new int[] {3, 1}, new int[] {4, 0})).isEqualTo(true);
        assertThat(Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9})).isEqualTo(false);
        assertThat(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3})).isEqualTo(false);
        assertThat(Task3.isNestable(new int[] {-2, 4, 0, 1}, new int[] {-12, 2, 4, 4})).isEqualTo(false);
        assertThat(Task3.isNestable(new int[] {-20, 4, 9, 1}, new int[] {-12, 2, 4, 4})).isEqualTo(false);
        assertThat(Task3.isNestable(new int[] {0}, new int[] {0})).isEqualTo(false);
        assertThat(Task3.isNestable(new int[] {3}, new int[] {1, 2, 6})).isEqualTo(true);

        Assertions.assertEquals("Arrays cannot be null", Assertions.assertThrows(NullPointerException.class,
            () -> Task3.isNestable(new int[] {3}, null), "NullPointerException was expected"
        ).getMessage());

        Assertions.assertEquals("Arrays cannot be null", Assertions.assertThrows(NullPointerException.class,
                () -> Task3.isNestable(null, null), "NullPointerException was expected"
        ).getMessage());
    }
}
