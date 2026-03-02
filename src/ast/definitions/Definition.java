package ast.definitions;

import ast.base.AbstractLocatable;
import ast.types.Type;

public abstract class Definition extends AbstractLocatable {
    protected String name;
    protected Type type;

    public Definition(int line, int column, String name, Type type) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

}
