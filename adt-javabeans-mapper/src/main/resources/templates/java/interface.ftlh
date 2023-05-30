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
public interface ${className}<#if inherits()?has_content> implements <#list inherits() as inheritedType>${inheritedType}<#if inheritedType?has_next>,</#if></#list></#if> {
<#-- Output the class properties
<#list typeDefinition.annotations as annotation>
    <@generateAnnotation annotation/>
</#list>
-->

<#-- Output the getter methods -->
<#list typeDefinition.type.getOwnFields() as field>
    <#if typeDefinition.type.class.simpleName == "BoolType">
    ${toJavaType(field.type)} is${field.name?cap_first}();
    void set${field.name?cap_first}(${toJavaType(field.type)} value);
    <#else>
    ${toJavaType(field.type)} get${field.name?cap_first}();

    void set${field.name?cap_first}(${toJavaType(field.type)} value);
    </#if>
</#list>
}