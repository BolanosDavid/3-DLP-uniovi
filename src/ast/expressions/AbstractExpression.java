package ast.expressions;

import ast.Expression;
import ast.Type;
import ast.base.AbstractLocatable;
import visitor.Visitor;

public abstract class AbstractExpression extends AbstractLocatable implements Expression {
    protected Type type;
    private boolean lvalue;
    public AbstractExpression(int line, int column) {
        super(line, column);
    }
    @Override
    public Type getType() {
        return type;
    }
    @Override
    public void setType(Type type) {
        this.type = type;
    }
    @Override
    public boolean getLValue(){
        return lvalue;
    }
    @Override
    public void setLValue(boolean lvalue){
        this.lvalue = lvalue;
    }

}
