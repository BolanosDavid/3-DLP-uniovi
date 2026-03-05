package ast.statements;

import ast.Expression;

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

}
