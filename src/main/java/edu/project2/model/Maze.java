package edu.project2.model;

import edu.project2.generation.kruskal.KruskalGenerator;
import java.util.Random;
import java.util.function.Consumer;

import static edu.project2.model.Cell.Type.PASSAGE;
import static edu.project2.model.Cell.Type.WALL;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width) {
        if (height < 3 || width < 3) {
            throw new IllegalArgumentException(
                "Both the height and the width " +
                    "of the maze must be at least 3");
        }
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
        fillGrid();
    }

    public Maze(int size) {
        this(size, size);
    }

    private void fillGrid() {
        fillAlternately();
        fillGaps();
        makeEntranceAndExit();
        generatePassages();
    }

    private void putCell(int row, int column, Cell.Type type) {
        grid[row][column] = new Cell(row, column, type);
    }

    private void fillAlternately() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i & 1) == 0 || (j & 1) == 0) {
                    putCell(i, j, WALL);
                } else {
                    putCell(i, j, PASSAGE);
                }
            }
        }
    }

    private void fillGaps() {
        if (height % 2 == 0) {
            wallLastRow();
        }
        if (width % 2 == 0) {
            wallLastColumn();
        }
    }

    private void wallLastColumn() {
        for (int i = 0; i < height; i++) {
            putCell(i, width - 1, WALL);
        }
    }

    private void wallLastRow() {
        for (int i = 0; i < width; i++) {
            putCell(height - 1, i, WALL);
        }
    }

    private int getExitColumn() {
        return width - 3 + width % 2;
    }

    private void makeEntranceAndExit() {
        putCell(0, 1, PASSAGE);
        putCell(height - 1, getExitColumn(), PASSAGE);
        if (height % 2 == 0) {
            putCell(height - 2, getExitColumn(), PASSAGE);
        }
    }

    private void generatePassages() {
        new KruskalGenerator(new Random())
            .generate(height, width)
            .forEach(putCell());
    }

    private Consumer<Cell> putCell() {
        return cell -> grid[cell.row()][cell.column()] = cell;
    }

    private String toString(boolean showEscape) {
        var sb = new StringBuilder();
        for (var row : grid) {
            for (var cell : row) {
                if (cell.isWall()) {
                    sb.append("██");
                } else if (showEscape && cell.isEscape()) {
                    sb.append("XX");
                } else {
                    sb.append("  ");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
