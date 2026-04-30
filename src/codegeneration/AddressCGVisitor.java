package codegeneration;

import ast.definitions.VarDefinition;
import ast.expressions.ArrayAccess;
import ast.expressions.FieldAccess;
import ast.expressions.Variable;
import ast.types.IntType;
import ast.types.RecordType;

public class AddressCGVisitor extends AbstractCGVisitor<Void, Void> {
    private ValueCGVisitor valueCGVisitor;
    public AddressCGVisitor(CodeGenerator codeGenerator) {
        super(codeGenerator);
    }
    public void setValueCGVisitor(ValueCGVisitor valueCGVisitor) {
        this.valueCGVisitor = valueCGVisitor;
    }

    /**
     * address[[Variable: expr -> ID]]():
     * 		if(exp.definition.scope == 0)
     * 			<pusha> exp.definition.offset
     * 		else
     * 			<pushbp>
     * 			<pushi> exp.definition.offset
     * 			<addi>
     */
    public Void visit(Variable v, Void p) {
        if(v.getDefinition().getScope() == 0){
            VarDefinition varDefinition = (VarDefinition) v.getDefinition();
            getCodeGenerator().pusha(varDefinition.getOffset());
        }else{
            getCodeGenerator().pushBP();
            getCodeGenerator().push(((VarDefinition)v.getDefinition()).getOffset());
            getCodeGenerator().add(IntType.getInstance());
        }
        return null;
    }
    /**
     * address[[ArrayAcces: expression1 -> expression2 expression3 ]]()=
     *      address[[expression1]]()
     *      value[[expression3]]()
     *      cg.convertTo(expression3.type, IntType)
     *      <pushi> expression1.type.numberOfBytes()
     *      <muli>
     *      <addi>
     */
    @Override
    public Void visit(ArrayAccess a, Void p) {
        a.getArray().accept(this, p);
        a.getIndex().accept(valueCGVisitor, p);
        getCodeGenerator().convertTo(a.getIndex().getType(), IntType.getInstance());
        getCodeGenerator().push(a.getType().numberOfBytes());
        getCodeGenerator().mul(IntType.getInstance());
        getCodeGenerator().add(IntType.getInstance());

        return null;
    }


    /**
     * address[[FieldAccess:  expression1 -> expression2 ID]]():
     * 	    address[[expression2]]()
     * 	    <pushi> expression2.type.getField(ID).offset
     *  	<addi>
     */
    @Override
    public Void visit(FieldAccess f, Void p) {
        f.getRecord().accept(this, p);
        getCodeGenerator().push(((RecordType)f.getRecord().getType()).getField(f.getFieldName()).getOffset());
        getCodeGenerator().add(IntType.getInstance());
        return null;
    }


}
