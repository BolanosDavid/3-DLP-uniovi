package ast;

import java.util.List;

public interface Type extends ASTNode{
    /**
     * Devuelve el numero de bytes de un tipo
     * @return
     */
    int numberOfBytes();
    /**
     * Comprueba que este tipo pueda usarse en un contexto lógico.
     *
     * @param l localización del elemento en el código fuente
     */
    void mustBeLogical(Locatable l);

    /**
     * Comprueba si este tipo admite una operación aritmética con otro tipo.
     *
     * @param t tipo con el que se realiza la operación
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la operación es válida, o un {@code ErrorType} si no lo es
     */
    Type arithmetic(Type t, Locatable l);

    /**
     * Comprueba si este tipo admite una operación lógica con otro tipo.
     * Esta operación se usa para operadores como {@code &&} y {@code ||}.
     *
     * @param t tipo con el que se intenta realizar la operación lógica
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la operación es válida, o un {@code ErrorType} en caso contrario
     */
    Type logic(Type t, Locatable l);

    /**
     * Comprueba si este tipo admite el operador unario negativo.
     *
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la operación es válida, o un {@code ErrorType} si no lo es
     */
    Type unaryMinus(Locatable l);

    /**
     * Comprueba si este tipo puede compararse con otro tipo.
     *
     * @param t tipo con el que se compara
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la comparación es válida, o un {@code ErrorType} si no lo es
     */
    Type comparison(Type t, Locatable l);

    /**
     * Comprueba si este tipo puede promocionarse a otro tipo.
     *
     * @param t tipo destino de la promoción
     * @param l localización del elemento en el código fuente
     */
    void mustPromotesTo(Type t, Locatable l);

    /**
     * Comprueba que este tipo sea un tipo primitivo incorporado del lenguaje.
     *
     * @param l localización del elemento en el código fuente
     */
    void mustBeBuiltIn(Locatable l);

    /**
     * Comprueba si se puede acceder a un campo de este tipo mediante el operador punto.
     *
     * @param st nombre del campo
     * @param l localización del elemento en el código fuente
     * @return el tipo del campo si existe, o un {@code ErrorType} si el acceso no es válido
     */
    Type dot(String st, Locatable l);

    /**
     * Comprueba si este tipo puede invocarse como una función con los argumentos indicados.
     *
     * @param types lista de tipos de los argumentos
     * @param l localización del elemento en el código fuente
     * @return el tipo de retorno si la llamada es válida, o un {@code ErrorType} si no lo es
     */
    Type parenthesis(List<Type> types, Locatable l);

    /**
     * Comprueba si este tipo admite el operador lógico NOT.
     *
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si la operación es válida, o un {@code ErrorType} si no lo es
     */
    Type unaryNot(Locatable l);

    /**
     * Comprueba si este tipo puede indexarse mediante corchetes.
     *
     * @param t tipo usado como índice
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante del acceso, o un {@code ErrorType} si la indexación no es válida
     */
    Type squareBrackets(Type t, Locatable l);

    /**
     * Comprueba si este tipo puede convertirse mediante cast a otro tipo.
     *
     * @param t tipo destino del cast
     * @param l localización del elemento en el código fuente
     * @return el tipo resultante si el cast es válido, o un {@code ErrorType} si no lo es
     */
    Type canBeCastedTo(Type t, Locatable l);


}
