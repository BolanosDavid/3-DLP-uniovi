package ast.expressions;

import java.util.List;

public class FunctionInvocation extends Expression {
    private Variable function;
    private List<Expression> arguments;

    public FunctionInvocation(int line, int column) {
        super(line, column);
    }

    public Variable getFunction() {
        return function;
    }

    public void setFunction(Variable function) {
        this.function = function;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    public void setArguments(List<Expression> arguments) {
        this.arguments = arguments;
    }
}
