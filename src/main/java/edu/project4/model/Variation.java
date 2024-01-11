package edu.project4.model;

import edu.project4.transformations.Transformation;

public record Variation(
    Transformation func,
    Transformation shift,
    int red,
    int green,
    int blue
) {
}
