package ast.expressions;

public abstract class BinaryOperation extends AbstractExpression {
    protected Expression left;
    protected String operator;
    protected Expression right;

    public BinaryOperation(int line, int column, Expression left, Expression right, String operator) {
        super(line, column);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }
}
