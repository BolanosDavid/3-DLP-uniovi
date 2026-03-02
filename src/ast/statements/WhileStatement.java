package ast.statements;

import ast.expressions.Expression;

import java.util.List;

public class WhileStatement extends AbstractStatement {
    private Expression condition;
    private List<Statement> body;

    public WhileStatement(int line, int column,Expression condition, List<Statement> body) {
        super(line, column);
        this.condition = condition;
        this.body.addAll(body);
    }

    public Expression getCondition() {
        return condition;
    }


    public List<Statement> getBody() {
        return body;
    }

}
