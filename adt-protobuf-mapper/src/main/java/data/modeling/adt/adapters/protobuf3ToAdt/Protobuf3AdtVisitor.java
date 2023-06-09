package data.modeling.adt.adapters.protobuf3ToAdt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.*;
import data.modeling.adt.util.LambdaExceptionUtil;
import data.modeling.antlr4.Protobuf3BaseVisitor;
import data.modeling.antlr4.Protobuf3Parser;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

class Protobuf3AdtVisitor extends Protobuf3BaseVisitor<SchemaContext> {

    // proto docs: https://protobuf.dev/programming-guides/proto3/
    //  https://protobuf.dev/programming-guides/dos-donts/

    private final SchemaContext schemaContext;

    public Protobuf3AdtVisitor(){
        this(new SchemaContext());
    }
    public Protobuf3AdtVisitor(SchemaContext schemaContext){

        this.schemaContext = schemaContext;
    }

    @Override
    public SchemaContext visitImportStatement(Protobuf3Parser.ImportStatementContext ctx) {
        return super.visitImportStatement(ctx);
        // todo: start a new protoToAdt with the same schema context
        //  and avoid cyclic reference using shared traversing context.
    }

    @Override
    public SchemaContext visitPackageStatement(Protobuf3Parser.PackageStatementContext ctx) {
        String packageName = ctx.fullIdent().getText();
        schemaContext.setName(packageName);
        return schemaContext;
    }


    @Override
    public SchemaContext visitEnumDef(Protobuf3Parser.EnumDefContext ctx) {
        readEnumDefinition(ctx);
        return schemaContext;
    }

    @Override
    public SchemaContext visitMessageDef(Protobuf3Parser.MessageDefContext ctx) {
        this.readMessageDefinition(ctx);
        return schemaContext;
    }

    private Stream<AnyType> readMessageDefinition(Protobuf3Parser.MessageDefContext ctx) {
        String name = ctx.messageName().getText();

        List<AnyType> typeList = ctx.messageBody().messageElement()
                .stream()
                .flatMap(LambdaExceptionUtil.function(elm -> {
                    if(!Objects.isNull(elm.enumDef())){
                        readEnumDefinition(elm.enumDef());
                        return Stream.empty();
                    }
                    else if(!Objects.isNull(elm.field()))
                        return readField(elm.field());
                    else if(!Objects.isNull(elm.messageDef()))
                        return readMessageDefinition(elm.messageDef());
                    else if(!Objects.isNull(elm.extendDef()))
                        return readExternalDef(elm.extendDef());
                    else if(!Objects.isNull(elm.optionStatement()))
                        return readOptionStatement(elm.optionStatement());
                    else if(!Objects.isNull(elm.oneof()))
                        return Stream.of(readOneOf(elm.oneof()));
                    else if(!Objects.isNull(elm.mapField())) {
                        return readMapField(elm.mapField());
                    }
                    else if(!Objects.isNull(elm.reserved()))
                        return Stream.empty();
                    else if(!Objects.isNull(elm.emptyStatement_()))
                        return Stream.empty();
                    else
                        throw new AdtException("not implemented");
                }))
                        .toList();

        ProductType productType = ProductType.of(typeList.stream()
                .filter(type -> type instanceof FieldDefinition)
                .map(type->(FieldDefinition)type)
                .sorted(Comparator.comparingInt(FieldDefinition::getIndex)));

        schemaContext.registerNamedType(new TypeDefinition(name, productType));
        return Stream.empty();
    }

    private Stream<AnyType> readField(Protobuf3Parser.FieldContext field) throws AdtException {
        String fieldName = field.fieldName().ident().IDENTIFIER().toString();
        String label = field.fieldLabel().getText();
        String type = field.type_().getText();
        //todo: fieldOptions()
        FieldDefinition fieldDefinition = FieldDefinition.builder(fieldName, toAdt(label, type)).build();
        fieldDefinition.setIndex(Integer.parseInt(field.fieldNumber().getText()));
        fieldDefinition.setRequired(!label.equals("optional"));
        return Stream.of(fieldDefinition);
    }

    private Stream<FieldDefinition> readMapField(Protobuf3Parser.MapFieldContext mapField) throws AdtException {
        FieldDefinition fieldDefinition = new FieldDefinition(
                mapField.mapName().getText(),
                new MapType(
                        toAdt("", mapField.keyType().getText()),
                        toAdt("", mapField.type_().getText())));

        fieldDefinition.setIndex(Integer.parseInt(mapField.fieldNumber().getText()));
        return Stream.of(fieldDefinition);
    }

    private FieldDefinition readOneOf(Protobuf3Parser.OneofContext oneof) {
        return FieldDefinition.of(
                oneof.oneofName().getText(),
                UnionType.of(oneof.oneofField().stream().map(LambdaExceptionUtil.function(this::readOneOfField)))
        );
    }

    private ProductType readOneOfField(Protobuf3Parser.OneofFieldContext field) throws AdtException {
        String fieldName = field.fieldName().ident().IDENTIFIER().toString();
        String type = field.type_().getText();
        //todo: fieldOptions()
        FieldDefinition fieldDefinition = FieldDefinition.builder(fieldName, toAdt("", type)).build();
        fieldDefinition.setIndex(Integer.parseInt(field.fieldNumber().getText()));
        return ProductType.of(fieldDefinition);
    }

    private Stream<AnyType> readOptionStatement(Protobuf3Parser.OptionStatementContext optionStatement) {
        return Stream.empty();
    }

    private Stream<AnyType> readExternalDef(Protobuf3Parser.ExtendDefContext extendDef) {
        return Stream.empty();
    }

    private void readEnumDefinition(Protobuf3Parser.EnumDefContext enumContext) {
        schemaContext.registerNamedType(new TypeDefinition(enumContext.enumName().getText(),
                EnumType.of(new Int32Type(),
                        enumContext.enumBody().enumElement().stream()
                            .filter(enumElm -> !Objects.isNull(enumElm.enumField()))
                            .map(enumElm -> new EnumType.EnumItemType(
                                enumElm.enumField().ident().IDENTIFIER().getText(),
                                Int32Type.constantOf(Integer.parseInt(enumElm.enumField().intLit().getText())))))));
    }

    private AnyType toAdt(String label, String protoType) throws AdtException {
        AnyType anyType = switch (protoType){
            case "string" -> new StringType();
            case "bool" -> new BoolType();
            case "int32" -> new Int32Type();
            case "uint32" -> new UInt32Type();
            case "sint32" -> new SInt32Type();
            case "fixed32" -> new UInt32Type();
            case "sfixed32" -> new SInt32Type();
            case "int64" -> new Int64Type();
            case "uint64" -> new UInt64Type();
            case "sint64" -> new SInt64Type();
            case "fixed64" -> new UInt64Type();
            case "sfixed64" -> new SInt64Type();
            case "float" -> new FloatType();
            case "double" -> new DoubleType();
            case "bytes" -> new BinaryType();
            default -> new ReferencedDefinition(protoType);
        };
        if(label.equals("repeated")){
            return new ListType(anyType);
        }
        return anyType;
    }
}
