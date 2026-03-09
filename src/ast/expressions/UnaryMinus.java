package ast.expressions;

import ast.Expression;

public class UnaryMinus extends UnaryOperation {
    
    public UnaryMinus(int line, int column, Expression expression) {
        super(line, column,expression);
    }
    @Override
    public String toString() {
        return "-" + expression;
    }
}
