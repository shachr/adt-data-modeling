package data.modeling.adt.mappers.javabeansFromAdt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.monads.SchemaTypeStream;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.javabeansFromAdt.artifacts.JavaFile;
import data.modeling.adt.mappers.javabeansFromAdt.util.JavaFileUtil;
import data.modeling.adt.mappers.registries.FromAdtMapperRegistry;
import data.modeling.adt.typedefs.EnumType;
import data.modeling.adt.typedefs.InterfaceDefinition;
import data.modeling.adt.typedefs.TypeDefinition;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.stream.Stream;

public class JavaBeansFromAdt implements SchemaTypeStream<JavaFile> {
    protected SchemaContext schemaContext;

    private final FromAdtMapperRegistry fromAdtMapperRegistry = new FromAdtMapperRegistry();

    public JavaBeansFromAdt(SchemaContext schemaContext){

        this.schemaContext = schemaContext;
    }

    @Override
    public FromAdtMapperRegistry getMapperRegistry() {
        return fromAdtMapperRegistry;
    }

    @Override
    public Stream<JavaFile> stream() throws AdtException {
        // todo: add mapper registry
        JavaFileUtil javaFileUtil = new JavaFileUtil(schemaContext);
        // convert to java beans using a template
        return schemaContext.stream().map(LambdaExceptionUtil.function(namedType -> {
            if(namedType.getType() instanceof EnumType) {
                return javaFileUtil.toEnumFile(namedType);
            }
            else if(namedType.isProductTypeDefinition()) {
                if(namedType instanceof TypeDefinition)
                    return javaFileUtil.toClassFile((TypeDefinition) namedType);
                else if(namedType instanceof InterfaceDefinition)
                    return javaFileUtil.toInterfaceFile((InterfaceDefinition)namedType);
                else
                    throw new AdtException("not supported");
            } else {
                throw new AdtException("not supported");
            }
        }));
    }
}