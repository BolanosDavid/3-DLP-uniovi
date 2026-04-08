package ast;


public interface Definition extends Locatable {
    String getName();
    void setScope(int scope);
    int getScope();
    Type getType();
    void setType(Type type);
}
