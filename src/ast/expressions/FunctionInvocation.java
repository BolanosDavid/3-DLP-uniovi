package ast.expressions;

import java.util.ArrayList;
import java.util.List;

public class FunctionInvocation extends AbstractExpression {
    private Variable function;
    private List<Expression> arguments = new ArrayList<>();

    public FunctionInvocation(int line, int column, Variable function, List<Expression> arguments) {
        super(line, column);
        this.function = function;
        this.arguments.addAll(arguments);
    }

    public Variable getFunction() {
        return function;
    }

    public List<Expression> getArguments() {
        return arguments;
    }
}
