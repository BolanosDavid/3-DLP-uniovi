package semantic;

import ast.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.types.ErrorType;
import symboltable.SymbolTable;
import visitor.AbstractVisitor;

public class IdentificationVisitor extends AbstractVisitor<Void, Void> {
    private final SymbolTable symbolTable = new SymbolTable();

    @Override
    public Void visit(FuncDefinition f, Void p) {
        if (!symbolTable.insert(f)) {
            new ErrorType("Función con nombre '" + f.getName() + "' ya definida previamente.", f);
            return null;
        }

        symbolTable.set();
        super.visit(f, p);
        symbolTable.reset();
        return null;
    }

    @Override
    public Void visit(VarDefinition v, Void p) {
        if (!symbolTable.insert(v)) {
            new ErrorType("Variable con nombre '" + v.getName() + "' ya definida previamente.", v);
            return null;
        }

        v.getType().accept(this, p);
        return null;
    }

    @Override
    public Void visit(Variable v, Void p) {
        Definition def = symbolTable.find(v.getName());

        if (def == null) {
            def = new VarDefinition(
                    v.getLine(),
                    v.getColumn(),
                    v.getName(),
                    new ErrorType("Variable con nombre '" + v.getName() + "' no definida.", v)
            );
        }

        v.setDefinition(def);
        return null;
    }
}