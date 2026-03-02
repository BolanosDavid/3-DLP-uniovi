package ast.expressions;

public abstract class UnaryOperation extends AbstractExpression {
    protected String operator;
    protected Expression expression;

    public UnaryOperation(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
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
