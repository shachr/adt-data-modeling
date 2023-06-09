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
public class ${className}<#if inherits()?has_content> implements <#list inherits() as inheritedType>${inheritedType}<#if inheritedType?has_next>,</#if></#list></#if> {
<#-- Output the class properties
<#list typeDefinition.annotations as annotation>
    <@generateAnnotation annotation/>
</#list>
-->
<#list typeDefinition.type.getOwnFields() as field>
    private ${toJavaType(field.type)} ${escapeName(field.name)};
</#list>

<#-- Output the class constructor-->
<#if typeDefinition.type.getResolvedConstructors()?size = 0 >
    public ${className}() {}

    public ${className}(<#list typeDefinition.type.getOwnFields() as field>${toJavaType(field.type)} ${escapeName(field.name)}<#if field?has_next>,</#if></#list>) {
        <#list typeDefinition.type.getOwnFields() as field>
        this.${escapeName(field.name)} = ${escapeName(field.name)}
        </#list>
    }
</#if>

<#list typeDefinition.type.getResolvedConstructors() as fields>
    public ${className}(<#list fields as field>${toJavaType(field.type)} ${escapeName(field.name)}</#list>) {
    <#list fields as field>
        this.${escapeName(field.name)} = ${escapeName(field.name)}
    </#list>
    }
</#list>
<#-- Output the getter methods -->
<#list typeDefinition.type.getOwnFields() as field>
<#if typeDefinition.type.class.simpleName == "BoolType">
    public ${toJavaType(field.type)} is${field.name?cap_first}() {
        return this.${field.name};
    }

    public void set${field.name?cap_first}(${toJavaType(field.type)} value) {
        this.${field.name} = value;
    }
<#else>
    public ${toJavaType(field.type)} get${field.name?cap_first}() {
        return this.${field.name};
    }

    public void set${field.name?cap_first}(${toJavaType(field.type)} value) {
        this.${field.name} = value;
    }
</#if>
</#list>
}