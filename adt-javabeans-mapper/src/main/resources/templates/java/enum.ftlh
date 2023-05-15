<#assign packageName = packageName()>
<#assign imports = imports()>
<#assign className = className()>
<#assign namedType = namedType()>
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

<#list namedType.type.items as item>
    ${escapeName(item.name())}("${getTypeMapping().map(item.value())}")<#if item?has_next>,<#else>;</#if>
</#list>

    private final ${getTypeMapping().map(namedType.type.baseType)} value;

    ${className}(${getTypeMapping().map(namedType.type.baseType)} value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}