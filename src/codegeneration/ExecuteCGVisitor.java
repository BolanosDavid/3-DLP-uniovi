package codegeneration;

import ast.Definition;
import ast.Expression;
import ast.Program;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.statements.Assignment;
import ast.statements.InputStatement;
import ast.statements.LogStatement;
import ast.statements.WhileStatement;
import ast.types.FunctionType;
import ast.types.VoidType;
import ast.definitions.VarDefinition;
import com.esotericsoftware.kryo.io.Input;

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
        getCodeGenerator().printFunction(f.getName());

        // Comentarios de parámetros
        FunctionType funcType = (FunctionType) f.getType();
        funcType.getParameters().forEach(param ->
                getCodeGenerator().comment("Parameter " + param.getType() + " " + param.getName()
                        + " (offset " + param.getOffset() + ")")
        );

        // Comentarios de variables locales (VarDefinition dentro de statements)
        f.getStatements().stream()
                .filter(s -> s instanceof VarDefinition)
                .map(s -> (VarDefinition) s)
                .forEach(v ->
                        getCodeGenerator().comment("Local " + v.getType() + " " + v.getName()
                                + " (offset " + v.getOffset() + ")")
                );

        getCodeGenerator().enter(f.getByteLocalSum());

        f.getStatements().forEach(s -> s.accept(this, o));

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
     *  execute[[WhileStatement: statement1 -> expression statement2*]]():
     *
     *     forEach(Statement statement: statement2*){
     *          execute[[statement]]()
     *     }
     *
     *
     */
    public Void visit(WhileStatement w , Void p){
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
        getCodeGenerator().commentLine(l.getLine());
        getCodeGenerator().comment("Write");
        l.getExpressions().forEach(e -> {
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
    public Void visit(InputStatement i, Void p) {
        return null;
    }
}
