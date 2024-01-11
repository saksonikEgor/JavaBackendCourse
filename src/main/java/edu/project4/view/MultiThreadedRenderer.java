package edu.project4.view;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Variation;
import edu.project4.utils.RandomUtils;
import edu.project4.utils.RenderUtils;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;
import static edu.project4.properties.ApplicationProperties.MULTIPLIER;
import static edu.project4.properties.ApplicationProperties.X_BOUND;
import static edu.project4.properties.ApplicationProperties.Y_BOUND;
import static edu.project4.utils.RenderUtils.rotate;

public class MultiThreadedRenderer implements Renderer {
    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();
    private final int threadCount;

    public MultiThreadedRenderer(int threadCount) {
        this.threadCount = threadCount;
    }

    private static void renderSample(
        FractalImage image,
        List<Variation> variations,
        Point point,
        int symmetry,
        int repeats
    ) {
        for (int step = 0; step < repeats; step++) {
            Variation variation = variations.get(
                ThreadLocalRandom.current().nextInt(variations.size())
            );
            Point nextPoint = RenderUtils.transform(point, variation);

            for (int s = 0; s < symmetry; s++) {
                double theta = s * Math.PI * 2 / symmetry;
                Point rotadedPoint = rotate(nextPoint, theta);

                int x = (int) (
                    -((X_BOUND - rotadedPoint.x()) / (X_BOUND * MULTIPLIER)) * image.width()
                        + image.width()
                );
                int y = (int) (
                    -((Y_BOUND - rotadedPoint.y()) / (Y_BOUND * MULTIPLIER)) * image.height()
                        + image.height()
                );

                Pixel pixel;

                try {
                    LOCK.readLock().lock();
                    pixel = image.pixel(x, y);

                    if (pixel == null) {
                        LOCK.readLock().unlock();
                        continue;
                    }

                    if (pixel.hitCount() == 0) {
                        pixel = new Pixel(variation.red(), variation.green(), variation.blue(), 0, 1);
                    } else {
                        pixel = new Pixel(
                            (variation.red() + pixel.r()) / MULTIPLIER,
                            (variation.green() + pixel.g()) / MULTIPLIER,
                            (variation.blue() + pixel.b()) / MULTIPLIER,
                            pixel.normal(),
                            pixel.hitCount() + 1
                        );
                    }
                } finally {
                    LOCK.readLock().unlock();
                }

                try {
                    LOCK.writeLock().lock();
                    image.setPixel(pixel, x, y);
                } finally {
                    LOCK.writeLock().unlock();
                }
            }
        }
    }

    @Override
    public void render(
        FractalImage image,
        List<Variation> variations,
        int symmetry,
        int sampleCount,
        short iterPerSample
    ) {
        try (ExecutorService service = Executors.newFixedThreadPool(threadCount)) {
            for (int num = 0; num < sampleCount; num++) {
                Point point = RandomUtils.getRandomPoint();

                service.submit(
                    () -> IntStream
                        .range(0, threadCount)
                        .forEach(i -> renderSample(
                            image,
                            variations,
                            point,
                            symmetry,
                            iterPerSample / threadCount
                        ))
                );
            }
        }
    }
}
