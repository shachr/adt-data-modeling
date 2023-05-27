// Generated from /Users/sbardavid/IdeaProjects/test/adt-sdl/src/main/resources/AdtSdl.g4 by ANTLR 4.12.0
package data.modeling.adt.sdl.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AdtSdlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AdtSdlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#schema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchema(AdtSdlParser.SchemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#definitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinitions(AdtSdlParser.DefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(AdtSdlParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#contextStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextStatement(AdtSdlParser.ContextStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#importStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportStatement(AdtSdlParser.ImportStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#setDefinitionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetDefinitionStatement(AdtSdlParser.SetDefinitionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#allOfDefinitionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllOfDefinitionStatement(AdtSdlParser.AllOfDefinitionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#anyOfDefinitionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnyOfDefinitionStatement(AdtSdlParser.AnyOfDefinitionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#typeDefinitionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinitionStatement(AdtSdlParser.TypeDefinitionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#unionDefinitionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionDefinitionStatement(AdtSdlParser.UnionDefinitionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#enumDefinitionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumDefinitionStatement(AdtSdlParser.EnumDefinitionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#entityDefinitionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntityDefinitionStatement(AdtSdlParser.EntityDefinitionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#modificationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModificationStatement(AdtSdlParser.ModificationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#removeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveStatement(AdtSdlParser.RemoveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#renameStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameStatement(AdtSdlParser.RenameStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#removeFieldStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveFieldStatement(AdtSdlParser.RemoveFieldStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#renameFieldStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameFieldStatement(AdtSdlParser.RenameFieldStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#overrideFieldStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOverrideFieldStatement(AdtSdlParser.OverrideFieldStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#defineFieldStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineFieldStatement(AdtSdlParser.DefineFieldStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#setDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetDefinition(AdtSdlParser.SetDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#fieldDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDefinition(AdtSdlParser.FieldDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#unionDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionDefinitions(AdtSdlParser.UnionDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#enumDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumDefinition(AdtSdlParser.EnumDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#extendsTypeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendsTypeList(AdtSdlParser.ExtendsTypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#extends}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtends(AdtSdlParser.ExtendsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#schemaPipelines}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchemaPipelines(AdtSdlParser.SchemaPipelinesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#override}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOverride(AdtSdlParser.OverrideContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#define}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefine(AdtSdlParser.DefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#rename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRename(AdtSdlParser.RenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#remove}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemove(AdtSdlParser.RemoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocation(AdtSdlParser.LocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(AdtSdlParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#annotations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotations(AdtSdlParser.AnnotationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#annotation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotation(AdtSdlParser.AnnotationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#annotationArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationArgs(AdtSdlParser.AnnotationArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#annotationArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationArg(AdtSdlParser.AnnotationArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#contextRenameCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextRenameCall(AdtSdlParser.ContextRenameCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#contextRenameCallArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextRenameCallArgs(AdtSdlParser.ContextRenameCallArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#contextRenameCallArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContextRenameCallArg(AdtSdlParser.ContextRenameCallArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(AdtSdlParser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#pipeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPipeName(AdtSdlParser.PipeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#newFieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewFieldName(AdtSdlParser.NewFieldNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#labelList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelList(AdtSdlParser.LabelListContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#labelSetList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelSetList(AdtSdlParser.LabelSetListContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(AdtSdlParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#complexTypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexTypeName(AdtSdlParser.ComplexTypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#complexTypeNameArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexTypeNameArg(AdtSdlParser.ComplexTypeNameArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#complexTypeNameArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexTypeNameArgs(AdtSdlParser.ComplexTypeNameArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#relationshipTypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipTypeName(AdtSdlParser.RelationshipTypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#relationshipArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipArg(AdtSdlParser.RelationshipArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#relationshipArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipArgs(AdtSdlParser.RelationshipArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#allTypeNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllTypeNames(AdtSdlParser.AllTypeNamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#newtypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewtypeName(AdtSdlParser.NewtypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#boolean}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(AdtSdlParser.BooleanContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(AdtSdlParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link AdtSdlParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(AdtSdlParser.StringContext ctx);
}