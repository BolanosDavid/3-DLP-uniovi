package ast;

import visitor.Visitor;

public interface ASTNode {
    <PT, RT> RT accept(Visitor<PT, RT> v, PT tp);
}
