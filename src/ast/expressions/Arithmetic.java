package ast.expressions;

import ast.Expression;

public class Arithmetic extends BinaryOperation {

    public Arithmetic(int line, int column, Expression left, Expression right, String operator) {

        super(line, column,left, right, operator);
    }

    @Override
    public String toString() {
        return left + " " + operator + " " + right;
    }
}
