package semantic;


import ast.Expression;
import ast.Program;
import ast.base.AbstractLocatable;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import visitor.Visitor;
//import visitor.AbstractVisitor;

public class LValueVisitor<TP, TR> implements Visitor<Void,Void> /**extends AbstractVisitor<Void, Void>**/ {

    @Override
    public Void visit(Program pr, Void p) {
        pr.getDefinitions().forEach(d -> d.accept(this, p));
        pr.accept(this, p);
        return null;
    }

    @Override
    public Void visit(FuncDefinition f, Void p) {
        f.getStatements().forEach(s -> s.accept(this, p));
        f.accept(this, p);
        return null;
    }

    @Override
    public Void visit(VarDefinition v, Void p) {
        v.accept(this, p);
        return null;
    }

    @Override
    public Void visit(Arithmetic a, Void p) {
        a.getLeft().accept(this, p);
        a.getRight().accept(this, p);
        a.setLValue(false);
        return null;
    }

    @Override
    public Void visit(ArrayAccess a, Void p) {
        a.accept(this, p);
        a.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Cast c, Void p) {
        c.accept(this, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(CharLiteral c, Void p) {
        c.accept(this, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Comparison c, Void p) {
        c.accept(this, p);
        c.setLValue(false);
        return null;
    }

    @Override
    public Void visit(FieldAccess f, Void p) {
        f.accept(this, p);
        f.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Invocation f, Void p) {
        f.accept(this, p);
        f.setLValue(false);
        return null;
    }

    @Override
    public Void visit(IntLiteral i, Void p) {
        i.accept(this, p);
        i.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Logic l, Void p) {
        l.accept(this, p);
        l.setLValue(false);
        return null;
    }

    @Override
    public Void visit(NumberLiteral n, Void p) {
        n.accept(this, p);
        n.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryMinus u, Void p) {
        u.accept(this, p);
        u.setLValue(false);
        return null;
    }

    @Override
    public Void visit(UnaryNot u, Void p) {
        u.accept(this, p);
        u.setLValue(false);
        return null;
    }

    @Override
    public Void visit(Variable v, Void p) {
        v.accept(this, p);
        v.setLValue(true);
        return null;
    }

    @Override
    public Void visit(Assignment a, Void p) {
        a.getLeft().accept(this, p);
        a.getRight().accept(this, p);
        if (!a.getLeft().getLValue()) {
            new ErrorType("Esperado valor direccionable en asignación",a.getLine(), a.getColumn());
        }
        return null;
    }

    @Override
    public Void visit(IfStatement i, Void p) {
        i.getCondition().accept(this, p);
        i.getElseBody().forEach(s -> s.accept(this, p));
        i.getThenBody().forEach(s -> s.accept(this, p));
        i.accept(this, p);
        return null;
    }

    @Override
    public Void visit(InputStatement i, Void p) {
        for(Expression e : i.getExpressions()){
            e.accept(this, p);
        }
        i.accept(this, p);
        return null;
    }

    @Override
    public Void visit(LogStatement l, Void p) {
        l.getExpressions().forEach(e -> e.accept(this, p));
        l.accept(this, p);
        return null;
    }

    @Override
    public Void visit(ReturnStatement r, Void p) {
        r.getExpression().accept(this, p);
        r.accept(this, p);
        return null;
    }

    @Override
    public Void visit(WhileStatement w, Void p) {
        w.getCondition().accept(this, p);
        w.getBody().forEach(s -> s.accept(this, p));
        w.accept(this, p);
        return null;
    }

    @Override
    public Void visit(ArrayType a, Void p) {
        a.accept(this, p);
        return null;
    }

    @Override
    public Void visit(CharType c, Void p) {
        c.accept(this, p);
        return null;
    }

    @Override
    public Void visit(ErrorType e, Void p) {
        e.accept(this, p);
        return null;
    }

    @Override
    public Void visit(FunctionType f, Void p) {
        f.getParameters().forEach(par -> par.accept(this,p));
        f.getReturnType().accept(this,p);
        f.accept(this, p);
        return null;
    }

    @Override
    public Void visit(IntType i, Void p) {
        i.accept(this, p);
        return null;
    }

    @Override
    public Void visit(NumberType n, Void p) {
        n.accept(this, p);
        return null;
    }

    @Override
    public Void visit(RecordField r, Void p) {
        r.getType().accept(this, p);
        r.accept(this, p);
        return null;
    }

    @Override
    public Void visit(RecordType r, Void p) {
        r.getFields().forEach(f -> f.accept(this, p));
        r.accept(this, p);
        return null;
    }

    @Override
    public Void visit(VoidType v, Void p) {
        v.accept(this, p);
        return null;
    }
}
