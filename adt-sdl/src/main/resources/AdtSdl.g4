grammar AdtSdl;

// Parser rules
schema
    : definitions+ ;

definitions
    : setDefinitionStatement
    | importStatement+
    | statement+
    | contextStatement+
    EOF;

statement
    : importStatement
    | setDefinitionStatement
    | typeDefinitionStatement
    | entityDefinitionStatement
    | unionDefinitionStatement
    | enumDefinitionStatement
    | modificationStatement
    | removeStatement
    | renameStatement
    | renameFieldStatement
    ;

contextStatement
    : CONTEXT label LBRACE statement* RBRACE contextRenameCall?
    ;

importStatement: IMPORT label location schemaPipelines?;
setDefinitionStatement: setDefinition+;
allOfDefinitionStatement: annotations ALLOF typeName extends? (LBRACE fieldDefinition* RBRACE)?;
anyOfDefinitionStatement: annotations ANYOF typeName extends? (LBRACE fieldDefinition* RBRACE)?;
typeDefinitionStatement: annotations TYPE typeName extends? (LBRACE fieldDefinition* RBRACE)?;
unionDefinitionStatement: annotations UNION typeName EQ unionDefinitions*;
enumDefinitionStatement: annotations ENUM typeName LBRACE enumDefinition* RBRACE;
entityDefinitionStatement: annotations ENTITY typeName extends? (LBRACE fieldDefinition* RBRACE)?;

modificationStatement: MODIFY ((entityDefinitionStatement | typeDefinitionStatement | allOfDefinitionStatement | anyOfDefinitionStatement) (schemaPipelines?)(removeFieldStatement)? (renameFieldStatement)? (overrideFieldStatement)? (defineFieldStatement)? | unionDefinitionStatement | enumDefinitionStatement (removeFieldStatement)? (renameFieldStatement)? (overrideFieldStatement)? (defineFieldStatement)?);
removeStatement: annotations REMOVE (TYPE|ALLOF|ANYOF|ENTITY|ENUM|UNION) typeName;
renameStatement: annotations RENAME (TYPE|ALLOF|ANYOF|ENTITY|ENUM|UNION) typeName TO newtypeName;
removeFieldStatement: remove labelList;
renameFieldStatement: rename labelSetList;
overrideFieldStatement: override fieldDefinition;
defineFieldStatement: define fieldDefinition;

setDefinition: SET label value;
fieldDefinition: annotations label COLON allTypeNames ;
unionDefinitions: typeName ('|' typeName)+;
enumDefinition: annotations label(EQ value)? ;
extendsTypeList: typeName (',' typeName)*;
extends: (EXTENDS extendsTypeList);


schemaPipelines: ('>' + pipeName((',') pipeName)?);

override: OVERRIDE;
define: DEFINE;
rename: RENAME;
remove: REMOVE;
location: STRING;
value: (string | number | boolean);
annotations: (annotation+)? ;
annotation: ANNO typeName ('(' annotationArgs? ')')? ;
annotationArgs: annotationArg (',' annotationArg)* ;
annotationArg: label EQ (string | number) ;
contextRenameCall: rename ('(' contextRenameCallArgs? ')')? ;
contextRenameCallArgs: contextRenameCallArg (',' contextRenameCallArg)* ;
contextRenameCallArg: string | number;

label: IDENTIFIER;
pipeName: IDENTIFIER;
newFieldName: IDENTIFIER;
labelList: label (',' label)*;
labelSetList: label TO newFieldName;
typeName: IDENTIFIER('.' IDENTIFIER)*;
complexTypeName: IDENTIFIER('.' IDENTIFIER)* ('(' complexTypeNameArgs ')') ;
complexTypeNameArg: string | number;
complexTypeNameArgs: complexTypeNameArg (',' complexTypeNameArg)* ;

relationshipTypeName: IDENTIFIER('.' IDENTIFIER)* ('(' relationshipArgs? ')')? ;
relationshipArg: label EQ (string | number) ;
relationshipArgs: relationshipArg (',' relationshipArg)* ;

allTypeNames: typeName |  complexTypeName | relationshipTypeName;


newtypeName: IDENTIFIER ;
boolean: FALSE | TRUE;
number: NUMBER;
string: STRING;

// Lexer rules
ENTITY: 'entity';
RELATIONSHIP: 'relationship';
ANNO: '@' ;
SET: 'set';
IMPORT: 'import' ;
TO: 'to';
ENUM: 'enum';
UNION: 'union' ;
MODIFY: 'modify' ;
TYPE: 'type' ;
ANYOF: 'anyOf' ;
ALLOF: 'allOf' ;
DTO: 'dto' ;
EXTENDS: 'extends' ;
REMOVE: 'remove' ;
RENAME: 'rename' ;
DEFINE: 'define';
OVERRIDE: 'override';
CONTEXT: 'context';
LBRACE: '{' ;
RBRACE: '}' ;
COLON: ':' ;
EQ: '=' ;
FALSE : 'false';
TRUE : 'true';
IDENTIFIER: [a-zA-Z] [a-zA-Z_0-9]*;
STRING: '"' (~["\n\r])* '"' ;
NUMBER : [0-9]+ ;

// Comment rules
LINE_COMMENT: '//' ~[\r\n]* -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;

// Whitespace rule
WS: [ \t\r\n]+ -> skip;