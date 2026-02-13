package ast.statements;

import ast.expressions.Expression;
import java.util.List;

public class LogStatement extends Statement {
    private List<Expression> expressions;

    public LogStatement(int line, int column) {
        super(line, column);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }
}
