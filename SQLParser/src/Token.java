/**
 * Token class that declares all types of tokens for parsing the SQL
 * query. (Keywords for SQL).
 * 
 * @author Andrew Sinn
 */

public class Token {
   
	public static final int SELECT = 1;        //Select token
	public static final int AS = 2;            //As token
	public static final int FROM = 3;          //From token
	public static final int JOIN = 4;          //Join token
	public static final int WHERE = 5;         //Where token
	public static final int INSERT = 6;        //Insert token
	public static final int VALUES = 7;        //Values token
    public static final int COMMA = 8;         //, token
    public static final int ASTERISK = 9;      //* token
    public static final int PERIOD = 10;       //. token
    public static final int NEWLINE = 11;      //The newline token
    public static final int LEFTPAREN = 12;    //Left Parentheses token
    public static final int RIGHTPAREN = 13;   //Right Parentheses token
    public static final int OPERATOR = 14;     //An operator token
    public static final int NUMBER = 15;       //A number
    public static final int SQLSTRING = 16;    //A string in single quotes
    public static final int NAME = 17;         //A table or attribute name
    public static final int AND = 18;          //The AND keyword.

    private int tokenType;              //What token is it?
    private String input;               //Input string
   
    /**
     * Public constructor for Token.
     * 
     * @param tokenType  The type of token it is.
     * @param input      The actual token it is.
     */

	public Token(int tokenType, String input)
	{
        this.tokenType = tokenType;
        this.input = input;
	}

    /**
     * Returns the tokenType
     * 
     * @return tokenType
     */

    public int getTokenType()
    {
        return tokenType;
    }

    /**
    * Returns the input.
    * 
    * @return input
    */
    
    public String getInput()
    {
        return input;
    }
}