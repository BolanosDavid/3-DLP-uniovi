package errorhandler;

import ast.types.ErrorType;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {
    List<ErrorType> errors;
    private ErrorHandler(){
        errors = new ArrayList<ErrorType>();
    }
    private static ErrorHandler instance = new ErrorHandler();
    public static ErrorHandler getInstance(){
        return instance;
    }

    /** Imprime todos los errores */
    public void showErrors(PrintStream out){
        out.println("Se han encontrado " + errors.size() + " error(es):");
        for(ErrorType error : errors){
            out.println(" - " + error.getCause() + " en la linea:"+error.getLocatable().getLine() +" columna:" + error.getLocatable().getColumn());
        }
    }
    /** Añade un error*/
    public void addError(ErrorType type){
        this.errors.add(type);
    }
    /**
     * Comprueba si hay algún error
     * @return
     */
    public boolean anyError(){

        return !errors.isEmpty();
    }
}
