package edu.hw2;

import edu.hw2.task1.Expr;
import edu.hw2.task3.ConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;

public class Main {
    public static void first() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));

        System.out.println(res + " = " + res.evaluate());
    }

    public static void third() {
        new PopularCommandExecutor(new ConnectionManager.FaultyConnectionManager(), 1).updatePackages();
    }
    public static void main(String[] args) {
        third();
    }
}
