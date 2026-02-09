package ast.expressions;

public class CharLiteral extends Expression {
    private char value;

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }
}
