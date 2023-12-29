package edu.project4.model;

import edu.project4.transformations.Transformation;

public record Shift(
    double a,
    double b,
    double c,
    double d,
    double e,
    double f
) implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            a * point.x() + b * point.y() + c,
            d * point.x() + e * point.y() + f
        );
    }
}
