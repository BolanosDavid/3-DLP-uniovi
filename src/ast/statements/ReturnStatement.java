package ast.statements;

import ast.expressions.Expression;

public class ReturnStatement extends Statement {
    private Expression expression;

    public ReturnStatement(int line, int column) {
        super(line, column);
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
