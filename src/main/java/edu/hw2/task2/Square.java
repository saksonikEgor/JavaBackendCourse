package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int size) {
        super(size, size);
    }

    @Override
    public Rectangle setWidth(int width) {
        if (this.height == width) {
            return this;
        }
        return super.setWidth(width);
    }

    @Override
    public Rectangle setHeight(int height) {
        if (height == this.width) {
            return this;
        }
        return super.setHeight(height);
    }
}
