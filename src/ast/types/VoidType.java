package ast.types;

import ast.Type;
import visitor.Visitor;

public class VoidType extends AbstractType {
    private static VoidType instance;

    private VoidType() {
    }

    public static VoidType getInstance() {
        if (instance == null) {
            instance = new VoidType();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "void";
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
    @Override
    public int numberOfBytes(){
        throw new UnsupportedOperationException("ERROR - Invariante violada: Var definition no puede ser de tipo void");
    }
}
