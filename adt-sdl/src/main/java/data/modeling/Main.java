package data.modeling;

import data.modeling.adt.AdtDslContext;
import data.modeling.adt.AdtDslVisitor;
import data.modeling.adt.util.ResourcesUtil;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import data.modeling.adt.sdl.antlr4.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        String adtDsl = new ResourcesUtil(Main.class.getClassLoader()).asString("example.adt");

        // Create a CharStream from your Proto file
        CharStream input = CharStreams.fromString(adtDsl);

        // Create a lexer that feeds off the input CharStream
        AdtSdlLexer lexer = new AdtSdlLexer(input);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        AdtSdlParser parser = new AdtSdlParser(tokens);

        // Start parsing from the 'proto' rule
        ParseTree parseTree = parser.schema();

        AdtDslVisitor visitor = new AdtDslVisitor();
        AdtDslContext schemaContextStream = visitor.visit(parseTree);
        schemaContextStream.stream().forEach(schemaContext -> {
            System.out.println(schemaContext.getName());
            schemaContext.getSettings().forEach(schemaContextSetting -> {
                System.out.println("\t" + schemaContextSetting.key()+ " = "+ schemaContextSetting.value());
            });

        });

        System.out.println();
    }
}