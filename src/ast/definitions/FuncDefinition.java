package ast.definitions;

import ast.statements.Statement;
import ast.types.FunctionType;
import ast.types.Type;

import java.util.ArrayList;
import java.util.List;

public class FuncDefinition extends Definition {
    private List<VarDefinition> parameters = new ArrayList<>();
    private List<Statement> statements = new ArrayList<>();

    public FuncDefinition(int line, int column, String name, Type type, List<VarDefinition> parameters, List<Statement> statements) {
        super(line, column, name, type);
        this.parameters.addAll(parameters);
        this.statements.addAll(statements);
    }

    public List<VarDefinition> getParameters() {
        return parameters;
    }


    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        String params = parameters.stream()
                .map(Object::toString)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        String body = statements.stream()
                .map(stmt -> "    " + stmt)
                .reduce((a, b) -> a + "\n" + b)
                .orElse("");

        String returnType = (type instanceof FunctionType)
                ? ((FunctionType) type).getReturnType().toString()
                : type.toString();

        return "function " + name + "(" + params + ") : " + returnType + " {\n" +
                body + "\n" +
                "} [" + line + ":" + column + "]";
    }
}
