package ast.expressions;

import ast.Expression;
import visitor.Visitor;

public class ArrayAccess extends AbstractExpression {
    private Expression array;
    private Expression index;

    public ArrayAccess(int line, int column, Expression array, Expression index) {
        super(line, column);
        this.array = array;
        this.index = index;
    }

    public Expression getArray() {
        return array;
    }

    public Expression getIndex() {
        return index;
    }
    @Override
    public String toString() {
        return array + "[" + index + "]";
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
