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


    public String getOperator() {
        return operator;
    }


    public Expression getRight() {
        return right;
    }


}
