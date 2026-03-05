package ast.program;

import ast.base.ASTNode;
import ast.definitions.Definition;
import ast.definitions.VarDefinition;

import java.util.ArrayList;
import java.util.List;

public class Program implements ASTNode {
    private List<Definition> definitions = new ArrayList<>();
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

}
