package ast.base;

public abstract class AbstractLocatable implements ASTNode, Locatable {
    protected int line;
    protected int column;

    public AbstractLocatable(int line, int column) {
        this.line = line;
        this.column = column;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }
}
