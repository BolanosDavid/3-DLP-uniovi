package ast.types;

import ast.Locatable;
import ast.Type;
import visitor.Visitor;

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
    public Type dot(String fieldName, Locatable location) {
        RecordField field = getField(fieldName);
        if (field != null) {
            return field.getType();
        }
        return super.dot(fieldName, location);
    }

    @Override
    public String toString() {
        return "record";
    }

    @Override
    public <PT, RT> RT accept(Visitor<PT, RT> v, PT tp) {
        return v.visit(this, tp);
    }
    @Override
    public int numberOfBytes(){
        return fields.stream().mapToInt(field -> field.getType().numberOfBytes()).sum();
    }
}
