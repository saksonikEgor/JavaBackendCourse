package edu.project2.model;

import edu.project2.generation.kruskal.KruskalGenerator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import static edu.project2.model.Cell.Type.PASSAGE;
import static edu.project2.model.Cell.Type.WALL;

public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;
    private Cell entrance;
    private Cell exit;

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

    private void fillGrid() {
        fillAlternately();
        fillGaps();
        makeEntranceAndExit();
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
        setEntrance(grid[0][1]);

        putCell(height - 1, getExitColumn(), PASSAGE);
        setExit(grid[height - 1][getExitColumn()]);

        if (height % 2 == 0) {
            putCell(height - 2, getExitColumn(), PASSAGE);
            setExit(grid[height - 2][getExitColumn()]);
        }
    }

    private void setEntrance(Cell entrance) {
        this.entrance = entrance;
    }

    private void setExit(Cell exit) {
        this.exit = exit;
    }

    public Cell getEntrance() {
        return entrance;
    }

    public Cell getExit() {
        return exit;
    }

    public void putCells(List<Cell> passages) {
        passages.forEach(cell -> grid[cell.row()][cell.column()] = cell);
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
