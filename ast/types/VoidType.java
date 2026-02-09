package ast.types;

public class VoidType extends Type {
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
