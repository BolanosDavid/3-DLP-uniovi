package ast.definitions;

import ast.statements.Statement;
import ast.types.Type;

public class VarDefinition extends Definition implements Statement {
    
    public VarDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }
    @Override
    public String toString() {
        return type + " " + name;
    }
}
