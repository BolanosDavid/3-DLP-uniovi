package ast.types;

import ast.Locatable;
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
    public Type arithmetic(Type t, Locatable l) {
        if(this == t) return IntType.getInstance();
        return super.arithmetic(t, l);
    }

    @Override
    public Type unaryMinus(Locatable l) {
        return IntType.getInstance();
    }

    @Override
    public Type comparison(Type t, Locatable l) {
        if(this == t)  return IntType.getInstance();
        return super.comparison(t, l);
    }

    @Override
    public Type canBeCastedTo(Type t, Locatable l) {
        if(this == t || t == IntType.getInstance() || t == NumberType.getInstance())
            return t;
        return super.canBeCastedTo(t, l);
    }

    @Override
    public void mustPromotesTo(Type t, Locatable l) {
        if(this == t)
            return;
        super.mustPromotesTo(t, l);
    }

    @Override
    public void mustBeBuiltIn(Locatable l) {
        // Vacío porque es primitivo
    }

    @Override
    public String toString() {
        return "char";
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }

    @Override
    public int numberOfBytes(){
        return 1;
    }
}
