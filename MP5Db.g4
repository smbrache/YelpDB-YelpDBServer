// MP5Db ANTLR4 Query
//
// Written according to the MP5 'Part V: Structured Queries' specification.
 
grammar MP5Db;

root
	:	orExpr SPACE* EOF
	; 
orExpr
	:	andExpr (or andExpr)*
	;
andExpr
	:	atom (and atom)*
	;
atom
	:	in | category |	rating | price | name |	LParen orExpr RParen
	;
or
	:	SPACE* '||' SPACE*
	;
and
	:	SPACE* '&&' SPACE*
	;
ineq
	:	SPACE* (gt | gte | lt | lte | eq) SPACE*
	;
gt
	:	'>'
	;
gte
	:	'>='
	;
lt
	:	'<'
	;
lte
	:	'<='
	;
eq 	
	:	'='
	;
in
	:	'in' LParen STRING RParen
	;
category
	:	'category' LParen STRING RParen
	;
name
	:	'name' LParen STRING RParen
	;
rating
	:	'rating' ineq NUM
	;
price
	:	'price' ineq NUM
	;
LParen
	:	'('
	;
RParen
	:	')'
	;
NUM 
	:	[1-5]
	;
STRING
	:	WORD (STRINGSPACE WORD)*
	;
WORD
	:	[a-zA-Z]+
	;
SPACE	
	: 	[ \t\r\n]+ -> skip
	;
STRINGSPACE
	:	[ \t\r\n]+
	;