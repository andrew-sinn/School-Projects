%%

%caseless
%class Scanner
%function nextToken
%type Token

%%

"SELECT" { return new Token(Token.SELECT, "SELECT");           }
"AS"     { return new Token(Token.AS, "AS");                   }
"FROM"   { return new Token(Token.FROM, "FROM");               }
"JOIN"   { return new Token(Token.JOIN, "JOIN");               }
"WHERE"  { return new Token(Token.WHERE, "WHERE");             }
"INSERT INTO" { return new Token(Token.INSERT, "INSERT INTO"); }
"VALUES" { return new Token(Token.VALUES, "VALUES");           }
"AND"    { return new Token(Token.AND, "AND");              }

","      { return new Token(Token.COMMA, ",");                           }
"*"      { return new Token(Token.ASTERISK, "*");                        }
"."      { return new Token(Token.PERIOD, ".");                          }
"\n"     { return new Token(Token.NEWLINE, "Newline");                   }
"("      { return new Token(Token.LEFTPAREN, "(");                       }
")"      { return new Token(Token.RIGHTPAREN, ")");                      }
">"|"="|"<>"|">="|"<"|"<=" { return new Token(Token.OPERATOR, yytext()); }

[0-9]*          { return new Token(Token.NUMBER, yytext());                      }
'[0-9A-Za-z_#$.]*' { return new Token(Token.SQLSTRING, yytext());                   }
[0-9A-Za-z_#$.]*   { return new Token(Token.NAME, yytext());                        }
[ \t\b\f\r]+ { break; /* Ignore any whitespace */                                }