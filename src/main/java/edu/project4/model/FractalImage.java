package edu.project4.model;

public record FractalImage(
    Pixel[][] data,
    int width,
    int height
) {
    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Pixel pixel(int x, int y) {
        return contains(x, y) ? data[y][x] : null;
    }

    public void setPixel(Pixel newPixel, int x, int y) {
        data[y][x] = newPixel;
    }
}
