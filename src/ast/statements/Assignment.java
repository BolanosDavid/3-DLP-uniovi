package ast.statements;

import ast.Expression;
import visitor.Visitor;

public class Assignment extends AbstractStatement {
    private Expression left;
    private Expression right;

    public Assignment(int line, int column, Expression left, Expression right) {
        super(line, column);
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return left + " = " + right + ";";
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
