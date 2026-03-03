package ast.types;

import java.util.ArrayList;
import java.util.List;

public class RecordType extends Type {
    private List<RecordField> fields = new ArrayList<>();

    public RecordType(List<RecordField> fields)
    {
        this.fields.addAll(fields);
    }

    public List<RecordField> getFields() {
        return fields;
    }


    public RecordField getField(String name) {
        for (RecordField field : fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public boolean isEquivalentTo(Type type) {
        if (!(type instanceof RecordType)) {
            return false;
        }
        RecordType other = (RecordType) type;
        if (this.fields.size() != other.fields.size()) {
            return false;
        }
        for (int i = 0; i < fields.size(); i++) {
            if (!fields.get(i).getName().equals(other.fields.get(i).getName()) ||
                !fields.get(i).getType().isEquivalentTo(other.fields.get(i).getType())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("record {");
        for (int i = 0; i < fields.size(); i++) {
            sb.append(fields.get(i).getName()).append(": ").append(fields.get(i).getType());
            if (i < fields.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
