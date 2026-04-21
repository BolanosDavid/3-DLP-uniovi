package codegeneration;

import ast.Definition;
import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.statements.Assignment;
import ast.statements.WhileStatement;

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

    public Void visit(WhileStatement w , Void p){
        getCodeGenerator().commentLine(w.getLine());
        getCodeGenerator().comment("While");
        w.getCondition().accept(valueCGVisitor, null);
        w.getBody().forEach(s -> s.accept(this, p));
        return null;
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
        getCodeGenerator().label(f.getLine());
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
}
