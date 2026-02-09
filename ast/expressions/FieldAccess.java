package ast.expressions;

public class FieldAccess extends Expression {
    private Expression record;
    private String fieldName;

    public Expression getRecord() {
        return record;
    }

    public void setRecord(Expression record) {
        this.record = record;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
