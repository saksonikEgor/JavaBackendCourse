package edu.project4.view;

import edu.project4.model.FractalImage;
import edu.project4.model.Rectangle;
import edu.project4.transformation.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rectangle world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed
    );
}
