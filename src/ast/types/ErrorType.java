package ast.types;

import ast.Type;

public class ErrorType extends AbstractType {
    private static ErrorType instance;

    private ErrorType() {
    }

    public static ErrorType getInstance() {
        if (instance == null) {
            instance = new ErrorType();
        }
        return instance;
    }

    @Override
    public boolean isEquivalentTo(Type type) {
        return true;
    }

    @Override
    public String toString() {
        return "error";
    }
}
