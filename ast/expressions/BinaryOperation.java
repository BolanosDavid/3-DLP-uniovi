package ast.expressions;

public abstract class BinaryOperation extends Expression {
    protected Expression left;
    protected String operator;
    protected Expression right;

    public BinaryOperation(int line, int column) {
        super(line, column);
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
