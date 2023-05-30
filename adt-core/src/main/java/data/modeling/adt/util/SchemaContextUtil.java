package data.modeling.adt.util;

import data.modeling.adt.SchemaContext;
import data.modeling.adt.typedefs.*;

import java.util.List;

public class SchemaContextUtil {
    private SchemaContext schemaContext;

    public SchemaContextUtil(SchemaContext schemaContext){

        this.schemaContext = schemaContext;
    }
    public void rename(String pattern, String replacement){
        List<Definition<ComplexType>> typeDefinitionList = this.schemaContext.stream().toList();
        typeDefinitionList.forEach(namedType -> {
            if(namedType.getName().matches(pattern)){
                schemaContext.removeNamedType(namedType);
                String oldName = namedType.getName();
                String newName = oldName.replaceAll(pattern, replacement);
                namedType.setName(newName);
                schemaContext.registerNamedType(namedType);

                // find all references
                renameReferencedNamedType(oldName, newName, typeDefinitionList);
            }
        });
    }

    private void renameReferencedNamedType(String oldName, String newName, List<Definition<ComplexType>> typeDefinitionList) {
        typeDefinitionList.forEach(namedType -> {
            if(namedType.getType() instanceof ProductType productType){
                productType.getOwnFields().stream()
                        .filter(fieldType -> fieldType.getType() instanceof ReferencedDefinition)
                        .map(FieldDefinition::getType)
                        .map(anyType -> (ReferencedDefinition)anyType)
                        .filter(referenceNamedType -> referenceNamedType.getReferenceName().equals(oldName))
                        .forEach(referenceNamedType -> referenceNamedType.setReferenceName(newName));
            } else if(namedType.getType() instanceof UnionType unionType){
                unionType.getTypes().stream()
                        .filter(anyType -> anyType instanceof ReferencedDefinition)
                        .map(anyType -> (ReferencedDefinition)anyType)
                        .filter(referenceNamedType -> referenceNamedType.getReferenceName().equals(oldName))
                        .forEach(referenceNamedType -> referenceNamedType.setReferenceName(newName));
            }
            //todo: support the rest of the complex types
        });
    }
}
