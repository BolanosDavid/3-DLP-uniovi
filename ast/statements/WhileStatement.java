package ast.statements;

import java.util.List;
import ast.expressions.Expression;

public class WhileStatement extends Statement {
    private Expression condition;
    private List<Statement> body;

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public List<Statement> getBody() {
        return body;
    }

    public void setBody(List<Statement> body) {
        this.body = body;
    }
}
