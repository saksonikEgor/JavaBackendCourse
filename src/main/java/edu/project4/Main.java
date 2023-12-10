package edu.project4;

import edu.project4.model.ImageFormat;
import edu.project4.transformations.DiskTransformation;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void single() throws IOException {
        FractalFlame fractalFlame = new FractalFlame(1000, 1000, new DiskTransformation());
        fractalFlame.render(1, 1000, (short) 100);
        fractalFlame.saveToFile(Path.of("src/test/resources/project4/test.png"), ImageFormat.PNG);
    }

    public static void multi() throws IOException {
        FractalFlame fractalFlame = new FractalFlame(1000, 1000, new DiskTransformation(), 2);
        fractalFlame.render(1, 1000, (short) 100);
        fractalFlame.saveToFile(Path.of("src/test/resources/project4/test.png"), ImageFormat.PNG);
    }

    public static void main(String[] args) throws IOException {
//        single();

        multi();
    }
}
