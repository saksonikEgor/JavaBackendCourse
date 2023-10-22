package edu.porject2.solving;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.properties.ApplicationProperties;
import edu.project2.solving.aStar.AStarSolver;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AStarSolvingTest {
    private static String deleteEverySecondCharacter(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 1; i < sb.length(); i++) {
            sb.deleteCharAt(i);
        }
        return sb.toString();
    }

    private static Cell[][] generateGridByString(String maze, int height, int width) {
        String[] splits = maze.split("\n");
        char passageChar = ApplicationProperties.PASSAGE_STRING.charAt(0);
        char wallChar = ApplicationProperties.WALL_STRING.charAt(0);
        Cell[][] grid = new Cell[height][width];

        for (int i = 0; i < splits.length; i++) {
            String row = deleteEverySecondCharacter(splits[i]);

            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == passageChar) {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else if (row.charAt(j) == wallChar) {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.ESCAPE);
                }
            }
        }
        return grid;
    }

    @Test
    @DisplayName("Нахождение пути в квадратном лабиринте алгоритмом A*")
    void aStarSquareSolving() {
        Maze maze;

        maze = new Maze(generateGridByString("""
            ██  ██████
            ██  ██  ██
            ██  ██  ██
            ██      ██
            ██████  ██""", 5, 5));
        assertThat(extractPathByString("""
            ██▓▓██████
            ██▓▓██  ██
            ██▓▓██  ██
            ██▓▓▓▓▓▓██
            ██████▓▓██""", 5, 5))
            .hasSameElementsAs(new AStarSolver().solve(maze, maze.getEntrance(), maze.getExit()));

        maze = new Maze(generateGridByString("""
            ██  ████████████████████████████████████
            ██                  ██      ██      ████
            ██  ██████  ██████████  ██  ██  ██  ████
            ██      ██      ██      ██  ██  ██  ████
            ██  ██  ██████  ██  ██████████  ██  ████
            ██  ██      ██  ██              ██  ████
            ██  ██████  ██  ██████████████████  ████
            ██      ██  ██      ██  ██          ████
            ██████  ██████  ██  ██  ██  ████████████
            ██  ██  ██      ██  ██  ██      ██  ████
            ██  ██  ██  ██  ██  ██  ██████  ██  ████
            ██  ██  ██  ██  ██  ██              ████
            ██  ██  ██  ██████  ██  ████████████████
            ██      ██  ██      ██  ██          ████
            ██████  ██  ██  ██████  ██████████  ████
            ██  ██  ██  ██  ██      ██      ██  ████
            ██  ██  ██  ██████  ██████  ██  ██  ████
            ██      ██                  ██      ████
            ██████████████████████████████████  ████
            ██████████████████████████████████  ████""", 20, 20));
        assertThat(extractPathByString("""
            ██▓▓████████████████████████████████████
            ██▓▓▓▓▓▓▓▓▓▓        ██      ██      ████
            ██  ██████▓▓██████████  ██  ██  ██  ████
            ██      ██▓▓▓▓▓▓██      ██  ██  ██  ████
            ██  ██  ██████▓▓██  ██████████  ██  ████
            ██  ██      ██▓▓██              ██  ████
            ██  ██████  ██▓▓██████████████████  ████
            ██      ██  ██▓▓    ██  ██          ████
            ██████  ██████▓▓██  ██  ██  ████████████
            ██  ██  ██▓▓▓▓▓▓██  ██  ██      ██  ████
            ██  ██  ██▓▓██  ██  ██  ██████  ██  ████
            ██  ██  ██▓▓██  ██  ██              ████
            ██  ██  ██▓▓██████  ██  ████████████████
            ██      ██▓▓██      ██  ██          ████
            ██████  ██▓▓██  ██████  ██████████  ████
            ██  ██  ██▓▓██  ██      ██▓▓▓▓▓▓██  ████
            ██  ██  ██▓▓██████  ██████▓▓██▓▓██  ████
            ██      ██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▓▓▓▓▓▓████
            ██████████████████████████████████▓▓████
            ██████████████████████████████████  ████""", 20, 20))
            .hasSameElementsAs(new AStarSolver().solve(maze, maze.getEntrance(), maze.getExit()));

        maze = new Maze(generateGridByString("""
            ██  ██████████████████████████████████████████████████████████████████
            ██  ██          ██      ██                              ██      ██  ██
            ██  ██████████  ██  ██████  ██████████████████  ██  ██  ██  ██  ██  ██
            ██          ██      ██      ██                  ██  ██  ██  ██      ██
            ██  ██████  ██  ██████  ██████  ██████████████  ██  ██████  ██████████
            ██      ██                  ██      ██          ██          ██      ██
            ██  ██  ██████████████████████████  ██  ██████████████  ██████  ██  ██
            ██  ██  ██                  ██  ██  ██  ██          ██  ██      ██  ██
            ██  ██  ██  ██████████  ██  ██  ██  ██  ██████████  ██  ██  ██████  ██
            ██  ██      ██      ██  ██      ██  ██  ██      ██  ██  ██      ██  ██
            ██  ██████████████  ██  ██████████  ██  ██  ██  ██  ██  ██████████  ██
            ██  ██  ██          ██      ██      ██      ██  ██  ██  ██          ██
            ██  ██  ██  ██████████  ██  ██  ██  ██████████  ██  ██████  ██████████
            ██      ██              ██  ██  ██  ██      ██      ██      ██      ██
            ██████████████  ██  ██████  ██  ██  ██  ██  ██████████  ██████  ██  ██
            ██          ██  ██  ██      ██  ██      ██  ██      ██      ██  ██  ██
            ██  ██████  ██████  ██  ██  ██  ██████████  ██  ██  ██████  ██  ██████
            ██  ██  ██          ██  ██  ██          ██  ██  ██          ██      ██
            ██  ██  ██  ██████████  ██  ██████████  ██  ██████  ██████████  ██  ██
            ██      ██  ██          ██  ██          ██      ██              ██  ██
            ██████████████  ██  ██████  ██  ██████████████  ██████  ██████████████
            ██      ██      ██      ██  ██      ██      ██                      ██
            ██  ██  ██  ██████████  ██  ██████████  ██  ██████████████████████  ██
            ██  ██      ██          ██      ██      ██              ██          ██
            ██  ██████████  ██████████████████  ██████████████████  ██████████████
            ██      ██                          ██          ██      ██      ██  ██
            ██████  ██████████  ██████████████████████████  ██  ██  ██  ██  ██  ██
            ██  ██          ██          ██              ██  ██  ██  ██  ██      ██
            ██  ██████  ██  ██████████  ██  ██████  ██  ██  ██  ██  ██  ██████  ██
            ██      ██  ██          ██  ██  ██      ██  ██  ██  ██  ██      ██  ██
            ██████  ██████████  ██  ██  ██  ██  ██████████  ██  ██  ██████████  ██
            ██  ██          ██  ██  ██      ██      ██      ██  ██  ██          ██
            ██  ██████████  ██  ██  ██████████████  ██  ██████  ██████  ██████████
            ██                  ██              ██      ██                      ██
            ██████████████████████████████████████████████████████████████████  ██""", 35, 35));
        assertThat(extractPathByString("""
            ██▓▓██████████████████████████████████████████████████████████████████
            ██▓▓██          ██      ██                              ██      ██  ██
            ██▓▓██████████  ██  ██████  ██████████████████  ██  ██  ██  ██  ██  ██
            ██▓▓        ██      ██      ██                  ██  ██  ██  ██      ██
            ██▓▓██████  ██  ██████  ██████  ██████████████  ██  ██████  ██████████
            ██▓▓▓▓▓▓██                  ██      ██          ██          ██      ██
            ██  ██▓▓██████████████████████████  ██  ██████████████  ██████  ██  ██
            ██  ██▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓    ██  ██  ██  ██          ██  ██      ██  ██
            ██  ██▓▓██▓▓██████████▓▓██  ██  ██  ██  ██████████  ██  ██  ██████  ██
            ██  ██▓▓▓▓▓▓██      ██▓▓██      ██  ██  ██      ██  ██  ██      ██  ██
            ██  ██████████████  ██▓▓██████████  ██  ██  ██  ██  ██  ██████████  ██
            ██  ██  ██          ██▓▓▓▓▓▓██      ██      ██  ██  ██  ██          ██
            ██  ██  ██  ██████████  ██▓▓██  ██  ██████████  ██  ██████  ██████████
            ██      ██              ██▓▓██  ██  ██      ██      ██      ██      ██
            ██████████████  ██  ██████▓▓██  ██  ██  ██  ██████████  ██████  ██  ██
            ██          ██  ██  ██▓▓▓▓▓▓██  ██      ██  ██      ██      ██  ██  ██
            ██  ██████  ██████  ██▓▓██  ██  ██████████  ██  ██  ██████  ██  ██████
            ██  ██  ██          ██▓▓██  ██          ██  ██  ██          ██      ██
            ██  ██  ██  ██████████▓▓██  ██████████  ██  ██████  ██████████  ██  ██
            ██      ██  ██    ▓▓▓▓▓▓██  ██          ██      ██              ██  ██
            ██████████████  ██▓▓██████  ██  ██████████████  ██████  ██████████████
            ██      ██      ██▓▓▓▓▓▓██  ██      ██▓▓▓▓▓▓██                      ██
            ██  ██  ██  ██████████▓▓██  ██████████▓▓██▓▓██████████████████████  ██
            ██  ██      ██▓▓▓▓▓▓▓▓▓▓██      ██▓▓▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓██          ██
            ██  ██████████▓▓██████████████████▓▓██████████████████▓▓██████████████
            ██      ██    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██          ██▓▓▓▓▓▓██      ██  ██
            ██████  ██████████  ██████████████████████████  ██▓▓██  ██  ██  ██  ██
            ██  ██          ██          ██              ██  ██▓▓██  ██  ██      ██
            ██  ██████  ██  ██████████  ██  ██████  ██  ██  ██▓▓██  ██  ██████  ██
            ██      ██  ██          ██  ██  ██      ██  ██  ██▓▓██  ██      ██  ██
            ██████  ██████████  ██  ██  ██  ██  ██████████  ██▓▓██  ██████████  ██
            ██  ██          ██  ██  ██      ██      ██      ██▓▓██  ██          ██
            ██  ██████████  ██  ██  ██████████████  ██  ██████▓▓██████  ██████████
            ██                  ██              ██      ██    ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██
            ██████████████████████████████████████████████████████████████████▓▓██""", 35, 35))
            .hasSameElementsAs(new AStarSolver().solve(maze, maze.getEntrance(), maze.getExit()));
    }

    @Test
    @DisplayName("Нахождение пути в прямоугольном лабиринте алгоритмом A*")
    void aStarRectangleSolving() {
        Maze maze;

        maze = new Maze(generateGridByString("""
            ██  ██████
            ██      ██
            ██████  ██
            ██████  ██""", 4, 5));
        assertThat(extractPathByString("""
            ██▓▓██████
            ██▓▓▓▓▓▓██
            ██████▓▓██
            ██████  ██""", 4, 5))
            .hasSameElementsAs(new AStarSolver().solve(maze, maze.getEntrance(), maze.getExit()));

        maze = new Maze(generateGridByString("""
            ██  ████
            ██  ████
            ██  ████
            ██  ████
            ██  ████""", 5, 4));
        assertThat(extractPathByString("""
            ██▓▓████
            ██▓▓████
            ██▓▓████
            ██▓▓████
            ██▓▓████""", 5, 4))
            .hasSameElementsAs(new AStarSolver().solve(maze, maze.getEntrance(), maze.getExit()));

        maze = new Maze(generateGridByString("""
            ██  ████████████████████████████████████
            ██  ██  ██          ██              ████
            ██  ██  ██  ██  ██████  ██████████  ████
            ██  ██      ██  ██      ██      ██  ████
            ██  ██████████  ██  ██████  ██  ████████
            ██  ██              ██      ██      ████
            ██  ██  ██████████████  ██████████  ████
            ██                      ██          ████
            ██████████████████████████████████  ████
            ██████████████████████████████████  ████""", 10, 20));
        assertThat(extractPathByString("""
            ██▓▓████████████████████████████████████
            ██▓▓██  ██          ██              ████
            ██▓▓██  ██  ██  ██████  ██████████  ████
            ██▓▓██      ██  ██      ██▓▓▓▓▓▓██  ████
            ██▓▓██████████  ██  ██████▓▓██▓▓████████
            ██▓▓██              ██▓▓▓▓▓▓██▓▓▓▓▓▓████
            ██▓▓██  ██████████████▓▓██████████▓▓████
            ██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██        ▓▓████
            ██████████████████████████████████▓▓████
            ██████████████████████████████████  ████""", 10, 20))
            .hasSameElementsAs(new AStarSolver().solve(maze, maze.getEntrance(), maze.getExit()));

        maze = new Maze(generateGridByString("""
                ██  ██████████████████████████████████████████████████████████████████████████████████████
                ██  ██      ██      ██                          ██      ██          ██      ██      ██  ██
                ██  ██████  ██████  ██  ██  ██████████████████  ██  ██  ██  ██████  ██████  ██████  ██  ██
                ██                  ██  ██          ██          ██  ██  ██  ██  ██      ██  ██      ██  ██
                ██  ██████████████████  ██████████  ██████████████  ██████  ██  ██████  ██  ██  ██████  ██
                ██  ██              ██  ██      ██      ██              ██  ██  ██      ██  ██  ██  ██  ██
                ██  ██  ██████████  ██  ██████  ██  ██  ██  ██████████  ██  ██  ██  ██████  ██  ██  ██  ██
                ██  ██          ██              ██  ██          ██      ██      ██          ██          ██
                ██  ██████  ██████████████████████  ██████████  ██  ██████████████  ██████████████████  ██
                ██          ██      ██  ██      ██      ██      ██                  ██  ██      ██  ██  ██
                ██  ██  ██  ██  ██  ██  ██  ██████████  ██  ██  ██  ██████████████  ██  ██  ██  ██  ██  ██
                ██  ██  ██  ██  ██      ██  ██      ██  ██  ██  ██  ██          ██      ██  ██          ██
                ██  ██  ██████  ██████  ██  ██  ██  ██  ██  ██  ██  ██████████  ██████  ██████████████  ██
                ██  ██      ██      ██  ██  ██  ██      ██  ██  ██          ██      ██      ██      ██  ██
                ██  ██████  ██████  ██  ██  ██  ██████████  ██████████████  ██████  ██████  ██  ██  ██  ██
                ██      ██      ██  ██  ██      ██      ██              ██  ██      ██  ██  ██  ██  ██  ██
                ██████  ██████  ██████  ██████████  ██████████  ██████  ██  ██  ██████  ██  ██  ██  ██  ██
                ██          ██                  ██              ██      ██              ██      ██      ██
                ██████████████████████████████████████████████████████████████████████████████████████  ██
                ██████████████████████████████████████████████████████████████████████████████████████  ██""",
            20, 45
        ));
        assertThat(extractPathByString("""
                ██▓▓██████████████████████████████████████████████████████████████████████████████████████
                ██▓▓██      ██      ██▓▓▓▓▓▓                    ██      ██          ██      ██      ██  ██
                ██▓▓██████  ██████  ██▓▓██▓▓██████████████████  ██  ██  ██  ██████  ██████  ██████  ██  ██
                ██▓▓                ██▓▓██▓▓▓▓▓▓▓▓▓▓██          ██  ██  ██  ██  ██      ██  ██      ██  ██
                ██▓▓██████████████████▓▓██████████▓▓██████████████  ██████  ██  ██████  ██  ██  ██████  ██
                ██▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▓▓██      ██▓▓▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓██  ██  ██      ██  ██  ██  ██  ██
                ██▓▓██▓▓██████████▓▓██▓▓██████  ██  ██▓▓██▓▓██████████▓▓██  ██  ██  ██████  ██  ██  ██  ██
                ██▓▓██▓▓▓▓▓▓    ██▓▓▓▓▓▓        ██  ██▓▓▓▓▓▓    ██▓▓▓▓▓▓██      ██          ██          ██
                ██▓▓██████▓▓██████████████████████  ██████████  ██▓▓██████████████  ██████████████████  ██
                ██▓▓▓▓▓▓▓▓▓▓██      ██  ██      ██      ██      ██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██  ██      ██  ██  ██
                ██  ██  ██  ██  ██  ██  ██  ██████████  ██  ██  ██  ██████████████▓▓██  ██  ██  ██  ██  ██
                ██  ██  ██  ██  ██      ██  ██      ██  ██  ██  ██  ██          ██▓▓▓▓▓▓██  ██          ██
                ██  ██  ██████  ██████  ██  ██  ██  ██  ██  ██  ██  ██████████  ██████▓▓██████████████  ██
                ██  ██      ██      ██  ██  ██  ██      ██  ██  ██          ██      ██▓▓▓▓▓▓██▓▓▓▓▓▓██  ██
                ██  ██████  ██████  ██  ██  ██  ██████████  ██████████████  ██████  ██████▓▓██▓▓██▓▓██  ██
                ██      ██      ██  ██  ██      ██      ██              ██  ██      ██  ██▓▓██▓▓██▓▓██  ██
                ██████  ██████  ██████  ██████████  ██████████  ██████  ██  ██  ██████  ██▓▓██▓▓██▓▓██  ██
                ██          ██                  ██              ██      ██              ██▓▓▓▓▓▓██▓▓▓▓▓▓██
                ██████████████████████████████████████████████████████████████████████████████████████▓▓██
                ██████████████████████████████████████████████████████████████████████████████████████  ██""",
            20, 45
        ))
            .hasSameElementsAs(new AStarSolver().solve(maze, maze.getEntrance(), maze.getExit()));
    }

    public List<Cell> extractPathByString(String maze, int height, int width) {
        Cell[][] grid = generateGridByString(maze, height, width);
        List<Cell> path = new ArrayList<>();

        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.isEscape()) {
                    path.add(cell);
                }
            }
        }
        return path;
    }
}
