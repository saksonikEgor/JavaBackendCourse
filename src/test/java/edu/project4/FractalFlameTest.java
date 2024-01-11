package edu.project4;

import edu.project4.model.ImageFormat;
import edu.project4.transformations.DiskTransformation;
import edu.project4.transformations.SphericalTransformation;
import edu.project4.utils.ImageUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FractalFlameTest {
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final int SYMMETRY = 5;
    private static final int SAMPLE_COUNT = 10_000;
    private static final short ITER_PER_SAMPLE = 100;
    private static final Path path = Path.of("src/test/resources/project4/sample.png");

    private static long getDuration(Runnable task) {
        long start = System.currentTimeMillis();

        task.run();

        long end = System.currentTimeMillis();
        return end - start;
    }

    @Test
    @DisplayName("Сохранение изображения")
    void saving() throws IOException {
        FractalFlame fractalFlame1 = new FractalFlame(WIDTH, HEIGHT, new DiskTransformation());
        fractalFlame1.render(SYMMETRY, SAMPLE_COUNT, ITER_PER_SAMPLE);
        fractalFlame1.saveToFile(path, ImageFormat.PNG);

        assertTrue(Files.exists(path));
    }

    @Test
    @DisplayName("Генерация изображения")
    void generating() {
        FractalFlame fractalFlame1 = new FractalFlame(WIDTH, HEIGHT, new DiskTransformation());
        fractalFlame1.render(SYMMETRY, SAMPLE_COUNT, ITER_PER_SAMPLE);

        assertNotNull(fractalFlame1.render(SYMMETRY, SAMPLE_COUNT, ITER_PER_SAMPLE));
        assertNotEquals(
            ImageUtils.initImage(WIDTH, HEIGHT),
            fractalFlame1.render(SYMMETRY, SAMPLE_COUNT, ITER_PER_SAMPLE)
        );
    }

    @Test
    @DisplayName("Сравнение многопоточного и однопоточного времени выполнения")
    void multiThreadingAndSingleThreadingMetric() {
        final int threadCount = 4;

        Runnable multiThreadingTask = () -> new FractalFlame(WIDTH, HEIGHT, new SphericalTransformation(), threadCount)
            .render(SYMMETRY, SAMPLE_COUNT, ITER_PER_SAMPLE);

        Runnable singleThreadingTask = () -> new FractalFlame(WIDTH, HEIGHT, new SphericalTransformation())
            .render(SYMMETRY, SAMPLE_COUNT, ITER_PER_SAMPLE);

        assertTrue(getDuration(multiThreadingTask) < getDuration(singleThreadingTask));
    }
}
