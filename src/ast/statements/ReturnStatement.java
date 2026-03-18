package ast.statements;

import ast.Expression;
import visitor.Visitor;

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
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
