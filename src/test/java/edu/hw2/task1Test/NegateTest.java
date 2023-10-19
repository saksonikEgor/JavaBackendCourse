package edu.hw2.task1Test;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NegateTest {
    @Test
    @DisplayName("Вычисление отрицания")
    void negateEvaluate() {
        Assertions.assertEquals(-1, new Expr.Negate(new Expr.Constant(1)).evaluate());
        Assertions.assertEquals(25, new Expr.Negate(new Expr.Constant(-25)).evaluate());
        Assertions.assertEquals(-Double.MIN_VALUE, new Expr.Negate(new Expr.Constant(Double.MIN_VALUE)).evaluate());
        Assertions.assertEquals(-Double.MAX_VALUE, new Expr.Negate(new Expr.Constant(Double.MAX_VALUE)).evaluate());
        Assertions.assertEquals(-256, new Expr.Negate(new Expr.Constant(256)).evaluate());
        Assertions.assertEquals(-0.25, new Expr.Negate(new Expr.Constant(0.25)).evaluate());
        Assertions.assertEquals(-233.233, new Expr.Negate(new Expr.Constant(233.233)).evaluate());
        Assertions.assertEquals(789.789, new Expr.Negate(new Expr.Constant(-789.789)).evaluate());
    }
}
