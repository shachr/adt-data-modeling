package data.modeling.adt.mappers.javabeansFromAdt.util;

import data.modeling.adt.exceptions.AdtException;
import data.modeling.adt.typedefs.*;

public class AdtToJavaMapping {

  public String map(AnyType anyType) throws AdtException {
    if(anyType instanceof BoolType){
      return this.map((BoolType) anyType);
    } else if(anyType instanceof Int32Type){
      return this.map((Int32Type) anyType);
    } else if(anyType instanceof Int64Type){
      return this.map((Int64Type) anyType);
    } else if(anyType instanceof FloatType){
      return this.map((FloatType) anyType);
    } else if(anyType instanceof DoubleType){
      return this.map((DoubleType) anyType);
    } else if(anyType instanceof DecimalType){
      return this.map((DecimalType) anyType);
    } else if(anyType instanceof StringType){
      return this.map((StringType) anyType);
    } else if(anyType instanceof TimestampType){
      return this.map((TimestampType) anyType);
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

  private String map(Int32Type integerType) {
    return "Integer";
  }

  private String map(Int64Type int64Type) {
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

  private String map(TimestampType timestampType) throws AdtException {
    final String clazz;

    if (timestampType instanceof DateType) {
      clazz = "java.time.LocalDate";
    } else if (timestampType instanceof DateTimeType) {
        clazz = "java.time.ZonedDateTime";
    } else if (timestampType instanceof DateTimeLocalType) {
      clazz = "java.time.LocalDateTime";
    } else {
      throw new AdtException(timestampType.getClass().toString());
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