package edu.hw2.task1Test;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdditionTest {
    @Test
    @DisplayName("Вычисление сложения")
    void additionEvaluate() {
        Assertions.assertEquals(1 + 1, new Expr.Addition(new Expr.Constant(1), new Expr.Constant(1)).evaluate());
        Assertions.assertEquals(0 + 0, new Expr.Addition(new Expr.Constant(0), new Expr.Constant(0)).evaluate());
        Assertions.assertEquals(1 + 13, new Expr.Addition(new Expr.Constant(1), new Expr.Constant(13)).evaluate());
        Assertions.assertEquals(0 + (-14), new Expr.Addition(new Expr.Constant(0), new Expr.Constant(-14)).evaluate());
        Assertions.assertEquals(-14 + 0, new Expr.Addition(new Expr.Constant(-14), new Expr.Constant(0)).evaluate());
        Assertions.assertEquals(-200 + (-200), new Expr.Addition(new Expr.Constant(-200), new Expr.Constant(-200)).evaluate());
        Assertions.assertEquals(Double.MAX_VALUE + Double.MIN_VALUE, new Expr.Addition(new Expr.Constant(Double.MAX_VALUE), new Expr.Constant(Double.MIN_VALUE)).evaluate());
        Assertions.assertEquals(Double.MIN_VALUE + Double.MAX_VALUE, new Expr.Addition(new Expr.Constant(Double.MIN_VALUE), new Expr.Constant(Double.MAX_VALUE)).evaluate());
        Assertions.assertEquals(Double.MAX_VALUE + Double.MAX_VALUE, new Expr.Addition(new Expr.Constant(Double.MAX_VALUE), new Expr.Constant(Double.MAX_VALUE)).evaluate());
        Assertions.assertEquals(Double.MIN_VALUE + Double.MIN_VALUE, new Expr.Addition(new Expr.Constant(Double.MIN_VALUE), new Expr.Constant(Double.MIN_VALUE)).evaluate());
    }
}
