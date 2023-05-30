package data.modeling.adt.mappers.javabeansFromAdt.util;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.mappers.javabeansFromAdt.artifacts.JavaFile;
import data.modeling.adt.typedefs.*;
import data.modeling.adt.util.LambdaExceptionUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaFileUtil {
    public static String tmplFile = "/templates/java";
    private final SchemaContext schemaContext;
    private final ClassLoader classLoader;
//    private static final Logger logger = LoggerFactory.getLogger(JavaCodeGenUtil.class);
    public JavaFileUtil(SchemaContext schemaContext){
        this(schemaContext, JavaFileUtil.class.getClassLoader());
    }
    public JavaFileUtil(SchemaContext schemaContext, ClassLoader classLoader){
        this.schemaContext = schemaContext;
        this.classLoader = classLoader;
    }
    public JavaFile toClassFile(TypeDefinition typeDefinition) throws IOException, TemplateException {
        return processType(typeDefinition, "/class.ftlh");
    }
    public JavaFile toInterfaceFile(InterfaceDefinition typeDefinition) throws IOException, TemplateException {
        return processType(typeDefinition, "/interface.ftlh");
    }
    public JavaFile toEnumFile(Definition<ComplexType> typeDefinition) throws IOException, TemplateException {
        return processType(typeDefinition, "/enum.ftlh");
    }
    private JavaFile processType(Definition<ComplexType> typeDefinition, String templateLocation)
            throws IOException, TemplateException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(this.getClass(), tmplFile);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer out = new OutputStreamWriter(outputStream);

        final Template temp = cfg.getTemplate(templateLocation);
        AbstractMap.SimpleEntry<String, String> simpleEntry = toFullyQualifiedName(typeDefinition.getName());
        Set<String> inherits = getInherits(typeDefinition);
        Set<String> imports = getImports(schemaContext, typeDefinition);

        JavaFileTemplate javaFile = new JavaFileTemplate(simpleEntry.getKey(), imports, simpleEntry.getValue(), inherits, typeDefinition);
        temp.process(javaFile, out);

        byte[] bufferContent = outputStream.toByteArray();
        String fileName = simpleEntry.getKey().replaceAll("\\.", FileSystems.getDefault().getSeparator()) + FileSystems.getDefault().getSeparator() + simpleEntry.getValue();
        String bufferString = new String(bufferContent, "UTF-8");
        return new JavaFile(fileName, bufferString);
    }

    private Set<String> getInherits(Definition<ComplexType> typeDefinition) {
        if(typeDefinition.getType() instanceof ProductType productType){
            return productType.getImplements().stream()
                    .map(ReferencedDefinition::getReferenceName)
                    .map(JavaFileUtil::toFullyQualifiedName)
                    .map(AbstractMap.SimpleEntry::getValue)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
        return new LinkedHashSet<>();
    }

    public static AbstractMap.SimpleEntry<String, String> toFullyQualifiedName(String input) {

        if(isFullyQualifiedName(input)) {
            String packageName = normalizeString(input.substring(0, input.lastIndexOf(".")));
            String className = normalizeString(input.substring(input.lastIndexOf(".") + 1));
            return new AbstractMap.SimpleEntry<>(toPackageName(packageName), toClassName(className));
        } else {
            String normalized = normalizeString(input);
            return new AbstractMap.SimpleEntry<>(toPackageName(normalized), "");
        }
    }
    private static String normalizeString(String input) {
        // Remove any leading or trailing whitespace
        String trimmed = input.trim();

        // Replace consecutive spaces with a single space
        String noConsecutiveSpaces = trimmed.replaceAll("\\s+", " ");

        // Replace non-alphanumeric characters with underscores
        String alphanumericOnly = noConsecutiveSpaces.replaceAll("[^a-zA-Z0-9\\.]", "_");

        // Remove any leading or trailing underscores
        String noLeadingTrailingUnderscores = alphanumericOnly.replaceAll("^_|_$", "");

        // Convert to lowercase
        String lowercase = noLeadingTrailingUnderscores.toLowerCase();

        return lowercase;
    }
    private static boolean isFullyQualifiedName(String input) {
        return input.contains(".");
    }
    private static String toPackageName(String normalized) {
        // Replace underscores with dots to form a valid package name
        return normalized;//.replaceAll("_", ".");
    }
    private static String toClassName(String normalized) {
        // Capitalize the first letter to form a valid class name
        return normalized.substring(0, 1).toUpperCase() + normalized.substring(1);
    }
    private static Set<String> getImports(SchemaContext schemaContext, Definition<ComplexType> typeDefinition) {
        if(typeDefinition.getType() instanceof ProductType){
            ProductType productType = (ProductType) typeDefinition.getType();
            return Stream.concat(
                // inherits
                productType.getImplements().stream()
                .map(ReferencedDefinition::getReferenceName)
                .map(JavaFileUtil::toFullyQualifiedName)
                .map(entry->entry.getKey() + "." + entry.getValue()),

                // references
                productType.resolveAllFields(schemaContext).stream()
                .filter(fieldType -> fieldType.getType() instanceof ReferencedDefinition)
                .map(fieldType -> ((ReferencedDefinition)fieldType.getType()).getReferenceName())
                .map(LambdaExceptionUtil.function(JavaFileUtil::toFullyQualifiedName))
                .map(entry-> entry.getKey() + "." + entry.getValue())

            ).collect(Collectors.toCollection(LinkedHashSet::new));
        }
        return new LinkedHashSet<>();
    }

}
