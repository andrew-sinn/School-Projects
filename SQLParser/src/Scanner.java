/* The following code was generated by JFlex 1.4.3 on 11/5/11 1:49 PM */

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 11/5/11 1:49 PM from the specification file
 * <tt>C:/Users/Andrew Sinn/Desktop/SQLParser/src/SQLScanner.flex</tt>
 */
class Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\10\0\2\40\1\27\1\0\2\40\22\0\1\20\2\0\2\37\2\0"+
    "\1\36\1\30\1\31\1\25\1\0\1\24\1\0\1\26\1\0\12\35"+
    "\2\0\1\34\1\33\1\32\2\0\1\6\1\37\1\4\1\23\1\2"+
    "\1\7\1\37\1\17\1\14\1\13\1\37\1\3\1\12\1\15\1\11"+
    "\2\37\1\10\1\1\1\5\1\22\1\21\1\16\3\37\4\0\1\37"+
    "\1\0\1\6\1\37\1\4\1\23\1\2\1\7\1\37\1\17\1\14"+
    "\1\13\1\37\1\3\1\12\1\15\1\11\2\37\1\10\1\1\1\5"+
    "\1\22\1\21\1\16\3\37\uff85\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\7\2\1\3\1\2\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\3\12\1\1\1\0\1\2\1\13\6\2"+
    "\1\14\1\2\1\15\6\2\1\16\1\17\5\2\1\20"+
    "\1\2\1\21\1\2\1\22\4\0\1\23";

  private static int [] zzUnpackAction() {
    int [] result = new int[55];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\41\0\102\0\143\0\204\0\245\0\306\0\347"+
    "\0\u0108\0\u0129\0\u014a\0\u014a\0\102\0\u014a\0\u014a\0\u014a"+
    "\0\u016b\0\u014a\0\u018c\0\u01ad\0\u01ce\0\u01ef\0\102\0\u0210"+
    "\0\u0231\0\u0252\0\u0273\0\u0294\0\u02b5\0\u014a\0\u02d6\0\102"+
    "\0\u02f7\0\u0318\0\u0339\0\u035a\0\u037b\0\u039c\0\102\0\102"+
    "\0\u03bd\0\u03de\0\u03ff\0\u0420\0\u0441\0\102\0\u0462\0\102"+
    "\0\u0483\0\102\0\u04a4\0\u04c5\0\u04e6\0\u0507\0\u014a";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[55];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\0\1\2\4\3\1\4\1\5\3\3\1\6\1\7"+
    "\1\3\1\10\1\3\1\11\1\12\2\3\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\3\1\11\1\0\1\3\1\26\15\3\1\0"+
    "\3\3\2\0\1\3\6\0\1\3\1\0\1\3\2\0"+
    "\17\3\1\0\3\3\2\0\1\3\6\0\1\3\1\0"+
    "\1\3\2\0\1\27\13\3\1\30\2\3\1\0\3\3"+
    "\2\0\1\3\6\0\1\3\1\0\1\3\2\0\7\3"+
    "\1\31\7\3\1\0\3\3\2\0\1\3\6\0\1\3"+
    "\1\0\1\3\2\0\10\3\1\32\6\3\1\0\3\3"+
    "\2\0\1\3\6\0\1\3\1\0\1\3\2\0\14\3"+
    "\1\33\2\3\1\0\3\3\2\0\1\3\6\0\1\3"+
    "\1\0\1\3\2\0\16\3\1\34\1\0\3\3\2\0"+
    "\1\3\6\0\1\3\1\0\1\3\21\0\1\11\17\0"+
    "\1\11\1\0\5\3\1\35\11\3\1\0\3\3\2\0"+
    "\1\3\6\0\1\3\1\0\1\3\75\0\1\22\37\0"+
    "\2\22\6\0\17\3\1\0\3\3\2\0\1\3\6\0"+
    "\1\24\1\0\1\3\2\0\17\25\1\0\3\25\2\0"+
    "\1\25\6\0\1\25\1\36\1\25\2\0\2\3\1\37"+
    "\14\3\1\0\3\3\2\0\1\3\6\0\1\3\1\0"+
    "\1\3\2\0\17\3\1\0\2\3\1\40\2\0\1\3"+
    "\6\0\1\3\1\0\1\3\2\0\10\3\1\41\6\3"+
    "\1\0\3\3\2\0\1\3\6\0\1\3\1\0\1\3"+
    "\2\0\13\3\1\42\3\3\1\0\3\3\2\0\1\3"+
    "\6\0\1\3\1\0\1\3\2\0\1\43\16\3\1\0"+
    "\3\3\2\0\1\3\6\0\1\3\1\0\1\3\2\0"+
    "\1\3\1\44\15\3\1\0\3\3\2\0\1\3\6\0"+
    "\1\3\1\0\1\3\2\0\2\3\1\45\14\3\1\0"+
    "\3\3\2\0\1\3\6\0\1\3\1\0\1\3\2\0"+
    "\1\3\1\46\15\3\1\0\3\3\2\0\1\3\6\0"+
    "\1\3\1\0\1\3\2\0\11\3\1\47\5\3\1\0"+
    "\3\3\2\0\1\3\6\0\1\3\1\0\1\3\2\0"+
    "\14\3\1\50\2\3\1\0\3\3\2\0\1\3\6\0"+
    "\1\3\1\0\1\3\2\0\1\3\1\51\15\3\1\0"+
    "\3\3\2\0\1\3\6\0\1\3\1\0\1\3\2\0"+
    "\7\3\1\52\7\3\1\0\3\3\2\0\1\3\6\0"+
    "\1\3\1\0\1\3\2\0\17\3\1\0\1\3\1\53"+
    "\1\3\2\0\1\3\6\0\1\3\1\0\1\3\2\0"+
    "\3\3\1\54\13\3\1\0\3\3\2\0\1\3\6\0"+
    "\1\3\1\0\1\3\2\0\7\3\1\55\7\3\1\0"+
    "\3\3\2\0\1\3\6\0\1\3\1\0\1\3\2\0"+
    "\1\3\1\56\15\3\1\0\3\3\2\0\1\3\6\0"+
    "\1\3\1\0\1\3\2\0\1\3\1\57\15\3\1\0"+
    "\3\3\2\0\1\3\6\0\1\3\1\0\1\3\2\0"+
    "\4\3\1\60\12\3\1\0\3\3\2\0\1\3\6\0"+
    "\1\3\1\0\1\3\2\0\4\3\1\61\12\3\1\0"+
    "\3\3\2\0\1\3\6\0\1\3\1\0\1\3\2\0"+
    "\1\62\16\3\1\0\3\3\2\0\1\3\6\0\1\3"+
    "\1\0\1\3\2\0\17\3\1\63\3\3\2\0\1\3"+
    "\6\0\1\3\1\0\1\3\15\0\1\64\41\0\1\65"+
    "\30\0\1\66\44\0\1\67\27\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1320];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\12\1\2\11\1\1\3\11\1\1\1\11\2\1\1\0"+
    "\10\1\1\11\24\1\4\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[55];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 150) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Token nextToken() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 6: 
          { return new Token(Token.PERIOD, ".");
          }
        case 20: break;
        case 17: 
          { return new Token(Token.SELECT, "SELECT");
          }
        case 21: break;
        case 19: 
          { return new Token(Token.INSERT, "INSERT INTO");
          }
        case 22: break;
        case 15: 
          { return new Token(Token.JOIN, "JOIN");
          }
        case 23: break;
        case 14: 
          { return new Token(Token.FROM, "FROM");
          }
        case 24: break;
        case 13: 
          { return new Token(Token.AND, "AND");
          }
        case 25: break;
        case 4: 
          { return new Token(Token.COMMA, ",");
          }
        case 26: break;
        case 5: 
          { return new Token(Token.ASTERISK, "*");
          }
        case 27: break;
        case 12: 
          { return new Token(Token.SQLSTRING, yytext());
          }
        case 28: break;
        case 1: 
          { return new Token(Token.NUMBER, yytext());
          }
        case 29: break;
        case 8: 
          { return new Token(Token.LEFTPAREN, "(");
          }
        case 30: break;
        case 7: 
          { return new Token(Token.NEWLINE, "Newline");
          }
        case 31: break;
        case 18: 
          { return new Token(Token.VALUES, "VALUES");
          }
        case 32: break;
        case 10: 
          { return new Token(Token.OPERATOR, yytext());
          }
        case 33: break;
        case 2: 
          { return new Token(Token.NAME, yytext());
          }
        case 34: break;
        case 16: 
          { return new Token(Token.WHERE, "WHERE");
          }
        case 35: break;
        case 3: 
          { break; /* Ignore any whitespace */
          }
        case 36: break;
        case 9: 
          { return new Token(Token.RIGHTPAREN, ")");
          }
        case 37: break;
        case 11: 
          { return new Token(Token.AS, "AS");
          }
        case 38: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
