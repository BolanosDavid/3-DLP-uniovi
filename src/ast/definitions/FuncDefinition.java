package ast.definitions;

import ast.statements.Statement;
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


}
