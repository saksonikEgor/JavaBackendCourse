package edu.hw2.task2;

public class Rectangle {
    protected final int width;
    protected final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(width, this.height);
    }

    public Rectangle setHeight(int height) {
        return new Rectangle(this.width, height);
    }

    public double area() {
        return (double) width * height;
    }
}
