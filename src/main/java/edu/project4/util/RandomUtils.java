package edu.project4.util;

import edu.project4.model.Color;
import edu.project4.model.Point;
import edu.project4.model.Shift;
import edu.project4.model.Variation;
import edu.project4.properties.ApplicationProperties;
import edu.project4.transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static edu.project4.properties.ApplicationProperties.BOUND;
import static edu.project4.properties.ApplicationProperties.COLOR_COMPONENT_UPPER_BOUND;
import static edu.project4.properties.ApplicationProperties.VARIATIONS_AMOUNT;
import static edu.project4.properties.ApplicationProperties.X_BOUND;
import static edu.project4.properties.ApplicationProperties.Y_BOUND;

public class RandomUtils {
    public static final Random RANDOM = new Random();

    private RandomUtils() {
    }

    public static Point getRandomPoint() {
        return new Point(
            RANDOM.nextDouble(-1 * X_BOUND, X_BOUND),
            RANDOM.nextDouble(-1 * Y_BOUND, Y_BOUND)
        );
    }

    public static List<Variation> getVariations(Transformation transformation) {
        List<Variation> variations = new ArrayList<>(VARIATIONS_AMOUNT);

        for (int i = 0; i < VARIATIONS_AMOUNT; i++) {
            Color color = getRandomColor();
            Shift shift = getRandomShift();

            variations.add(new Variation(transformation, shift, color.red(), color.green(), color.blue()));
        }

        return variations;
    }

    private static Color getRandomColor() {
        return new Color(
            RANDOM.nextInt(COLOR_COMPONENT_UPPER_BOUND),
            RANDOM.nextInt(COLOR_COMPONENT_UPPER_BOUND),
            RANDOM.nextInt(COLOR_COMPONENT_UPPER_BOUND)
        );
    }

    private static Shift getRandomShift() {
        double a = 0;
        double b = 0;
        double c;
        double d = 0;
        double e = 0;
        double f;

        boolean areNotCorrect = true;

        while (areNotCorrect) {
            a = RANDOM.nextDouble(-1, 1);
            b = RANDOM.nextDouble(-1, 1);
            d = RANDOM.nextDouble(-1, 1);
            e = RANDOM.nextDouble(-1, 1);
            areNotCorrect = a * a + d * d >= 1;
            areNotCorrect |= b * b + e * e >= 1;
            areNotCorrect |= a * a + d * d + b * b + e * e >= 1 + (a * e - b * d) * (a * e - b * d);
        }

        c = RANDOM.nextDouble(-1 * BOUND, BOUND);
        f = RANDOM.nextDouble(-1 * BOUND, BOUND);

        return new Shift(a, b, c, d, e, f);
    }
}
