package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int size) {
        super(size, size);
    }

    @Override
    public Rectangle setWidth(int width) {
        if (getHeight() == width) {
            return this;
        }

        return new Rectangle(width, getHeight());
    }

    @Override
    public Rectangle setHeight(int height) {
        if (height == getWidth()) {
            return this;
        }

        return new Rectangle(getWidth(), height);
    }
}
