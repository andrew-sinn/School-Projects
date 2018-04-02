using System;

/**
 * Defines the tokens for the Scanner class.
 * 
 * @author Andrew Sinn
 */ 

public class Token
{
    public const int EOF = -1;         //End of file token
    public const int COLON = 1;        //: token
    public const int AT = 2;           //@ token
    public const int COMMA = 3;        //, token
    public const int ASTERISK = 4;     //* token
    public const int SEMICOLON = 5;    //; token
    public const int STATE = 6;        //A state name token
    public const int TRANSITION = 7;   //Input transition token

    public int tokenType;              //What token is it?
    public String input;               //Input string
   
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
