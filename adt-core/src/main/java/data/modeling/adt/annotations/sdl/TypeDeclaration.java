package data.modeling.adt.annotations.sdl;

import data.modeling.adt.abstraction.annotations.SDL;
import data.modeling.adt.enums.TypeDeclarations;

public final class TypeDeclaration extends SDL<TypeDeclarations> {

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


