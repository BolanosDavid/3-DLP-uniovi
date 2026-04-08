package ast.types;

import ast.Locatable;
import ast.Type;
import visitor.Visitor;

public class ArrayType extends AbstractType {
    private int size;
    private Type elementType;

    public ArrayType(int size, Type elementType) {
        this.size = size;
        this.elementType = elementType;
    }

    public int getSize() {
        return size;
    }

    public Type getElementType() {
        return elementType;
    }
    @Override
    public Type squareBrackets(Type t, Locatable l) {
        if (t instanceof ErrorType)
            return t;

        if (t == IntType.getInstance())
            return elementType;

        return super.squareBrackets(t, l);
    }

    @Override
    public String toString() {
        return "array";
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
