package edu.porject2.generation;

import edu.project2.generation.kruskal.KruskalGenerator;
import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.properties.ApplicationProperties;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KruskalGenerationTest {
    @Test
    @DisplayName("Генерация валидных квадратных лабиринтов алгоритмом Kruskal")
    void kruskalSquareGeneration() {
        assertEquals(
            new Maze(generateGridByString("""
                ██  ██████
                ██      ██
                ██  ██  ██
                ██  ██  ██
                ██████  ██""", 5, 5)),
            new KruskalGenerator(new Random(5)).generate(5, 5)
        );

        assertEquals(
            new Maze(generateGridByString("""
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
                ██████████████████████████████████  ████""", 20, 20)),
            new KruskalGenerator(new Random(5)).generate(20, 20)
        );

        assertEquals(
            new Maze(generateGridByString("""
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
                ██████████████████████████████████████████████████████████████████  ██""", 35, 35)),
            new KruskalGenerator(new Random(5)).generate(35, 35)
        );
    }

    @Test
    @DisplayName("Генерация валидных прямоугольных лабиринтов алгоритмом Kruskal")
    void kruskalRectangleGeneration() {
        assertEquals(
            new Maze(generateGridByString("""
                ██  ██████
                ██      ██
                ██████  ██
                ██████  ██""", 4, 5)),
            new KruskalGenerator(new Random(5)).generate(4, 5)
        );

        assertEquals(
            new Maze(generateGridByString("""
                ██  ████
                ██  ████
                ██  ████
                ██  ████
                ██  ████""", 5, 4)),
            new KruskalGenerator(new Random(5)).generate(5, 4)
        );

        assertEquals(
            new Maze(generateGridByString("""
                ██  ████████████████████████████████████
                ██  ██          ██      ██          ████
                ██  ██████  ██  ██  ██████████  ██  ████
                ██          ██  ██      ██      ██  ████
                ██████  ██████████  ██████████████  ████
                ██          ██      ██      ██      ████
                ██████████  ██  ██  ██  ██████████  ████
                ██              ██                  ████
                ██████████████████████████████████  ████
                ██████████████████████████████████  ████""", 10, 20)),
            new KruskalGenerator(new Random(5)).generate(10, 20)
        );

        assertEquals(
            new Maze(generateGridByString("""
                    ██  ██████████████████████████████████████████████████████████████████████████████████████
                    ██      ██      ██  ██      ██          ██  ██                  ██      ██              ██
                    ██████  ██  ██  ██  ██  ██████  ██████████  ██████████  ██████  ██  ██████████████  ██████
                    ██          ██  ██              ██          ██      ██  ██      ██      ██  ██      ██  ██
                    ██████████  ██  ██████████████  ██████  ██  ██████  ██████████  ██████  ██  ██████  ██  ██
                    ██      ██  ██  ██                      ██  ██  ██          ██      ██          ██      ██
                    ██████  ██████  ██  ██  ██████  ██  ██████  ██  ██████████  ██  ██  ██  ██████████████  ██
                    ██  ██  ██          ██      ██  ██  ██  ██      ██  ██          ██  ██      ██          ██
                    ██  ██  ██████████  ██████████  ██████  ██  ██████  ██  ██████████  ██  ██████████  ██████
                    ██  ██                  ██      ██      ██      ██      ██              ██      ██      ██
                    ██  ██████████████  ██████████  ██  ██  ██████████  ██████  ██████  ██  ██████  ██████  ██
                    ██  ██  ██      ██  ██          ██  ██      ██          ██  ██  ██  ██      ██  ██      ██
                    ██  ██  ██  ██  ██  ██  ██  ██████████████  ██████████████  ██  ██  ██████  ██  ██  ██  ██
                    ██          ██  ██  ██  ██          ██  ██      ██      ██  ██  ██      ██  ██  ██  ██  ██
                    ██  ██████  ██████  ██████████████  ██  ██  ██  ██████  ██  ██  ██  ██████████  ██████  ██
                    ██  ██                  ██                  ██  ██              ██      ██              ██
                    ██  ██  ██████████  ██████  ██  ██████████████████  ██████████████████  ██  ██████  ██  ██
                    ██  ██      ██  ██      ██  ██              ██          ██  ██  ██  ██      ██  ██  ██  ██
                    ██  ██  ██  ██  ██████  ██████████████████  ██  ██████████  ██  ██  ██  ██████  ██  ██  ██
                    ██  ██  ██      ██  ██      ██          ██      ██          ██      ██  ██      ██  ██  ██
                    ██  ██████████████  ██  ██████  ██  ██  ██  ██  ██  ██████████  ██████████  ██████████  ██
                    ██          ██                  ██  ██  ██  ██                          ██          ██  ██
                    ██████████  ██████  ██████████████████████████████████  ██████████████  ██  ██  ██  ██████
                    ██          ██                  ██                              ██          ██  ██      ██
                    ██████████████████████████████████████████████████████████████████████████████████████  ██""",
                25, 45
            )),
            new KruskalGenerator(new Random(5)).generate(25, 45)
        );
    }

    @Test
    @DisplayName("Генерация невалидных лабиринтов алгоритмом Kruskal")
    void kruskalInvalidGeneration() {
        Assertions.assertEquals(
            ApplicationProperties.MAZE_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE,
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> new KruskalGenerator(new Random()).generate(2, 3), "IllegalArgumentException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            ApplicationProperties.MAZE_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE,
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> new KruskalGenerator(new Random()).generate(-2, 20), "IllegalArgumentException was expected"
            ).getMessage()
        );

        Assertions.assertEquals(
            ApplicationProperties.MAZE_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE,
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> new KruskalGenerator(new Random()).generate(12, 2), "IllegalArgumentException was expected"
            ).getMessage()
        );
    }

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
}
