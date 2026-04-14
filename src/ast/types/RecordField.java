package ast.types;

import ast.Type;
import ast.base.AbstractLocatable;
import visitor.Visitor;

public class RecordField extends AbstractLocatable {
    private int offset;
    private String name;
    private Type type;

    public RecordField(int line, int column, String name, Type type) {
        super(line, column);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }

    public void setOffset(int bytes) {
        this.offset = bytes;
    }
    public int getOffset() {
        return offset;
    }
}
