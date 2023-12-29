package edu.project4.processing;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.properties.ApplicationProperties;

public class GammaCorrector implements ImageProcessor {
    private static final double GAMMA = ApplicationProperties.GAMMA_PARAMETER;

    private static double findMax(FractalImage image) {
        double max = 0;

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.pixel(x, y);

                if (pixel.hitCount() != 0) {
                    image.setPixel(
                        new Pixel(pixel.r(), pixel.g(), pixel.b(), Math.log10(pixel.hitCount()), pixel.hitCount()),
                        x, y
                    );
                }

                max = Math.max(image.pixel(x, y).normal(), max);
            }
        }
        return max;
    }

    @Override
    public void process(FractalImage image) {
        final double max = findMax(image);

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.pixel(x, y);
                double normal = pixel.normal() / max;

                image.setPixel(
                    new Pixel(
                        (int) (pixel.r() * Math.pow(normal, (1 / GAMMA))),
                        (int) (pixel.g() * Math.pow(normal, (1 / GAMMA))),
                        (int) (pixel.b() * Math.pow(normal, (1 / GAMMA))),
                        normal,
                        pixel.hitCount()
                    ),
                    x, y
                );
            }
        }
    }
}
