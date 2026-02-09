package ast.types;

public class ErrorType extends Type {
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
        return type instanceof ErrorType;
    }

    @Override
    public String toString() {
        return "error";
    }

    public static ErrorType getInstance() {
        return new ErrorType();
    }
}
