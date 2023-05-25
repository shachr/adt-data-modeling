package data.modeling.adt;

import data.modeling.adt.abstraction.monads.NamedTypeStream;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.registries.ToAdtMapperRegistry;
import data.modeling.adt.typedefs.NamedType;
import data.modeling.antlr4.Protobuf3Lexer;
import data.modeling.antlr4.Protobuf3Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.util.stream.Stream;

public class Protobuf3ToAdt implements NamedTypeStream {

    // todo: add mapper registry
    private File protoFilename;
    private String protoContent;
    private SchemaContext schemaContext;

    private ToAdtMapperRegistry toAdtMapperRegistry = new ToAdtMapperRegistry();

    public Protobuf3ToAdt(SchemaContext schemaContext, File protoFilename){
        throw new RuntimeException("not implemented");
//        this.schemaContext = schemaContext;
//        this.protoFilename = protoFilename;
//        this.protoContent = null;
    }

    public Protobuf3ToAdt(SchemaContext schemaContext, String protoContent){
        this.schemaContext = schemaContext;
        this.protoFilename = null;

        this.protoContent = protoContent;
    }

    @Override
    public ToAdtMapperRegistry getMapperRegistry() {
        return toAdtMapperRegistry;
    }

    @Override
    public Stream<NamedType> stream() throws AdtException {

// Create a CharStream from your Proto file
        CharStream input = CharStreams.fromString(protoContent);

        // Create a lexer that feeds off the input CharStream
        Protobuf3Lexer lexer = new Protobuf3Lexer(input);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        Protobuf3Parser parser = new Protobuf3Parser(tokens);

        // Start parsing from the 'proto' rule
        ParseTree parseTree = parser.proto();

        Protobuf3AdtVisitor protobuf3AdtVisitor = new Protobuf3AdtVisitor(schemaContext);
        protobuf3AdtVisitor.visit(parseTree);

        return schemaContext.stream();
    }
}