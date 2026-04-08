package ast.types;

import ast.Locatable;
import ast.Type;
import ast.definitions.VarDefinition;
import visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class FunctionType extends AbstractType {
    private List<VarDefinition> parameters = new ArrayList<>();
    private Type returnType;

    public FunctionType(List<VarDefinition> parameters, Type returnType) {
        this.parameters.addAll(parameters);
        this.returnType = returnType;
    }

    public List<VarDefinition> getParameters() {
        return parameters;
    }

    public Type getReturnType() {
        return returnType;
    }

    @Override
    public String toString() {
        return "function type";
    }
    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
    @Override
    public Type parenthesis(List<Type> types, Locatable l) {
        if (types.size() != parameters.size()) {
            return super.parenthesis(types, l);
        }

        for (int i = 0; i < parameters.size(); i++) {
            if (types.get(i) instanceof ErrorType) {
                return types.get(i);
            }
            types.get(i).mustPromotesTo(parameters.get(i).getType(), l);
        }
        return returnType;
    }

    @Override
    public void mustBeBuiltIn(Locatable l) {
        if (returnType != VoidType.getInstance()) {
            returnType.mustBeBuiltIn(l);
        }
    }
}