package edu.project2.model;

import java.util.Objects;

public class Node {
    private final int row;
    private final int column;
    private final boolean isWall;
    private Node parent;

    public Node(int row, int column, boolean isWall) {
        this.row = row;
        this.column = column;
        this.isWall = isWall;
        parent = this;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isWall() {
        return isWall;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return row == node.row
            && column == node.column
            && isWall == node.isWall;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, isWall);
    }
}
