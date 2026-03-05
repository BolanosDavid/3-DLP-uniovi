package ast.expressions;

import ast.Expression;

public class UnaryNot extends UnaryOperation {
    
    public UnaryNot(int line, int column, Expression expression) {
        super(line, column,expression,"!");
    }
    @Override
    public String toString() {
        return "!" + expression;
    }
}
