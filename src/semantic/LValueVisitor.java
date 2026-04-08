package semantic;

import ast.Expression;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import visitor.AbstractVisitor;

public class LValueVisitor extends AbstractVisitor<Void, Void> {


    /**──────────────────────────────────────────────────────────────────────
     *                               Expresiones
     * ──────────────────────────────────────────────────────────────────────
     */

    @Override
    public Void visit(Arithmetic a, Void p) {
        super.visit(a, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Void visit(ArrayAccess a, Void p) {
        super.visit(a, p);
        a.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Cast c, Void p) {
        super.visit(c, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(CharLiteral c, Void p) {
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Comparison c, Void p) {
        super.visit(c, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(FieldAccess f, Void p) {
        super.visit(f, p);
        f.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Invocation f, Void p) {
        super.visit(f, p);
        f.setLValue(false);
        return null;
    }

    @Override
    public Void visit(IntLiteral i, Void p) {
        i.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Logic l, Void p) {
        super.visit(l, p);
        l.setLValue(false);
        return null;
    }

    @Override
    public Void visit(NumberLiteral n, Void p) {
        n.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryMinus u, Void p) {
        super.visit(u, p);
        u.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryNot u, Void p) {
        super.visit(u, p);
        u.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Variable v, Void p) {
        super.visit(v, p);
        v.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Assignment a, Void p){
        super.visit(a, p);
        if (!a.getLeft().getLValue()) {
            new ErrorType( "Se esperaba un valor redireccionable en asignación ",a);
        }
        return null;
    }
    public Void visit(InputStatement i, Void p) {
        super.visit(i, p);
        for(Expression e : i.getExpressions()){
            if (!e.getLValue()) {
                new ErrorType("Se esperaba valor redireccionable en input",e);
            }
        }
        return null;
    }

}
