package data.modeling.adt.mappers.javabeansFromAdt.util;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.*;

public class AdtToJavaMapping {

  public String map(AnyType anyType) throws AdtException {
    if(anyType instanceof BoolType){
      return this.map((BoolType) anyType);
    } else if(anyType instanceof IntType){
      return this.map((IntType) anyType);
    } else if(anyType instanceof LongType){
      return this.map((LongType) anyType);
    } else if(anyType instanceof FloatType){
      return this.map((FloatType) anyType);
    } else if(anyType instanceof DoubleType){
      return this.map((DoubleType) anyType);
    } else if(anyType instanceof DecimalType){
      return this.map((DecimalType) anyType);
    } else if(anyType instanceof StringType){
      return this.map((StringType) anyType);
    } else if(anyType instanceof TemporalType){
      return this.map((TemporalType) anyType);
    } else if(anyType instanceof SetType){
      return this.map((SetType) anyType);
    } else if(anyType instanceof ListType){
      return this.map((ListType) anyType);
    } else if(anyType instanceof NullValueType){
      return this.map((NullValueType) anyType);
    } else if(anyType instanceof MapType) {
      return this.map((MapType) anyType);
    } else if(anyType instanceof ReferenceNamedType){
        return JavaFileUtil.toFullyQualifiedName(((ReferenceNamedType) anyType).getReferenceName()).getValue();
    } else if(anyType instanceof ConstantPrimitiveType){
      return ((ConstantPrimitiveType)anyType).getConstant().toString();
    } else {
      throw new AdtException("not implemented");
    }
  }

  private String map(BoolType booleanType) {
    return "Boolean";
  }

  private String map(IntType integerType) {
    return "Integer";
  }

  private String map(LongType longType) {
    return "Long";
  }

  private String map(FloatType floatType) {
    return "Float";
  }

  private String map(DoubleType doubleType) {
    return "Double";
  }

  private String map(DecimalType decimalType) {
    return "java.math.BigDecimal";
  }

  private String map(StringType stringType) {
    return "String";
  }

  private String map(TemporalType temporalType) throws AdtException {
    final String clazz;

    if (temporalType instanceof DateType) {
      clazz = "java.time.LocalDate";
    } else if (temporalType instanceof DateTimeType) {
        clazz = "java.time.ZonedDateTime";
    } else if (temporalType instanceof DateTimeLocalType) {
      clazz = "java.time.LocalDateTime";
    } else {
      throw new AdtException(temporalType.getClass().toString());
    }
    return clazz;
  }

  private String map(ListType listType) {
    return "java.util.List<%s>";
  }

  private String map(SetType setType) {
    return "java.util.Set<%s>";
  }

  private String map(NullValueType nullableType)  {
    return "%s";
  }

  private String map(MapType mapType)  {
    return "Map<%s, %s>";
  }
}