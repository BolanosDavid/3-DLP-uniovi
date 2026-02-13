package ast.expressions;

public class ArrayAccess extends Expression {
    private Expression array;
    private Expression index;

    public ArrayAccess(int line, int column) {
        super(line, column);
    }

    public Expression getArray() {
        return array;
    }

    public void setArray(Expression array) {
        this.array = array;
    }

    public Expression getIndex() {
        return index;
    }

    public void setIndex(Expression index) {
        this.index = index;
    }
}
