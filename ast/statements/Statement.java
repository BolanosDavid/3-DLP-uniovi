package ast.statements;

import ast.base.AbstractLocatable;

public abstract class Statement extends AbstractLocatable {
    
    public Statement(int line, int column) {
        super(line, column);
    }
}
