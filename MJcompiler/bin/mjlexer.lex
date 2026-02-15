package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	//ukljucivanje informacija o poziciji tokena
	private Symbol new_symbol(int type){
		return new Symbol(type,yyline+1,yycolumn);
	}
	//ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type,Object value){
		return new Symbol(type,yyline+1,yycolumn,value);
	}
%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" "    {}
"\b"   {}
"\t"   {}
"\r\n" {}
"\t"   {}


"program" 	{return new_symbol(sym.PROG, yytext());}
"return"  	{return new_symbol(sym.RETURN, yytext());}
"void"		{return new_symbol(sym.VOID, yytext());}
"break"	  	{return new_symbol(sym.BREAK, yytext());}
"enum"	  	{return new_symbol(sym.ENUM, yytext());}
"else"	  	{return new_symbol(sym.ELSE, yytext());}
"const"		{return new_symbol(sym.CONST, yytext());}
"if"	  	{return new_symbol(sym.IF, yytext());}
"new"	  	{return new_symbol(sym.NEW, yytext());}
"print"   	{return new_symbol(sym.PRINT, yytext());}
"read"    	{return new_symbol(sym.READ, yytext());}
"continue"	{return new_symbol(sym.CONTINUE, yytext());}
"for"	  	{return new_symbol(sym.FOR, yytext());}
"length"  	{return new_symbol(sym.LENGTH, yytext());}
"switch"  	{return new_symbol(sym.SWITCH, yytext());}
"case" 	  	{return new_symbol(sym.CASE, yytext());}

"//"	          {yybegin(COMMENT);}
<COMMENT> .	      {yybegin(COMMENT);}
<COMMENT> "\r\n"  {yybegin(YYINITIAL);}

"+" 	{return new_symbol(sym.PLUS, yytext());}
"-" 	{return new_symbol(sym.MINUS, yytext());}
"*" 	{return new_symbol(sym.MUL, yytext());}
"?"		{return new_symbol(sym.QUESTIONMARK, yytext());}
"/" 	{return new_symbol(sym.DIV, yytext());}
"%" 	{return new_symbol(sym.PERCENT, yytext());}
"=="	{return new_symbol(sym.EQUAL, yytext());}
"!="	{return new_symbol(sym.DIFFERENT, yytext());}
">"		{return new_symbol(sym.GREATERTHEN, yytext());}
">="	{return new_symbol(sym.GREATERTHENOREQUAL, yytext());}
"<"		{return new_symbol(sym.LESSTHEN, yytext());}
"<="	{return new_symbol(sym.LESSTHENOREQUAL, yytext());}
"&&" 	{return new_symbol(sym.AND, yytext());}
"||"	{return new_symbol(sym.OR, yytext());}
"="	    {return new_symbol(sym.ASSIGN, yytext());}
"++"	{return new_symbol(sym.INC, yytext());}
"--"	{return new_symbol(sym.DEC, yytext());}
";"	    {return new_symbol(sym.SEMICOLON, yytext());}
":"	    {return new_symbol(sym.COLON, yytext());}
","	    {return new_symbol(sym.COMMA, yytext());}
"."	    {return new_symbol(sym.DOT, yytext());}
"("	    {return new_symbol(sym.OPENBRACKET, yytext());}
")"	    {return new_symbol(sym.CLBRACKET, yytext());}
"{"	    {return new_symbol(sym.CURLYOPENBRACKET, yytext());}
"}"	    {return new_symbol(sym.CURLYCLBRACKET, yytext());}
"["	    {return new_symbol(sym.SQUAREOPENBRACKET, yytext());}
"]"	    {return new_symbol(sym.SQUARECLBRACKET, yytext());}
"@"	    {return new_symbol(sym.MONKEY, yytext());}

[0-9]+	{return new_symbol(sym.NUMBER, new Integer (yytext()));}
"'"."'"	{return new_symbol(sym.CHARACTER, new Character (yytext().charAt(1)));}
("true"|"false")	{return new_symbol(sym.BOOL, yytext().equals("true")?1 : 0);}
([a-z]|[A-Z])[a-z|A-Z|0-9|_]*	{return new_symbol(sym.IDENT, yytext());}


.		{System.err.println("Leksicka greska ("+yytext()+") na liniji "+(yyline+1)+" u koloni "+ (yycolumn + 1) + "\n");}