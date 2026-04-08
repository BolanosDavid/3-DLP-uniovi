package ast.types;

import ast.Locatable;
import ast.Type;

import java.util.List;
import java.util.StringJoiner;

/**
 * Implementación base para los distintos tipos del AST.
 *
 * <p>Esta clase proporciona el comportamiento por defecto para las operaciones
 * semánticas entre tipos. En general, si una subclase no sobreescribe uno
 * de estos métodos, se asumirá que la operación no es válida para ese tipo
 * y se generará un {@code ErrorType} descriptivo.</p>
 */
public abstract class AbstractType implements Type {

    /**
     * Devuelve la representación textual del tipo.
     *
     * @return representación textual del tipo
     */
    @Override
    public abstract String toString();

    /**
     * Comprueba que el tipo pueda usarse como expresión lógica.
     * Por defecto, cualquier tipo que no sobrescriba esta función
     * se considera inválido en contexto booleano.
     *
     * @param l localización del elemento en el código fuente
     */
    @Override
    public void mustBeLogical(Locatable l) {
        new ErrorType("Se esperaba una expresión booleana, pero se encontró '" + this + "'.", l);
    }

    /**
     * Comprueba si este tipo admite una operación aritmética con otro tipo.
     * Si el otro tipo ya es un {@code ErrorType}, se propaga directamente.
     *
     * @param t tipo con el que se intenta realizar la operación aritmética
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la operación es válida, o un {@code ErrorType} en caso contrario
     */
    @Override
    public Type arithmetic(Type t, Locatable l) {
        if (t instanceof ErrorType)
            return t;

        return new ErrorType(
                "La operación aritmética no está soportada entre '" + this + "' y '" + t + "'.",
                l
        );
    }
    /**
     * Comprueba si este tipo admite una operación lógica con otro tipo.
     * Esta operación se usa para operadores como {@code &&} y {@code ||}.
     *
     * @param t tipo con el que se intenta realizar la operación lógica
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la operación es válida, o un {@code ErrorType} en caso contrario
     */
    @Override
    public Type logic(Type t, Locatable l) {
        if (t instanceof ErrorType)
            return t;

        return new ErrorType(
                "La operación lógica no está soportada entre '" + this + "' y '" + t + "'.",
                l
        );
    }
    /**
     * Comprueba si este tipo admite el operador unario negativo.
     *
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la operación es válida, o un {@code ErrorType} en caso contrario
     */
    @Override
    public Type unaryMinus(Locatable l) {
        return new ErrorType("La operación de signo negativo no está soportada sobre '" + this + "'.", l);
    }

    /**
     * Comprueba si este tipo puede compararse con otro tipo.
     * Si el otro tipo ya es un {@code ErrorType}, se propaga directamente.
     *
     * @param t tipo con el que se intenta realizar la comparación
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la comparación es válida, o un {@code ErrorType} en caso contrario
     */
    @Override
    public Type comparison(Type t, Locatable l) {
        if (t instanceof ErrorType)
            return t;

        return new ErrorType("No se puede comparar '" + this + "' con '" + t + "'.", l);
    }

    /**
     * Comprueba si este tipo puede promocionarse a otro tipo.
     * Si el tipo destino ya es un {@code ErrorType}, se ignora porque el error
     * ya ha sido detectado previamente.
     *
     * @param t tipo destino de la promoción
     * @param l localización del elemento en el código fuente
     */
    @Override
    public void mustPromotesTo(Type t, Locatable l) {
        if (t instanceof ErrorType)
            return;

        new ErrorType("No se puede promocionar el tipo '" + this + "' a '" + t + "'.", l);
    }

    /**
     * Comprueba que el tipo sea un tipo primitivo incorporado.
     * Por defecto, cualquier tipo que no lo sobrescriba no se considera built-in.
     *
     * @param l localización del elemento en el código fuente
     */
    @Override
    public void mustBeBuiltIn(Locatable l) {
        new ErrorType("Se esperaba un tipo primitivo incorporado, pero se encontró '" + this + "'.", l);
    }

    /**
     * Comprueba si se puede acceder a un campo mediante el operador punto.
     * Por defecto, un tipo genérico no tiene campos accesibles.
     *
     * @param st nombre del campo al que se intenta acceder
     * @param l localización del elemento en el código fuente
     * @return el tipo del campo si el acceso es válido, o un {@code ErrorType} en caso contrario
     */
    @Override
    public Type dot(String st, Locatable l) {
        return new ErrorType("No se puede acceder al campo '" + st + "' sobre el tipo '" + this + "'.", l);
    }

    /**
     * Comprueba si este tipo puede invocarse como si fuera una función
     * con la lista de argumentos dada.
     *
     * <p>Si alguno de los tipos recibidos ya es un {@code ErrorType}, se propaga
     * directamente para no encadenar errores redundantes.</p>
     *
     * @param types lista de tipos de los argumentos recibidos
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante de la llamada si es válida, o un {@code ErrorType} en caso contrario
     */
    @Override
    public Type parenthesis(List<Type> types, Locatable l) {
        StringJoiner receivedTypes = new StringJoiner(", ");

        for (Type type : types) {
            if (type instanceof ErrorType) {
                return type;
            }
            receivedTypes.add(type.toString());
        }

        String arguments = receivedTypes.length() > 0
                ? receivedTypes.toString()
                : "sin parámetros";

        return new ErrorType(
                "La llamada a función no admite esos tipos de argumentos. Recibidos: (" + arguments + ").",
                l
        );
    }

    /**
     * Comprueba si este tipo admite el operador lógico NOT.
     *
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la operación es válida, o un {@code ErrorType} en caso contrario
     */
    @Override
    public Type unaryNot(Locatable l) {
        return new ErrorType("La negación lógica requiere un operando booleano, pero se encontró '" + this + "'.", l);
    }

    /**
     * Comprueba si este tipo puede indexarse mediante corchetes.
     * Por defecto, solo los tipos indexables como arrays deberían sobrescribir esta función.
     *
     * @param t tipo usado como índice
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante del acceso si es válido, o un {@code ErrorType} en caso contrario
     */
    @Override
    public Type squareBrackets(Type t, Locatable l) {
        if (t instanceof ErrorType)
            return t;

        return new ErrorType("No se puede indexar el tipo '" + this + "'. Se esperaba un tipo array.", l);
    }

    /**
     * Comprueba si este tipo puede convertirse mediante cast a otro tipo.
     * Si el tipo destino ya es un {@code ErrorType}, se propaga directamente.
     *
     * @param t tipo destino del cast
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la conversión es válida, o un {@code ErrorType} en caso contrario
     */
    @Override
    public Type canBeCastedTo(Type t, Locatable l) {
        if (t instanceof ErrorType)
            return t;

        return new ErrorType("No se puede convertir el tipo '" + this + "' a '" + t + "'.", l);
    }
}