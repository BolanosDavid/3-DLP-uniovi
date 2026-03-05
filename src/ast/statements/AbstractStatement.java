package ast.statements;

import ast.Statement;
import ast.base.AbstractLocatable;

public abstract class AbstractStatement extends AbstractLocatable implements Statement {
    
    public AbstractStatement(int line, int column) {
        super(line, column);
    }
}
