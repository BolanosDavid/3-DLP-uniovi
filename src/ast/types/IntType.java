package ast.types;

public class IntType extends Type {
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
