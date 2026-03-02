package ast.expressions;

public class ArrayAccess extends AbstractExpression {
    private AbstractExpression array;
    private AbstractExpression index;

    public ArrayAccess(int line, int column) {
        super(line, column);
    }

    public AbstractExpression getArray() {
        return array;
    }

    public void setArray(AbstractExpression array) {
        this.array = array;
    }

    public AbstractExpression getIndex() {
        return index;
    }

    public void setIndex(AbstractExpression index) {
        this.index = index;
    }
}
