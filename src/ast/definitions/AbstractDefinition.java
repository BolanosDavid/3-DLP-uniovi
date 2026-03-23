package ast.definitions;

import ast.Definition;
import ast.Type;
import ast.base.AbstractLocatable;

public abstract class
AbstractDefinition extends AbstractLocatable implements Definition {
    protected String name;
    protected Type type;
    protected int scope;
    public AbstractDefinition(int line, int column, String name, Type type) {
        super(line, column);
        this.name = name;
        this.type = type;
    }
    @Override
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
    @Override
    public int getScope(){
        return scope;
    }
    @Override
    public void setScope(int scope){
        this.scope = scope;
    }

}
