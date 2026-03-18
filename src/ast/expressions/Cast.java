package ast.expressions;

import ast.Expression;
import ast.Type;
import visitor.Visitor;

public class Cast extends AbstractExpression {
    private Expression expression;
    private Type castType;

    public Cast(int line, int column,Expression expression, Type castType) {

        super(line, column);
        this.expression = expression;
        this.castType = castType;
    }

    public Expression getExpression() {
        return expression;
    }


    public Type getCastType() {
        return castType;
    }

    @Override
    public String toString() {
        return "(" + expression + " as " + castType + ")";
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
}
