package ast.statements;

import ast.expressions.Expression;
import java.util.List;

public class IfStatement extends Statement {
    private Expression condition;
    private List<Statement> thenBody;
    private List<Statement> elseBody;

    public IfStatement(int line, int column) {
        super(line, column);
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public List<Statement> getThenBody() {
        return thenBody;
    }

    public void setThenBody(List<Statement> thenBody) {
        this.thenBody = thenBody;
    }

    public List<Statement> getElseBody() {
        return elseBody;
    }

    public void setElseBody(List<Statement> elseBody) {
        this.elseBody = elseBody;
    }
}
