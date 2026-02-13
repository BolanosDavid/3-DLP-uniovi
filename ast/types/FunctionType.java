package ast.types;

import java.util.List;

public class FunctionType extends Type {
    private List<Type> parameterTypes;
    private Type returnType;

    public FunctionType(List<Type> parameterTypes, Type returnType) {
        this.parameterTypes = parameterTypes;
        this.returnType = returnType;
    }

    public List<Type> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(List<Type> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
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
        if (this.parameterTypes.size() != other.parameterTypes.size()) {
            return false;
        }
        for (int i = 0; i < parameterTypes.size(); i++) {
            if (!parameterTypes.get(i).isEquivalentTo(other.parameterTypes.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < parameterTypes.size(); i++) {
            sb.append(parameterTypes.get(i));
            if (i < parameterTypes.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(") -> ").append(returnType);
        return sb.toString();
    }
}
