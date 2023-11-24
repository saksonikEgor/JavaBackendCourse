package edu.project4.view;

import edu.project4.model.FractalImage;
import edu.project4.transformation.Transformation;
import org.w3c.dom.css.Rect;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed
    );
}
