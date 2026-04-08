package ast.types;

import ast.Locatable;
import ast.Type;
import visitor.Visitor;

public class IntType extends AbstractType {
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
    public Type arithmetic(Type other, Locatable l) {
        if (other instanceof ErrorType) {
            return other;
        }
        if (other == NumberType.getInstance()) {
            return NumberType.getInstance();
        }
        if (other == this || other == CharType.getInstance()) {
            return this;
        }
        return super.arithmetic(other, l);
    }

    @Override
    public Type unaryMinus(Locatable l) {
        return this;
    }

    @Override
    public Type unaryNot(Locatable l) {
        return this;
    }

    @Override
    public Type logic(Type t, Locatable l) {
        return t == this ? this : super.logic(t, l);
    }

    @Override
    public Type comparison(Type t, Locatable l) {
        if (t == this || t == CharType.getInstance() || t == NumberType.getInstance()) {
            return this;
        }
        return super.comparison(t, l);
    }

    @Override
    public Type canBeCastedTo(Type t, Locatable l) {
        if (t == this
                || t == NumberType.getInstance()
                || t == CharType.getInstance()) {
            return t;
        }
        return super.canBeCastedTo(t, l);
    }

    @Override
    public void mustBeLogical(Locatable l) {
        // Vacío porque int puede usarse como tipo lógico en este lenguaje
    }

    @Override
    public void mustPromotesTo(Type t, Locatable l) {
        if (t == this || t == NumberType.getInstance()) {
            return;
        }
        super.mustPromotesTo(t, l);
    }

    @Override
    public void mustBeBuiltIn(Locatable l) {
        // Vacío porque es un tipo primitivo
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}