package ast.statements;

import ast.expressions.Expression;

public class ReturnStatement extends AbstractStatement {
    private Expression expression;

    public ReturnStatement(int line, int column,Expression expression) {
        super(line, column);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
    @Override
    public String toString() {
        return "return " + expression + ";";
    }
}
