package ast.expressions;

import ast.Expression;
import ast.Type;
import ast.base.AbstractLocatable;

public abstract class AbstractExpression extends AbstractLocatable implements Expression {
    protected Type type;

    public AbstractExpression(int line, int column) {
        super(line, column);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
