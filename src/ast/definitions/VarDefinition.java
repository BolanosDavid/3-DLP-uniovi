package ast.definitions;

import ast.Statement;
import ast.Type;
import visitor.Visitor;

public class VarDefinition extends AbstractDefinition implements Statement {
    private int offset;
    
    public VarDefinition(int line, int column, String name, Type type) {
        super(line, column, name, type);
    }
    @Override
    public String toString() {
        return type + " " + name;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
    public int getOffset(){
        return offset;
    }
    public void setOffset(int offset){
        this.offset= offset;
    }
}
