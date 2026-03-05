package ast;

public interface Type extends ASTNode{
    boolean isEquivalentTo(Type type);
}
