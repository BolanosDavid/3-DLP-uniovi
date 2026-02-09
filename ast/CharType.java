package ast;

public class CharType extends Type {
    @Override
    public boolean isEquivalentTo(Type type) {
        return type instanceof CharType;
    }

    @Override
    public String toString() {
        return "char";
    }
}
