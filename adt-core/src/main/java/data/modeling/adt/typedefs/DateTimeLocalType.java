package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

import java.time.LocalDateTime;

public class DateTimeLocalType extends TimestampType {
    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.visit(this);
    }

    @Override
    public boolean isValueOf(Object value) {
        return value instanceof LocalDateTime;
    }
}
