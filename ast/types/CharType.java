package ast.types;

public class CharType extends Type {
    private static CharType instance;

    private CharType() {
    }

    public static CharType getInstance() {
        if (instance == null) {
            instance = new CharType();
        }
        return instance;
    }

    @Override
    public boolean isEquivalentTo(Type type) {
        return type instanceof CharType;
    }

    @Override
    public String toString() {
        return "char";
    }
}
