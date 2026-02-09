package ast;

import java.util.List;

public class RecordType extends Type {
    private List<RecordField> fields;

    public List<RecordField> getFields() {
        return fields;
    }

    public void setFields(List<RecordField> fields) {
        this.fields = fields;
    }

    public RecordField getField(String name) {
        if (fields == null) {
            return null;
        }
        for (RecordField field : fields) {
            if (field != null && name != null && name.equals(field.getName())) {
                return field;
            }
        }
        return null;
    }

    @Override
    public boolean isEquivalentTo(Type type) {
        return type instanceof RecordType;
    }

    @Override
    public String toString() {
        return "record";
    }
}
