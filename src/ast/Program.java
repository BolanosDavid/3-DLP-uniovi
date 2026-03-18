package ast;

import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Program implements ASTNode {
    private List<Definition> definitions = new ArrayList<>();
    private boolean LValue;
    public Program(List<Definition> definitions) {
        this.definitions.addAll(definitions);
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }
    @Override
    public String toString() {
        return definitions.stream()
                .map(Object::toString)
                .reduce((a, b) -> a + "\n\n" + b)
                .orElse("");
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }

    public void setLValue(boolean value) {
        this.LValue = value;
    }
    public boolean getLValue(){
        return LValue;
    }
}
