package ast;

public abstract class Type extends AbstractASTNode {
    public abstract boolean isEquivalentTo(Type type);

    @Override
    public abstract String toString();

    public boolean isError() {
        return false;
    }

    public boolean isPrimitive() {
        return false;
    }

    public boolean canPromoteTo(Type target) {
        return false;
    }

    public int promotionLevel() {
        return 0;
    }

    public int getSize() {
        return 0;
    }
}
