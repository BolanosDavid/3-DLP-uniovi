package ast.definitions;

import ast.base.AbstractLocatable;
import ast.types.Type;

public abstract class Definition extends AbstractLocatable {
    protected String name;
    protected Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
