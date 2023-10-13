package edu.hw2.task2;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle() {
        width = 0;
        height = 0;
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle setWidth(int width) {
        this.width = width;
        return this;
    }

    public Rectangle setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double area() {
        return width * height;
    }
}
