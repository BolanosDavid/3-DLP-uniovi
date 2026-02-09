package ast.statements;

import java.util.List;
import ast.expressions.Expression;

public class InputStatement extends Statement {
    private List<Expression> expressions;

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }
}
