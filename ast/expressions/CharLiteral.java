package ast.expressions;

public class CharLiteral extends Expression {
    private char value;

    public CharLiteral(int line, int column, char value) {
        super(line, column);
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }
}
