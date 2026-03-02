package ast.expressions;

public class FieldAccess extends AbstractExpression {
    private AbstractExpression record;
    private String fieldName;

    public FieldAccess(int line, int column) {
        super(line, column);
    }

    public AbstractExpression getRecord() {
        return record;
    }

    public void setRecord(AbstractExpression record) {
        this.record = record;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
