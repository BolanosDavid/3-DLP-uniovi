package codegeneration;

import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.types.FunctionType;
import ast.types.RecordField;
import ast.types.RecordType;
import visitor.AbstractVisitor;

import java.util.List;

/**
 * OffsetVisitor calcula el offset de memoria de cada variable/campo.
 *
 * El parámetro TP=Boolean indica si estamos dentro de una función:
 *   - false → contexto global
 *   - true  → contexto local (dentro de una función)
 *
 * Flujo general:
 *   Program
 *     └─► VarDefinition (global)   → visit(VarDef, false)
 *     └─► FuncDefinition           → visit(FuncDef, false)
 *           └─► FunctionType       → visit(FuncType, false)  ← parámetros
 *           └─► VarDefinition      → visit(VarDef, true)     ← locales
 */
public class OffsetVisitor extends AbstractVisitor<Boolean, Void> {

    // Acumulador de bytes para variables globales (crece hacia adelante: 0, 2, 4...)
    private int globalBytesSum = 0;

    // Acumulador de bytes para variables locales (se resetea en cada función)
    private int localBytesSum = 0;

    @Override
    public Void visit(Program p, Boolean inFunction) {
        p.getDefinitions().forEach(d -> d.accept(this, false));
        return null;
    }

    /**
     * VARIABLES GLOBALES Y LOCALES
     *
     * Este métod se invoca automáticamente desde super.visit() cuando
     * se itera sobre los statements de FuncDefinition o las definiciones
     * del Program. El parámetro 'inFunction' nos dice en qué contexto estamos.
     *
     * Global  (inFunction=false): offset ANTES de contar la variable actual
     *   globalBytesSum: 0  →  var a:int(2)  →  offset=0, sum=2
     *                   2  →  var b:int(2)  →  offset=2, sum=4
     *
     * Local   (inFunction=true):  offset NEGATIVO contando la variable actual
     *   localBytesSum:  0  →  var x:int(2)  →  sum=2, offset=-2
     *                   2  →  var y:real(4) →  sum=6, offset=-6
     */
    @Override
    public Void visit(VarDefinition v, Boolean inFunction) {
        super.visit(v, inFunction); // visita el tipo (por si es RecordType o ArrayType)

        if (!inFunction) {
            // GLOBAL: el offset es el acumulado ANTES de sumar esta variable
            v.setOffset(globalBytesSum);
            globalBytesSum += v.getType().numberOfBytes();
        } else {
            // LOCAL: primero sumamos el tamaño, luego asignamos en negativo
            localBytesSum += v.getType().numberOfBytes();
            v.setOffset(-localBytesSum);
        }
        return null;
    }

    /**
     * DEFINICIÓN DE FUNCIÓN
     *
     * Al entrar en una función:
     *   1. Reseteamos localBytesSum (cada función tiene su propio frame)
     *   2. Llamamos super.visit(f, TRUE) para que todos los hijos
     *      (FunctionType con parámetros + statements con variables locales)
     *      reciban inFunction=true y se procesen en contexto local.
     *
     * Flujo de super.visit(f, true) [ver AbstractVisitor]:
     *   f.getType().accept(this, true)          → visit(FunctionType, true)
     *   f.getStatements().forEach(s ->
     *       s.accept(this, true))               → visit(VarDefinition, true)
     *                                             (o cualquier otro Statement)
     */
    @Override
    public Void visit(FuncDefinition f, Boolean inFunction) {
        localBytesSum = 0; // nuevo frame, nuevo acumulador local
        super.visit(f, true); // propaga true a FunctionType y a todos los statements
        return null;
    }

    /**
     * PARÁMETROS DE FUNCIÓN
     *
     * Los parámetros se apilan en orden inverso al de llamada, por lo que
     * el último parámetro queda más cerca del BP (Base Pointer).
     * El offset parte desde 4 (para saltar la dirección de retorno + BP guardado).
     *
     *   BP+4: último parámetro
     *   BP+6: penúltimo parámetro
     *   ...
     *
     * Ejemplo con (int a, real b):  int=2 bytes, real=4 bytes
     *   paramOffset: 4 → param a:int  → offset=4, paramOffset=6
     *                6 → param b:real → offset=6, paramOffset=10
     *
     * OJO: los parámetros son VarDefinition con scope > 0, pero su offset
     * se calcula aquí manualmente, NO pasando por visit(VarDefinition).
     * Por eso iteramos explícitamente en lugar de llamar super.visit().
     */
    @Override
    public Void visit(FunctionType f, Boolean inFunction) {
        // Recorremos en orden inverso: el último parámetro está más cercano al BP (offset=4)
        // y el primero está más alejado.
        //
        // Pila justo antes de entrar a la función:
        //   BP+4  → pr (último parámetro, real=4 bytes)
        //   BP+8  → pi (primer parámetro, int=2 bytes)
        //   BP+10 → ...

        int paramOffset = 4;
        List<VarDefinition> params = f.getParameters();

        for (int i = params.size() - 1; i >= 0; i--) {
            VarDefinition param = params.get(i);
            param.getType().accept(this, inFunction);
            param.setOffset(paramOffset);
            paramOffset += param.getType().numberOfBytes();
        }

        f.getReturnType().accept(this, inFunction);
        return null;
    }

    /**
     * CAMPOS DE RECORD
     *
     * Similar a las variables globales: el offset de cada campo es
     * la suma de los bytes de los campos ANTERIORES (no se cuenta el actual).
     *
     * Ejemplo: struct { int a; real b; char c; }
     *   bytesFieldsSum: 0 → campo a:int(2)  → offset=0, sum=2
     *                   2 → campo b:real(4) → offset=2, sum=6
     *                   6 → campo c:char(1) → offset=6, sum=7
     */
    @Override
    public Void visit(RecordType r, Boolean inFunction) {
        int bytesFieldsSum = 0;

        for (RecordField field : r.getFields()) {
            field.getType().accept(this, inFunction); // por si hay records anidados
            field.setOffset(bytesFieldsSum);
            bytesFieldsSum += field.getType().numberOfBytes();
        }
        return null;
    }
}