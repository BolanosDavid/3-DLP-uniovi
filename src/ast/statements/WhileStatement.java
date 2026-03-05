package ast.statements;

import ast.expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public class WhileStatement extends AbstractStatement {
    private Expression condition;
    private List<Statement> body = new ArrayList<>();

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

    @Override
    public String toString() {
        String bodyStr = body.stream()
                .map(Object::toString)
                .map(s -> "    " + s.replace("\n", "\n    "))
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");

        return "while (" + condition + ") {\n" +
                bodyStr + "\n" +
                "}";
    }
}
