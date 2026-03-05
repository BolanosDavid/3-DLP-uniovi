package ast.types;

import ast.Type;
import ast.definitions.VarDefinition;

import java.util.ArrayList;
import java.util.List;

public class FunctionType extends AbstractType {
    private List<VarDefinition> parameters = new ArrayList<>();
    private Type returnType;

    public FunctionType(List<VarDefinition> parameters, Type returnType) {
        this.parameters.addAll(parameters);
        this.returnType = returnType;
    }

    public List<VarDefinition> getParameters() {
        return parameters;
    }

    public Type getReturnType() {
        return returnType;
    }

    @Override
    public boolean isEquivalentTo(Type type) {
        if (!(type instanceof FunctionType)) {
            return false;
        }
        FunctionType other = (FunctionType) type;
        if (!this.returnType.isEquivalentTo(other.returnType)) {
            return false;
        }
        if (this.parameters.size() != other.parameters.size()) {
            return false;
        }
        for (int i = 0; i < parameters.size(); i++) {
            if (!parameters.get(i).getType().isEquivalentTo(other.parameters.get(i).getType())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < parameters.size(); i++) {
            sb.append(parameters.get(i).getType());
            if (i < parameters.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(") -> ").append(returnType);
        return sb.toString();
    }
}