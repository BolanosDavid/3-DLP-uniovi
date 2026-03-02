package ast.expressions;

import ast.types.Type;

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

}
