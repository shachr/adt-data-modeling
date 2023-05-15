package data.modeling.adt.mappers.javabeansFromAdt.util;

import java.util.Arrays;
import java.util.List;

public interface JavaFileTemplateBase {
  AdtToJavaMapping typeMapping = new AdtToJavaMapping();

  List<String> reservedWords =
      Arrays.asList(
          "abstract",
          "assert",
          "boolean",
          "break",
          "byte",
          "case",
          "catch",
          "char",
          "class",
          "const",
          "default",
          "do",
          "double",
          "else",
          "enum",
          "extends",
          "false",
          "final",
          "finally",
          "float",
          "for",
          "goto",
          "if",
          "implements",
          "import",
          "instanceof",
          "int",
          "interface",
          "long",
          "native",
          "new",
          "null",
          "package",
          "private",
          "protected",
          "public",
          "return",
          "short",
          "static",
          "strictfp",
          "super",
          "switch",
          "synchronized",
          "this",
          "throw",
          "throws",
          "short",
          "transient",
          "true",
          "try",
          "void",
          "volatile",
          "while",
          "continue");

  default AdtToJavaMapping getTypeMapping() {
    return typeMapping;
  }

  default String escapeName(String name) {

    if (reservedWords.contains(name)) {
      return "`" + name + "`";
    }
    return name;
  }
}