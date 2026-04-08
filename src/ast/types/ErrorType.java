package ast.types;

import ast.Locatable;
import ast.Type;
import ast.base.AbstractLocatable;
import errorhandler.ErrorHandler;
import visitor.Visitor;

import java.util.List;

public class ErrorType extends AbstractType {
    private String cause;
    private Locatable locatable;

    public  ErrorType(String cause, Locatable locatable) {
        this.cause = cause;
        this.locatable = locatable;
        ErrorHandler.getInstance().addError(this);
    }

    @Override
    public String toString() {
        return "error";
    }
    public String getCause(){
        return cause;
    }
    public Locatable getLocatable(){return this.locatable;}

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }

    @Override
    public Type arithmetic(Type t, Locatable l) {
        return this;
    }

    @Override
    public Type unaryMinus(Locatable l) {
        return this;
    }

    @Override
    public Type logic(Type t, Locatable l) {
        return this;
    }

    @Override
    public Type unaryNot(Locatable l) {
        return this;
    }

    @Override
    public Type canBeCastedTo(Type t, Locatable l) {
        return this;
    }

    @Override
    public Type comparison(Type t, Locatable l) {
        return this;
    }

    @Override
    public Type squareBrackets(Type t, Locatable l) {
        return this;
    }

    @Override
    public Type parenthesis(List<Type> types, Locatable l) {
        return this;
    }

    @Override
    public Type dot(String st, Locatable l) {
        return this;
    }

    @Override
    public void mustBeBuiltIn(Locatable l) {

    }

    @Override
    public void mustPromotesTo(Type t, Locatable l) {

    }

    @Override
    public void mustBeLogical(Locatable l) {

    }
}
