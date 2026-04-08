package ast.statements;

import ast.Expression;
import ast.Statement;
import ast.expressions.AbstractExpression;
import ast.expressions.Variable;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Invocation extends AbstractExpression implements Statement {
    private Variable function;
    private List<Expression> arguments = new ArrayList<>();
    private boolean LValue;
    public Invocation(int line, int column, Variable function, List<Expression> arguments) {
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

    @Override
    public String toString() {
        String argsStr = arguments.stream()
                .map(Object::toString)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        return function + "(" + argsStr + ");";
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }

    @Override
    public boolean getLValue() {
        return LValue;
    }

    @Override
    public void setLValue(boolean lValue) {
        this.LValue = lValue;
    }
}
