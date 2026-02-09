package ast.types;

public class NumberType extends Type {
    @Override
    public boolean isEquivalentTo(Type type) {
        return type instanceof NumberType;
    }

    @Override
    public String toString() {
        return "number";
    }
}
