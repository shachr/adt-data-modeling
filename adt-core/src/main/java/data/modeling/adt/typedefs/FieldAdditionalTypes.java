package data.modeling.adt.typedefs;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;

public class FieldAdditionalTypes extends FieldType {
    public FieldAdditionalTypes(AnyType type) {
        super(null, type);
    }

    @Override
    public int hashCode() {
        return this.getType().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldAdditionalTypes fieldType = (FieldAdditionalTypes) o;
        return this.getType().equals(fieldType.getType())
                && this.getAnnotations().equals(fieldType.getAnnotations())
                && this.isRequired().equals(fieldType.isRequired());
    }
}
