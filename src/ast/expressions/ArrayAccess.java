package ast.expressions;

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
}
