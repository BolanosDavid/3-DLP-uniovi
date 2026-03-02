package ast.expressions;

import java.util.List;

public class FunctionInvocation extends AbstractExpression {
    private Variable function;
    private List<AbstractExpression> arguments;

    public FunctionInvocation(int line, int column) {
        super(line, column);
    }

    public Variable getFunction() {
        return function;
    }

    public void setFunction(Variable function) {
        this.function = function;
    }

    public List<AbstractExpression> getArguments() {
        return arguments;
    }

    public void setArguments(List<AbstractExpression> arguments) {
        this.arguments = arguments;
    }
}
