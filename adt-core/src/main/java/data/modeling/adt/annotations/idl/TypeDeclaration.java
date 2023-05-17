package data.modeling.adt.annotations.idl;

import data.modeling.adt.abstraction.annotations.Annotation;
import data.modeling.adt.enums.TypeDeclarations;

public final class TypeDeclaration extends Annotation<TypeDeclarations> {

    public TypeDeclaration(TypeDeclarations value) {
        super(value);
    }

    public static TypeDeclaration of(TypeDeclarations value) {
        return new TypeDeclaration(value);
    }

    public static TypeDeclaration of(String value) {
        return new TypeDeclaration(TypeDeclarations.fromString(value));
    }
}


