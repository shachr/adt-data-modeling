package data.modeling.adt.mappers.javabeansFromAdt.util;

import data.modeling.adt.typedefs.ComplexType;
import data.modeling.adt.typedefs.Definition;
import data.modeling.adt.typedefs.TypeDefinition;

import java.util.Set;

public record JavaFileTemplate(String packageName, Set<String> imports, String className, Set<String> inherits, Definition<ComplexType> typeDefinition)
            implements JavaFileTemplateBase {}