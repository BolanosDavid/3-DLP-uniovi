package ast.types;

public class ArrayType extends Type {
    private int size;
    private Type elementType;

    public ArrayType(int size, Type elementType) {
        this.size = size;
        this.elementType = elementType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Type getElementType() {
        return elementType;
    }

    public void setElementType(Type elementType) {
        this.elementType = elementType;
    }

    @Override
    public boolean isEquivalentTo(Type type) {
        if (!(type instanceof ArrayType)) {
            return false;
        }
        ArrayType other = (ArrayType) type;
        return this.size == other.size && this.elementType.isEquivalentTo(other.elementType);
    }

    @Override
    public String toString() {
        return "[" + size + "]" + elementType.toString();
    }
}
