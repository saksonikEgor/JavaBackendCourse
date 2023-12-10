package edu.project4.view;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Variation;
import edu.project4.util.RandomUtils;
import edu.project4.util.RenderUtils;
import java.util.List;
import static edu.project4.properties.ApplicationProperties.MULTIPLIER;
import static edu.project4.properties.ApplicationProperties.X_BOUND;
import static edu.project4.properties.ApplicationProperties.Y_BOUND;
import static edu.project4.util.RenderUtils.rotate;

public class SingleThreadedRenderer implements Renderer {
    @Override
    public void render(
        FractalImage image,
        List<Variation> variations,
        int symmetry,
        int sampleCount,
        short iterPerSample
    ) {
        for (int num = 0; num < sampleCount; num++) {
            Point point = RandomUtils.getRandomPoint();

            for (int step = 0; step < iterPerSample; step++) {
                Variation variation = variations.get(RandomUtils.RANDOM.nextInt(variations.size()));
                point = RenderUtils.transform(point, variation);

                for (int s = 0; s < symmetry; s++) {
                    double theta = s * Math.PI * 2 / symmetry;
                    Point rotadedPoint = rotate(point, theta);

                    int x = (int) (
                        -((X_BOUND - rotadedPoint.x()) / (X_BOUND * MULTIPLIER)) * image.width()
                            + image.width()
                    );
                    int y = (int) (
                        -((Y_BOUND - rotadedPoint.y()) / (Y_BOUND * MULTIPLIER)) * image.height()
                            + image.height()
                    );

                    Pixel pixel = image.pixel(x, y);
                    if (pixel == null) {
                        continue;
                    }

                    image.setPixel(getNextPixel(pixel, variation), x, y);
                }
            }
        }
    }

    private static Pixel getNextPixel(Pixel pixel, Variation variation) {
        Pixel nextPixel;
        if (pixel.hitCount() == 0) {
            nextPixel =
                new Pixel(variation.red(), variation.green(), variation.blue(), 0, 1);
        } else {
            nextPixel = new Pixel(
                (variation.red() + pixel.r()) / 2,
                (variation.green() + pixel.g()) / 2,
                (variation.blue() + pixel.b()) / 2,
                pixel.normal(),
                pixel.hitCount() + 1
            );
        }
        return nextPixel;
    }
}
