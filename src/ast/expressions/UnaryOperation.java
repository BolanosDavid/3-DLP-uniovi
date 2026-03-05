package ast.expressions;

import ast.Expression;

public abstract class UnaryOperation extends AbstractExpression {
    protected String operator;
    protected Expression expression;

    public UnaryOperation(int line, int column, Expression expression, String operator) {
        super(line, column);
        this.expression = expression;
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Expression getOperand() {
        return expression;
    }

    public void setOperand(Expression operand) {
        this.expression = operand;
    }
}
