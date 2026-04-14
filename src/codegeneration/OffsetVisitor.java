package codegeneration;


import ast.Locatable;
import ast.Statement;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.types.FunctionType;
import ast.types.RecordField;
import ast.types.RecordType;
import visitor.AbstractVisitor;

/**
 *   Offset variable global:
 *   Sumatorio val.type.numberOfBytes() (No se cuenta el tamaño de la variable) No hay que tener en cuenta el
 *
 *   Offset variable local:
 *   (menos) "-" Sumatorio de val.type.numberOfBytes() (Hay que contar el tamaño de la variable)
 *   Offset de parametro:
 *   4 + Sumatorio val.type.numberOfBytes()
 *   (No se cuenta el tamaño de la variable) Al apilarlos en pila, el último parámetro
 *   será el que está más cerca del BP. Es decir, ahora hay que tener en cuenta el tamaño de los posteriores
 *   Offset recordfield
 *    No import el tamaño de la variable, importa el tamaño de los anteriores (igual que en las globales) Esto es
 *    porque se toma como dirección de memoria de referencia la dirección de memoria del propio field.
  */


public class OffsetVisitor extends AbstractVisitor<Void,Void> {
    private int globalBytesSum;

    @Override
    public Void visit(VarDefinition v, Void p){
        super.visit(v, p);
        if(v.getScope() == 0){
            v.setOffset(globalBytesSum);        // Variables globales
            globalBytesSum += v.getType().numberOfBytes();
        }
        return null;
    }
    @Override
    public Void visit(RecordType r, Void p) {
        int bytesFieldsSum = 0;
        for(RecordField field : r.getFields()){
            field.getType().accept(this, p);
            field.setOffset(bytesFieldsSum);
            bytesFieldsSum += field.getType().numberOfBytes();
        }
        return null;
    }
    @Override
    public Void visit(FuncDefinition f, Void p){ // usar parámetro booleano para diferenciar entre parámetro y variable local
        super.visit(f, p);
        int localBytesSum = 0;
        for(Statement stmt : f.getStatements()){
            if(stmt instanceof VarDefinition variableActual){
                localBytesSum += variableActual.getType().numberOfBytes();
                variableActual.setOffset(-localBytesSum);
            }
        }
        return null;
    }
    @Override
    public Void visit(FunctionType f, Void p){

        return null;
    }
}
