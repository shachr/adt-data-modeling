package data.modeling.adt.util;

import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.*;

public class AdtVisitorUtil {
    public static void visit(AdtVisitor visitor, AnyType type) throws AdtException {
        if(type instanceof ProductType) {
            visitor.visit((ProductType)type);
            ((ProductType) type).accept(visitor);
        } else if(type instanceof FieldDefinition){
            ((FieldDefinition) type).accept(visitor);
        } else if(type instanceof EnumType){
            ((EnumType) type).accept(visitor);
        } else if(type instanceof UnionType){
            visitor.visit((UnionType) type);
        } else if(type instanceof NullValueType){
            visitor.visit((NullValueType) type);
        } else if(type instanceof ReferencedDefinition){
            visitor.visit((ReferencedDefinition) type);
        } else if(type instanceof SumType){
            visitor.visit((SumType) type);
        } else if(type instanceof AllOfType){
            visitor.visit((AllOfType) type);
        }  else if(type instanceof AnyOfType) {
            visitor.visit((AnyOfType) type);
        }

    }
}
