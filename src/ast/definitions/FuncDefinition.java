package ast.definitions;

import ast.statements.Statement;
import ast.types.FunctionType;
import ast.types.Type;
import java.util.List;

public class FuncDefinition extends Definition {
    private List<VarDefinition> parameters;
    private List<Statement> statements;

    public FuncDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }

    public List<VarDefinition> getParameters() {
        return parameters;
    }

    public void setParameters(List<VarDefinition> parameters) {
        this.parameters = parameters;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    public FunctionType getFunctionType() {
        if (type instanceof FunctionType) {
            return (FunctionType) type;
        }
        return null;
    }
}
