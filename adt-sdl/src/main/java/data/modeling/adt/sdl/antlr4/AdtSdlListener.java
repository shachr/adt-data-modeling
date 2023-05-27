// Generated from /Users/sbardavid/IdeaProjects/test/adt-sdl/src/main/resources/AdtSdl.g4 by ANTLR 4.12.0
package data.modeling.adt.sdl.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AdtSdlParser}.
 */
public interface AdtSdlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#schema}.
	 * @param ctx the parse tree
	 */
	void enterSchema(AdtSdlParser.SchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#schema}.
	 * @param ctx the parse tree
	 */
	void exitSchema(AdtSdlParser.SchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#definitions}.
	 * @param ctx the parse tree
	 */
	void enterDefinitions(AdtSdlParser.DefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#definitions}.
	 * @param ctx the parse tree
	 */
	void exitDefinitions(AdtSdlParser.DefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(AdtSdlParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(AdtSdlParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#contextStatement}.
	 * @param ctx the parse tree
	 */
	void enterContextStatement(AdtSdlParser.ContextStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#contextStatement}.
	 * @param ctx the parse tree
	 */
	void exitContextStatement(AdtSdlParser.ContextStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void enterImportStatement(AdtSdlParser.ImportStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void exitImportStatement(AdtSdlParser.ImportStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#setDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSetDefinitionStatement(AdtSdlParser.SetDefinitionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#setDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSetDefinitionStatement(AdtSdlParser.SetDefinitionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#allOfDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void enterAllOfDefinitionStatement(AdtSdlParser.AllOfDefinitionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#allOfDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void exitAllOfDefinitionStatement(AdtSdlParser.AllOfDefinitionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#anyOfDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void enterAnyOfDefinitionStatement(AdtSdlParser.AnyOfDefinitionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#anyOfDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void exitAnyOfDefinitionStatement(AdtSdlParser.AnyOfDefinitionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#typeDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefinitionStatement(AdtSdlParser.TypeDefinitionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#typeDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefinitionStatement(AdtSdlParser.TypeDefinitionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#unionDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void enterUnionDefinitionStatement(AdtSdlParser.UnionDefinitionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#unionDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void exitUnionDefinitionStatement(AdtSdlParser.UnionDefinitionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#enumDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void enterEnumDefinitionStatement(AdtSdlParser.EnumDefinitionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#enumDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void exitEnumDefinitionStatement(AdtSdlParser.EnumDefinitionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#entityDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void enterEntityDefinitionStatement(AdtSdlParser.EntityDefinitionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#entityDefinitionStatement}.
	 * @param ctx the parse tree
	 */
	void exitEntityDefinitionStatement(AdtSdlParser.EntityDefinitionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#modificationStatement}.
	 * @param ctx the parse tree
	 */
	void enterModificationStatement(AdtSdlParser.ModificationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#modificationStatement}.
	 * @param ctx the parse tree
	 */
	void exitModificationStatement(AdtSdlParser.ModificationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#removeStatement}.
	 * @param ctx the parse tree
	 */
	void enterRemoveStatement(AdtSdlParser.RemoveStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#removeStatement}.
	 * @param ctx the parse tree
	 */
	void exitRemoveStatement(AdtSdlParser.RemoveStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#renameStatement}.
	 * @param ctx the parse tree
	 */
	void enterRenameStatement(AdtSdlParser.RenameStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#renameStatement}.
	 * @param ctx the parse tree
	 */
	void exitRenameStatement(AdtSdlParser.RenameStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#removeFieldStatement}.
	 * @param ctx the parse tree
	 */
	void enterRemoveFieldStatement(AdtSdlParser.RemoveFieldStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#removeFieldStatement}.
	 * @param ctx the parse tree
	 */
	void exitRemoveFieldStatement(AdtSdlParser.RemoveFieldStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#renameFieldStatement}.
	 * @param ctx the parse tree
	 */
	void enterRenameFieldStatement(AdtSdlParser.RenameFieldStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#renameFieldStatement}.
	 * @param ctx the parse tree
	 */
	void exitRenameFieldStatement(AdtSdlParser.RenameFieldStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#overrideFieldStatement}.
	 * @param ctx the parse tree
	 */
	void enterOverrideFieldStatement(AdtSdlParser.OverrideFieldStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#overrideFieldStatement}.
	 * @param ctx the parse tree
	 */
	void exitOverrideFieldStatement(AdtSdlParser.OverrideFieldStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#defineFieldStatement}.
	 * @param ctx the parse tree
	 */
	void enterDefineFieldStatement(AdtSdlParser.DefineFieldStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#defineFieldStatement}.
	 * @param ctx the parse tree
	 */
	void exitDefineFieldStatement(AdtSdlParser.DefineFieldStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#setDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSetDefinition(AdtSdlParser.SetDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#setDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSetDefinition(AdtSdlParser.SetDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#fieldDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFieldDefinition(AdtSdlParser.FieldDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#fieldDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFieldDefinition(AdtSdlParser.FieldDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#unionDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterUnionDefinitions(AdtSdlParser.UnionDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#unionDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitUnionDefinitions(AdtSdlParser.UnionDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#enumDefinition}.
	 * @param ctx the parse tree
	 */
	void enterEnumDefinition(AdtSdlParser.EnumDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#enumDefinition}.
	 * @param ctx the parse tree
	 */
	void exitEnumDefinition(AdtSdlParser.EnumDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#extendsTypeList}.
	 * @param ctx the parse tree
	 */
	void enterExtendsTypeList(AdtSdlParser.ExtendsTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#extendsTypeList}.
	 * @param ctx the parse tree
	 */
	void exitExtendsTypeList(AdtSdlParser.ExtendsTypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#extends}.
	 * @param ctx the parse tree
	 */
	void enterExtends(AdtSdlParser.ExtendsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#extends}.
	 * @param ctx the parse tree
	 */
	void exitExtends(AdtSdlParser.ExtendsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#schemaPipelines}.
	 * @param ctx the parse tree
	 */
	void enterSchemaPipelines(AdtSdlParser.SchemaPipelinesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#schemaPipelines}.
	 * @param ctx the parse tree
	 */
	void exitSchemaPipelines(AdtSdlParser.SchemaPipelinesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#override}.
	 * @param ctx the parse tree
	 */
	void enterOverride(AdtSdlParser.OverrideContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#override}.
	 * @param ctx the parse tree
	 */
	void exitOverride(AdtSdlParser.OverrideContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#define}.
	 * @param ctx the parse tree
	 */
	void enterDefine(AdtSdlParser.DefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#define}.
	 * @param ctx the parse tree
	 */
	void exitDefine(AdtSdlParser.DefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#rename}.
	 * @param ctx the parse tree
	 */
	void enterRename(AdtSdlParser.RenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#rename}.
	 * @param ctx the parse tree
	 */
	void exitRename(AdtSdlParser.RenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#remove}.
	 * @param ctx the parse tree
	 */
	void enterRemove(AdtSdlParser.RemoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#remove}.
	 * @param ctx the parse tree
	 */
	void exitRemove(AdtSdlParser.RemoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(AdtSdlParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(AdtSdlParser.LocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(AdtSdlParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(AdtSdlParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#annotations}.
	 * @param ctx the parse tree
	 */
	void enterAnnotations(AdtSdlParser.AnnotationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#annotations}.
	 * @param ctx the parse tree
	 */
	void exitAnnotations(AdtSdlParser.AnnotationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#annotation}.
	 * @param ctx the parse tree
	 */
	void enterAnnotation(AdtSdlParser.AnnotationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#annotation}.
	 * @param ctx the parse tree
	 */
	void exitAnnotation(AdtSdlParser.AnnotationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#annotationArgs}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationArgs(AdtSdlParser.AnnotationArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#annotationArgs}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationArgs(AdtSdlParser.AnnotationArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#annotationArg}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationArg(AdtSdlParser.AnnotationArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#annotationArg}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationArg(AdtSdlParser.AnnotationArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#contextRenameCall}.
	 * @param ctx the parse tree
	 */
	void enterContextRenameCall(AdtSdlParser.ContextRenameCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#contextRenameCall}.
	 * @param ctx the parse tree
	 */
	void exitContextRenameCall(AdtSdlParser.ContextRenameCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#contextRenameCallArgs}.
	 * @param ctx the parse tree
	 */
	void enterContextRenameCallArgs(AdtSdlParser.ContextRenameCallArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#contextRenameCallArgs}.
	 * @param ctx the parse tree
	 */
	void exitContextRenameCallArgs(AdtSdlParser.ContextRenameCallArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#contextRenameCallArg}.
	 * @param ctx the parse tree
	 */
	void enterContextRenameCallArg(AdtSdlParser.ContextRenameCallArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#contextRenameCallArg}.
	 * @param ctx the parse tree
	 */
	void exitContextRenameCallArg(AdtSdlParser.ContextRenameCallArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(AdtSdlParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(AdtSdlParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#pipeName}.
	 * @param ctx the parse tree
	 */
	void enterPipeName(AdtSdlParser.PipeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#pipeName}.
	 * @param ctx the parse tree
	 */
	void exitPipeName(AdtSdlParser.PipeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#newFieldName}.
	 * @param ctx the parse tree
	 */
	void enterNewFieldName(AdtSdlParser.NewFieldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#newFieldName}.
	 * @param ctx the parse tree
	 */
	void exitNewFieldName(AdtSdlParser.NewFieldNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#labelList}.
	 * @param ctx the parse tree
	 */
	void enterLabelList(AdtSdlParser.LabelListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#labelList}.
	 * @param ctx the parse tree
	 */
	void exitLabelList(AdtSdlParser.LabelListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#labelSetList}.
	 * @param ctx the parse tree
	 */
	void enterLabelSetList(AdtSdlParser.LabelSetListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#labelSetList}.
	 * @param ctx the parse tree
	 */
	void exitLabelSetList(AdtSdlParser.LabelSetListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(AdtSdlParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(AdtSdlParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#complexTypeName}.
	 * @param ctx the parse tree
	 */
	void enterComplexTypeName(AdtSdlParser.ComplexTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#complexTypeName}.
	 * @param ctx the parse tree
	 */
	void exitComplexTypeName(AdtSdlParser.ComplexTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#complexTypeNameArg}.
	 * @param ctx the parse tree
	 */
	void enterComplexTypeNameArg(AdtSdlParser.ComplexTypeNameArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#complexTypeNameArg}.
	 * @param ctx the parse tree
	 */
	void exitComplexTypeNameArg(AdtSdlParser.ComplexTypeNameArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#complexTypeNameArgs}.
	 * @param ctx the parse tree
	 */
	void enterComplexTypeNameArgs(AdtSdlParser.ComplexTypeNameArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#complexTypeNameArgs}.
	 * @param ctx the parse tree
	 */
	void exitComplexTypeNameArgs(AdtSdlParser.ComplexTypeNameArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#relationshipTypeName}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipTypeName(AdtSdlParser.RelationshipTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#relationshipTypeName}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipTypeName(AdtSdlParser.RelationshipTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#relationshipArg}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipArg(AdtSdlParser.RelationshipArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#relationshipArg}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipArg(AdtSdlParser.RelationshipArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#relationshipArgs}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipArgs(AdtSdlParser.RelationshipArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#relationshipArgs}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipArgs(AdtSdlParser.RelationshipArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#allTypeNames}.
	 * @param ctx the parse tree
	 */
	void enterAllTypeNames(AdtSdlParser.AllTypeNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#allTypeNames}.
	 * @param ctx the parse tree
	 */
	void exitAllTypeNames(AdtSdlParser.AllTypeNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#newtypeName}.
	 * @param ctx the parse tree
	 */
	void enterNewtypeName(AdtSdlParser.NewtypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#newtypeName}.
	 * @param ctx the parse tree
	 */
	void exitNewtypeName(AdtSdlParser.NewtypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#boolean}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(AdtSdlParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#boolean}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(AdtSdlParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(AdtSdlParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(AdtSdlParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link AdtSdlParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(AdtSdlParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link AdtSdlParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(AdtSdlParser.StringContext ctx);
}