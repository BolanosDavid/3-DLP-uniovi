package codegeneration;


import ast.Expression;
import ast.Statement;
import ast.expressions.*;
import ast.expressions.Arithmetic;
import ast.expressions.Cast;
import ast.expressions.Comparison;
import ast.expressions.Logic;
import ast.expressions.UnaryMinus;
import ast.expressions.UnaryNot;
import ast.statements.Invocation;
import ast.types.FunctionType;
import ast.types.IntType;

public class ValueCGVisitor extends AbstractCGVisitor<Void, Void> {
    private AddressCGVisitor addressCGVisitor;

    public ValueCGVisitor(CodeGenerator codeGenerator) {
        super(codeGenerator);
    }

    public void setAddressCGVisitor(AddressCGVisitor addressCGVisitor) {
        this.addressCGVisitor = addressCGVisitor;
    }

    // Expressions

    /**
     * value[[Variable: expression -> ID definition]]:
     *      address[[expression]]()
     *      <load>  expression.type.suffix()
     */
    @Override
    public Void visit(Variable v, Void o) {
        v.accept(addressCGVisitor, o);
        getCodeGenerator().load(v.getType());
        return null;
    }

    /**
     *  value[[NumberLiteral: expression -> Number.getInstance()]]():
     *      <pushf> Number.getInstance()
     */
    @Override
    public Void visit(NumberLiteral r, Void o) {
        getCodeGenerator().push(r.getValue());
        return null;
    }

    /**
     * value[[IntLiteral: expression -> INT_CONSTANT]]():
     *      <pushi> INT_CONSTANT
     */
    @Override
    public Void visit(IntLiteral i, Void o) {
        getCodeGenerator().push(i.getValue());
        return null;
    }

    /**
     * value[[CharLiteral: expression -> CHAR_CONSTANT]():
     *      <pushb> CHAR_CONSTANT
     */
    @Override
    public Void visit(CharLiteral c, Void o) {
        getCodeGenerator().push(c.getValue());
        return null;
    }

    /**
     * value[[LogicalOperation: expression1 -> expression2 (|| | &&) expression3]]():
     *      value[[expression2]]()
     *      value[[expression3]]()
     *      codegenerator.logical(expression1.operator)
     */
    @Override
    public Void visit(Logic l, Void o) {
        l.getLeft().accept(this, o);
        l.getRight().accept(this, o);
        getCodeGenerator().logical(l.getOperator());
        return null;
    }

    /**
     *  value[[UnaryNot: expression1 -> expression2]]()
     *      value[[expression2]]()
     *      <not>
     */
    @Override
    public Void visit(UnaryNot u, Void o) {
        u.getOperand().accept(this, o);
        getCodeGenerator().not();
        return null;
    }

    /**
     * value[[Comparison: expression1 -> expression2 (> | >= | < | <= | != | ==) expression3]]():
     *      value[[expression2]]()
     *      codegenerator.convertTo(expression2.type, expression1.type)
     *      value[[expression3]]()
     *      codegenerator.convertTo(expression3.type, expression1.type)
     *      codegenerator.comparison(expression1.operator, expression1.type)
     */
    @Override
    public Void visit(Comparison c, Void o) {
        c.getLeft().accept(this, o);
        getCodeGenerator().convertTo(c.getLeft().getType(), c.getType());
        c.getRight().accept(this, o);
        getCodeGenerator().convertTo(c.getRight().getType(), c.getType());
        getCodeGenerator().comparison(c.getOperator(), c.getType());

        return null;
    }

    /**
     *  value[[Arithmetic: expression1 -> expression2 (+ | - | * | / | %) expression3]]():
     *      value[[expression2]]()
     *      codegenerator.convertTo(expression2.type, expression1.type)
     *      value[[expression3]]()
     *      codegenerator.convertTo(expression3.type, expression1.type)
     *      codegenerator.arithmetic(expression1.operator, expression1.type)
     */
    @Override
    public Void visit(Arithmetic a, Void o) {
        a.getLeft().accept(this, o);
        getCodeGenerator().convertTo(a.getLeft().getType(), a.getType());
        a.getRight().accept(this, o);
        getCodeGenerator().convertTo(a.getRight().getType(), a.getType());
        getCodeGenerator().arithmetic(a.getOperator(), a.getType());

        return null;
    }

    /**
     * value[[Cast: expression1 -> expression2 type]]():
     *      value[[expression2]]()
     *      codegenerator.convertTo(expression2.type, type)
     */
    @Override
    public Void visit(Cast c, Void o) {
        c.getExpression().accept(this, o);
        getCodeGenerator().convertTo(c.getExpression().getType(), c.getType());
        return null;
    }

    /**
     * value[[UnaryMinus: expression1 -> expression2]]():
     *      value[[expression2]]()
     *      codegenerator.convertTo(expression2.type, expression1.type)
     *      <pushi> -1
     *      codegenerator.convertTo(IntType, expression1.type)
     *      <mul> expression1.type.suffix()
     */
    @Override
    public Void visit(UnaryMinus u, Void o) {
        u.getOperand().accept(this, o);
        getCodeGenerator().convertTo(u.getOperand().getType(), u.getType());
        getCodeGenerator().push(-1);
        getCodeGenerator().convertTo(IntType.getInstance(), u.getType());
        getCodeGenerator().mul(u.getType());
        return null;
    }


    /**
     *  value [[FieldAccess: expression1 -> expression2 ID]]():
     * 	    address[[expression1]]()
     * 	    <load> expression1.type.suffix()
     */
    @Override
    public Void visit(FieldAccess f, Void o) {
        f.accept(addressCGVisitor, o);
        getCodeGenerator().load(f.getType());
        return null;
    }

    /**
     *  value [[ArrayAccess: expression1 -> expression2 expression3]]():
     * 	    address[[expression1]]()
     * 	    <load> expression1.type.suffix()
     */
    @Override
    public Void visit(ArrayAccess a, Void o) {
        a.accept(addressCGVisitor, o);
        getCodeGenerator().load(a.getType());
        return null;
    }


    /**
     * value[[Invocation: expression1 -> expression2(esto es una variable) expression3*]]():
     *  for(int indice=0; indice<exp3*.size; indice++ ){
     * 		value[[exp3.get(indice)]]()
     * 	    codegenerator.convertTo(exp3.get(indice).type, expression2.type.parameters.get(indice).type)
     * 	<call> expression2.getName()
     */
    @Override
    public Void visit(Invocation i, Void o) {
        for(int indice=0; indice<i.getArguments().size(); indice++  ){
            Expression exp = i.getArguments().get(indice);
            exp.accept(this, o);
            FunctionType functionType = (FunctionType) i.getFunction().getType();
            getCodeGenerator().convertTo(exp.getType(), functionType.getParameters().get(indice).getType());
        }
        getCodeGenerator().call(i.getFunction().getName());
        return null;
    }
}