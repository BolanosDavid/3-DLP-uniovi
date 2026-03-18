package visitor;

import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;

public interface Visitor<TP, TR> {
    //visit para Program
    TR visit (Program pr, TP p);

    //visit para definitions
    TR visit (FuncDefinition f, TP p);
    TR visit (VarDefinition v, TP p);

    //visit para expressions
    TR visit (Arithmetic a, TP p);
    TR visit (ArrayAccess a, TP p);
    TR visit (Cast c, TP p);
    TR visit (CharLiteral c, TP p);
    TR visit (Comparison c, TP p);
    TR visit (FieldAccess f, TP p);
    TR visit (Invocation f, TP p);
    TR visit (IntLiteral i, TP p);
    TR visit (Logic l, TP p);
    TR visit (NumberLiteral n, TP p);
    TR visit (UnaryMinus u, TP p);
    TR visit (UnaryNot u, TP p);
    TR visit (Variable v, TP p);

    //visit para statements
    TR visit (Assignment a, TP p);
    TR visit (IfStatement i, TP p);
    TR visit (InputStatement i, TP p);
    TR visit (LogStatement l, TP p);
    TR visit (ReturnStatement r, TP p);
    TR visit (WhileStatement w, TP p);

    //visit para types
    TR visit (ArrayType a, TP p);
    TR visit (CharType c, TP p);
    TR visit (ErrorType e, TP p);
    TR visit (FunctionType f, TP p);
    TR visit (IntType i, TP p);
    TR visit (NumberType n, TP p);
    TR visit (RecordField r, TP p);
    TR visit (RecordType r, TP p);
    TR visit (VoidType v, TP p);
}