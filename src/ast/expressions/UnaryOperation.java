package ast.expressions;

import ast.Expression;

public abstract class UnaryOperation extends AbstractExpression {
    protected Expression expression;

    public UnaryOperation(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getOperand() {
        return expression;
    }

    public void setOperand(Expression operand) {
        this.expression = operand;
    }
}
