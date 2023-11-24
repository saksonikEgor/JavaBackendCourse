package edu.project4.util;

import edu.project4.model.Point;
import edu.project4.model.Rectangle;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static final Random RANDOM = new Random();

    private RandomUtils() {
    }

    public static Point nextPointInRectangle(Rectangle rectangle) {
        return new Point(
            RANDOM.nextDouble(rectangle.width()) + rectangle.x(),
            RANDOM.nextDouble(rectangle.height()) + rectangle.y()
        );
    }

    public static Transformation nextTransformation(List<Transformation> transformations) {
        return transformations.get(RANDOM.nextInt(transformations.size()));
    }
}
