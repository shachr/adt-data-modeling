package data.modeling.adt.typedefs;

import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;

public abstract class NumericType extends ScalarType {
    public void accept(AdtVisitor visitor) throws AdtException {
        visitor.visit(this);
    }
}
