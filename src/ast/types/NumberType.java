package ast.types;

import ast.Type;
import visitor.Visitor;

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
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
