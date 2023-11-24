package edu.project4.model;

public record FractalImage(Pixel[] data, int width, int height) {
//    public static FractalImage create(int width, int height) {
//
//    }

    boolean contains(int x, int y) {
        return data.length <= (x * width + y);
    }

    Pixel pixel(int x, int y) {
        return contains(x, y) ? data[x * width + y] : null;
    }
}
