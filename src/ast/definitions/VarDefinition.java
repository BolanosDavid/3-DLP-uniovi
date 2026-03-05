package ast.definitions;

import ast.Statement;
import ast.Type;

public class VarDefinition extends AbstractDefinition implements Statement {
    
    public VarDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }
    @Override
    public String toString() {
        return type + " " + name;
    }
}
