package edu.project4.view;

import edu.project4.model.FractalImage;
import edu.project4.model.Variation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    void render(
        FractalImage image,
        List<Variation> variations,
        int symmetry,
        int sampleCount,
        short iterPerSample
    );
}
