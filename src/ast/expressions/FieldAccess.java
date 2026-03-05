package ast.expressions;

public class FieldAccess extends AbstractExpression {
    private Expression record;
    private String fieldName;

    public FieldAccess(int line, int column, Expression record, String fieldName) {
        super(line, column);
        this.record = record;
        this.fieldName = fieldName;
    }

    public Expression getRecord() {
        return record;
    }

    public String getFieldName() {
        return fieldName;
    }
    @Override
    public String toString() {
        return record + "." + fieldName;
    }
}
