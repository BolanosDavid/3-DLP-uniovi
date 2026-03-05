package ast.definitions;

import ast.Definition;
import ast.Type;
import ast.base.AbstractLocatable;

public abstract class
AbstractDefinition extends AbstractLocatable implements Definition {
    protected String name;
    protected Type type;

    public AbstractDefinition(int line, int column, String name, Type type) {
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
