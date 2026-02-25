package ast.definitions;

import ast.types.Type;

public class VarDefinition extends Definition {
    
    public VarDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }
}
