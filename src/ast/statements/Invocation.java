package ast.statements;

import ast.expressions.Expression;
import java.util.List;

public class Invocation extends Statement {
    private Expression function;
    private List<Expression> arguments;

    public Invocation(int line, int column) {
        super(line, column);
    }

    public Expression getFunction() {
        return function;
    }

    public void setFunction(Expression function) {
        this.function = function;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    public void setArguments(List<Expression> arguments) {
        this.arguments = arguments;
    }
}
