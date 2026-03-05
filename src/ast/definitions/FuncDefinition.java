package ast.definitions;

import ast.Statement;
import ast.Type;
import ast.types.FunctionType;
import ast.types.AbstractType;

import java.util.ArrayList;
import java.util.List;

public class FuncDefinition extends AbstractDefinition {
    private List<Statement> statements = new ArrayList<>();

    public FuncDefinition(int line, int column, String name, Type retType, List<VarDefinition> parameters, List<Statement> statements) {
        super(line, column, name, new FunctionType(parameters, retType));
        this.statements.addAll(statements);
    }
    public List<Statement> getStatements() {
        return statements;
    }
    @Override
    public String toString() {
        FunctionType funcType = (FunctionType) this.type;
        StringBuilder sb = new StringBuilder();
        sb.append("function ").append(name).append("(");

        List<VarDefinition> params = funcType.getParameters();
        for (int i = 0; i < params.size(); i++) {
            VarDefinition param = params.get(i);
            sb.append(param.getName()).append(": ").append(param.getType());
            if (i < params.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("): ").append(funcType.getReturnType()).append(" {\n");

        for (Statement stmt : statements) {
            sb.append("    ").append(stmt.toString()).append("\n");
        }

        sb.append("}");

        return sb.toString();
    }


}
