<#assign packageName = packageName()>
<#assign imports = imports()>
<#assign className = className()>
<#assign typeDefinition = typeDefinition()>
<#-- Output the package statement -->
<#if packageName?has_content>
package ${packageName};
</#if>

<#-- Output the import statements -->
<#list imports as import>
import ${import};
</#list>

<#-- Output the class declaration -->
public enum ${className} {

<#list typeDefinition.type.items as item>
    ${escapeName(item.name())}("${toJavaType(item.value())}")<#if item?has_next>,<#else>;</#if>
</#list>

    private final ${toJavaType(typeDefinition.type.baseType)} value;

    ${className}(${toJavaType(typeDefinition.type.baseType)} value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}