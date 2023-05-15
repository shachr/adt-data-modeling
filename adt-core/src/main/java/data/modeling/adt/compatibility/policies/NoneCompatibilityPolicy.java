package data.modeling.adt.compatibility.policies;

import data.modeling.adt.abstraction.compatibility.CompatibilityPolicy;
import data.modeling.adt.compatibility.Difference;

import java.util.List;

public class NoneCompatibilityPolicy implements CompatibilityPolicy {
    @Override
    public boolean isCompatible(List<Difference> diffs) {
        return true;
    }
}
