package data.modeling.adt.pipelines.schemaparsing.parsers;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.annotations.golden.DataHandlingClassification;
import data.modeling.adt.annotations.golden.DefaultValue;
import data.modeling.adt.annotations.golden.Description;
import data.modeling.adt.enums.DataHandlingClassifications;
import data.modeling.adt.messages.SchemaParsingMessage;
import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.JsonSchemaDraft7ToAdt;
import data.modeling.adt.messages.SchemaParsedMessage;
import data.modeling.adt.typedefs.*;
import data.modeling.processing.abstraction.Task;

import java.util.Set;

public class JsonSchemaTypeParser implements Task<SchemaParsingMessage, SchemaParsedMessage>, AdtVisitor {
    @Override
    public boolean shouldExecute(SchemaParsingMessage message) {
        return message.getContentType().equals("application/schema+json");
    }

    @Override
    public SchemaParsedMessage execute(SchemaParsingMessage message) throws AdtException {
        JsonSchemaDraft7ToAdt mapper = new JsonSchemaDraft7ToAdt((String)message.getMessage());
        SchemaParsedMessage schemaParsedMessage = new SchemaParsedMessage(new SchemaContext(mapper.stream()));
        schemaParsedMessage.getSchemaContext().accept(this);
        return schemaParsedMessage;
    }

    private LabeledType lastLabeledType;
    @Override
    public void enterNamedType(LabeledType type) {
        lastLabeledType = type;
    }

    @Override
    public void exitNamedType(LabeledType type) {
        lastLabeledType = null;
    }

    @Override
    public void visit(ProductType type) {

    }

    @Override
    public void visit(SumType type) {

    }

    @Override
    public void visit(ReferenceObjectType type) {

    }

    @Override
    public void visit(Set<Annotation<?>> annotations) {
        for (Annotation<?> annotation : annotations) {
            if (annotation.getName().equals("x-data-handling-classification")) {
                DataHandlingClassifications classification = DataHandlingClassifications.fromString((String)annotation.getValue());
                DataHandlingClassification dataHandlingClassification = new DataHandlingClassification(classification);
                lastLabeledType.getAnnotations().add(dataHandlingClassification);
            } else if (annotation.getName().equals("description")) {
                lastLabeledType.getAnnotations().add(new Description((String)annotation.getValue()));
            } else if(annotation.getName().equals("default")){
                lastLabeledType.getAnnotations().add(new DefaultValue(annotation.getValue()));
            }
        }
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
