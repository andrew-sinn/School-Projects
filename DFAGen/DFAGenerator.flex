using System;

%%



%class Scanner

%function nextToken

%type Token



%eofval{

return (new Token(Token.EOF, ""));

%eofval}



%%



":"     { return new Token(Token.COLON, "");                     }

"@"     { return new Token(Token.AT, "");                        }

","     { return new Token(Token.COMMA, "");                     }

"*"     { return new Token(Token.ASTERISK, "");                  }

";"     { return new Token(Token.SEMICOLON, "");                 }

[0-9A-Za-z]*    { return new Token(Token.STATE, yytext());       }

'[0-9A-Za-z.]*' { return new Token(Token.TRANSITION, yytext());  }

'.'  { return new Token(Token.TRANSITION, yytext());             }

[ \t\b\f\r\n]+ { break; /* Ignore any whitespace */              }

"//"[^\n]*     { break; /* Ignore any comments */                }