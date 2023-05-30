package data.modeling.adt.typedefs;

// todo: should this be part of json-schema specifically? such that once its created,
//  a schema pipe can denormalize such that other mappers can understand.
public class FieldAdditionalDefinition extends FieldDefinition {
    public FieldAdditionalDefinition(AnyType type) {
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
        FieldAdditionalDefinition fieldType = (FieldAdditionalDefinition) o;
        return this.getType().equals(fieldType.getType())
                && this.getAnnotations().equals(fieldType.getAnnotations())
                && this.isRequired().equals(fieldType.isRequired());
    }
}
