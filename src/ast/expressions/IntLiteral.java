package ast.expressions;

import visitor.Visitor;

public class IntLiteral extends AbstractExpression {
    private int value;

    public IntLiteral(int line, int column, int value) {
        super(line, column);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    @Override
    public String toString() {
        return " "+ String.valueOf(value)+ " ";
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
