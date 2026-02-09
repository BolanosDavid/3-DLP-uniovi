package ast;

public class ErrorType extends Type {
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
