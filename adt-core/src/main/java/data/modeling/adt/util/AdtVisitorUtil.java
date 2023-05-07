package data.modeling.adt.util;

import data.modeling.adt.abstraction.visitors.AdtVisitor;
import data.modeling.adt.typedefs.*;

public class AdtVisitorUtil {
    public static void visit(AdtVisitor visitor, AnyType type){
        if(type instanceof ProductType){
             ((ProductType) type).accept(visitor);
        } else if(type instanceof EnumType){
            ((EnumType) type).accept(visitor);
        } else if(type instanceof UnionType){
            visitor.visit((UnionType) type);
        } else if(type instanceof NullValueType){
            visitor.visit((NullValueType) type);
        } else if(type instanceof ReferenceObjectType){
            visitor.visit((ReferenceObjectType) type);
        }
    }
}
