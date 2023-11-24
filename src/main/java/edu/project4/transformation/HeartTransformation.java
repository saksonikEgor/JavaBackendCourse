package edu.project4.transformation;

import edu.project4.model.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2))
                * Math.sin(
                Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2))
                    * Math.atan(point.y()) / point.x()
            ),
            -Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2))
                * Math.cos(
                Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2))
                    * Math.atan(point.y()) / point.x()
            )
        );
    }
}
