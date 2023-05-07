package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

import java.time.LocalDateTime;

public class DateTimeType implements TemporalType {
    public void accept(AdtVisitor visitor) {
        visitor.visit(this);
    }
}
