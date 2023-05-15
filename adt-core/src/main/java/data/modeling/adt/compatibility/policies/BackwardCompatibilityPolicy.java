package data.modeling.adt.compatibility.policies;

import data.modeling.adt.abstraction.compatibility.CompatibilityPolicy;
import data.modeling.adt.compatibility.Difference;
import data.modeling.adt.compatibility.DifferenceTypes;

import java.util.List;

public class BackwardCompatibilityPolicy implements CompatibilityPolicy {
    @Override
    public boolean isCompatible(List<Difference> diffs) {
        return diffs.stream().allMatch(difference ->
                difference.differenceType().isEither(
                        DifferenceTypes.FieldRemoved,
                        DifferenceTypes.FieldRemovedOptional,
                        DifferenceTypes.FieldAddedOptional,
                        DifferenceTypes.TypeAddedWithDefault
                ));
    }
}
