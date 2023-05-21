package data.modeling.adt.pipelines.schemaparsing.parsers;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.annotations.datagovernance.DataHandlingClassification;
import data.modeling.adt.annotations.sdl.TypeDeclaration;
import data.modeling.adt.annotations.syntactic.DefaultValue;
import data.modeling.adt.annotations.documentation.Description;
import data.modeling.adt.enums.DataHandlingClassifications;
import data.modeling.adt.messages.SchemaParsingMessage;
import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.JsonSchemaDraft7ToAdt;
import data.modeling.adt.messages.SchemaParsedMessage;
import data.modeling.adt.typedefs.*;
import data.modeling.processing.abstraction.Task;

import java.util.Objects;
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

    private NamedType lastNamedType;
    private FieldType lastFieldType;
    @Override
    public void enterLabeledType(LabeledType type) {
        if(type instanceof NamedType)
            lastNamedType = (NamedType)type;
        else
            lastFieldType = (FieldType)type;
    }

    @Override
    public void exitLabeledType(LabeledType type) {
        if(type instanceof FieldType)
            lastFieldType = null;
        else
            lastNamedType = null;
    }

    @Override
    public void visit(ProductType type) {

    }

    @Override
    public void visit(SumType type) {

    }

    @Override
    public void visit(AllOfType type) throws AdtException {

    }

    @Override
    public void visit(AnyOfType type) throws AdtException {

    }

    @Override
    public void visit(ReferenceNamedType type) {

    }

    @Override
    public void visit(Set<Annotation<?>> annotations) {
        LabeledType<?> lastLabeledType = !Objects.isNull(lastFieldType) ? lastFieldType : lastNamedType;
        annotations.forEach(annotation -> {
            switch (annotation.getName()) {
                case "x-data-handling-classification" -> {
                    DataHandlingClassifications classification = DataHandlingClassifications.fromString((String) annotation.getValue());
                    DataHandlingClassification dataHandlingClassification = new DataHandlingClassification(classification);
                    lastLabeledType.getAnnotations().add(dataHandlingClassification);
                }
                case "description" ->
                        lastLabeledType.getAnnotations().add(new Description((String) annotation.getValue()));
                case "default" -> lastLabeledType.getAnnotations().add(new DefaultValue(annotation.getValue()));
                case "x-idl-type-declaration" ->
                        lastLabeledType.getAnnotations().add(TypeDeclaration.of((String) annotation.getValue()));
            }
        });
    }

    @Override
    public void visit(Annotation<?> annotation) {

    }

    @Override
    public void visit(NumericType type) {

    }

    @Override
    public void visit(ScalarType type) {

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
