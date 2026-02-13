package ast.statements;

import ast.expressions.Expression;
import java.util.List;

public class InputStatement extends Statement {
    private List<Expression> expressions;

    public InputStatement(int line, int column) {
        super(line, column);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }
}
