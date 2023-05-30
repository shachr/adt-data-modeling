package data.modeling.adt.mappers.jsonschemadraft7FromAdt.fixtures;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.compatibility.Difference;
import data.modeling.adt.mappers.TestResourceReader;
import data.modeling.adt.mappers.fixtures.FromAdtFixtureData;
import data.modeling.adt.mappers.jsonschemadraft7ToAdt.annotations.JsonSchemaAnnotation;
import data.modeling.adt.typedefs.*;

import java.util.List;
import java.util.Map;

public class SanityFixtureData implements FromAdtFixtureData<Map<String, Object>> {
    final String schemaNamespace = "http://example.com/product.schema.json";

    @Override
    public String toString() {
        return "sanity test";
    }

    @Override
    public String getInputNamedTypeName() {
        return schemaNamespace;
    }

    @Override
    public SchemaContext getInputSchemaContext() {
        SchemaContext schemaContext = new SchemaContext();


        TypeDefinition typeDefinition = TypeDefinition.builder(schemaNamespace, ProductType.of(
                FieldDefinition.builder("productId", new Int32Type())
                        .withAnnotations(new JsonSchemaAnnotation("description", "The unique identifier for a product"))
                        .withIsRequired(true)
                        .build(),
                FieldDefinition.builder("productName", new StringType())
                        .withAnnotations(new JsonSchemaAnnotation("description", "Name of the product"))
                        .withIsRequired(true)
                        .build(),
                FieldDefinition.builder("price", new DoubleType())
                        .withAnnotations(new JsonSchemaAnnotation("description", "The price of the product"),
                                new JsonSchemaAnnotation("exclusiveMinimum", 0))
                        .withIsRequired(true)
                        .build(),
                FieldDefinition.builder("tags", new NullValueType(new SetType(new StringType())))
                        .withAnnotations(
                                new JsonSchemaAnnotation("minItems", 1),
                                new JsonSchemaAnnotation("description", "Tags for the product")
                        )
                        .build(),
                FieldDefinition.builder("dimensions", ProductType.of(
                                FieldDefinition.builder("length", new DoubleType()).withIsRequired(true).build(),
                                FieldDefinition.builder("width", new DoubleType()).withIsRequired(true).build(),
                                FieldDefinition.builder("height", new DoubleType()).withIsRequired(true).build()
                        ))
                        .withAnnotations(new JsonSchemaAnnotation("description", "Product dimensions"))
                        .build(),
                FieldDefinition.builder("color", EnumType.of(
                                new StringType(),
                                new EnumType.EnumItemType("red", StringType.constantOf("red")),
                                new EnumType.EnumItemType("green", StringType.constantOf("green")),
                                new EnumType.EnumItemType("blue", StringType.constantOf("blue"))
                        ))
                        .withAnnotations(new JsonSchemaAnnotation("description", "Product color"))
                        .build(),
                FieldDefinition.builder("isAvailable", new BoolType())
                        .withAnnotations(new JsonSchemaAnnotation("description", "Product availability"))
                        .build()
        )).build();

        typeDefinition.getAnnotations().add(new JsonSchemaAnnotation("$schema", "http://json-schema.org/draft-07/schema#"));
        typeDefinition.getAnnotations().add(new JsonSchemaAnnotation("$id", schemaNamespace));
        typeDefinition.getAnnotations().add(new JsonSchemaAnnotation("title", "Product"));
        typeDefinition.getAnnotations().add(new JsonSchemaAnnotation("description", "A product from Acme's catalog"));

        schemaContext.registerNamedType(typeDefinition);
        return schemaContext;
    }

    @Override
    public Map<String, Object> getExpectedSchema() throws Exception {
        Map<String, Object> map = new TestResourceReader().readJsonSchema("from-adt/sanity.json");
        map.put("$id", schemaNamespace);
        return map;
    }

    @Override
    public List<Difference> getExpectedDifferences() {
        return null;
    }
}
