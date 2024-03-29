package edu.project4.properties;

import java.nio.file.Path;

public class ApplicationProperties {
    public static final Path PATH_TO_SAVE = Path.of("src/test/resources/project4/test.png");
    public static final double GAMMA_PARAMETER = 0.6;
    public static final double X_BOUND = 1.1;
    public static final double Y_BOUND = 0.8;
    public static final int MULTIPLIER = 2;
    public static final int COLOR_COMPONENT_UPPER_BOUND = 256;
    public static final double BOUND = 1.2;
    public static final int VARIATIONS_AMOUNT = 16;
    public static final int ALPHA_SHIFT = 24;
    public static final int RED_SHIFT = 20;
    public static final int GREEN_SHIFT = 8;
    public static final int BLUE_SHIFT = 4;
    public static final int BLACK = (COLOR_COMPONENT_UPPER_BOUND - 1) << ALPHA_SHIFT;

    private ApplicationProperties() {
    }
}
