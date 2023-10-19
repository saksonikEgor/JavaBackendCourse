package edu.project2.model;

public record Cell(int row, int column, Type type) {
    public enum Type {
        WALL,
        PASSAGE,
        ESCAPE
    }

    public boolean isWall() {
        return type == Type.WALL;
    }

    public boolean isEscape() {
        return type == Type.ESCAPE;
    }
}
