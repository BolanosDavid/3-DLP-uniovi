package ast.statements;

import ast.Expression;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class LogStatement extends AbstractStatement {
    private List<Expression> expressions = new ArrayList<>();

    public LogStatement(int line, int column, List<Expression> expressions) {
        super(line, column);
        this.expressions.addAll(expressions);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }
    @Override
    public String toString() {
        String exprStr = expressions.stream()
                .map(Object::toString)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        return "log " + exprStr + ";";
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
