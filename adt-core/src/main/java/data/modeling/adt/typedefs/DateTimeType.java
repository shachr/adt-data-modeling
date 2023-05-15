package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

import java.time.LocalDateTime;

public class DateTimeType implements TemporalType {
    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.visit(this);
    }
}
