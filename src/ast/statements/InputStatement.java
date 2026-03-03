package ast.statements;

import ast.expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public class InputStatement extends AbstractStatement {
    private List<Expression> expressions = new ArrayList<>();

    public InputStatement(int line, int column,List<Expression> expressions)
    {
        super(line, column);
        this.expressions.addAll(expressions);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

}
