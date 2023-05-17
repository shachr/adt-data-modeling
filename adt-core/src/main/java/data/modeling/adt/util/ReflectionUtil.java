package data.modeling.adt.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtil{
    public static Class<?> getFirstTypeArgument(Class<?> clazz) {
        ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] typeArguments = type.getActualTypeArguments();
        Type typeArgument = typeArguments[0];
        if (typeArgument instanceof Class) {
            return (Class<?>) typeArgument;
        } else if (typeArgument instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) typeArgument).getRawType();
        } else {
            throw new IllegalStateException("Unable to determine first type argument for " + clazz.getName());
        }
    }
}
