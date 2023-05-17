package data.modeling.adt.mappers.javabeansFromAdt.util;

import data.modeling.adt.typedefs.NamedType;

import java.util.Set;

public record JavaFileTemplate(String packageName, Set<String> imports, String className, Set<String> inherits, NamedType namedType)
            implements JavaFileTemplateBase {}