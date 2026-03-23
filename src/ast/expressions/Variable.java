package ast.expressions;

import ast.Definition;
import visitor.Visitor;

public class Variable extends AbstractExpression {
    private final String name;
    private Definition definition;
    public Variable(int line, int column, String name) {
        super(line, column);
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
    public Definition getDefinition() {
        return definition;
    }
    public void setDefinition(Definition definition) {
        this.definition = definition;
    }
}
