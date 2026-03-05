package ast.statements;

import ast.Expression;

import java.util.ArrayList;
import java.util.List;

public class Invocation extends AbstractStatement implements Expression{
    private Expression function;
    private List<Expression> arguments = new ArrayList<>();

    public Invocation(int line, int column, Expression function, List<Expression> arguments) {
        super(line, column);
        this.function = function;
        this.arguments.addAll(arguments);
    }

    public Expression getFunction() {
        return function;
    }

    public List<Expression> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        String argsStr = arguments.stream()
                .map(Object::toString)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        return function + "(" + argsStr + ");";
    }
}
