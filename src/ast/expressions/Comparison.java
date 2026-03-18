package ast.expressions;

import ast.Expression;
import visitor.Visitor;

public class Comparison extends BinaryOperation {
    public Comparison(int line, int column, Expression left, Expression right, String operator) {
        super(line, column, left, right, operator);
    }
    @Override
    public String toString() {
        return left + " " + operator + " " + right;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
