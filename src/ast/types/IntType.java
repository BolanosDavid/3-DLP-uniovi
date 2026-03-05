package ast.types;

import ast.Type;

public class IntType extends AbstractType {
    private static IntType instance;

    private IntType() {
    }

    public static IntType getInstance() {
        if (instance == null) {
            instance = new IntType();
        }
        return instance;
    }

    @Override
    public boolean isEquivalentTo(Type type) {
        return type instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }
}
