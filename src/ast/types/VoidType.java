package ast.types;

import ast.Type;

public class VoidType extends AbstractType {
    private static VoidType instance;

    private VoidType() {
    }

    public static VoidType getInstance() {
        if (instance == null) {
            instance = new VoidType();
        }
        return instance;
    }

    @Override
    public boolean isEquivalentTo(Type type) {
        return type instanceof VoidType;
    }

    @Override
    public String toString() {
        return "void";
    }
}
