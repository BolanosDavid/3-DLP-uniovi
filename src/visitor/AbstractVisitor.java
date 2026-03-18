//package visitor;
//
//import ast.Definition;
//import ast.Expression;
//import ast.Program;
//import ast.Statement;
//import ast.definitions.FuncDefinition;
//import ast.definitions.VarDefinition;
//import ast.expressions.*;
//import ast.statements.*;
//import ast.types.*;
//
//public class AbstractVisitor<TP, TR> implements Visitor<TP, TR> {
//    @Override
//    public TR visit(Program pr, TP p) {
//        //
//        return null;
//    }
//
//    @Override
//    public TR visit(FuncDefinition f, TP p) {
//        //
//        return null;
//    }
//
//    @Override
//    public TR visit(VarDefinition v, TP p) {
//        //
//        return null;
//    }
//
//    @Override
//    public TR visit(Arithmetic a, TP p) {
//            //
//        return null;
//    }
//
//    @Override
//    public TR visit(ArrayAccess a, TP p) {
//        //
//        return null;
//    }
//
//    @Override
//    public TR visit(Cast c, TP p) {
//        //
//        return null;
//    }
//
//    @Override
//    public TR visit(CharLiteral c, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(Comparison c, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(FieldAccess f, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(Invocation f, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(IntLiteral i, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(Logic l, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(NumberLiteral n, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(UnaryMinus u, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(UnaryNot u, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(Variable v, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(Assignment a, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(IfStatement i, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(InputStatement i, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(LogStatement l, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(ReturnStatement r, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(WhileStatement w, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(ArrayType a, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(CharType c, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(ErrorType e, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(FunctionType f, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(IntType i, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(NumberType n, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(RecordField r, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(RecordType r, TP p) {
//        return null;
//    }
//
//    @Override
//    public TR visit(VoidType v, TP p) {
//        return null;
//    }
//}