package visitor;

import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

public class AbstractVisitor<TP, TR> implements Visitor<TP, TR> {
    /**──────────────────────────────────────────────────────────────────────
     *                               Definiciones
     * ──────────────────────────────────────────────────────────────────────
     */
    @Override
    public TR visit(Program pr, TP p) {
        pr.getDefinitions().forEach(d -> d.accept(this, p));
        return null;
    }

    @Override
    public TR visit(FuncDefinition f, TP p) {
        f.getType().accept(this, p);
        f.getStatements().forEach(s -> s.accept(this, p));
        return null;
    }

    @Override
    public TR visit(VarDefinition v, TP p) {
        v.getType().accept(this, p);
        return null;
    }
    /**──────────────────────────────────────────────────────────────────────
     *                               Expresiones
     * ──────────────────────────────────────────────────────────────────────
     */
    @Override
    public TR visit(Arithmetic a, TP p) {
        a.getLeft().accept(this, p);
        a.getRight().accept(this, p);
        return null;
    }

    @Override
    public TR visit(ArrayAccess a, TP p) {
        a.getArray().accept(this, p);
        a.getIndex().accept(this, p);
        return null;
    }

    @Override
    public TR visit(Cast c, TP p) {
        c.getExpression().accept(this, p);
        return null;
    }

    @Override
    public TR visit(CharLiteral c, TP p) {
        return null;
    }


    @Override
    public TR visit(Comparison c, TP p) {
        c.getLeft().accept(this, p);
        c.getRight().accept(this, p);
        return null;
    }

    @Override
    public TR visit(FieldAccess f, TP p) {
        f.getRecord().accept(this, p);
        return null;
    }

    @Override
    public TR visit(Invocation f, TP p) {
        f.getFunction().accept(this, p);
        f.getArguments().forEach(a -> a.accept(this, p));
        return null;
    }

    @Override
    public TR visit(IntLiteral i, TP p) {
        return null;
    }

    @Override
    public TR visit(Logic l, TP p) {

        l.getLeft().accept(this, p);
        l.getRight().accept(this, p);
        return null;
    }

    @Override
    public TR visit(NumberLiteral n, TP p) {
        return null;
    }

    @Override
    public TR visit(UnaryMinus u, TP p) {

        u.getOperand().accept(this, p);
        return null;
    }

    @Override
    public TR visit(UnaryNot u, TP p) {
        u.getOperand().accept(this, p);
        return null;
    }

    @Override
    public TR visit(Variable v, TP p) {
        return null;
    }

    /**──────────────────────────────────────────────────────────────────────
     *                               Statements
     * ──────────────────────────────────────────────────────────────────────
     */

    @Override
    public TR visit(Assignment a, TP p) {
        a.getLeft().accept(this, p);
        a.getRight().accept(this, p);
        return null;
    }

    @Override
    public TR visit(IfStatement i, TP p) {
        i.getCondition().accept(this, p);
        i.getThenBody().forEach(s -> s.accept(this, p));
        i.getElseBody().forEach(s -> s.accept(this, p));
        return null;
    }

    @Override
    public TR visit(InputStatement i, TP p) {
        i.getExpressions().forEach(e -> e.accept(this, p));
        return null;
    }

    @Override
    public TR visit(LogStatement l, TP p) {
        l.getExpressions().forEach(e -> e.accept(this, p));
        return null;
    }

    @Override
    public TR visit(ReturnStatement r, TP p) {
        r.getExpression().accept(this, p);
        return null;
    }

    @Override
    public TR visit(WhileStatement w, TP p) {
        w.getCondition().accept(this, p);
        w.getBody().forEach(s -> s.accept(this, p));
        return null;
    }

    /**──────────────────────────────────────────────────────────────────────
     *                               TIPOS
     * ──────────────────────────────────────────────────────────────────────
     */
    @Override
    public TR visit(ArrayType a, TP p) {
        a.getElementType().accept(this, p);
        return null;
    }

    @Override
    public TR visit(CharType c, TP p) {
        return null;
    }

    @Override
    public TR visit(ErrorType e, TP p) {
        return null;
    }

    @Override
    public TR visit(FunctionType f, TP p) {
        f.getParameters().forEach(par -> par.accept(this, p));
        f.getReturnType().accept(this, p);
        return null;
    }

    @Override
    public TR visit(IntType i, TP p) {
        return null;
    }

    @Override
    public TR visit(NumberType n, TP p) {
        return null;
    }

    @Override
    public TR visit(RecordField r, TP p) {
        r.getType().accept(this, p);
        return null;
    }

    @Override
    public TR visit(RecordType r, TP p) {
        r.getFields().forEach(f -> f.accept(this, p));
        return null;
    }

    @Override
    public TR visit(VoidType v, TP p) {
        return null;
    }
}