package ast.types;

import ast.Type;

import java.util.ArrayList;
import java.util.List;

public class RecordType extends AbstractType {
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
        return toStringWithIndent("");
    }

    private String toStringWithIndent(String currentIndent) {
        if (fields.isEmpty()) {
            return "[]";
        }

        String nextIndent = currentIndent + "    ";

        StringBuilder sb = new StringBuilder("[\n");
        for (RecordField field : fields) {
            sb.append(nextIndent).append("let ").append(field.getName()).append(": ");

            if (field.getType() instanceof RecordType) {
                sb.append(((RecordType) field.getType()).toStringWithIndent(nextIndent));
            } else {
                sb.append(field.getType());
            }

            sb.append(";\n");
        }
        sb.append(currentIndent).append("]");
        return sb.toString();
    }
}
