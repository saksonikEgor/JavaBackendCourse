package edu.project2.model;

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
//        fillGrid();
    }

    public Maze(int size) {
        this(size, size);
    }
}
