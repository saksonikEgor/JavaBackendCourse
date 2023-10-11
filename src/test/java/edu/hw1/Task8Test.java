package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Проверка на отсутствие коней под ударом")
    void knightBoardCapture() {
        assertThat(Task8.knightBoardCapture(new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        })).isEqualTo(true);

        assertThat(Task8.knightBoardCapture(new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        })).isEqualTo(false);

        assertThat(Task8.knightBoardCapture(new int[][] {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        })).isEqualTo(false);

        assertThat(Task8.knightBoardCapture(new int[][] {
            {0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        })).isEqualTo(true);

        assertThat(Task8.knightBoardCapture(new int[][] {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        })).isEqualTo(false);

        assertThat(Task8.knightBoardCapture(new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        })).isEqualTo(true);

        Assertions.assertEquals(
            "\"board\" is invalid because the \"board.length\" is not equals \"BOARD_SIZE\"",
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> Task8.knightBoardCapture(new int[][] {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 1, 0},
                    {0, 0, 1, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}
                }), "IllegalArgumentException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            "\"board\" is invalid because the \"board[2].length\" is not equals \"BOARD_SIZE\"",
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> Task8.knightBoardCapture(new int[][] {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 1, 0},
                    {0, 0, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 0, 1, 0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0}
                }), "IllegalArgumentException was expected"
            ).getMessage()
        );
    }
}
