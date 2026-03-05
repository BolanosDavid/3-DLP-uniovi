package ast.expressions;

public class NumberLiteral extends AbstractExpression {
    private double value;

    public NumberLiteral(int line, int column, double value) {
        super(line, column);
        this.value = value;
    }

    public double getValue() {
        return value;
    }
    @Override
    public String toString() {
        return " "+ String.valueOf(value)+ " ";
    }
}
