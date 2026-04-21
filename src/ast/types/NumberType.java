package ast.types;

import ast.Locatable;
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
    public Type arithmetic(Type t, Locatable l) {
        if (t instanceof ErrorType) {
            return t;
        }
        if (this == t || t == IntType.getInstance() || t == CharType.getInstance()) {
            return this;
        }
        return super.arithmetic(t, l);
    }

    @Override
    public Type unaryMinus(Locatable l) {
        return this;
    }

    @Override
    public Type comparison(Type t, Locatable l) {
        if (t instanceof ErrorType) {
            return t;
        }
        if (this == t || t == IntType.getInstance() || t == CharType.getInstance()) {
            return IntType.getInstance();
        }
        return super.comparison(t, l);
    }

    @Override
    public Type canBeCastedTo(Type t, Locatable l) {
        if (this == t || t == IntType.getInstance()) {
            return t;
        }
        return super.canBeCastedTo(t, l);
    }

    @Override
    public void mustPromotesTo(Type t, Locatable l) {
        if (this == t) {
            return;
        }
        super.mustPromotesTo(t, l);
    }

    @Override
    public void mustBeBuiltIn(Locatable l) {
        // Vacío porque es primitivo
    }

    @Override
    public String toString() {
        return "number";
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
    @Override
    public int numberOfBytes(){
        return 4;
    }
    public char suffix(){
        return 'f';
    }
}