package ast.definitions;

import java.util.List;
import ast.statements.Statement;
import ast.types.FunctionType;

public class FuncDefinition extends Definition {
    private List<VarDefinition> parameters;
    private List<VarDefinition> varDefinitions;
    private List<Statement> statements;

    public List<VarDefinition> getParameters() {
        return parameters;
    }

    public void setParameters(List<VarDefinition> parameters) {
        this.parameters = parameters;
    }

    public List<VarDefinition> getVarDefinitions() {
        return varDefinitions;
    }

    public void setVarDefinitions(List<VarDefinition> varDefinitions) {
        this.varDefinitions = varDefinitions;
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
