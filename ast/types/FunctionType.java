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
        return type instanceof FunctionType;
    }

    @Override
    public String toString() {
        return "function";
    }
}
