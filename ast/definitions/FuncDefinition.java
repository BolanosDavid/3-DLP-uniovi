package ast.definitions;

import java.util.List;
import ast.statements.Statement;
import ast.types.Type;

public class FuncDefinition extends Definition {
    private String name;
    private List<Parameter> parameters;
    private Type returnType;
    private List<VarDefinition> varDefinitions;
    private List<Statement> statements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
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
}
