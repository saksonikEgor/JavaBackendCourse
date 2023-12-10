package edu.project4.util;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import static edu.project4.properties.ApplicationProperties.ALPHA_SHIFT;
import static edu.project4.properties.ApplicationProperties.BLACK;
import static edu.project4.properties.ApplicationProperties.COLOR_COMPONENT_UPPER_BOUND;
import static edu.project4.properties.ApplicationProperties.GREEN_SHIFT;
import static edu.project4.properties.ApplicationProperties.RED_SHIFT;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static FractalImage initImage(int width, int height) {
        Pixel[][] data = new Pixel[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                data[i][j] = new Pixel(0, 0, 0, 0, 0);
            }
        }

        return new FractalImage(data, width, height);
    }

    public static void save(FractalImage image, Path path, ImageFormat format) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                Pixel pixel = image.pixel(x, y);

                if (pixel.hitCount() == 0) {
                    bufferedImage.setRGB(x, y, BLACK);
                    continue;
                }
                int color = ((COLOR_COMPONENT_UPPER_BOUND - 1) << ALPHA_SHIFT) | (pixel.r() << RED_SHIFT)
                    | (pixel.g() << GREEN_SHIFT) | pixel.b();
                bufferedImage.setRGB(x, y, color);
            }
        }

        File outputFile = path.toFile();
        ImageIO.write(bufferedImage, format.name(), outputFile);
    }
}
