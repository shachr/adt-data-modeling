package data.modeling.adt.abstraction.listeners;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.typedefs.Definition;

public interface AdtListener {
    void annotationAdded(Annotation<?> annotation);
    void namedTypeAdded(Definition objectType);

}
