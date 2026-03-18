package semantic;

import ast.Expression;
import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import visitor.Visitor;

public class LValueVisitor implements Visitor<Void, Void> {

    /**──────────────────────────────────────────────────────────────────────
     *                               Definiciones
     * ──────────────────────────────────────────────────────────────────────
     */
    @Override
    public Void visit(Program pr, Void p) {
        pr.getDefinitions().forEach(d -> d.accept(this, p));
        return null;
    }

    @Override
    public Void visit(FuncDefinition f, Void p) {
        f.getType().accept(this, p);
        f.getStatements().forEach(s -> s.accept(this, p));
        return null;
    }

    @Override
    public Void visit(VarDefinition v, Void p) {
        v.getType().accept(this, p);
        return null;
    }

    /**──────────────────────────────────────────────────────────────────────
     *                               Expresiones
     * ──────────────────────────────────────────────────────────────────────
     */

    @Override
    public Void visit(Arithmetic a, Void p) {
        a.getLeft().accept(this, p);
        a.getRight().accept(this, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Void visit(ArrayAccess a, Void p) {
        a.getArray().accept(this, p);
        a.getIndex().accept(this, p);
        a.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Cast c, Void p) {
        c.getExpression().accept(this, p);
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
        c.getLeft().accept(this, p);
        c.getRight().accept(this, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(FieldAccess f, Void p) {
        f.getRecord().accept(this, p);
        f.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Invocation f, Void p) {
        f.getFunction().accept(this, p);
        f.getArguments().forEach(a -> a.accept(this, p));
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
        l.getLeft().accept(this, p);
        l.getRight().accept(this, p);
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
        u.getOperand().accept(this, p);
        u.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryNot u, Void p) {
        u.getOperand().accept(this, p);
        u.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Variable v, Void p) {
        v.setLValue(true);
        return null;
    }

    /**──────────────────────────────────────────────────────────────────────
     *                               Statements
     * ──────────────────────────────────────────────────────────────────────
     */

    @Override
    public Void visit(Assignment a, Void p) {
        a.getLeft().accept(this, p);
        a.getRight().accept(this, p);
        if (!a.getLeft().getLValue()) {
            new ErrorType("Esperado valor direccionable en asignación", a.getLine(), a.getColumn());
        }
        return null;
    }

    @Override
    public Void visit(IfStatement i, Void p) {
        i.getCondition().accept(this, p);
        i.getThenBody().forEach(s -> s.accept(this, p));
        i.getElseBody().forEach(s -> s.accept(this, p));
        return null;
    }

    @Override
    public Void visit(InputStatement i, Void p) {
        i.getExpressions().forEach(e -> e.accept(this, p));
        if (i.getExpressions().stream().anyMatch(e -> !e.getLValue())) {
            new ErrorType("Esperado valor direccionable en el input", i.getLine(), i.getColumn());
        }
        return null;
    }

    @Override
    public Void visit(LogStatement l, Void p) {
        l.getExpressions().forEach(e -> e.accept(this, p));
        return null;
    }

    @Override
    public Void visit(ReturnStatement r, Void p) {
        r.getExpression().accept(this, p);
        return null;
    }

    @Override
    public Void visit(WhileStatement w, Void p) {
        w.getCondition().accept(this, p);
        w.getBody().forEach(s -> s.accept(this, p));
        return null;
    }

    /**──────────────────────────────────────────────────────────────────────
     *                               TIPOS
     * ──────────────────────────────────────────────────────────────────────
    */
    @Override
    public Void visit(ArrayType a, Void p) {
        a.getElementType().accept(this, p);
        return null;
    }

    @Override
    public Void visit(CharType c, Void p) {
        return null;
    }

    @Override
    public Void visit(ErrorType e, Void p) {
        return null;
    }

    @Override
    public Void visit(FunctionType f, Void p) {
        f.getParameters().forEach(par -> par.accept(this, p));
        f.getReturnType().accept(this, p);
        return null;
    }

    @Override
    public Void visit(IntType i, Void p) {
        return null;
    }

    @Override
    public Void visit(NumberType n, Void p) {
        return null;
    }

    @Override
    public Void visit(RecordField r, Void p) {
        r.getType().accept(this, p);
        return null;
    }

    @Override
    public Void visit(RecordType r, Void p) {
        r.getFields().forEach(f -> f.accept(this, p));
        return null;
    }

    @Override
    public Void visit(VoidType v, Void p) {
        return null;
    }
}
