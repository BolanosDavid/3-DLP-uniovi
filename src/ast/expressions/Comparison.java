package ast.expressions;

public class Comparison extends BinaryOperation {
    public Comparison(int line, int column, Expression left, Expression right, String operator) {
        super(line, column, left, right, operator);
    }
    @Override
    public String toString() {
        return left + " " + operator + " " + right;
    }
}
