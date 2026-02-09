package ast.expressions;

import java.util.List;

public class FunctionInvocation extends Expression {
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
