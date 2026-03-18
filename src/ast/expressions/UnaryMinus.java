package ast.expressions;

import ast.Expression;
import visitor.Visitor;

public class UnaryMinus extends UnaryOperation {
    
    public UnaryMinus(int line, int column, Expression expression) {
        super(line, column,expression);
    }
    @Override
    public String toString() {
        return "-" + expression;
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
