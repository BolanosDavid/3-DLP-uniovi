package codegeneration;

import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.expressions.Arithmetic;
import ast.expressions.Cast;
import ast.expressions.Comparison;
import ast.expressions.Logic;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;
import ast.statements.*;
import ast.types.*;
import visitor.Visitor;

public abstract class AbstractCGVisitor<TP, TR> implements Visitor<TP, TR> {

    private final CodeGenerator codeGenerator;

    public AbstractCGVisitor(CodeGenerator codeGenerator) {
        this.codeGenerator = codeGenerator;
    }

    public CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }

    @Override
    public TR visit(Arithmetic a, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + a.getClass().getSimpleName());
    }

    @Override
    public TR visit(UnaryMinus u, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + u.getClass().getSimpleName());
    }

    @Override
    public TR visit(UnaryNot u, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + u.getClass().getSimpleName());
    }

    @Override
    public TR visit(Cast c, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + c.getClass().getSimpleName());
    }

    @Override
    public TR visit(Comparison c, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + c.getClass().getSimpleName());
    }

    @Override
    public TR visit(Logic l, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + l.getClass().getSimpleName());
    }

    @Override
    public TR visit(ArrayAccess a, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + a.getClass().getSimpleName());
    }

    @Override
    public TR visit(CharLiteral c, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + c.getClass().getSimpleName());
    }

    @Override
    public TR visit(FieldAccess f, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + f.getClass().getSimpleName());
    }

    @Override
    public TR visit(IntLiteral i, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + i.getClass().getSimpleName());
    }

    @Override
    public TR visit(Invocation i, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + i.getClass().getSimpleName());
    }

    @Override
    public TR visit(NumberLiteral r, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + r.getClass().getSimpleName());
    }

    @Override
    public TR visit(Variable v, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + v.getClass().getSimpleName());
    }

    @Override
    public TR visit(Assignment a, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + a.getClass().getSimpleName());
    }

    @Override
    public TR visit(IfStatement i, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + i.getClass().getSimpleName());
    }

    @Override
    public TR visit(LogStatement r, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + r.getClass().getSimpleName());
    }

    @Override
    public TR visit(ReturnStatement r, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + r.getClass().getSimpleName());
    }

    @Override
    public TR visit(InputStatement w, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + w.getClass().getSimpleName());
    }

    @Override
    public TR visit(WhileStatement w, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + w.getClass().getSimpleName());
    }

    @Override
    public TR visit(FuncDefinition f, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + f.getClass().getSimpleName());
    }

    @Override
    public TR visit(VarDefinition v, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + v.getClass().getSimpleName());
    }

    @Override
    public TR visit(ArrayType a, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + a.getClass().getSimpleName());
    }

    @Override
    public TR visit(CharType c, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + c.getClass().getSimpleName());
    }

    @Override
    public TR visit(ErrorType e, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + e.getClass().getSimpleName());
    }

    @Override
    public TR visit(FunctionType f, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + f.getClass().getSimpleName());
    }

    @Override
    public TR visit(IntType i, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + i.getClass().getSimpleName());
    }

    @Override
    public TR visit(NumberType n, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + n.getClass().getSimpleName());
    }

    @Override
    public TR visit(RecordType r, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + r.getClass().getSimpleName());
    }

    @Override
    public TR visit(RecordField r, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + r.getClass().getSimpleName());
    }

    @Override
    public TR visit(VoidType v, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + v.getClass().getSimpleName());
    }

    @Override
    public TR visit(Program p, TP o) {
        throw new IllegalStateException("Code generation not implemented for node: " + p.getClass().getSimpleName());
    }
}