package ast;

public interface Locatable extends ASTNode{
    int getLine();
    int getColumn();
}
