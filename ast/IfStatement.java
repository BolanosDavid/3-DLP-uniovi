package ast;

import java.util.List;

public class IfStatement extends Statement {
    private Expression condition;
    private List<Statement> thenBody;
    private List<Statement> elseBody;

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
