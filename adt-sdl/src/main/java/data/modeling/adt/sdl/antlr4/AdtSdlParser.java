// Generated from /Users/sbardavid/IdeaProjects/test/adt-sdl/src/main/resources/AdtSdl.g4 by ANTLR 4.12.0
package data.modeling.adt.sdl.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class AdtSdlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, ENTITY=7, RELATIONSHIP=8, 
		ANNO=9, SET=10, IMPORT=11, TO=12, ENUM=13, UNION=14, MODIFY=15, TYPE=16, 
		ANYOF=17, ALLOF=18, DTO=19, EXTENDS=20, REMOVE=21, RENAME=22, DEFINE=23, 
		OVERRIDE=24, CONTEXT=25, LBRACE=26, RBRACE=27, COLON=28, EQ=29, FALSE=30, 
		TRUE=31, IDENTIFIER=32, STRING=33, NUMBER=34, LINE_COMMENT=35, BLOCK_COMMENT=36, 
		WS=37;
	public static final int
		RULE_schema = 0, RULE_definitions = 1, RULE_statement = 2, RULE_contextStatement = 3, 
		RULE_importStatement = 4, RULE_setDefinitionStatement = 5, RULE_allOfDefinitionStatement = 6, 
		RULE_anyOfDefinitionStatement = 7, RULE_typeDefinitionStatement = 8, RULE_unionDefinitionStatement = 9, 
		RULE_enumDefinitionStatement = 10, RULE_entityDefinitionStatement = 11, 
		RULE_modificationStatement = 12, RULE_removeStatement = 13, RULE_renameStatement = 14, 
		RULE_removeFieldStatement = 15, RULE_renameFieldStatement = 16, RULE_overrideFieldStatement = 17, 
		RULE_defineFieldStatement = 18, RULE_setDefinition = 19, RULE_fieldDefinition = 20, 
		RULE_unionDefinitions = 21, RULE_enumDefinition = 22, RULE_extendsTypeList = 23, 
		RULE_extends = 24, RULE_schemaPipelines = 25, RULE_override = 26, RULE_define = 27, 
		RULE_rename = 28, RULE_remove = 29, RULE_location = 30, RULE_value = 31, 
		RULE_annotations = 32, RULE_annotation = 33, RULE_annotationArgs = 34, 
		RULE_annotationArg = 35, RULE_contextRenameCall = 36, RULE_contextRenameCallArgs = 37, 
		RULE_contextRenameCallArg = 38, RULE_label = 39, RULE_pipeName = 40, RULE_newFieldName = 41, 
		RULE_labelList = 42, RULE_labelSetList = 43, RULE_typeName = 44, RULE_complexTypeName = 45, 
		RULE_complexTypeNameArg = 46, RULE_complexTypeNameArgs = 47, RULE_relationshipTypeName = 48, 
		RULE_relationshipArg = 49, RULE_relationshipArgs = 50, RULE_allTypeNames = 51, 
		RULE_newtypeName = 52, RULE_boolean = 53, RULE_number = 54, RULE_string = 55;
	private static String[] makeRuleNames() {
		return new String[] {
			"schema", "definitions", "statement", "contextStatement", "importStatement", 
			"setDefinitionStatement", "allOfDefinitionStatement", "anyOfDefinitionStatement", 
			"typeDefinitionStatement", "unionDefinitionStatement", "enumDefinitionStatement", 
			"entityDefinitionStatement", "modificationStatement", "removeStatement", 
			"renameStatement", "removeFieldStatement", "renameFieldStatement", "overrideFieldStatement", 
			"defineFieldStatement", "setDefinition", "fieldDefinition", "unionDefinitions", 
			"enumDefinition", "extendsTypeList", "extends", "schemaPipelines", "override", 
			"define", "rename", "remove", "location", "value", "annotations", "annotation", 
			"annotationArgs", "annotationArg", "contextRenameCall", "contextRenameCallArgs", 
			"contextRenameCallArg", "label", "pipeName", "newFieldName", "labelList", 
			"labelSetList", "typeName", "complexTypeName", "complexTypeNameArg", 
			"complexTypeNameArgs", "relationshipTypeName", "relationshipArg", "relationshipArgs", 
			"allTypeNames", "newtypeName", "boolean", "number", "string"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'|'", "','", "'>'", "'('", "')'", "'.'", "'entity'", "'relationship'", 
			"'@'", "'set'", "'import'", "'to'", "'enum'", "'union'", "'modify'", 
			"'type'", "'anyOf'", "'allOf'", "'dto'", "'extends'", "'remove'", "'rename'", 
			"'define'", "'override'", "'context'", "'{'", "'}'", "':'", "'='", "'false'", 
			"'true'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "ENTITY", "RELATIONSHIP", "ANNO", 
			"SET", "IMPORT", "TO", "ENUM", "UNION", "MODIFY", "TYPE", "ANYOF", "ALLOF", 
			"DTO", "EXTENDS", "REMOVE", "RENAME", "DEFINE", "OVERRIDE", "CONTEXT", 
			"LBRACE", "RBRACE", "COLON", "EQ", "FALSE", "TRUE", "IDENTIFIER", "STRING", 
			"NUMBER", "LINE_COMMENT", "BLOCK_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "AdtSdl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AdtSdlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SchemaContext extends ParserRuleContext {
		public List<DefinitionsContext> definitions() {
			return getRuleContexts(DefinitionsContext.class);
		}
		public DefinitionsContext definitions(int i) {
			return getRuleContext(DefinitionsContext.class,i);
		}
		public SchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitSchema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaContext schema() throws RecognitionException {
		SchemaContext _localctx = new SchemaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_schema);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(112);
				definitions();
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 39972480L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefinitionsContext extends ParserRuleContext {
		public SetDefinitionStatementContext setDefinitionStatement() {
			return getRuleContext(SetDefinitionStatementContext.class,0);
		}
		public List<ImportStatementContext> importStatement() {
			return getRuleContexts(ImportStatementContext.class);
		}
		public ImportStatementContext importStatement(int i) {
			return getRuleContext(ImportStatementContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode EOF() { return getToken(AdtSdlParser.EOF, 0); }
		public List<ContextStatementContext> contextStatement() {
			return getRuleContexts(ContextStatementContext.class);
		}
		public ContextStatementContext contextStatement(int i) {
			return getRuleContext(ContextStatementContext.class,i);
		}
		public DefinitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definitions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterDefinitions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitDefinitions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitDefinitions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionsContext definitions() throws RecognitionException {
		DefinitionsContext _localctx = new DefinitionsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_definitions);
		int _la;
		try {
			int _alt;
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				setDefinitionStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(119); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(118);
						importStatement();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(121); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(124); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(123);
						statement();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(126); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(128);
					contextStatement();
					}
					}
					setState(131); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CONTEXT );
				setState(133);
				match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public ImportStatementContext importStatement() {
			return getRuleContext(ImportStatementContext.class,0);
		}
		public SetDefinitionStatementContext setDefinitionStatement() {
			return getRuleContext(SetDefinitionStatementContext.class,0);
		}
		public TypeDefinitionStatementContext typeDefinitionStatement() {
			return getRuleContext(TypeDefinitionStatementContext.class,0);
		}
		public EntityDefinitionStatementContext entityDefinitionStatement() {
			return getRuleContext(EntityDefinitionStatementContext.class,0);
		}
		public UnionDefinitionStatementContext unionDefinitionStatement() {
			return getRuleContext(UnionDefinitionStatementContext.class,0);
		}
		public EnumDefinitionStatementContext enumDefinitionStatement() {
			return getRuleContext(EnumDefinitionStatementContext.class,0);
		}
		public ModificationStatementContext modificationStatement() {
			return getRuleContext(ModificationStatementContext.class,0);
		}
		public RemoveStatementContext removeStatement() {
			return getRuleContext(RemoveStatementContext.class,0);
		}
		public RenameStatementContext renameStatement() {
			return getRuleContext(RenameStatementContext.class,0);
		}
		public RenameFieldStatementContext renameFieldStatement() {
			return getRuleContext(RenameFieldStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				importStatement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				setDefinitionStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(139);
				typeDefinitionStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(140);
				entityDefinitionStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(141);
				unionDefinitionStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(142);
				enumDefinitionStatement();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(143);
				modificationStatement();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(144);
				removeStatement();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(145);
				renameStatement();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(146);
				renameFieldStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContextStatementContext extends ParserRuleContext {
		public TerminalNode CONTEXT() { return getToken(AdtSdlParser.CONTEXT, 0); }
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(AdtSdlParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AdtSdlParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ContextRenameCallContext contextRenameCall() {
			return getRuleContext(ContextRenameCallContext.class,0);
		}
		public ContextStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterContextStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitContextStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitContextStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContextStatementContext contextStatement() throws RecognitionException {
		ContextStatementContext _localctx = new ContextStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_contextStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(CONTEXT);
			setState(150);
			label();
			setState(151);
			match(LBRACE);
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6418048L) != 0)) {
				{
				{
				setState(152);
				statement();
				}
				}
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(158);
			match(RBRACE);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RENAME) {
				{
				setState(159);
				contextRenameCall();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportStatementContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(AdtSdlParser.IMPORT, 0); }
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public SchemaPipelinesContext schemaPipelines() {
			return getRuleContext(SchemaPipelinesContext.class,0);
		}
		public ImportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterImportStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitImportStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitImportStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStatementContext importStatement() throws RecognitionException {
		ImportStatementContext _localctx = new ImportStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_importStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(IMPORT);
			setState(163);
			label();
			setState(164);
			location();
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(165);
				schemaPipelines();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetDefinitionStatementContext extends ParserRuleContext {
		public List<SetDefinitionContext> setDefinition() {
			return getRuleContexts(SetDefinitionContext.class);
		}
		public SetDefinitionContext setDefinition(int i) {
			return getRuleContext(SetDefinitionContext.class,i);
		}
		public SetDefinitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setDefinitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterSetDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitSetDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitSetDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetDefinitionStatementContext setDefinitionStatement() throws RecognitionException {
		SetDefinitionStatementContext _localctx = new SetDefinitionStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_setDefinitionStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(169); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(168);
					setDefinition();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(171); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AllOfDefinitionStatementContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode ALLOF() { return getToken(AdtSdlParser.ALLOF, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ExtendsContext extends_() {
			return getRuleContext(ExtendsContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(AdtSdlParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AdtSdlParser.RBRACE, 0); }
		public List<FieldDefinitionContext> fieldDefinition() {
			return getRuleContexts(FieldDefinitionContext.class);
		}
		public FieldDefinitionContext fieldDefinition(int i) {
			return getRuleContext(FieldDefinitionContext.class,i);
		}
		public AllOfDefinitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allOfDefinitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterAllOfDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitAllOfDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitAllOfDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllOfDefinitionStatementContext allOfDefinitionStatement() throws RecognitionException {
		AllOfDefinitionStatementContext _localctx = new AllOfDefinitionStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_allOfDefinitionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			annotations();
			setState(174);
			match(ALLOF);
			setState(175);
			typeName();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(176);
				extends_();
				}
			}

			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(179);
				match(LBRACE);
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ANNO || _la==IDENTIFIER) {
					{
					{
					setState(180);
					fieldDefinition();
					}
					}
					setState(185);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(186);
				match(RBRACE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnyOfDefinitionStatementContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode ANYOF() { return getToken(AdtSdlParser.ANYOF, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ExtendsContext extends_() {
			return getRuleContext(ExtendsContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(AdtSdlParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AdtSdlParser.RBRACE, 0); }
		public List<FieldDefinitionContext> fieldDefinition() {
			return getRuleContexts(FieldDefinitionContext.class);
		}
		public FieldDefinitionContext fieldDefinition(int i) {
			return getRuleContext(FieldDefinitionContext.class,i);
		}
		public AnyOfDefinitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anyOfDefinitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterAnyOfDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitAnyOfDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitAnyOfDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnyOfDefinitionStatementContext anyOfDefinitionStatement() throws RecognitionException {
		AnyOfDefinitionStatementContext _localctx = new AnyOfDefinitionStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_anyOfDefinitionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			annotations();
			setState(190);
			match(ANYOF);
			setState(191);
			typeName();
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(192);
				extends_();
				}
			}

			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(195);
				match(LBRACE);
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ANNO || _la==IDENTIFIER) {
					{
					{
					setState(196);
					fieldDefinition();
					}
					}
					setState(201);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(202);
				match(RBRACE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeDefinitionStatementContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(AdtSdlParser.TYPE, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ExtendsContext extends_() {
			return getRuleContext(ExtendsContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(AdtSdlParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AdtSdlParser.RBRACE, 0); }
		public List<FieldDefinitionContext> fieldDefinition() {
			return getRuleContexts(FieldDefinitionContext.class);
		}
		public FieldDefinitionContext fieldDefinition(int i) {
			return getRuleContext(FieldDefinitionContext.class,i);
		}
		public TypeDefinitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDefinitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterTypeDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitTypeDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitTypeDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefinitionStatementContext typeDefinitionStatement() throws RecognitionException {
		TypeDefinitionStatementContext _localctx = new TypeDefinitionStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeDefinitionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			annotations();
			setState(206);
			match(TYPE);
			setState(207);
			typeName();
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(208);
				extends_();
				}
			}

			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(211);
				match(LBRACE);
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ANNO || _la==IDENTIFIER) {
					{
					{
					setState(212);
					fieldDefinition();
					}
					}
					setState(217);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(218);
				match(RBRACE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnionDefinitionStatementContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode UNION() { return getToken(AdtSdlParser.UNION, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode EQ() { return getToken(AdtSdlParser.EQ, 0); }
		public List<UnionDefinitionsContext> unionDefinitions() {
			return getRuleContexts(UnionDefinitionsContext.class);
		}
		public UnionDefinitionsContext unionDefinitions(int i) {
			return getRuleContext(UnionDefinitionsContext.class,i);
		}
		public UnionDefinitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionDefinitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterUnionDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitUnionDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitUnionDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionDefinitionStatementContext unionDefinitionStatement() throws RecognitionException {
		UnionDefinitionStatementContext _localctx = new UnionDefinitionStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_unionDefinitionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			annotations();
			setState(222);
			match(UNION);
			setState(223);
			typeName();
			setState(224);
			match(EQ);
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENTIFIER) {
				{
				{
				setState(225);
				unionDefinitions();
				}
				}
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnumDefinitionStatementContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode ENUM() { return getToken(AdtSdlParser.ENUM, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(AdtSdlParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AdtSdlParser.RBRACE, 0); }
		public List<EnumDefinitionContext> enumDefinition() {
			return getRuleContexts(EnumDefinitionContext.class);
		}
		public EnumDefinitionContext enumDefinition(int i) {
			return getRuleContext(EnumDefinitionContext.class,i);
		}
		public EnumDefinitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDefinitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterEnumDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitEnumDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitEnumDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumDefinitionStatementContext enumDefinitionStatement() throws RecognitionException {
		EnumDefinitionStatementContext _localctx = new EnumDefinitionStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_enumDefinitionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			annotations();
			setState(232);
			match(ENUM);
			setState(233);
			typeName();
			setState(234);
			match(LBRACE);
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ANNO || _la==IDENTIFIER) {
				{
				{
				setState(235);
				enumDefinition();
				}
				}
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(241);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EntityDefinitionStatementContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode ENTITY() { return getToken(AdtSdlParser.ENTITY, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ExtendsContext extends_() {
			return getRuleContext(ExtendsContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(AdtSdlParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AdtSdlParser.RBRACE, 0); }
		public List<FieldDefinitionContext> fieldDefinition() {
			return getRuleContexts(FieldDefinitionContext.class);
		}
		public FieldDefinitionContext fieldDefinition(int i) {
			return getRuleContext(FieldDefinitionContext.class,i);
		}
		public EntityDefinitionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entityDefinitionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterEntityDefinitionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitEntityDefinitionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitEntityDefinitionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EntityDefinitionStatementContext entityDefinitionStatement() throws RecognitionException {
		EntityDefinitionStatementContext _localctx = new EntityDefinitionStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_entityDefinitionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			annotations();
			setState(244);
			match(ENTITY);
			setState(245);
			typeName();
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(246);
				extends_();
				}
			}

			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(249);
				match(LBRACE);
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ANNO || _la==IDENTIFIER) {
					{
					{
					setState(250);
					fieldDefinition();
					}
					}
					setState(255);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(256);
				match(RBRACE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModificationStatementContext extends ParserRuleContext {
		public TerminalNode MODIFY() { return getToken(AdtSdlParser.MODIFY, 0); }
		public UnionDefinitionStatementContext unionDefinitionStatement() {
			return getRuleContext(UnionDefinitionStatementContext.class,0);
		}
		public EnumDefinitionStatementContext enumDefinitionStatement() {
			return getRuleContext(EnumDefinitionStatementContext.class,0);
		}
		public EntityDefinitionStatementContext entityDefinitionStatement() {
			return getRuleContext(EntityDefinitionStatementContext.class,0);
		}
		public TypeDefinitionStatementContext typeDefinitionStatement() {
			return getRuleContext(TypeDefinitionStatementContext.class,0);
		}
		public AllOfDefinitionStatementContext allOfDefinitionStatement() {
			return getRuleContext(AllOfDefinitionStatementContext.class,0);
		}
		public AnyOfDefinitionStatementContext anyOfDefinitionStatement() {
			return getRuleContext(AnyOfDefinitionStatementContext.class,0);
		}
		public RemoveFieldStatementContext removeFieldStatement() {
			return getRuleContext(RemoveFieldStatementContext.class,0);
		}
		public RenameFieldStatementContext renameFieldStatement() {
			return getRuleContext(RenameFieldStatementContext.class,0);
		}
		public OverrideFieldStatementContext overrideFieldStatement() {
			return getRuleContext(OverrideFieldStatementContext.class,0);
		}
		public DefineFieldStatementContext defineFieldStatement() {
			return getRuleContext(DefineFieldStatementContext.class,0);
		}
		public SchemaPipelinesContext schemaPipelines() {
			return getRuleContext(SchemaPipelinesContext.class,0);
		}
		public ModificationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modificationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterModificationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitModificationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitModificationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModificationStatementContext modificationStatement() throws RecognitionException {
		ModificationStatementContext _localctx = new ModificationStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_modificationStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			match(MODIFY);
			setState(295);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(264);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(260);
					entityDefinitionStatement();
					}
					break;
				case 2:
					{
					setState(261);
					typeDefinitionStatement();
					}
					break;
				case 3:
					{
					setState(262);
					allOfDefinitionStatement();
					}
					break;
				case 4:
					{
					setState(263);
					anyOfDefinitionStatement();
					}
					break;
				}
				{
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(266);
					schemaPipelines();
					}
				}

				}
				setState(270);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(269);
					removeFieldStatement();
					}
					break;
				}
				setState(273);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(272);
					renameFieldStatement();
					}
					break;
				}
				setState(276);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OVERRIDE) {
					{
					setState(275);
					overrideFieldStatement();
					}
				}

				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DEFINE) {
					{
					setState(278);
					defineFieldStatement();
					}
				}

				}
				break;
			case 2:
				{
				setState(281);
				unionDefinitionStatement();
				}
				break;
			case 3:
				{
				setState(282);
				enumDefinitionStatement();
				setState(284);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(283);
					removeFieldStatement();
					}
					break;
				}
				setState(287);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
				case 1:
					{
					setState(286);
					renameFieldStatement();
					}
					break;
				}
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OVERRIDE) {
					{
					setState(289);
					overrideFieldStatement();
					}
				}

				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DEFINE) {
					{
					setState(292);
					defineFieldStatement();
					}
				}

				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RemoveStatementContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode REMOVE() { return getToken(AdtSdlParser.REMOVE, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(AdtSdlParser.TYPE, 0); }
		public TerminalNode ALLOF() { return getToken(AdtSdlParser.ALLOF, 0); }
		public TerminalNode ANYOF() { return getToken(AdtSdlParser.ANYOF, 0); }
		public TerminalNode ENTITY() { return getToken(AdtSdlParser.ENTITY, 0); }
		public TerminalNode ENUM() { return getToken(AdtSdlParser.ENUM, 0); }
		public TerminalNode UNION() { return getToken(AdtSdlParser.UNION, 0); }
		public RemoveStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_removeStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterRemoveStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitRemoveStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitRemoveStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveStatementContext removeStatement() throws RecognitionException {
		RemoveStatementContext _localctx = new RemoveStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_removeStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			annotations();
			setState(298);
			match(REMOVE);
			setState(299);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 483456L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(300);
			typeName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RenameStatementContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public TerminalNode RENAME() { return getToken(AdtSdlParser.RENAME, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode TO() { return getToken(AdtSdlParser.TO, 0); }
		public NewtypeNameContext newtypeName() {
			return getRuleContext(NewtypeNameContext.class,0);
		}
		public TerminalNode TYPE() { return getToken(AdtSdlParser.TYPE, 0); }
		public TerminalNode ALLOF() { return getToken(AdtSdlParser.ALLOF, 0); }
		public TerminalNode ANYOF() { return getToken(AdtSdlParser.ANYOF, 0); }
		public TerminalNode ENTITY() { return getToken(AdtSdlParser.ENTITY, 0); }
		public TerminalNode ENUM() { return getToken(AdtSdlParser.ENUM, 0); }
		public TerminalNode UNION() { return getToken(AdtSdlParser.UNION, 0); }
		public RenameStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renameStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterRenameStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitRenameStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitRenameStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenameStatementContext renameStatement() throws RecognitionException {
		RenameStatementContext _localctx = new RenameStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_renameStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			annotations();
			setState(303);
			match(RENAME);
			setState(304);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 483456L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(305);
			typeName();
			setState(306);
			match(TO);
			setState(307);
			newtypeName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RemoveFieldStatementContext extends ParserRuleContext {
		public RemoveContext remove() {
			return getRuleContext(RemoveContext.class,0);
		}
		public LabelListContext labelList() {
			return getRuleContext(LabelListContext.class,0);
		}
		public RemoveFieldStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_removeFieldStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterRemoveFieldStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitRemoveFieldStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitRemoveFieldStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveFieldStatementContext removeFieldStatement() throws RecognitionException {
		RemoveFieldStatementContext _localctx = new RemoveFieldStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_removeFieldStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			remove();
			setState(310);
			labelList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RenameFieldStatementContext extends ParserRuleContext {
		public RenameContext rename() {
			return getRuleContext(RenameContext.class,0);
		}
		public LabelSetListContext labelSetList() {
			return getRuleContext(LabelSetListContext.class,0);
		}
		public RenameFieldStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_renameFieldStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterRenameFieldStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitRenameFieldStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitRenameFieldStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenameFieldStatementContext renameFieldStatement() throws RecognitionException {
		RenameFieldStatementContext _localctx = new RenameFieldStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_renameFieldStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			rename();
			setState(313);
			labelSetList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OverrideFieldStatementContext extends ParserRuleContext {
		public OverrideContext override() {
			return getRuleContext(OverrideContext.class,0);
		}
		public FieldDefinitionContext fieldDefinition() {
			return getRuleContext(FieldDefinitionContext.class,0);
		}
		public OverrideFieldStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_overrideFieldStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterOverrideFieldStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitOverrideFieldStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitOverrideFieldStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OverrideFieldStatementContext overrideFieldStatement() throws RecognitionException {
		OverrideFieldStatementContext _localctx = new OverrideFieldStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_overrideFieldStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			override();
			setState(316);
			fieldDefinition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefineFieldStatementContext extends ParserRuleContext {
		public DefineContext define() {
			return getRuleContext(DefineContext.class,0);
		}
		public FieldDefinitionContext fieldDefinition() {
			return getRuleContext(FieldDefinitionContext.class,0);
		}
		public DefineFieldStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineFieldStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterDefineFieldStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitDefineFieldStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitDefineFieldStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineFieldStatementContext defineFieldStatement() throws RecognitionException {
		DefineFieldStatementContext _localctx = new DefineFieldStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_defineFieldStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			define();
			setState(319);
			fieldDefinition();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetDefinitionContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(AdtSdlParser.SET, 0); }
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public SetDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterSetDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitSetDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitSetDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetDefinitionContext setDefinition() throws RecognitionException {
		SetDefinitionContext _localctx = new SetDefinitionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_setDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			match(SET);
			setState(322);
			label();
			setState(323);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldDefinitionContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode COLON() { return getToken(AdtSdlParser.COLON, 0); }
		public AllTypeNamesContext allTypeNames() {
			return getRuleContext(AllTypeNamesContext.class,0);
		}
		public FieldDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterFieldDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitFieldDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitFieldDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldDefinitionContext fieldDefinition() throws RecognitionException {
		FieldDefinitionContext _localctx = new FieldDefinitionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_fieldDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			annotations();
			setState(326);
			label();
			setState(327);
			match(COLON);
			setState(328);
			allTypeNames();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnionDefinitionsContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public UnionDefinitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionDefinitions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterUnionDefinitions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitUnionDefinitions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitUnionDefinitions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionDefinitionsContext unionDefinitions() throws RecognitionException {
		UnionDefinitionsContext _localctx = new UnionDefinitionsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_unionDefinitions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			typeName();
			setState(333); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(331);
				match(T__0);
				setState(332);
				typeName();
				}
				}
				setState(335); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnumDefinitionContext extends ParserRuleContext {
		public AnnotationsContext annotations() {
			return getRuleContext(AnnotationsContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode EQ() { return getToken(AdtSdlParser.EQ, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public EnumDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterEnumDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitEnumDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitEnumDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumDefinitionContext enumDefinition() throws RecognitionException {
		EnumDefinitionContext _localctx = new EnumDefinitionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_enumDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			annotations();
			setState(338);
			label();
			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EQ) {
				{
				setState(339);
				match(EQ);
				setState(340);
				value();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExtendsTypeListContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public ExtendsTypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extendsTypeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterExtendsTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitExtendsTypeList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitExtendsTypeList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtendsTypeListContext extendsTypeList() throws RecognitionException {
		ExtendsTypeListContext _localctx = new ExtendsTypeListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_extendsTypeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			typeName();
			setState(348);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(344);
				match(T__1);
				setState(345);
				typeName();
				}
				}
				setState(350);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExtendsContext extends ParserRuleContext {
		public TerminalNode EXTENDS() { return getToken(AdtSdlParser.EXTENDS, 0); }
		public ExtendsTypeListContext extendsTypeList() {
			return getRuleContext(ExtendsTypeListContext.class,0);
		}
		public ExtendsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extends; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterExtends(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitExtends(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitExtends(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExtendsContext extends_() throws RecognitionException {
		ExtendsContext _localctx = new ExtendsContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_extends);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(351);
			match(EXTENDS);
			setState(352);
			extendsTypeList();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SchemaPipelinesContext extends ParserRuleContext {
		public List<PipeNameContext> pipeName() {
			return getRuleContexts(PipeNameContext.class);
		}
		public PipeNameContext pipeName(int i) {
			return getRuleContext(PipeNameContext.class,i);
		}
		public SchemaPipelinesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schemaPipelines; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterSchemaPipelines(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitSchemaPipelines(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitSchemaPipelines(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaPipelinesContext schemaPipelines() throws RecognitionException {
		SchemaPipelinesContext _localctx = new SchemaPipelinesContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_schemaPipelines);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(355); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(354);
				match(T__2);
				}
				}
				setState(357); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			setState(359);
			pipeName();
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				{
				setState(360);
				match(T__1);
				}
				setState(361);
				pipeName();
				}
			}

			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OverrideContext extends ParserRuleContext {
		public TerminalNode OVERRIDE() { return getToken(AdtSdlParser.OVERRIDE, 0); }
		public OverrideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_override; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterOverride(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitOverride(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitOverride(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OverrideContext override() throws RecognitionException {
		OverrideContext _localctx = new OverrideContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_override);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(OVERRIDE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefineContext extends ParserRuleContext {
		public TerminalNode DEFINE() { return getToken(AdtSdlParser.DEFINE, 0); }
		public DefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_define; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineContext define() throws RecognitionException {
		DefineContext _localctx = new DefineContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_define);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(DEFINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RenameContext extends ParserRuleContext {
		public TerminalNode RENAME() { return getToken(AdtSdlParser.RENAME, 0); }
		public RenameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rename; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterRename(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitRename(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitRename(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RenameContext rename() throws RecognitionException {
		RenameContext _localctx = new RenameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_rename);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			match(RENAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RemoveContext extends ParserRuleContext {
		public TerminalNode REMOVE() { return getToken(AdtSdlParser.REMOVE, 0); }
		public RemoveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_remove; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterRemove(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitRemove(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitRemove(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveContext remove() throws RecognitionException {
		RemoveContext _localctx = new RemoveContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_remove);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			match(REMOVE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocationContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(AdtSdlParser.STRING, 0); }
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitLocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitLocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_location);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public BooleanContext boolean_() {
			return getRuleContext(BooleanContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(374);
				string();
				}
				break;
			case NUMBER:
				{
				setState(375);
				number();
				}
				break;
			case FALSE:
			case TRUE:
				{
				setState(376);
				boolean_();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationsContext extends ParserRuleContext {
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AnnotationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterAnnotations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitAnnotations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitAnnotations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationsContext annotations() throws RecognitionException {
		AnnotationsContext _localctx = new AnnotationsContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_annotations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ANNO) {
				{
				setState(380); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(379);
					annotation();
					}
					}
					setState(382); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ANNO );
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode ANNO() { return getToken(AdtSdlParser.ANNO, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public AnnotationArgsContext annotationArgs() {
			return getRuleContext(AnnotationArgsContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			match(ANNO);
			setState(387);
			typeName();
			setState(393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(388);
				match(T__3);
				setState(390);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(389);
					annotationArgs();
					}
				}

				setState(392);
				match(T__4);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationArgsContext extends ParserRuleContext {
		public List<AnnotationArgContext> annotationArg() {
			return getRuleContexts(AnnotationArgContext.class);
		}
		public AnnotationArgContext annotationArg(int i) {
			return getRuleContext(AnnotationArgContext.class,i);
		}
		public AnnotationArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterAnnotationArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitAnnotationArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitAnnotationArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationArgsContext annotationArgs() throws RecognitionException {
		AnnotationArgsContext _localctx = new AnnotationArgsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_annotationArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			annotationArg();
			setState(400);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(396);
				match(T__1);
				setState(397);
				annotationArg();
				}
				}
				setState(402);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationArgContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode EQ() { return getToken(AdtSdlParser.EQ, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public AnnotationArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterAnnotationArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitAnnotationArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitAnnotationArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationArgContext annotationArg() throws RecognitionException {
		AnnotationArgContext _localctx = new AnnotationArgContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_annotationArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(403);
			label();
			setState(404);
			match(EQ);
			setState(407);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(405);
				string();
				}
				break;
			case NUMBER:
				{
				setState(406);
				number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContextRenameCallContext extends ParserRuleContext {
		public RenameContext rename() {
			return getRuleContext(RenameContext.class,0);
		}
		public ContextRenameCallArgsContext contextRenameCallArgs() {
			return getRuleContext(ContextRenameCallArgsContext.class,0);
		}
		public ContextRenameCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextRenameCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterContextRenameCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitContextRenameCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitContextRenameCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContextRenameCallContext contextRenameCall() throws RecognitionException {
		ContextRenameCallContext _localctx = new ContextRenameCallContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_contextRenameCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			rename();
			setState(415);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(410);
				match(T__3);
				setState(412);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRING || _la==NUMBER) {
					{
					setState(411);
					contextRenameCallArgs();
					}
				}

				setState(414);
				match(T__4);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContextRenameCallArgsContext extends ParserRuleContext {
		public List<ContextRenameCallArgContext> contextRenameCallArg() {
			return getRuleContexts(ContextRenameCallArgContext.class);
		}
		public ContextRenameCallArgContext contextRenameCallArg(int i) {
			return getRuleContext(ContextRenameCallArgContext.class,i);
		}
		public ContextRenameCallArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextRenameCallArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterContextRenameCallArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitContextRenameCallArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitContextRenameCallArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContextRenameCallArgsContext contextRenameCallArgs() throws RecognitionException {
		ContextRenameCallArgsContext _localctx = new ContextRenameCallArgsContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_contextRenameCallArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			contextRenameCallArg();
			setState(422);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(418);
				match(T__1);
				setState(419);
				contextRenameCallArg();
				}
				}
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContextRenameCallArgContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ContextRenameCallArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextRenameCallArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterContextRenameCallArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitContextRenameCallArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitContextRenameCallArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContextRenameCallArgContext contextRenameCallArg() throws RecognitionException {
		ContextRenameCallArgContext _localctx = new ContextRenameCallArgContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_contextRenameCallArg);
		try {
			setState(427);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(425);
				string();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(426);
				number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AdtSdlParser.IDENTIFIER, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PipeNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AdtSdlParser.IDENTIFIER, 0); }
		public PipeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pipeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterPipeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitPipeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitPipeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PipeNameContext pipeName() throws RecognitionException {
		PipeNameContext _localctx = new PipeNameContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_pipeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NewFieldNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AdtSdlParser.IDENTIFIER, 0); }
		public NewFieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newFieldName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterNewFieldName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitNewFieldName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitNewFieldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewFieldNameContext newFieldName() throws RecognitionException {
		NewFieldNameContext _localctx = new NewFieldNameContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_newFieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelListContext extends ParserRuleContext {
		public List<LabelContext> label() {
			return getRuleContexts(LabelContext.class);
		}
		public LabelContext label(int i) {
			return getRuleContext(LabelContext.class,i);
		}
		public LabelListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterLabelList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitLabelList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitLabelList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelListContext labelList() throws RecognitionException {
		LabelListContext _localctx = new LabelListContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_labelList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			label();
			setState(440);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(436);
				match(T__1);
				setState(437);
				label();
				}
				}
				setState(442);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelSetListContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode TO() { return getToken(AdtSdlParser.TO, 0); }
		public NewFieldNameContext newFieldName() {
			return getRuleContext(NewFieldNameContext.class,0);
		}
		public LabelSetListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelSetList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterLabelSetList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitLabelSetList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitLabelSetList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelSetListContext labelSetList() throws RecognitionException {
		LabelSetListContext _localctx = new LabelSetListContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_labelSetList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443);
			label();
			setState(444);
			match(TO);
			setState(445);
			newFieldName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(AdtSdlParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(AdtSdlParser.IDENTIFIER, i);
		}
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_typeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			match(IDENTIFIER);
			setState(452);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(448);
				match(T__5);
				setState(449);
				match(IDENTIFIER);
				}
				}
				setState(454);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComplexTypeNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(AdtSdlParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(AdtSdlParser.IDENTIFIER, i);
		}
		public ComplexTypeNameArgsContext complexTypeNameArgs() {
			return getRuleContext(ComplexTypeNameArgsContext.class,0);
		}
		public ComplexTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complexTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterComplexTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitComplexTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitComplexTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComplexTypeNameContext complexTypeName() throws RecognitionException {
		ComplexTypeNameContext _localctx = new ComplexTypeNameContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_complexTypeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			match(IDENTIFIER);
			setState(460);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(456);
				match(T__5);
				setState(457);
				match(IDENTIFIER);
				}
				}
				setState(462);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			{
			setState(463);
			match(T__3);
			setState(464);
			complexTypeNameArgs();
			setState(465);
			match(T__4);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComplexTypeNameArgContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ComplexTypeNameArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complexTypeNameArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterComplexTypeNameArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitComplexTypeNameArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitComplexTypeNameArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComplexTypeNameArgContext complexTypeNameArg() throws RecognitionException {
		ComplexTypeNameArgContext _localctx = new ComplexTypeNameArgContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_complexTypeNameArg);
		try {
			setState(469);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(467);
				string();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(468);
				number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComplexTypeNameArgsContext extends ParserRuleContext {
		public List<ComplexTypeNameArgContext> complexTypeNameArg() {
			return getRuleContexts(ComplexTypeNameArgContext.class);
		}
		public ComplexTypeNameArgContext complexTypeNameArg(int i) {
			return getRuleContext(ComplexTypeNameArgContext.class,i);
		}
		public ComplexTypeNameArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complexTypeNameArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterComplexTypeNameArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitComplexTypeNameArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitComplexTypeNameArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComplexTypeNameArgsContext complexTypeNameArgs() throws RecognitionException {
		ComplexTypeNameArgsContext _localctx = new ComplexTypeNameArgsContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_complexTypeNameArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			complexTypeNameArg();
			setState(476);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(472);
				match(T__1);
				setState(473);
				complexTypeNameArg();
				}
				}
				setState(478);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationshipTypeNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(AdtSdlParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(AdtSdlParser.IDENTIFIER, i);
		}
		public RelationshipArgsContext relationshipArgs() {
			return getRuleContext(RelationshipArgsContext.class,0);
		}
		public RelationshipTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterRelationshipTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitRelationshipTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitRelationshipTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipTypeNameContext relationshipTypeName() throws RecognitionException {
		RelationshipTypeNameContext _localctx = new RelationshipTypeNameContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_relationshipTypeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			match(IDENTIFIER);
			setState(484);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(480);
				match(T__5);
				setState(481);
				match(IDENTIFIER);
				}
				}
				setState(486);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(492);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(487);
				match(T__3);
				setState(489);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(488);
					relationshipArgs();
					}
				}

				setState(491);
				match(T__4);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationshipArgContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public TerminalNode EQ() { return getToken(AdtSdlParser.EQ, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public RelationshipArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterRelationshipArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitRelationshipArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitRelationshipArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipArgContext relationshipArg() throws RecognitionException {
		RelationshipArgContext _localctx = new RelationshipArgContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_relationshipArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(494);
			label();
			setState(495);
			match(EQ);
			setState(498);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(496);
				string();
				}
				break;
			case NUMBER:
				{
				setState(497);
				number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationshipArgsContext extends ParserRuleContext {
		public List<RelationshipArgContext> relationshipArg() {
			return getRuleContexts(RelationshipArgContext.class);
		}
		public RelationshipArgContext relationshipArg(int i) {
			return getRuleContext(RelationshipArgContext.class,i);
		}
		public RelationshipArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterRelationshipArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitRelationshipArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitRelationshipArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipArgsContext relationshipArgs() throws RecognitionException {
		RelationshipArgsContext _localctx = new RelationshipArgsContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_relationshipArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			relationshipArg();
			setState(505);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(501);
				match(T__1);
				setState(502);
				relationshipArg();
				}
				}
				setState(507);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AllTypeNamesContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ComplexTypeNameContext complexTypeName() {
			return getRuleContext(ComplexTypeNameContext.class,0);
		}
		public RelationshipTypeNameContext relationshipTypeName() {
			return getRuleContext(RelationshipTypeNameContext.class,0);
		}
		public AllTypeNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allTypeNames; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterAllTypeNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitAllTypeNames(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitAllTypeNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AllTypeNamesContext allTypeNames() throws RecognitionException {
		AllTypeNamesContext _localctx = new AllTypeNamesContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_allTypeNames);
		try {
			setState(511);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(508);
				typeName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(509);
				complexTypeName();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(510);
				relationshipTypeName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NewtypeNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(AdtSdlParser.IDENTIFIER, 0); }
		public NewtypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newtypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterNewtypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitNewtypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitNewtypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewtypeNameContext newtypeName() throws RecognitionException {
		NewtypeNameContext _localctx = new NewtypeNameContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_newtypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanContext extends ParserRuleContext {
		public TerminalNode FALSE() { return getToken(AdtSdlParser.FALSE, 0); }
		public TerminalNode TRUE() { return getToken(AdtSdlParser.TRUE, 0); }
		public BooleanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanContext boolean_() throws RecognitionException {
		BooleanContext _localctx = new BooleanContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_boolean);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(515);
			_la = _input.LA(1);
			if ( !(_la==FALSE || _la==TRUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(AdtSdlParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(AdtSdlParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AdtSdlListener ) ((AdtSdlListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AdtSdlVisitor ) return ((AdtSdlVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(519);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001%\u020a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u0001\u0000\u0004\u0000r\b\u0000\u000b\u0000\f\u0000s\u0001\u0001"+
		"\u0001\u0001\u0004\u0001x\b\u0001\u000b\u0001\f\u0001y\u0001\u0001\u0004"+
		"\u0001}\b\u0001\u000b\u0001\f\u0001~\u0001\u0001\u0004\u0001\u0082\b\u0001"+
		"\u000b\u0001\f\u0001\u0083\u0001\u0001\u0001\u0001\u0003\u0001\u0088\b"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u0094"+
		"\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u009a"+
		"\b\u0003\n\u0003\f\u0003\u009d\t\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"\u00a1\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"\u00a7\b\u0004\u0001\u0005\u0004\u0005\u00aa\b\u0005\u000b\u0005\f\u0005"+
		"\u00ab\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00b2"+
		"\b\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00b6\b\u0006\n\u0006\f\u0006"+
		"\u00b9\t\u0006\u0001\u0006\u0003\u0006\u00bc\b\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00c2\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0005\u0007\u00c6\b\u0007\n\u0007\f\u0007\u00c9\t\u0007\u0001\u0007"+
		"\u0003\u0007\u00cc\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00d2"+
		"\b\b\u0001\b\u0001\b\u0005\b\u00d6\b\b\n\b\f\b\u00d9\t\b\u0001\b\u0003"+
		"\b\u00dc\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u00e3\b\t"+
		"\n\t\f\t\u00e6\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u00ed"+
		"\b\n\n\n\f\n\u00f0\t\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u00f8\b\u000b\u0001\u000b\u0001\u000b\u0005\u000b"+
		"\u00fc\b\u000b\n\u000b\f\u000b\u00ff\t\u000b\u0001\u000b\u0003\u000b\u0102"+
		"\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0109\b\f\u0001"+
		"\f\u0003\f\u010c\b\f\u0001\f\u0003\f\u010f\b\f\u0001\f\u0003\f\u0112\b"+
		"\f\u0001\f\u0003\f\u0115\b\f\u0001\f\u0003\f\u0118\b\f\u0001\f\u0001\f"+
		"\u0001\f\u0003\f\u011d\b\f\u0001\f\u0003\f\u0120\b\f\u0001\f\u0003\f\u0123"+
		"\b\f\u0001\f\u0003\f\u0126\b\f\u0003\f\u0128\b\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0004\u0015\u014e\b\u0015\u000b\u0015\f"+
		"\u0015\u014f\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u0156\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u015b\b"+
		"\u0017\n\u0017\f\u0017\u015e\t\u0017\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0019\u0004\u0019\u0164\b\u0019\u000b\u0019\f\u0019\u0165\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u016b\b\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0003"+
		"\u001f\u017a\b\u001f\u0001 \u0004 \u017d\b \u000b \f \u017e\u0003 \u0181"+
		"\b \u0001!\u0001!\u0001!\u0001!\u0003!\u0187\b!\u0001!\u0003!\u018a\b"+
		"!\u0001\"\u0001\"\u0001\"\u0005\"\u018f\b\"\n\"\f\"\u0192\t\"\u0001#\u0001"+
		"#\u0001#\u0001#\u0003#\u0198\b#\u0001$\u0001$\u0001$\u0003$\u019d\b$\u0001"+
		"$\u0003$\u01a0\b$\u0001%\u0001%\u0001%\u0005%\u01a5\b%\n%\f%\u01a8\t%"+
		"\u0001&\u0001&\u0003&\u01ac\b&\u0001\'\u0001\'\u0001(\u0001(\u0001)\u0001"+
		")\u0001*\u0001*\u0001*\u0005*\u01b7\b*\n*\f*\u01ba\t*\u0001+\u0001+\u0001"+
		"+\u0001+\u0001,\u0001,\u0001,\u0005,\u01c3\b,\n,\f,\u01c6\t,\u0001-\u0001"+
		"-\u0001-\u0005-\u01cb\b-\n-\f-\u01ce\t-\u0001-\u0001-\u0001-\u0001-\u0001"+
		".\u0001.\u0003.\u01d6\b.\u0001/\u0001/\u0001/\u0005/\u01db\b/\n/\f/\u01de"+
		"\t/\u00010\u00010\u00010\u00050\u01e3\b0\n0\f0\u01e6\t0\u00010\u00010"+
		"\u00030\u01ea\b0\u00010\u00030\u01ed\b0\u00011\u00011\u00011\u00011\u0003"+
		"1\u01f3\b1\u00012\u00012\u00012\u00052\u01f8\b2\n2\f2\u01fb\t2\u00013"+
		"\u00013\u00013\u00033\u0200\b3\u00014\u00014\u00015\u00015\u00016\u0001"+
		"6\u00017\u00017\u00017\u0000\u00008\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF"+
		"HJLNPRTVXZ\\^`bdfhjln\u0000\u0002\u0003\u0000\u0007\u0007\r\u000e\u0010"+
		"\u0012\u0001\u0000\u001e\u001f\u021e\u0000q\u0001\u0000\u0000\u0000\u0002"+
		"\u0087\u0001\u0000\u0000\u0000\u0004\u0093\u0001\u0000\u0000\u0000\u0006"+
		"\u0095\u0001\u0000\u0000\u0000\b\u00a2\u0001\u0000\u0000\u0000\n\u00a9"+
		"\u0001\u0000\u0000\u0000\f\u00ad\u0001\u0000\u0000\u0000\u000e\u00bd\u0001"+
		"\u0000\u0000\u0000\u0010\u00cd\u0001\u0000\u0000\u0000\u0012\u00dd\u0001"+
		"\u0000\u0000\u0000\u0014\u00e7\u0001\u0000\u0000\u0000\u0016\u00f3\u0001"+
		"\u0000\u0000\u0000\u0018\u0103\u0001\u0000\u0000\u0000\u001a\u0129\u0001"+
		"\u0000\u0000\u0000\u001c\u012e\u0001\u0000\u0000\u0000\u001e\u0135\u0001"+
		"\u0000\u0000\u0000 \u0138\u0001\u0000\u0000\u0000\"\u013b\u0001\u0000"+
		"\u0000\u0000$\u013e\u0001\u0000\u0000\u0000&\u0141\u0001\u0000\u0000\u0000"+
		"(\u0145\u0001\u0000\u0000\u0000*\u014a\u0001\u0000\u0000\u0000,\u0151"+
		"\u0001\u0000\u0000\u0000.\u0157\u0001\u0000\u0000\u00000\u015f\u0001\u0000"+
		"\u0000\u00002\u0163\u0001\u0000\u0000\u00004\u016c\u0001\u0000\u0000\u0000"+
		"6\u016e\u0001\u0000\u0000\u00008\u0170\u0001\u0000\u0000\u0000:\u0172"+
		"\u0001\u0000\u0000\u0000<\u0174\u0001\u0000\u0000\u0000>\u0179\u0001\u0000"+
		"\u0000\u0000@\u0180\u0001\u0000\u0000\u0000B\u0182\u0001\u0000\u0000\u0000"+
		"D\u018b\u0001\u0000\u0000\u0000F\u0193\u0001\u0000\u0000\u0000H\u0199"+
		"\u0001\u0000\u0000\u0000J\u01a1\u0001\u0000\u0000\u0000L\u01ab\u0001\u0000"+
		"\u0000\u0000N\u01ad\u0001\u0000\u0000\u0000P\u01af\u0001\u0000\u0000\u0000"+
		"R\u01b1\u0001\u0000\u0000\u0000T\u01b3\u0001\u0000\u0000\u0000V\u01bb"+
		"\u0001\u0000\u0000\u0000X\u01bf\u0001\u0000\u0000\u0000Z\u01c7\u0001\u0000"+
		"\u0000\u0000\\\u01d5\u0001\u0000\u0000\u0000^\u01d7\u0001\u0000\u0000"+
		"\u0000`\u01df\u0001\u0000\u0000\u0000b\u01ee\u0001\u0000\u0000\u0000d"+
		"\u01f4\u0001\u0000\u0000\u0000f\u01ff\u0001\u0000\u0000\u0000h\u0201\u0001"+
		"\u0000\u0000\u0000j\u0203\u0001\u0000\u0000\u0000l\u0205\u0001\u0000\u0000"+
		"\u0000n\u0207\u0001\u0000\u0000\u0000pr\u0003\u0002\u0001\u0000qp\u0001"+
		"\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000"+
		"st\u0001\u0000\u0000\u0000t\u0001\u0001\u0000\u0000\u0000u\u0088\u0003"+
		"\n\u0005\u0000vx\u0003\b\u0004\u0000wv\u0001\u0000\u0000\u0000xy\u0001"+
		"\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000"+
		"z\u0088\u0001\u0000\u0000\u0000{}\u0003\u0004\u0002\u0000|{\u0001\u0000"+
		"\u0000\u0000}~\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~\u007f"+
		"\u0001\u0000\u0000\u0000\u007f\u0088\u0001\u0000\u0000\u0000\u0080\u0082"+
		"\u0003\u0006\u0003\u0000\u0081\u0080\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0083\u0084"+
		"\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0086"+
		"\u0005\u0000\u0000\u0001\u0086\u0088\u0001\u0000\u0000\u0000\u0087u\u0001"+
		"\u0000\u0000\u0000\u0087w\u0001\u0000\u0000\u0000\u0087|\u0001\u0000\u0000"+
		"\u0000\u0087\u0081\u0001\u0000\u0000\u0000\u0088\u0003\u0001\u0000\u0000"+
		"\u0000\u0089\u0094\u0003\b\u0004\u0000\u008a\u0094\u0003\n\u0005\u0000"+
		"\u008b\u0094\u0003\u0010\b\u0000\u008c\u0094\u0003\u0016\u000b\u0000\u008d"+
		"\u0094\u0003\u0012\t\u0000\u008e\u0094\u0003\u0014\n\u0000\u008f\u0094"+
		"\u0003\u0018\f\u0000\u0090\u0094\u0003\u001a\r\u0000\u0091\u0094\u0003"+
		"\u001c\u000e\u0000\u0092\u0094\u0003 \u0010\u0000\u0093\u0089\u0001\u0000"+
		"\u0000\u0000\u0093\u008a\u0001\u0000\u0000\u0000\u0093\u008b\u0001\u0000"+
		"\u0000\u0000\u0093\u008c\u0001\u0000\u0000\u0000\u0093\u008d\u0001\u0000"+
		"\u0000\u0000\u0093\u008e\u0001\u0000\u0000\u0000\u0093\u008f\u0001\u0000"+
		"\u0000\u0000\u0093\u0090\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000"+
		"\u0000\u0000\u0093\u0092\u0001\u0000\u0000\u0000\u0094\u0005\u0001\u0000"+
		"\u0000\u0000\u0095\u0096\u0005\u0019\u0000\u0000\u0096\u0097\u0003N\'"+
		"\u0000\u0097\u009b\u0005\u001a\u0000\u0000\u0098\u009a\u0003\u0004\u0002"+
		"\u0000\u0099\u0098\u0001\u0000\u0000\u0000\u009a\u009d\u0001\u0000\u0000"+
		"\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000"+
		"\u0000\u009c\u009e\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000"+
		"\u0000\u009e\u00a0\u0005\u001b\u0000\u0000\u009f\u00a1\u0003H$\u0000\u00a0"+
		"\u009f\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1"+
		"\u0007\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005\u000b\u0000\u0000\u00a3"+
		"\u00a4\u0003N\'\u0000\u00a4\u00a6\u0003<\u001e\u0000\u00a5\u00a7\u0003"+
		"2\u0019\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a7\t\u0001\u0000\u0000\u0000\u00a8\u00aa\u0003&\u0013"+
		"\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000"+
		"\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000"+
		"\u0000\u00ac\u000b\u0001\u0000\u0000\u0000\u00ad\u00ae\u0003@ \u0000\u00ae"+
		"\u00af\u0005\u0012\u0000\u0000\u00af\u00b1\u0003X,\u0000\u00b0\u00b2\u0003"+
		"0\u0018\u0000\u00b1\u00b0\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b2\u00bb\u0001\u0000\u0000\u0000\u00b3\u00b7\u0005\u001a"+
		"\u0000\u0000\u00b4\u00b6\u0003(\u0014\u0000\u00b5\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b6\u00b9\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00ba\u0001\u0000\u0000"+
		"\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000\u00ba\u00bc\u0005\u001b\u0000"+
		"\u0000\u00bb\u00b3\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bc\r\u0001\u0000\u0000\u0000\u00bd\u00be\u0003@ \u0000\u00be"+
		"\u00bf\u0005\u0011\u0000\u0000\u00bf\u00c1\u0003X,\u0000\u00c0\u00c2\u0003"+
		"0\u0018\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c2\u00cb\u0001\u0000\u0000\u0000\u00c3\u00c7\u0005\u001a"+
		"\u0000\u0000\u00c4\u00c6\u0003(\u0014\u0000\u00c5\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c6\u00c9\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00ca\u0001\u0000\u0000"+
		"\u0000\u00c9\u00c7\u0001\u0000\u0000\u0000\u00ca\u00cc\u0005\u001b\u0000"+
		"\u0000\u00cb\u00c3\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000"+
		"\u0000\u00cc\u000f\u0001\u0000\u0000\u0000\u00cd\u00ce\u0003@ \u0000\u00ce"+
		"\u00cf\u0005\u0010\u0000\u0000\u00cf\u00d1\u0003X,\u0000\u00d0\u00d2\u0003"+
		"0\u0018\u0000\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000"+
		"\u0000\u0000\u00d2\u00db\u0001\u0000\u0000\u0000\u00d3\u00d7\u0005\u001a"+
		"\u0000\u0000\u00d4\u00d6\u0003(\u0014\u0000\u00d5\u00d4\u0001\u0000\u0000"+
		"\u0000\u00d6\u00d9\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8\u00da\u0001\u0000\u0000"+
		"\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00da\u00dc\u0005\u001b\u0000"+
		"\u0000\u00db\u00d3\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000\u0000"+
		"\u0000\u00dc\u0011\u0001\u0000\u0000\u0000\u00dd\u00de\u0003@ \u0000\u00de"+
		"\u00df\u0005\u000e\u0000\u0000\u00df\u00e0\u0003X,\u0000\u00e0\u00e4\u0005"+
		"\u001d\u0000\u0000\u00e1\u00e3\u0003*\u0015\u0000\u00e2\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e6\u0001\u0000\u0000\u0000\u00e4\u00e2\u0001\u0000"+
		"\u0000\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5\u0013\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e7\u00e8\u0003@ \u0000"+
		"\u00e8\u00e9\u0005\r\u0000\u0000\u00e9\u00ea\u0003X,\u0000\u00ea\u00ee"+
		"\u0005\u001a\u0000\u0000\u00eb\u00ed\u0003,\u0016\u0000\u00ec\u00eb\u0001"+
		"\u0000\u0000\u0000\u00ed\u00f0\u0001\u0000\u0000\u0000\u00ee\u00ec\u0001"+
		"\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f1\u0001"+
		"\u0000\u0000\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f1\u00f2\u0005"+
		"\u001b\u0000\u0000\u00f2\u0015\u0001\u0000\u0000\u0000\u00f3\u00f4\u0003"+
		"@ \u0000\u00f4\u00f5\u0005\u0007\u0000\u0000\u00f5\u00f7\u0003X,\u0000"+
		"\u00f6\u00f8\u00030\u0018\u0000\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f7"+
		"\u00f8\u0001\u0000\u0000\u0000\u00f8\u0101\u0001\u0000\u0000\u0000\u00f9"+
		"\u00fd\u0005\u001a\u0000\u0000\u00fa\u00fc\u0003(\u0014\u0000\u00fb\u00fa"+
		"\u0001\u0000\u0000\u0000\u00fc\u00ff\u0001\u0000\u0000\u0000\u00fd\u00fb"+
		"\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000\u0000\u00fe\u0100"+
		"\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u0100\u0102"+
		"\u0005\u001b\u0000\u0000\u0101\u00f9\u0001\u0000\u0000\u0000\u0101\u0102"+
		"\u0001\u0000\u0000\u0000\u0102\u0017\u0001\u0000\u0000\u0000\u0103\u0127"+
		"\u0005\u000f\u0000\u0000\u0104\u0109\u0003\u0016\u000b\u0000\u0105\u0109"+
		"\u0003\u0010\b\u0000\u0106\u0109\u0003\f\u0006\u0000\u0107\u0109\u0003"+
		"\u000e\u0007\u0000\u0108\u0104\u0001\u0000\u0000\u0000\u0108\u0105\u0001"+
		"\u0000\u0000\u0000\u0108\u0106\u0001\u0000\u0000\u0000\u0108\u0107\u0001"+
		"\u0000\u0000\u0000\u0109\u010b\u0001\u0000\u0000\u0000\u010a\u010c\u0003"+
		"2\u0019\u0000\u010b\u010a\u0001\u0000\u0000\u0000\u010b\u010c\u0001\u0000"+
		"\u0000\u0000\u010c\u010e\u0001\u0000\u0000\u0000\u010d\u010f\u0003\u001e"+
		"\u000f\u0000\u010e\u010d\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000"+
		"\u0000\u0000\u010f\u0111\u0001\u0000\u0000\u0000\u0110\u0112\u0003 \u0010"+
		"\u0000\u0111\u0110\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000"+
		"\u0000\u0112\u0114\u0001\u0000\u0000\u0000\u0113\u0115\u0003\"\u0011\u0000"+
		"\u0114\u0113\u0001\u0000\u0000\u0000\u0114\u0115\u0001\u0000\u0000\u0000"+
		"\u0115\u0117\u0001\u0000\u0000\u0000\u0116\u0118\u0003$\u0012\u0000\u0117"+
		"\u0116\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000\u0000\u0000\u0118"+
		"\u0128\u0001\u0000\u0000\u0000\u0119\u0128\u0003\u0012\t\u0000\u011a\u011c"+
		"\u0003\u0014\n\u0000\u011b\u011d\u0003\u001e\u000f\u0000\u011c\u011b\u0001"+
		"\u0000\u0000\u0000\u011c\u011d\u0001\u0000\u0000\u0000\u011d\u011f\u0001"+
		"\u0000\u0000\u0000\u011e\u0120\u0003 \u0010\u0000\u011f\u011e\u0001\u0000"+
		"\u0000\u0000\u011f\u0120\u0001\u0000\u0000\u0000\u0120\u0122\u0001\u0000"+
		"\u0000\u0000\u0121\u0123\u0003\"\u0011\u0000\u0122\u0121\u0001\u0000\u0000"+
		"\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0125\u0001\u0000\u0000"+
		"\u0000\u0124\u0126\u0003$\u0012\u0000\u0125\u0124\u0001\u0000\u0000\u0000"+
		"\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0128\u0001\u0000\u0000\u0000"+
		"\u0127\u0108\u0001\u0000\u0000\u0000\u0127\u0119\u0001\u0000\u0000\u0000"+
		"\u0127\u011a\u0001\u0000\u0000\u0000\u0128\u0019\u0001\u0000\u0000\u0000"+
		"\u0129\u012a\u0003@ \u0000\u012a\u012b\u0005\u0015\u0000\u0000\u012b\u012c"+
		"\u0007\u0000\u0000\u0000\u012c\u012d\u0003X,\u0000\u012d\u001b\u0001\u0000"+
		"\u0000\u0000\u012e\u012f\u0003@ \u0000\u012f\u0130\u0005\u0016\u0000\u0000"+
		"\u0130\u0131\u0007\u0000\u0000\u0000\u0131\u0132\u0003X,\u0000\u0132\u0133"+
		"\u0005\f\u0000\u0000\u0133\u0134\u0003h4\u0000\u0134\u001d\u0001\u0000"+
		"\u0000\u0000\u0135\u0136\u0003:\u001d\u0000\u0136\u0137\u0003T*\u0000"+
		"\u0137\u001f\u0001\u0000\u0000\u0000\u0138\u0139\u00038\u001c\u0000\u0139"+
		"\u013a\u0003V+\u0000\u013a!\u0001\u0000\u0000\u0000\u013b\u013c\u0003"+
		"4\u001a\u0000\u013c\u013d\u0003(\u0014\u0000\u013d#\u0001\u0000\u0000"+
		"\u0000\u013e\u013f\u00036\u001b\u0000\u013f\u0140\u0003(\u0014\u0000\u0140"+
		"%\u0001\u0000\u0000\u0000\u0141\u0142\u0005\n\u0000\u0000\u0142\u0143"+
		"\u0003N\'\u0000\u0143\u0144\u0003>\u001f\u0000\u0144\'\u0001\u0000\u0000"+
		"\u0000\u0145\u0146\u0003@ \u0000\u0146\u0147\u0003N\'\u0000\u0147\u0148"+
		"\u0005\u001c\u0000\u0000\u0148\u0149\u0003f3\u0000\u0149)\u0001\u0000"+
		"\u0000\u0000\u014a\u014d\u0003X,\u0000\u014b\u014c\u0005\u0001\u0000\u0000"+
		"\u014c\u014e\u0003X,\u0000\u014d\u014b\u0001\u0000\u0000\u0000\u014e\u014f"+
		"\u0001\u0000\u0000\u0000\u014f\u014d\u0001\u0000\u0000\u0000\u014f\u0150"+
		"\u0001\u0000\u0000\u0000\u0150+\u0001\u0000\u0000\u0000\u0151\u0152\u0003"+
		"@ \u0000\u0152\u0155\u0003N\'\u0000\u0153\u0154\u0005\u001d\u0000\u0000"+
		"\u0154\u0156\u0003>\u001f\u0000\u0155\u0153\u0001\u0000\u0000\u0000\u0155"+
		"\u0156\u0001\u0000\u0000\u0000\u0156-\u0001\u0000\u0000\u0000\u0157\u015c"+
		"\u0003X,\u0000\u0158\u0159\u0005\u0002\u0000\u0000\u0159\u015b\u0003X"+
		",\u0000\u015a\u0158\u0001\u0000\u0000\u0000\u015b\u015e\u0001\u0000\u0000"+
		"\u0000\u015c\u015a\u0001\u0000\u0000\u0000\u015c\u015d\u0001\u0000\u0000"+
		"\u0000\u015d/\u0001\u0000\u0000\u0000\u015e\u015c\u0001\u0000\u0000\u0000"+
		"\u015f\u0160\u0005\u0014\u0000\u0000\u0160\u0161\u0003.\u0017\u0000\u0161"+
		"1\u0001\u0000\u0000\u0000\u0162\u0164\u0005\u0003\u0000\u0000\u0163\u0162"+
		"\u0001\u0000\u0000\u0000\u0164\u0165\u0001\u0000\u0000\u0000\u0165\u0163"+
		"\u0001\u0000\u0000\u0000\u0165\u0166\u0001\u0000\u0000\u0000\u0166\u0167"+
		"\u0001\u0000\u0000\u0000\u0167\u016a\u0003P(\u0000\u0168\u0169\u0005\u0002"+
		"\u0000\u0000\u0169\u016b\u0003P(\u0000\u016a\u0168\u0001\u0000\u0000\u0000"+
		"\u016a\u016b\u0001\u0000\u0000\u0000\u016b3\u0001\u0000\u0000\u0000\u016c"+
		"\u016d\u0005\u0018\u0000\u0000\u016d5\u0001\u0000\u0000\u0000\u016e\u016f"+
		"\u0005\u0017\u0000\u0000\u016f7\u0001\u0000\u0000\u0000\u0170\u0171\u0005"+
		"\u0016\u0000\u0000\u01719\u0001\u0000\u0000\u0000\u0172\u0173\u0005\u0015"+
		"\u0000\u0000\u0173;\u0001\u0000\u0000\u0000\u0174\u0175\u0005!\u0000\u0000"+
		"\u0175=\u0001\u0000\u0000\u0000\u0176\u017a\u0003n7\u0000\u0177\u017a"+
		"\u0003l6\u0000\u0178\u017a\u0003j5\u0000\u0179\u0176\u0001\u0000\u0000"+
		"\u0000\u0179\u0177\u0001\u0000\u0000\u0000\u0179\u0178\u0001\u0000\u0000"+
		"\u0000\u017a?\u0001\u0000\u0000\u0000\u017b\u017d\u0003B!\u0000\u017c"+
		"\u017b\u0001\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000\u0000\u017e"+
		"\u017c\u0001\u0000\u0000\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f"+
		"\u0181\u0001\u0000\u0000\u0000\u0180\u017c\u0001\u0000\u0000\u0000\u0180"+
		"\u0181\u0001\u0000\u0000\u0000\u0181A\u0001\u0000\u0000\u0000\u0182\u0183"+
		"\u0005\t\u0000\u0000\u0183\u0189\u0003X,\u0000\u0184\u0186\u0005\u0004"+
		"\u0000\u0000\u0185\u0187\u0003D\"\u0000\u0186\u0185\u0001\u0000\u0000"+
		"\u0000\u0186\u0187\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000\u0000"+
		"\u0000\u0188\u018a\u0005\u0005\u0000\u0000\u0189\u0184\u0001\u0000\u0000"+
		"\u0000\u0189\u018a\u0001\u0000\u0000\u0000\u018aC\u0001\u0000\u0000\u0000"+
		"\u018b\u0190\u0003F#\u0000\u018c\u018d\u0005\u0002\u0000\u0000\u018d\u018f"+
		"\u0003F#\u0000\u018e\u018c\u0001\u0000\u0000\u0000\u018f\u0192\u0001\u0000"+
		"\u0000\u0000\u0190\u018e\u0001\u0000\u0000\u0000\u0190\u0191\u0001\u0000"+
		"\u0000\u0000\u0191E\u0001\u0000\u0000\u0000\u0192\u0190\u0001\u0000\u0000"+
		"\u0000\u0193\u0194\u0003N\'\u0000\u0194\u0197\u0005\u001d\u0000\u0000"+
		"\u0195\u0198\u0003n7\u0000\u0196\u0198\u0003l6\u0000\u0197\u0195\u0001"+
		"\u0000\u0000\u0000\u0197\u0196\u0001\u0000\u0000\u0000\u0198G\u0001\u0000"+
		"\u0000\u0000\u0199\u019f\u00038\u001c\u0000\u019a\u019c\u0005\u0004\u0000"+
		"\u0000\u019b\u019d\u0003J%\u0000\u019c\u019b\u0001\u0000\u0000\u0000\u019c"+
		"\u019d\u0001\u0000\u0000\u0000\u019d\u019e\u0001\u0000\u0000\u0000\u019e"+
		"\u01a0\u0005\u0005\u0000\u0000\u019f\u019a\u0001\u0000\u0000\u0000\u019f"+
		"\u01a0\u0001\u0000\u0000\u0000\u01a0I\u0001\u0000\u0000\u0000\u01a1\u01a6"+
		"\u0003L&\u0000\u01a2\u01a3\u0005\u0002\u0000\u0000\u01a3\u01a5\u0003L"+
		"&\u0000\u01a4\u01a2\u0001\u0000\u0000\u0000\u01a5\u01a8\u0001\u0000\u0000"+
		"\u0000\u01a6\u01a4\u0001\u0000\u0000\u0000\u01a6\u01a7\u0001\u0000\u0000"+
		"\u0000\u01a7K\u0001\u0000\u0000\u0000\u01a8\u01a6\u0001\u0000\u0000\u0000"+
		"\u01a9\u01ac\u0003n7\u0000\u01aa\u01ac\u0003l6\u0000\u01ab\u01a9\u0001"+
		"\u0000\u0000\u0000\u01ab\u01aa\u0001\u0000\u0000\u0000\u01acM\u0001\u0000"+
		"\u0000\u0000\u01ad\u01ae\u0005 \u0000\u0000\u01aeO\u0001\u0000\u0000\u0000"+
		"\u01af\u01b0\u0005 \u0000\u0000\u01b0Q\u0001\u0000\u0000\u0000\u01b1\u01b2"+
		"\u0005 \u0000\u0000\u01b2S\u0001\u0000\u0000\u0000\u01b3\u01b8\u0003N"+
		"\'\u0000\u01b4\u01b5\u0005\u0002\u0000\u0000\u01b5\u01b7\u0003N\'\u0000"+
		"\u01b6\u01b4\u0001\u0000\u0000\u0000\u01b7\u01ba\u0001\u0000\u0000\u0000"+
		"\u01b8\u01b6\u0001\u0000\u0000\u0000\u01b8\u01b9\u0001\u0000\u0000\u0000"+
		"\u01b9U\u0001\u0000\u0000\u0000\u01ba\u01b8\u0001\u0000\u0000\u0000\u01bb"+
		"\u01bc\u0003N\'\u0000\u01bc\u01bd\u0005\f\u0000\u0000\u01bd\u01be\u0003"+
		"R)\u0000\u01beW\u0001\u0000\u0000\u0000\u01bf\u01c4\u0005 \u0000\u0000"+
		"\u01c0\u01c1\u0005\u0006\u0000\u0000\u01c1\u01c3\u0005 \u0000\u0000\u01c2"+
		"\u01c0\u0001\u0000\u0000\u0000\u01c3\u01c6\u0001\u0000\u0000\u0000\u01c4"+
		"\u01c2\u0001\u0000\u0000\u0000\u01c4\u01c5\u0001\u0000\u0000\u0000\u01c5"+
		"Y\u0001\u0000\u0000\u0000\u01c6\u01c4\u0001\u0000\u0000\u0000\u01c7\u01cc"+
		"\u0005 \u0000\u0000\u01c8\u01c9\u0005\u0006\u0000\u0000\u01c9\u01cb\u0005"+
		" \u0000\u0000\u01ca\u01c8\u0001\u0000\u0000\u0000\u01cb\u01ce\u0001\u0000"+
		"\u0000\u0000\u01cc\u01ca\u0001\u0000\u0000\u0000\u01cc\u01cd\u0001\u0000"+
		"\u0000\u0000\u01cd\u01cf\u0001\u0000\u0000\u0000\u01ce\u01cc\u0001\u0000"+
		"\u0000\u0000\u01cf\u01d0\u0005\u0004\u0000\u0000\u01d0\u01d1\u0003^/\u0000"+
		"\u01d1\u01d2\u0005\u0005\u0000\u0000\u01d2[\u0001\u0000\u0000\u0000\u01d3"+
		"\u01d6\u0003n7\u0000\u01d4\u01d6\u0003l6\u0000\u01d5\u01d3\u0001\u0000"+
		"\u0000\u0000\u01d5\u01d4\u0001\u0000\u0000\u0000\u01d6]\u0001\u0000\u0000"+
		"\u0000\u01d7\u01dc\u0003\\.\u0000\u01d8\u01d9\u0005\u0002\u0000\u0000"+
		"\u01d9\u01db\u0003\\.\u0000\u01da\u01d8\u0001\u0000\u0000\u0000\u01db"+
		"\u01de\u0001\u0000\u0000\u0000\u01dc\u01da\u0001\u0000\u0000\u0000\u01dc"+
		"\u01dd\u0001\u0000\u0000\u0000\u01dd_\u0001\u0000\u0000\u0000\u01de\u01dc"+
		"\u0001\u0000\u0000\u0000\u01df\u01e4\u0005 \u0000\u0000\u01e0\u01e1\u0005"+
		"\u0006\u0000\u0000\u01e1\u01e3\u0005 \u0000\u0000\u01e2\u01e0\u0001\u0000"+
		"\u0000\u0000\u01e3\u01e6\u0001\u0000\u0000\u0000\u01e4\u01e2\u0001\u0000"+
		"\u0000\u0000\u01e4\u01e5\u0001\u0000\u0000\u0000\u01e5\u01ec\u0001\u0000"+
		"\u0000\u0000\u01e6\u01e4\u0001\u0000\u0000\u0000\u01e7\u01e9\u0005\u0004"+
		"\u0000\u0000\u01e8\u01ea\u0003d2\u0000\u01e9\u01e8\u0001\u0000\u0000\u0000"+
		"\u01e9\u01ea\u0001\u0000\u0000\u0000\u01ea\u01eb\u0001\u0000\u0000\u0000"+
		"\u01eb\u01ed\u0005\u0005\u0000\u0000\u01ec\u01e7\u0001\u0000\u0000\u0000"+
		"\u01ec\u01ed\u0001\u0000\u0000\u0000\u01eda\u0001\u0000\u0000\u0000\u01ee"+
		"\u01ef\u0003N\'\u0000\u01ef\u01f2\u0005\u001d\u0000\u0000\u01f0\u01f3"+
		"\u0003n7\u0000\u01f1\u01f3\u0003l6\u0000\u01f2\u01f0\u0001\u0000\u0000"+
		"\u0000\u01f2\u01f1\u0001\u0000\u0000\u0000\u01f3c\u0001\u0000\u0000\u0000"+
		"\u01f4\u01f9\u0003b1\u0000\u01f5\u01f6\u0005\u0002\u0000\u0000\u01f6\u01f8"+
		"\u0003b1\u0000\u01f7\u01f5\u0001\u0000\u0000\u0000\u01f8\u01fb\u0001\u0000"+
		"\u0000\u0000\u01f9\u01f7\u0001\u0000\u0000\u0000\u01f9\u01fa\u0001\u0000"+
		"\u0000\u0000\u01fae\u0001\u0000\u0000\u0000\u01fb\u01f9\u0001\u0000\u0000"+
		"\u0000\u01fc\u0200\u0003X,\u0000\u01fd\u0200\u0003Z-\u0000\u01fe\u0200"+
		"\u0003`0\u0000\u01ff\u01fc\u0001\u0000\u0000\u0000\u01ff\u01fd\u0001\u0000"+
		"\u0000\u0000\u01ff\u01fe\u0001\u0000\u0000\u0000\u0200g\u0001\u0000\u0000"+
		"\u0000\u0201\u0202\u0005 \u0000\u0000\u0202i\u0001\u0000\u0000\u0000\u0203"+
		"\u0204\u0007\u0001\u0000\u0000\u0204k\u0001\u0000\u0000\u0000\u0205\u0206"+
		"\u0005\"\u0000\u0000\u0206m\u0001\u0000\u0000\u0000\u0207\u0208\u0005"+
		"!\u0000\u0000\u0208o\u0001\u0000\u0000\u0000>sy~\u0083\u0087\u0093\u009b"+
		"\u00a0\u00a6\u00ab\u00b1\u00b7\u00bb\u00c1\u00c7\u00cb\u00d1\u00d7\u00db"+
		"\u00e4\u00ee\u00f7\u00fd\u0101\u0108\u010b\u010e\u0111\u0114\u0117\u011c"+
		"\u011f\u0122\u0125\u0127\u014f\u0155\u015c\u0165\u016a\u0179\u017e\u0180"+
		"\u0186\u0189\u0190\u0197\u019c\u019f\u01a6\u01ab\u01b8\u01c4\u01cc\u01d5"+
		"\u01dc\u01e4\u01e9\u01ec\u01f2\u01f9\u01ff";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}