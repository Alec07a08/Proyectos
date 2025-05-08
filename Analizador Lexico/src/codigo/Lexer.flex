%%

%class Lexer
%unicode
%public
%type Token

%line
%column

%{
package codigo;
import codigo.Token;
%}

%%

// Palabras clave y acciones
"Robot"             { return new Token("RESERVADA", yytext(), "-"); }
"iniciar"           { return new Token("ACCION", yytext(), "-"); }
"finalizar"         { return new Token("ACCION", yytext(), "-"); }

// Identificadores:
r[0-9]+             { return new Token("ID", yytext(), "-"); }

// Métodos del robot
"base"              { return new Token("ACCION", yytext(), "-"); }
"cuerpo"            { return new Token("ACCION", yytext(), "-"); }
"garra"             { return new Token("ACCION", yytext(), "-"); }
"velocidad"         { return new Token("ACCION", yytext(), "-"); }

// Símbolos
"="                 { return new Token("ASIGNACION", yytext(), "-"); }
"."                 { return new Token("PUNTO", yytext(), "-"); }

// Números
[0-9]+              { return new Token("NUM", yytext(), yytext()); }

// Espacios y saltos de línea
[\t\r\n ]+          { /* ignorar */ }

// Todo lo demás se considera error
.                   { return new Token("ERROR", yytext(), "-"); }
