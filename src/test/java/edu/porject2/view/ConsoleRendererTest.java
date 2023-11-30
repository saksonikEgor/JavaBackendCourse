package edu.porject2.view;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.properties.ApplicationProperties;
import edu.project2.view.console.ConsoleRenderer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleRendererTest {
    private static String deleteEverySecondCharacter(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 1; i < sb.length(); i++) {
            sb.deleteCharAt(i);
        }
        return sb.toString();
    }

    static Cell[][] generateGridByString(String maze, int height, int width) {
        String[] splits = maze.split("\n");
        char passageChar = ApplicationProperties.PASSAGE_STRING.charAt(0);
        Cell[][] grid = new Cell[height][width];

        for (int i = 0; i < splits.length; i++) {
            String row = deleteEverySecondCharacter(splits[i]);

            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == passageChar) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
        }
        return grid;
    }

    @Test
    @DisplayName("Преобразование лабиринта в строку")
    void mazeToString() {
        assertEquals(
            """
                ██  ██████
                ██      ██
                ██  ██  ██
                ██  ██  ██
                ██████  ██""",
            new ConsoleRenderer().render(new Maze(generateGridByString("""
                ██  ██████
                ██      ██
                ██  ██  ██
                ██  ██  ██
                ██████  ██""", 5, 5))).trim()
        );

        assertEquals(
            """
                ██  ████████████████████████████████████
                ██      ██          ██  ██      ██  ████
                ██  ██████████  ██████  ██  ██  ██  ████
                ██  ██          ██          ██      ████
                ██  ██████  ██████  ████████████████████
                ██  ██      ██      ██      ██  ██  ████
                ██  ██  ██  ██████  ██  ██████  ██  ████
                ██      ██      ██          ██  ██  ████
                ██  ██████  ██  ██████  ██████  ██  ████
                ██      ██  ██  ██      ██  ██  ██  ████
                ██████  ██████████████  ██  ██  ██  ████
                ██      ██      ██      ██          ████
                ██████  ██  ██  ██████  ██████  ████████
                ██          ██  ██              ██  ████
                ██  ██  ██████  ██████  ██  ██  ██  ████
                ██  ██  ██      ██      ██  ██      ████
                ██  ██████  ██████  ██  ████████████████
                ██  ██              ██              ████
                ██████████████████████████████████  ████
                ██████████████████████████████████  ████""",
            new ConsoleRenderer().render(new Maze(generateGridByString("""
                ██  ████████████████████████████████████
                ██      ██          ██  ██      ██  ████
                ██  ██████████  ██████  ██  ██  ██  ████
                ██  ██          ██          ██      ████
                ██  ██████  ██████  ████████████████████
                ██  ██      ██      ██      ██  ██  ████
                ██  ██  ██  ██████  ██  ██████  ██  ████
                ██      ██      ██          ██  ██  ████
                ██  ██████  ██  ██████  ██████  ██  ████
                ██      ██  ██  ██      ██  ██  ██  ████
                ██████  ██████████████  ██  ██  ██  ████
                ██      ██      ██      ██          ████
                ██████  ██  ██  ██████  ██████  ████████
                ██          ██  ██              ██  ████
                ██  ██  ██████  ██████  ██  ██  ██  ████
                ██  ██  ██      ██      ██  ██      ████
                ██  ██████  ██████  ██  ████████████████
                ██  ██              ██              ████
                ██████████████████████████████████  ████
                ██████████████████████████████████  ████""", 20, 20))).trim()
        );

        assertEquals(
            """
                ██  ██████████████████████████████████████████████████████████████████
                ██  ██  ██      ██          ██  ██  ██                          ██  ██
                ██  ██  ██  ██████████████  ██  ██  ██████  ██████  ██  ██████  ██  ██
                ██  ██          ██      ██      ██      ██  ██      ██  ██          ██
                ██  ██  ██  ██████  ██  ██  ██  ██  ██████████  ██████████  ██████████
                ██      ██      ██  ██  ██  ██          ██      ██      ██          ██
                ██████████████  ██  ██████  ██  ██████████████  ██  ██████████  ██  ██
                ██      ██          ██      ██  ██  ██  ██          ██      ██  ██  ██
                ██████  ██  ██  ██████  ██  ██  ██  ██  ██████████  ██████  ██  ██████
                ██  ██      ██  ██      ██  ██  ██      ██  ██  ██  ██      ██  ██  ██
                ██  ██  ██  ██████  ██████████  ██████  ██  ██  ██  ██  ██████████  ██
                ██      ██          ██      ██  ██  ██      ██              ██  ██  ██
                ██████  ██████  ██████  ██  ██  ██  ██  ██  ██  ██████████████  ██  ██
                ██      ██          ██  ██      ██  ██  ██      ██              ██  ██
                ██████████████████  ██████████  ██  ██  ██████  ██  ██  ██  ██  ██  ██
                ██              ██  ██  ██      ██  ██  ██          ██  ██  ██      ██
                ██  ██  ██  ██████████  ██████  ██  ██  ██  ██  ██████████████  ██  ██
                ██  ██  ██              ██      ██      ██  ██  ██          ██  ██  ██
                ██  ██████  ██████████  ██  ██  ██  ██████████  ██████  ██████████████
                ██      ██  ██          ██  ██      ██  ██      ██              ██  ██
                ██████████████████  ██████████  ██  ██  ██  ██  ██████████████  ██  ██
                ██  ██                          ██      ██  ██  ██  ██      ██      ██
                ██  ██████  ██████████████████  ██████████████  ██  ██  ██████  ██  ██
                ██              ██                          ██                  ██  ██
                ██████  ██  ██  ██████████  ██████  ██  ██████  ██████  ██████  ██████
                ██      ██  ██  ██  ██  ██      ██  ██      ██  ██      ██  ██      ██
                ██████████  ██  ██  ██  ██████  ██████████████████████████  ██████████
                ██      ██  ██      ██          ██          ██                      ██
                ██████  ██████  ██████  ██████████████  ██████████  ██████  ██████████
                ██          ██      ██          ██          ██  ██  ██              ██
                ██  ██  ██  ██  ██████████  ██  ██  ██  ██████  ██████  ██  ██████  ██
                ██  ██  ██  ██      ██  ██  ██      ██  ██      ██  ██  ██      ██  ██
                ██  ██████  ██  ██  ██  ██  ██████  ██████  ██████  ██████  ██████  ██
                ██  ██          ██  ██      ██                                  ██  ██
                ██████████████████████████████████████████████████████████████████  ██""",
            new ConsoleRenderer().render(new Maze(generateGridByString("""
                ██  ██████████████████████████████████████████████████████████████████
                ██  ██  ██      ██          ██  ██  ██                          ██  ██
                ██  ██  ██  ██████████████  ██  ██  ██████  ██████  ██  ██████  ██  ██
                ██  ██          ██      ██      ██      ██  ██      ██  ██          ██
                ██  ██  ██  ██████  ██  ██  ██  ██  ██████████  ██████████  ██████████
                ██      ██      ██  ██  ██  ██          ██      ██      ██          ██
                ██████████████  ██  ██████  ██  ██████████████  ██  ██████████  ██  ██
                ██      ██          ██      ██  ██  ██  ██          ██      ██  ██  ██
                ██████  ██  ██  ██████  ██  ██  ██  ██  ██████████  ██████  ██  ██████
                ██  ██      ██  ██      ██  ██  ██      ██  ██  ██  ██      ██  ██  ██
                ██  ██  ██  ██████  ██████████  ██████  ██  ██  ██  ██  ██████████  ██
                ██      ██          ██      ██  ██  ██      ██              ██  ██  ██
                ██████  ██████  ██████  ██  ██  ██  ██  ██  ██  ██████████████  ██  ██
                ██      ██          ██  ██      ██  ██  ██      ██              ██  ██
                ██████████████████  ██████████  ██  ██  ██████  ██  ██  ██  ██  ██  ██
                ██              ██  ██  ██      ██  ██  ██          ██  ██  ██      ██
                ██  ██  ██  ██████████  ██████  ██  ██  ██  ██  ██████████████  ██  ██
                ██  ██  ██              ██      ██      ██  ██  ██          ██  ██  ██
                ██  ██████  ██████████  ██  ██  ██  ██████████  ██████  ██████████████
                ██      ██  ██          ██  ██      ██  ██      ██              ██  ██
                ██████████████████  ██████████  ██  ██  ██  ██  ██████████████  ██  ██
                ██  ██                          ██      ██  ██  ██  ██      ██      ██
                ██  ██████  ██████████████████  ██████████████  ██  ██  ██████  ██  ██
                ██              ██                          ██                  ██  ██
                ██████  ██  ██  ██████████  ██████  ██  ██████  ██████  ██████  ██████
                ██      ██  ██  ██  ██  ██      ██  ██      ██  ██      ██  ██      ██
                ██████████  ██  ██  ██  ██████  ██████████████████████████  ██████████
                ██      ██  ██      ██          ██          ██                      ██
                ██████  ██████  ██████  ██████████████  ██████████  ██████  ██████████
                ██          ██      ██          ██          ██  ██  ██              ██
                ██  ██  ██  ██  ██████████  ██  ██  ██  ██████  ██████  ██  ██████  ██
                ██  ██  ██  ██      ██  ██  ██      ██  ██      ██  ██  ██      ██  ██
                ██  ██████  ██  ██  ██  ██  ██████  ██████  ██████  ██████  ██████  ██
                ██  ██          ██  ██      ██                                  ██  ██
                ██████████████████████████████████████████████████████████████████  ██""", 35, 35))).trim()
        );
    }

    @Test
    @DisplayName("Преобразование лабиринта с решением в строку")
    void mazeWithPathToString() {
        assertEquals(
            """
                ██▓▓████████████████████████████████████
                ██▓▓██  ██          ██              ████
                ██▓▓██  ██  ██  ██████  ██████████  ████
                ██▓▓██      ██  ██      ██▓▓▓▓▓▓██  ████
                ██▓▓██████████  ██  ██████▓▓██▓▓████████
                ██▓▓██              ██▓▓▓▓▓▓██▓▓▓▓▓▓████
                ██▓▓██  ██████████████▓▓██████████▓▓████
                ██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██        ▓▓████
                ██████████████████████████████████▓▓████
                ██████████████████████████████████  ████""",
            new ConsoleRenderer().render(new Maze(generateGridByString("""
                ██  ████████████████████████████████████
                ██  ██  ██          ██              ████
                ██  ██  ██  ██  ██████  ██████████  ████
                ██  ██      ██  ██      ██      ██  ████
                ██  ██████████  ██  ██████  ██  ████████
                ██  ██              ██      ██      ████
                ██  ██  ██████████████  ██████████  ████
                ██                      ██          ████
                ██████████████████████████████████  ████
                ██████████████████████████████████  ████""", 10, 20)), List.of(
                new Cell(0, 1, Cell.Type.ESCAPE), new Cell(1, 1, Cell.Type.ESCAPE),
                new Cell(2, 1, Cell.Type.ESCAPE), new Cell(3, 1, Cell.Type.ESCAPE),
                new Cell(4, 1, Cell.Type.ESCAPE), new Cell(5, 1, Cell.Type.ESCAPE),
                new Cell(6, 1, Cell.Type.ESCAPE), new Cell(7, 1, Cell.Type.ESCAPE),
                new Cell(7, 2, Cell.Type.ESCAPE), new Cell(7, 3, Cell.Type.ESCAPE),
                new Cell(7, 4, Cell.Type.ESCAPE), new Cell(7, 5, Cell.Type.ESCAPE),
                new Cell(7, 6, Cell.Type.ESCAPE), new Cell(7, 7, Cell.Type.ESCAPE),
                new Cell(7, 8, Cell.Type.ESCAPE), new Cell(7, 9, Cell.Type.ESCAPE),
                new Cell(7, 10, Cell.Type.ESCAPE), new Cell(7, 11, Cell.Type.ESCAPE),
                new Cell(6, 11, Cell.Type.ESCAPE), new Cell(5, 11, Cell.Type.ESCAPE),
                new Cell(5, 12, Cell.Type.ESCAPE), new Cell(5, 13, Cell.Type.ESCAPE),
                new Cell(4, 13, Cell.Type.ESCAPE), new Cell(3, 13, Cell.Type.ESCAPE),
                new Cell(3, 14, Cell.Type.ESCAPE), new Cell(3, 15, Cell.Type.ESCAPE),
                new Cell(4, 15, Cell.Type.ESCAPE), new Cell(5, 15, Cell.Type.ESCAPE),
                new Cell(5, 16, Cell.Type.ESCAPE), new Cell(5, 17, Cell.Type.ESCAPE),
                new Cell(6, 17, Cell.Type.ESCAPE), new Cell(7, 17, Cell.Type.ESCAPE),
                new Cell(8, 17, Cell.Type.ESCAPE)
            )).trim()
        );
    }
}
