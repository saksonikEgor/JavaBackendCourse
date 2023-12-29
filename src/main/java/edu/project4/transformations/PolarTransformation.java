package edu.project4.transformations;

import edu.project4.model.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            Math.atan(point.y() / point.x()) / Math.PI,
            Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2)) - 1
        );
    }
}
