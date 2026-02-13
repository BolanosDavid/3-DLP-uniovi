package ast.expressions;

import ast.base.AbstractLocatable;
import ast.types.Type;

public abstract class Expression extends AbstractLocatable {
    protected Type type;

    public Expression(int line, int column) {
        super(line, column);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
