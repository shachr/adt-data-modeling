package data.modeling.adt.mappers.javabeansFromAdt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.monads.SchemaTypeStream;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.javabeansFromAdt.util.JavaFile;
import data.modeling.adt.mappers.javabeansFromAdt.util.JavaFileUtil;
import data.modeling.adt.typedefs.EnumType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.stream.Stream;

public class JavaBeansFromAdt implements SchemaTypeStream<JavaFile> {
    protected SchemaContext schemaContext;

    public JavaBeansFromAdt(SchemaContext schemaContext){

        this.schemaContext = schemaContext;
    }

    @Override
    public Stream<JavaFile> stream() throws AdtException {
        JavaFileUtil javaFileUtil = new JavaFileUtil(schemaContext);
        // convert to java beans using a template
        return schemaContext.stream().map(LambdaExceptionUtil.function(namedType -> {
            if(namedType.getType() instanceof EnumType) {
                return javaFileUtil.toEnumFile(namedType);
            }
            else if(namedType.getType() instanceof ProductType) {
                return javaFileUtil.toClassFile(namedType);
            } else {
                throw new AdtException("not supported");
            }
        }));
    }
}