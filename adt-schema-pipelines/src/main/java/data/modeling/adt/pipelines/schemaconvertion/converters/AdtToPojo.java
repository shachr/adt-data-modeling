package data.modeling.adt.pipelines.schemaconvertion.converters;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.*;

import java.util.Set;

public class AdtToPojo implements AdtVisitor {

    private final SchemaContext schemaContext;
    private LabeledType lastSeenLabeledType;

    public AdtToPojo(SchemaContext schemaContext){

        this.schemaContext = schemaContext;
    }

    public SchemaContext apply(){
        this.schemaContext.accept(this);
        return this.schemaContext;
    }

    @Override
    public void enterLabeledType(LabeledType type) {
        lastSeenLabeledType = type;
    }

    @Override
    public void exitLabeledType(LabeledType type) {
        lastSeenLabeledType = null;
    }

    @Override
    public void visit(ProductType type) throws AdtException {
        if(lastSeenLabeledType instanceof NamedType) {
            ProductType productType = (ProductType) type.resolveSubSchemes(schemaContext);
            lastSeenLabeledType.setType(productType);
        }
    }

    @Override
    public void visit(SumType type) throws AdtException {
        SumType sumType = (SumType) type.resolveSubSchemes(schemaContext);
        lastSeenLabeledType.setType(sumType);
    }

    @Override
    public void visit(ReferenceNamedType type) {

    }

    @Override
    public void visit(Set<Annotation<?>> annotations) {

    }

    @Override
    public void visit(Annotation<?> annotation) {

    }

    @Override
    public void visit(NumericType type) {

    }

    @Override
    public void visit(PrimitiveType type) {

    }

    @Override
    public void visit(CollectionType type) {

    }

    @Override
    public void visit(TemporalType type) {

    }

    @Override
    public void visit(AnyType type) {

    }
}
