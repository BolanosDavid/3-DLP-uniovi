package ast.program;

import ast.base.ASTNode;
import ast.definitions.Definition;
import java.util.List;

public class Program implements ASTNode {
    private List<Definition> definitions;

    public Program(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }
}
