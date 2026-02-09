package ast.types;

public class VoidType extends Type {
    @Override
    public boolean isEquivalentTo(Type type) {
        return type instanceof VoidType;
    }

    @Override
    public String toString() {
        return "void";
    }
}
