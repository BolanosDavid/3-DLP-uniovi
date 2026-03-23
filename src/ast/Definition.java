package ast;


public interface Definition extends Locatable {
    public String getName();
    public void setScope(int scope);
    public int getScope();
}
