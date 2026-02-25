package ast.expressions;

import ast.types.Type;

public class Cast extends Expression {
    private Expression expression;
    private Type castType;

    public Cast(int line, int column) {
        super(line, column);
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Type getCastType() {
        return castType;
    }

    public void setCastType(Type castType) {
        this.castType = castType;
    }
}
