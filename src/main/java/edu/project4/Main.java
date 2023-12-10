package edu.project4;

import edu.project4.model.ImageFormat;
import edu.project4.transformations.DiskTransformation;
import edu.project4.transformations.HeartTransformation;
import edu.project4.transformations.PolarTransformation;
import edu.project4.transformations.SinusoidalTransformation;
import edu.project4.transformations.SphericalTransformation;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void single() throws IOException {
        FractalFlame fractalFlame = new FractalFlame(1000, 1000, new DiskTransformation());
        fractalFlame.render(1, 1000, (short) 100);
        fractalFlame.saveToFile(Path.of("src/test/resources/project4/test.png"), ImageFormat.PNG);
    }

    public static void multi() throws IOException {
        FractalFlame fractalFlame1 = new FractalFlame(1920, 1080, new DiskTransformation(), 4);
        fractalFlame1.render(5, 100_000, (short) 1_00);
        fractalFlame1.saveToFile(Path.of("src/test/resources/project4/disk.png"), ImageFormat.PNG);

        FractalFlame fractalFlame2 = new FractalFlame(1920, 1080, new HeartTransformation(), 6);
        fractalFlame2.render(5, 1000_000, (short) 10_0);
        fractalFlame2.saveToFile(Path.of("src/test/resources/project4/heart.png"), ImageFormat.PNG);

        FractalFlame fractalFlame3 = new FractalFlame(1920, 1080, new PolarTransformation(), 6);
        fractalFlame3.render(5, 1000_000, (short) 1_00);
        fractalFlame3.saveToFile(Path.of("src/test/resources/project4/polar.png"), ImageFormat.PNG);

        FractalFlame fractalFlame4 = new FractalFlame(1920, 1000, new SinusoidalTransformation(), 6);
        fractalFlame4.render(5, 1000_000, (short) 1_00);
        fractalFlame4.saveToFile(Path.of("src/test/resources/project4/sinusoidal.png"), ImageFormat.PNG);

        FractalFlame fractalFlame5 = new FractalFlame(1920, 1080, new SphericalTransformation(), 6);
        fractalFlame5.render(5, 1000_000, (short) 10_0);
        fractalFlame5.saveToFile(Path.of("src/test/resources/project4/spherical.png"), ImageFormat.PNG);
    }

    public static void main(String[] args) throws IOException {
        single();
        multi();
    }
}
