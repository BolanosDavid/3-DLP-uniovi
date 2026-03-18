package ast.types;

import ast.Type;
import visitor.Visitor;

public class CharType extends AbstractType {
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
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
