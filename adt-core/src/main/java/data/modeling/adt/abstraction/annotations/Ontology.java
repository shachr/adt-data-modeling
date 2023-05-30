package data.modeling.adt.abstraction.annotations;

// todo: read more here: https://en.wikipedia.org/wiki/RDF_Schema
public abstract class Ontology<T> extends Annotation<T> {

    public Ontology(T value) {
        super(value);
    }
}

