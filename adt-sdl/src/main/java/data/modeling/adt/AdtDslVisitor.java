package data.modeling.adt;

import data.modeling.adt.sdl.antlr4.AdtSdlBaseVisitor;
import data.modeling.adt.sdl.antlr4.AdtSdlParser;

import java.util.Objects;

public class AdtDslVisitor extends AdtSdlBaseVisitor<AdtDslContext> {
    private final AdtDslContext adtDslContext = new AdtDslContext();

    public AdtDslVisitor(){
        super();
    }

    @Override
    public AdtDslContext visitSetDefinition(AdtSdlParser.SetDefinitionContext ctx) {
        super.visitSetDefinition(ctx);
        String settingName = ctx.label().IDENTIFIER().getText();
        Object value = ctx.value().getText();

        if(!Objects.isNull(ctx.value().number())){
            value = Integer.parseInt(value.toString());
        } else if(!Objects.isNull(ctx.value().string())){
            value = value.toString().substring(1, value.toString().length() - 1);
        } else if(!Objects.isNull(ctx.value().boolean_())){
            value = Boolean.parseBoolean(value.toString());
        } else {
            throw new RuntimeException("not supported");
        }

        adtDslContext.getActiveSchemaContext().set(settingName, value);
        return adtDslContext;
    }

    @Override
    public AdtDslContext visitContextStatement(AdtSdlParser.ContextStatementContext ctx) {
        SchemaContext schemaContext = new SchemaContext(ctx.label().IDENTIFIER().getText());
        adtDslContext.add(schemaContext);
        super.visitContextStatement(ctx);
        return adtDslContext;
    }

    @Override
    protected AdtDslContext aggregateResult(AdtDslContext aggregate, AdtDslContext nextResult) {
        if(null != nextResult)
            return nextResult;
        return aggregate;
//        return super.aggregateResult(aggregate, nextResult);
    }
}
