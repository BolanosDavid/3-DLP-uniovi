package ast.statements;

import java.util.List;
import ast.expressions.Expression;

public class Invocation extends Statement {
    private Expression function;
    private List<Expression> arguments;

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
