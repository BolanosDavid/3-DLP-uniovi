package ast.program;

import java.util.List;
import ast.base.AbstractASTNode;
import ast.definitions.Definition;

public class Program extends AbstractASTNode {
    private List<Definition> definitions;

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }
}
