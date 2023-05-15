package data.modeling.adt.abstraction.compatibility;

import data.modeling.adt.compatibility.Difference;

import java.util.List;

public interface CompatibilityPolicy {
    boolean isCompatible(List<Difference> diffs);
}
