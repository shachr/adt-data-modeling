package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;

public class DateType implements TemporalType {
    public void accept(AdtVisitor visitor) {
        visitor.visit(this);
    }
}
