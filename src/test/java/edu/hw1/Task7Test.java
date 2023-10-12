package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Циклический сдвиг влево")
    void rotateLeft() {
        for (int i = 0; i < 10; i++) {
            assertThat(Task7.rotateLeft(0, i)).isEqualTo(0);
        }

        for (int i = 0; i < 5; i++) {
            assertThat(Task7.rotateLeft(7, i)).isEqualTo(7);
        }

        assertThat(Task7.rotateLeft(16, 1)).isEqualTo(1);
        assertThat(Task7.rotateLeft(8, 34)).isEqualTo(2);
        assertThat(Task7.rotateLeft(-8, 1)).isEqualTo(4294967281L);
        assertThat(Task7.rotateLeft(-17, 82)).isEqualTo(4290772991L);

        // Using rotateRight
        assertThat(Task7.rotateLeft(8, -1)).isEqualTo(4);
        assertThat(Task7.rotateLeft(8, -34)).isEqualTo(2);
        assertThat(Task7.rotateLeft(-8, -1)).isEqualTo(2147483644);
        assertThat(Task7.rotateLeft(-8, -34)).isEqualTo(1073741822);
    }

    @Test
    @DisplayName("Циклический сдвиг вправо")
    void rotateRight() {
        for (int i = 0; i < 10; i++) {
            assertThat(Task7.rotateRight(0, i)).isEqualTo(0);
        }

        for (int i = 0; i < 5; i++) {
            assertThat(Task7.rotateRight(7, i)).isEqualTo(7);
        }

        assertThat(Task7.rotateRight(8, 1)).isEqualTo(4);
        assertThat(Task7.rotateRight(8, 34)).isEqualTo(2);
        assertThat(Task7.rotateRight(-8, 4)).isEqualTo(2415919103L);
        assertThat(Task7.rotateRight(-8, 34)).isEqualTo(1073741822);

        // Using rotateLeft
        assertThat(Task7.rotateRight(16, -1)).isEqualTo(1);
        assertThat(Task7.rotateRight(8, -34)).isEqualTo(2);
        assertThat(Task7.rotateRight(-8, -1)).isEqualTo(4294967281L);
        assertThat(Task7.rotateRight(-17, -82)).isEqualTo(4290772991L);
    }
}
