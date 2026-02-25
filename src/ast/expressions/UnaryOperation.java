package ast.expressions;

public abstract class UnaryOperation extends Expression {
    protected String operator;
    protected Expression operand;

    public UnaryOperation(int line, int column) {
        super(line, column);
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Expression getOperand() {
        return operand;
    }

    public void setOperand(Expression operand) {
        this.operand = operand;
    }
}
