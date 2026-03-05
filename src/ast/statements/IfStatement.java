package ast.statements;

import ast.Statement;
import ast.Expression;

import java.util.ArrayList;
import java.util.List;

public class IfStatement extends AbstractStatement {
    private Expression condition;
    private List<Statement> thenBody = new ArrayList<>();
    private List<Statement> elseBody = new ArrayList<>();

    public IfStatement(int line, int column, Expression condition, List<Statement> thenBody, List<Statement> elseBody) {
        super(line, column);
        this.condition = condition;
        this.thenBody.addAll(thenBody);
        this.elseBody.addAll(elseBody);
    }

    public Expression getCondition() {
        return condition;
    }


    public List<Statement> getThenBody() {
        return thenBody;
    }


    public List<Statement> getElseBody() {
        return elseBody;
    }

    @Override
    public String toString() {
        String thenStr = thenBody.stream()
                .map(Object::toString)
                .map(s -> "    " + s.replace("\n", "\n    "))
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");

        String elseStr = elseBody.stream()
                .map(Object::toString)
                .map(s -> "    " + s.replace("\n", "\n    "))
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");

        if (elseBody.isEmpty()) {
            return "if (" + condition + ") {\n" +
                    thenStr + "\n" +
                    "}";
        }

        return "if (" + condition + ") {\n" +
                thenStr + "\n" +
                "} else {\n" +
                elseStr + "\n" +
                "}";
    }
}
