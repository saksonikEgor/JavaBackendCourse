package edu.project4.transformations;

import edu.project4.model.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            point.x() / (Math.pow(point.x(), 2) + Math.pow(point.y(), 2)),
            point.y() / (Math.pow(point.x(), 2) + Math.pow(point.y(), 2))
        );
    }
}
