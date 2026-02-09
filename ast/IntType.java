package ast;

public class IntType extends Type {
    @Override
    public boolean isEquivalentTo(Type type) {
        return type instanceof IntType;
    }

    @Override
    public String toString() {
        return "int";
    }
}
