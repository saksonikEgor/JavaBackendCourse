package edu.project4.util;

import edu.project4.model.Point;
import edu.project4.model.Variation;

public class RenderUtils {
    private RenderUtils() {
    }

    public static Point transform(Point point, Variation variation) {
        return variation.func()
            .apply(variation.shift().apply(point));
    }

    public static Point rotate(Point point, double theta) {
        return new Point(
            point.x() * Math.cos(theta) - point.y() * Math.sin(theta),
            point.x() * Math.sin(theta) + point.y() * Math.cos(theta)
        );
    }
}
