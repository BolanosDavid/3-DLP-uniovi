package semantic;

import ast.Expression;
import ast.Type;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.statements.*;
import ast.types.*;
import visitor.AbstractVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor encargado de realizar la comprobación de tipos del programa.
 *
 * <p>Este visitor recorre el AST y asigna a cada expresión su tipo resultante,
 * además de validar que las operaciones, asignaciones, retornos e invocaciones
 * sean semánticamente correctas desde el punto de vista del sistema de tipos.</p>
 *
 * <p>El parámetro heredado se usa principalmente para propagar el tipo de retorno
 * esperado dentro de las funciones, de forma que los {@code return} puedan
 * validarse correctamente.</p>
 */
public class TypeCheckingVisitor extends AbstractVisitor<Type, Void> {

    /**
     * Asigna tipo {@code char} a un literal de carácter.
     *
     * @param c literal de carácter
     * @param type parámetro heredado del visitor
     * @return {@code null}, ya que este visitor no sintetiza valor de retorno
     */
    @Override
    public Void visit(CharLiteral c, Type type) {
        c.setType(CharType.getInstance());
        return null;
    }

    /**
     * Asigna tipo {@code int} a un literal entero.
     *
     * @param i literal entero
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(IntLiteral i, Type type) {
        i.setType(IntType.getInstance());
        return null;
    }

    /**
     * Asigna tipo numérico real a un literal numérico.
     *
     * @param r literal numérico
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(NumberLiteral r, Type type) {
        r.setType(NumberType.getInstance());
        return null;
    }

    /**
     * Comprueba una operación aritmética y asigna el tipo resultante.
     *
     * @param a expresión aritmética
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(Arithmetic a, Type type) {
        super.visit(a, type);
        a.setType(a.getLeft().getType().arithmetic(a.getRight().getType(), a));
        return null;
    }

    /**
     * Comprueba una operación de signo negativo unario y asigna el tipo resultante.
     *
     * @param u expresión de menos unario
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(UnaryMinus u, Type type) {
        super.visit(u, type);
        u.setType(u.getOperand().getType().unaryMinus(u));
        return null;
    }

    /**
     * Comprueba una negación lógica y asigna el tipo resultante.
     *
     * @param u expresión de negación lógica
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(UnaryNot u, Type type) {
        super.visit(u, type);
        u.setType(u.getOperand().getType().unaryNot(u));
        return null;
    }

    /**
     * Asigna a una variable el tipo de su definición asociada.
     *
     * @param v variable
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(Variable v, Type type) {
        super.visit(v, type);
        v.setType(v.getDefinition().getType());
        return null;
    }

    /**
     * Comprueba una operación lógica binaria y asigna el tipo resultante.
     *
     * @param l expresión lógica
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(Logic l, Type type) {
        super.visit(l, type);
        l.setType(l.getLeft().getType().logic(l.getRight().getType(), l));
        return null;
    }

    /**
     * Comprueba una comparación y asigna el tipo resultante.
     *
     * @param c expresión de comparación
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(Comparison c, Type type) {
        super.visit(c, type);
        c.setType(c.getLeft().getType().comparison(c.getRight().getType(), c));
        return null;
    }

    /**
     * Comprueba un cast y asigna el tipo resultante.
     *
     * @param c expresión de cast
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(Cast c, Type type) {
        super.visit(c, type);
        c.setType(c.getExpression().getType().canBeCastedTo(c.getCastType(), c));
        return null;
    }

    /**
     * Comprueba un acceso a array mediante corchetes y asigna el tipo resultante.
     *
     * @param a acceso a array
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(ArrayAccess a, Type type) {
        super.visit(a, type);
        a.setType(a.getArray().getType().squareBrackets(a.getIndex().getType(), a));
        return null;
    }

    /**
     * Comprueba un acceso a campo de un registro y asigna el tipo resultante.
     *
     * @param f acceso a campo
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(FieldAccess f, Type type) {
        super.visit(f, type);
        f.setType(f.getRecord().getType().dot(f.getFieldName(), f));
        return null;
    }

    /**
     * Comprueba una invocación a función y asigna el tipo devuelto por ella.
     *
     * <p>Para ello, primero obtiene los tipos de todos los argumentos reales
     * de la llamada y después comprueba si el tipo de la función admite
     * esa lista de parámetros.</p>
     *
     * @param i invocación a función
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(Invocation i, Type type) {
        super.visit(i, type);

        List<Type> argumentsTypes = new ArrayList<>();
        for (Expression e : i.getArguments()) {
            argumentsTypes.add(e.getType());
        }

        i.setType(i.getFunction().getDefinition().getType().parenthesis(argumentsTypes, i));
        return null;
    }

    /**
     * Comprueba que la condición de un {@code while} sea lógica.
     *
     * @param w sentencia while
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(WhileStatement w, Type type) {
        super.visit(w, type);
        w.getCondition().getType().mustBeLogical(w);
        return null;
    }

    /**
     * Comprueba que la condición de un {@code if} sea lógica.
     *
     * @param i sentencia if
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(IfStatement i, Type type) {
        super.visit(i, type);
        i.getCondition().getType().mustBeLogical(i);
        return null;
    }

    /**
     * Comprueba que el tipo de la parte derecha de una asignación
     * pueda promocionarse al tipo de la parte izquierda.
     *
     * @param a sentencia de asignación
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(Assignment a, Type type) {
        super.visit(a, type);
        a.getRight().getType().mustPromotesTo(a.getLeft().getType(), a);
        return null;
    }

    /**
     * Comprueba que todas las expresiones de una sentencia de salida
     * sean de tipo primitivo incorporado.
     *
     * @param w sentencia de escritura/log
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(LogStatement w, Type type) {
        super.visit(w, type);
        w.getExpressions().forEach(expression -> expression.getType().mustBeBuiltIn(w));
        return null;
    }

    /**
     * Comprueba que todas las expresiones de una sentencia de entrada
     * sean de tipo primitivo incorporado.
     *
     * @param r sentencia de lectura/input
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(InputStatement r, Type type) {
        super.visit(r, type);
        r.getExpressions().forEach(expression -> expression.getType().mustBeBuiltIn(r));
        return null;
    }

    /**
     * Comprueba que el tipo de la expresión devuelta en un {@code return}
     * pueda promocionarse al tipo de retorno esperado de la función.
     *
     * @param r sentencia return
     * @param type tipo de retorno esperado de la función actual
     * @return {@code null}
     */
    @Override
    public Void visit(ReturnStatement r, Type type) {
        super.visit(r, type);
        r.getExpression().getType().mustPromotesTo(type, r);
        return null;
    }

    /**
     * Comprueba que todos los parámetros de una función sean de tipo primitivo incorporado.
     *
     * @param f tipo de función
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(FunctionType f, Type type) {
        super.visit(f, type);

        for (VarDefinition v : f.getParameters()) {
            v.getType().mustBeBuiltIn(v);
        }

        return null;
    }

    /**
     * Comprueba una definición de función.
     *
     * <p>Primero obtiene el tipo de retorno declarado por la función y lo propaga
     * a los nodos hijos para que las sentencias {@code return} puedan validarse.
     * Después comprueba que el retorno declarado de la función sea un tipo
     * primitivo incorporado.</p>
     *
     * @param f definición de función
     * @param type parámetro heredado del visitor
     * @return {@code null}
     */
    @Override
    public Void visit(FuncDefinition f, Type type) {
        type = ((FunctionType) f.getType()).getReturnType();
        super.visit(f, type);

        f.getType().mustBeBuiltIn(f);
        return null;
    }
}