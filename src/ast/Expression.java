package ast;

public interface Expression extends Locatable {
    boolean getLValue();
    void setLValue(boolean lValue);
}
