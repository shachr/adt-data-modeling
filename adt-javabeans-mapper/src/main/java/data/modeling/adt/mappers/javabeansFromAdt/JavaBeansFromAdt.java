package data.modeling.adt.mappers.javabeansFromAdt;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.abstraction.monads.SchemaTypeStream;
import data.modeling.adt.annotations.sdl.TypeDeclaration;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.mappers.javabeansFromAdt.artifacts.JavaFile;
import data.modeling.adt.mappers.javabeansFromAdt.util.JavaFileUtil;
import data.modeling.adt.typedefs.EnumType;
import data.modeling.adt.typedefs.ProductType;
import data.modeling.adt.util.LambdaExceptionUtil;

import java.util.Optional;
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
                Optional<TypeDeclaration> typeDeclarationOpt = namedType.findAnnotation(TypeDeclaration.class);
                if(typeDeclarationOpt.isPresent()){
                    TypeDeclaration typeDeclaration = typeDeclarationOpt.get();
                    switch (typeDeclaration.getValue()) {
                        case Class -> {
                            return javaFileUtil.toClassFile(namedType);
                        }
                        case Interface -> {
                            return javaFileUtil.toInterfaceFile(namedType);
                        }
                    }
                }
                return javaFileUtil.toClassFile(namedType);
            } else {
                throw new AdtException("not supported");
            }
        }));
    }
}