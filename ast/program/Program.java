package ast.program;

import java.util.List;
import ast.base.ASTNode;
import ast.definitions.Definition;

public class Program implements ASTNode {
    private List<Definition> definitions;

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }
}
