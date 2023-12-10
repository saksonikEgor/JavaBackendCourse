package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Variation;
import edu.project4.transformations.Transformation;
import edu.project4.utils.ImageUtils;
import edu.project4.utils.RandomUtils;
import edu.project4.view.MultiThreadedRenderer;
import edu.project4.view.Renderer;
import edu.project4.view.SingleThreadedRenderer;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class FractalFlame {
    private final Renderer renderer;
    private final FractalImage image;
    private final List<Variation> variations;

    public FractalFlame(int width, int height, Transformation transformation) {
        renderer = new SingleThreadedRenderer();
        image = ImageUtils.initImage(width, height);
        variations = RandomUtils.getVariations(transformation);
    }

    public FractalFlame(int width, int height, Transformation transformation, int threadCount) {
        renderer = new MultiThreadedRenderer(threadCount);
        image = ImageUtils.initImage(width, height);
        variations = RandomUtils.getVariations(transformation);
    }

    public FractalImage render(int symmetry, int sampleCount, short iterPerSample) {
        renderer.render(image, variations, symmetry, sampleCount, iterPerSample);
        return image;
    }

    public void saveToFile(Path path, ImageFormat format) throws IOException {
        ImageUtils.save(image, path, format);
    }
}
