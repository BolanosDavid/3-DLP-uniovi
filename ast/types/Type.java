package ast.types;

import ast.base.ASTNode;

public abstract class Type implements ASTNode {
    
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
