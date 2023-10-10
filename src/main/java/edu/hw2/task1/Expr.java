package edu.hw2.task1;

public sealed interface Expr {
    double evaluate();

    record Constant(double value) implements Expr {
        @Override
        public double evaluate() {
            return value;
        }
    }

    record Negate(double value) implements Expr {
        public Negate(Expr expression) {
            this(expression.evaluate());
        }

        @Override
        public double evaluate() {
            return -value;
        }
    }

    record Exponent(double base, double degree) implements Expr {
        public Exponent(Expr expression, double degree) {
            this(expression.evaluate(), degree);
        }

        @Override
        public double evaluate() {
            return Math.pow(base, degree);
        }
    }

    record Addition(double first, double second) implements Expr {
        public Addition(Expr firstExpression, Expr secondExpression) {
            this(firstExpression.evaluate(), secondExpression.evaluate());
        }

        @Override
        public double evaluate() {
            return first + second;
        }
    }

    record Multiplication(double first, double second) implements Expr {
        public Multiplication(Expr firstExpression, Expr secondExpression) {
            this(firstExpression.evaluate(), secondExpression.evaluate());
        }

        @Override
        public double evaluate() {
            return first * second;
        }
    }
}
