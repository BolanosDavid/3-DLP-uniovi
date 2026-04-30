package codegeneration;

import ast.Definition;
import ast.Expression;
import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.statements.*;
import ast.types.FunctionType;
import ast.types.IntType;
import ast.types.VoidType;

public class ExecuteCGVisitor extends AbstractCGVisitor<Void, Void> {
    private final ValueCGVisitor valueCGVisitor;
    private final AddressCGVisitor addressCGVisitor;

    public ExecuteCGVisitor(CodeGenerator codeGen) {
        super(codeGen);
        addressCGVisitor = new AddressCGVisitor(codeGen);
        valueCGVisitor = new ValueCGVisitor(codeGen);
        valueCGVisitor.setAddressCGVisitor(addressCGVisitor);
        addressCGVisitor.setValueCGVisitor(valueCGVisitor);
    }



    /**
     * execute[[Program: defs*]]()=
     *     callMain()
     *     halt()
     */
    @Override
    public Void visit(Program p, Void o) {
        for (Definition d : p.getDefinitions()) {
            if(d instanceof VarDefinition) {
                d.accept(this, o);
            }
        }
        getCodeGenerator().callMain();
        getCodeGenerator().halt();
        for (Definition d : p.getDefinitions()) {
            if(d instanceof FuncDefinition) {
                d.accept(this, o);
            }
        }
        return null;
    }
    /**
     * Solo sirve para poner los comentarios
     * execute[[VariableDefinition: definition -> type ID]]():
     * 		< ' * > type ID < ( offset > definition.offset < ) >
     */
    @Override
    public Void visit(VarDefinition v, Void o) {
        getCodeGenerator().comment(v.getType() +" "+ v.getName() + " (offset " + v.getOffset() + ")");
        return null;
    }

    /**
     *  execute[[FunctionDefinition: definition -> ID type statement* ]]():
     *      ID <:>
     *      for(VarDefinition p : p.type.parameters)
     *          comentarios de los parametros
     *      for(VarDefinition p: p.type.locals)
     *          comentarios de las variables locales
     *      <enter> def.bytesLocalSum
     *      for( Statemets stmt : statement*){
     *          execute[[stmt]]
     *      }
     *      <ret> 0, definition.byteLocalSum, 0 <- TODO: Esto se quitará luego
     */
    @Override
    public Void visit(FuncDefinition f, Void o) {
        getCodeGenerator().commentLine(f.getLine());
        getCodeGenerator().printFunction(f.getName());

        // Comentarios de parámetros
        FunctionType funcType = (FunctionType) f.getType();
        getCodeGenerator().comment("Parameters");
        funcType.getParameters().forEach(param ->
                getCodeGenerator().comment(param.getType() + " " + param.getName()
                        + " (offset " + param.getOffset() + ")")
        );

        getCodeGenerator().comment("Local variables");
        f.getStatements().forEach(s -> {
            if (s instanceof VarDefinition v) {
                getCodeGenerator().comment(v.getType() + " " + v.getName()
                        + " (offset " + v.getOffset() + ")");
            }
        });

        getCodeGenerator().enter(f.getByteLocalSum());

        f.getStatements().forEach(s -> {
            if (!(s instanceof VarDefinition)) {
                s.accept(this, o);
            }
        });

        // ret provisional para funciones void
        if (funcType.getReturnType() instanceof VoidType) {
            getCodeGenerator().ret(0, f.getByteLocalSum(), funcType.getParameters().stream()
                    .mapToInt(p -> p.getType().numberOfBytes())
                    .sum());
        }

        return null;
    }
    /**
     * execute[[Assigment: statement -> expr1 expr2]]():
     *      address[[expr1]]()
     *      value[[expr2]]()
     *      codegenerator.convertTo(expr2.type,expr1.type)
     *      <store> expr1.type.suffix()
     */
    @Override
    public Void visit(Assignment a, Void o) {
        getCodeGenerator().commentLine(a.getLine());
        getCodeGenerator().comment("Assignment");
        a.getLeft().accept(addressCGVisitor, null);
        a.getRight().accept(valueCGVisitor, null);
        getCodeGenerator().convertTo(a.getRight().getType(), a.getLeft().getType());
        getCodeGenerator().store(a.getLeft().getType());
        return null;
    }
    /**
     * execute[[IfStatement: statement1 -> expression statement2* statement3*]]():
     *  int else = cg.getLabel()
     *  int end = cg.getLabel()
     *  value[[expression]]()
     *  cg.convertTo(expression.type, IntType)
     *  <jz else>
     *  for(s in statement2*)
     *      execute[[s]]()
     *  <jmp end>
     *  <label> else <:>
     *  for(s in statement3*)
     *       execute[[s]]()
     *  <label> end <:>
     */
    @Override
    public Void visit(IfStatement s, Void p){
        int elseLabel = getCodeGenerator().getLabel();
        int endLabel = getCodeGenerator().getLabel();
        s.getCondition().accept(valueCGVisitor, null);
        getCodeGenerator().convertTo(s.getCondition().getType(), IntType.getInstance());
        getCodeGenerator().jz(elseLabel);
        s.getThenBody().forEach(s1 -> s1.accept(this, p));
        getCodeGenerator().jmp(endLabel);
        getCodeGenerator().label(elseLabel);
        s.getElseBody().forEach(s1 -> s1.accept(this, p));
        getCodeGenerator().label(endLabel);
        return null;
    }

    /**
     * execute[[WhileStatement: statement -> expression statement*]]():
     *      condition = cg.getLabel()
     * 	    end = cg.getLabel()
     * 	    <label> condition <:>
     * 	    value[[expression]]()
     * 	    cg.convertTo(expression.type, IntType)
     * 	    <jz label> end
     * 	    for(s in statement2*)
     * 		    execute[[s]]()
     * 	    <jmp label> condition
     * 	    <label> end <:>
     */
    @Override
    public Void visit(WhileStatement w, Void p){
        int condition = getCodeGenerator().getLabel();
        int end = getCodeGenerator().getLabel();
        getCodeGenerator().label(condition);
        w.getCondition().accept(valueCGVisitor, null);
        getCodeGenerator().convertTo(w.getCondition().getType(), IntType.getInstance());
        getCodeGenerator().jz(end);
        w.getBody().forEach(s -> s.accept(this, p));
        getCodeGenerator().jmp(condition);
        getCodeGenerator().label(end);
        return null;
    }

    /**
     * execute[[LogStatement: statement -> expr*]]():
     *  forEach(Expression expr : expr*){
     *     value[[expr]]()
     *     < out expr.type.suffix()>
     *  }
     */
    @Override
    public Void visit(LogStatement l, Void o) {
        l.getExpressions().forEach(e -> {
            getCodeGenerator().commentLine(l.getLine());
            getCodeGenerator().comment("Write");
            e.accept(valueCGVisitor, null);
            getCodeGenerator().out(e.getType());
        });
        return null;
    }

    /**
     * execute[[InputStatement: statement -> expr*]]():
     *  forEach(Expression expr : expr*){
     *      value[[expr]]()
     *      <in expr.type.suffix()>
     *  }
     */
    @Override
    public Void visit(InputStatement i, Void o) {
        getCodeGenerator().commentLine(i.getLine());
        i.getExpressions().forEach(e -> {
            getCodeGenerator().comment("Read");
            e.accept(addressCGVisitor, null);
            getCodeGenerator().input(e.getType());
            getCodeGenerator().store(e.getType());
        });
        return null;
    }
}
