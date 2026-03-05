package ast.types;

import ast.Type;

public class NumberType extends AbstractType {
    private static NumberType instance;

    private NumberType() {
    }

    public static NumberType getInstance() {
        if (instance == null) {
            instance = new NumberType();
        }
        return instance;
    }

    @Override
    public boolean isEquivalentTo(Type type) {
        return type instanceof NumberType;
    }

    @Override
    public String toString() {
        return "number";
    }
}
