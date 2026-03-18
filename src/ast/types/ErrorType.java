package ast.types;

import ast.Locatable;
import ast.Type;
import ast.base.AbstractLocatable;
import errorhandler.ErrorHandler;
import visitor.Visitor;

public class ErrorType extends AbstractType {
    private String cause;
    private int linea;
    private int columna;

    public  ErrorType(String cause, int linea,int columna) {
        this.cause = cause;
        this.linea = linea;
        this.columna = columna;
        ErrorHandler.getInstance().addError(this);
    }


    @Override
    public boolean isEquivalentTo(Type type) {
        return true;
    }

    @Override
    public String toString() {
        return "error";
    }
    public String getCause(){
        return cause;
    }
    public int getLinea(){
        return linea;
    }
    public int getColumna(){
        return columna;
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
