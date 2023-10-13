package edu.hw2.task2Test;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    private static final int INITIAL_SIZE = 10;

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle(INITIAL_SIZE, INITIAL_SIZE)),
            Arguments.of(new Square(INITIAL_SIZE))
        };
    }

    @ParameterizedTest
    @DisplayName("Вычисление площади")
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(10);
        assertEquals(rect.area(), 10 * 10);

        rect = rect.setHeight(20);
        assertEquals(rect.area(), 20 * 10);

        rect = rect.setWidth(20);
        assertEquals(rect.area(), 20 * 20);
    }
}
