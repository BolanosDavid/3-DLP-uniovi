package ast.types;

import ast.Locatable;
import ast.Type;
import errorhandler.ErrorHandler;

public class ErrorType extends AbstractType {
    private String cause;
    private Locatable locatable;

    public  ErrorType(String cause, Locatable locatable) {
        this.cause = cause;
        this.locatable = locatable;
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
    public Locatable getLocatable(){
        return locatable;
    }
}
