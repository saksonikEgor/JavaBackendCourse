package edu.project4.transformation;

import edu.project4.model.Point;

public class DiskTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            (1 / Math.PI) * Math.atan(point.y() / point.x())
                * Math.sin(
                Math.PI * Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2))
            ),
            (1 / Math.PI) * Math.atan(point.y() / point.x())
                * Math.cos(
                Math.PI * Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2))
            )
        );
    }
}
