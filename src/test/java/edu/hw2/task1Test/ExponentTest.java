package edu.hw2.task1Test;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExponentTest {
    @Test
    @DisplayName("Вычисление возведения в степень")
    void negateEvaluate() {
        Assertions.assertEquals(1, new Expr.Exponent(new Expr.Constant(1), 1).evaluate());
        Assertions.assertEquals(1, new Expr.Exponent(new Expr.Constant(1), 0).evaluate());
        Assertions.assertEquals(1, new Expr.Exponent(new Expr.Constant(1), -24).evaluate());
        Assertions.assertEquals(1, new Expr.Exponent(new Expr.Constant(1), 78).evaluate());
        Assertions.assertEquals(25, new Expr.Exponent(new Expr.Constant(-5), 2).evaluate());
        Assertions.assertEquals(-125, new Expr.Exponent(new Expr.Constant(-5), 3).evaluate());
        Assertions.assertEquals(Math.pow(Double.MAX_VALUE, 3), new Expr.Exponent(new Expr.Constant(Double.MAX_VALUE), 3).evaluate());
        Assertions.assertEquals(Math.pow(Double.MIN_VALUE, -3), new Expr.Exponent(new Expr.Constant(Double.MIN_VALUE), -3).evaluate());
        Assertions.assertEquals(256, new Expr.Exponent(new Expr.Constant(2), 8).evaluate());
        Assertions.assertEquals(0.25, new Expr.Exponent(new Expr.Constant(4), -1).evaluate());
        Assertions.assertEquals(Math.pow(-233.233, -233.233), new Expr.Exponent(new Expr.Constant(-233.233), -233.233).evaluate());
        Assertions.assertEquals(Math.pow(789.789, -123.123), new Expr.Exponent(new Expr.Constant(789.789), -123.123).evaluate());
    }
}
