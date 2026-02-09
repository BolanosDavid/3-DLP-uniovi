package ast.expressions;

import ast.base.AbstractASTNode;
import ast.types.Type;

public abstract class Expression extends AbstractASTNode {
    protected Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
