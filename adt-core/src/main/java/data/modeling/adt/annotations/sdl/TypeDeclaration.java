package data.modeling.adt.annotations.sdl;

import data.modeling.adt.abstraction.annotations.SDL;
import data.modeling.adt.enums.TypeDeclarations;

// todo: there should be a way to infer it, e.g.
//  [x] if in a schema context, a named type is only referenced from a product type "implements",
//  it can become an interface, and the implementer should implement all fields and "own" them.
//  OR
//  [x] another way to think of it would be to restrict usage of certain named type annotated with this annotation,
//  e.g. an interface can only be used as "implements" of a product type.
//  OR
//  [√] another way it to create explicit InterfaceDefinition ( and renamed NamedType to TypeDefinition? )
//  this will be more explicit and provide more granularity. json-schema-mapper
//  should be able to ask schema context to automatically convert TypeDefinitions that are not used outside of
//  implements to InterfaceDefinition. this is not needed for graphQL that support interfaces, etc.
@Deprecated(since = "05/29/2023", forRemoval = true)
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


