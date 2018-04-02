using System;
using System.IO;
using System.Collections.Generic;
using System.Collections;

/**
 * This class will parse a DFA according to the input given and will generate
 * the C# code to run the DFA
 * 
 * @author Andrew Sinn
 */

public class DFAParser
{
    private TextReader reader;        //The text reader for the DFA Scanner
    private Scanner scan;             //Scanner that contains the DFA definitions
    private Token current;            //The current token
    private int tokenType;            //The token type
    private Dictionary<String, Dictionary<String, String>> table;   //The table that contains the 
                                                                    //DFA states/transitions
    private String stateDec;                       //The current state
    private Dictionary<String, String> transList;  //An element of the transition list
    private String className;         //The class name to be generated.
    private bool debug;               //The debug mode for parsing.
    private String start;             //The start state
    private ArrayList acceptStates;   //The accept states

    /**
     * Constructor for the DFAParser.
     * 
     * @args inputFile  The input file that contains the DFA
     *       className  The class name to be generated
     *       debug      Used to debug the program
     */ 
   
    public DFAParser(String inputFile, String className, bool debug) {
        
        reader = null;
        try
        {
            reader = File.OpenText(inputFile);
        }
        catch (FileNotFoundException fnfe)
        {
            Console.Error.WriteLine(fnfe.Message);
            return;
        }

        scan = new Scanner(reader);
        current = null;
        tokenType = 0;
        table = new Dictionary<String, Dictionary<String, String>>();
        transList = new Dictionary<String, String>();
        this.className = className;
        this.debug = debug;
        acceptStates = new ArrayList();
    }

    /**
     * Parse the DFA using this grammar: 
     * DFA             --> state_decl_list | E
     * state_decl_list --> state_decl state_decl_list | entry state_decl_list | E
     * state_decl      --> IDENT state_type trans_list
     * state_type      --> : | @
     * trans_list      --> trans trans_list_tail | E    
     * trans_list_tail --> , trans trans_list_tail | E
     * trans           --> STRING IDENT
     * 
     * Makes calls to updateToken, state_decl_list, and writeToFile.
     */

    public void parseDFA()
    {
        log("DFA             --> state_decl_list | E");
        updateToken();
        state_decl_list();
        writeToFile();
    }

    /**
     * Goes to state_decl and back to state_decl_list if the token is a state
     * declaration name. Does nothing if reads an EOF. Goes to entry and back
     * to state_decl_list if token is a start state token.
     */

    public void state_decl_list()
    {
        switch (tokenType)
        {
            case Token.STATE:
                log("state_decl_list --> state_decl state_decl_list");
                state_decl();
                state_decl_list();
                break;
            case Token.EOF:
                log("state_decl_list --> E");
                break;
            case Token.ASTERISK:
                log("state_decl_list --> entry state_decl_list");
                entry();
                state_decl_list();
                break;
            default:
                throw new Exception("Error with state declaration token");
        }
    }

    /**
     * Gets the state name and calls updateToken, state_type, and trans_list
     */

    public void state_decl() {
        log("state_decl      --> IDENT state_type trans_list");
        stateDec = current.getInput();
        updateToken();
        state_type();
        trans_list();
    }

    /**
     * Gets the next token and the next token type.
     */

    public void updateToken()
    {
        current = scan.nextToken();
        tokenType = current.getTokenType();
    }

    /**
     * Determine after declaration what type of state it is.
     * An @ symbol makes it a terminal/accept state.
     */

    public void state_type()
    {
        switch (tokenType)
        {
            case Token.COLON:
                log("state_type      --> :");
                break;
            case Token.AT:
                log("state_type      --> @");
                acceptStates.Add(stateDec);
                break;
            default:
                throw new Exception("Error with state type");
        }
        updateToken();
    }

    /**
     * Removes quotes from transition conditions, updates the token,
     * and binds the transition conditions with the transition state.
     */

    public void trans_list()
    {
        String conditions = "";    //Transition conditions
        switch(tokenType) {
            case Token.TRANSITION:
                log("trans_list      --> trans trans_list_tail | E ");
                char[] input = current.getInput().ToCharArray();
                
                //Removes '' from input
                for (int i = 0; i < input.Length; i++)
                {
                    if (i != 0 && i != input.Length - 1)
                    {
                        conditions += input[i];
                    }
                }

                updateToken();

                //In transList, conditions is the key and the transition state is
                //the value.

                if (tokenType == Token.STATE)
                {
                    log("trans           --> STRING IDENT");
                    transList.Add(conditions, current.getInput());
                    updateToken();
                }
                else
                {
                    throw new Exception("Error with transition state name");
                }
                trans_list_tail();
                transList = new Dictionary<String, String>();
                break;

            case Token.STATE: case Token.ASTERISK:
                //Empty transition list
                break;

            default:
                throw new Exception("Error with transition input");
        }
    }

    /**
     * Sees if there are more transition conditions for this state.
     * If yes, calls trans_list again. If not, add the state name
     * and the list of transition conditions into the table.
     */

    public void trans_list_tail()
    {
        log("trans_list_tail --> , trans trans_list_tail | E");
        switch (tokenType)
        {
            case Token.COMMA:
                updateToken();
                trans_list();
                break;

            case Token.SEMICOLON:
                table.Add(stateDec, transList);
                updateToken();
                break;

            default:
                throw new Exception("Invalid term tail");
        }
    }

    /**
     * Gets the start state.
     */

    public void entry()
    {
        updateToken();
        if (tokenType == Token.STATE)
        {
            start = current.getInput();
            updateToken();
        }
        else
        {
            throw new Exception("Error with declaring a start state");
        }
    }

    /**
     * Output for debugging
     * 
     * @param output  output to display.
     */

    public void log(String output)
    {
        if (debug)
        {
            Console.WriteLine(output);
        }
    }

    /**
     * Generates the C# program
     */

    public void writeToFile()
    {
        //Generate the output file.
        StreamWriter output = new StreamWriter(className+".cs");

        //Variable declarations.
        output.WriteLine("using System;\n");
        output.WriteLine("public class " + className + " {\n");
        output.WriteLine("\tprivate String startState = \""+ start + "\";");
        output.WriteLine("\tprivate bool debug = false;");
        if (table.Count != 0)
        {
            output.Write("\tprivate String[,] table = \n\t{\n");

            //Store the DFA states and data into a 2 dimensional array.
            List<String> keys = new List<String>(table.Keys); //State declarations
            List<Dictionary<String, String>> values = new
                List<Dictionary<String, String>>(table.Values); //Transition conditions. 

            for (int i = 0; i < keys.Count; i++)
            {
                Dictionary<String, String> elementValue = values[i];
                List<String> transitionKeys = new List<String>(elementValue.Keys);
                List<String> transitionState = new List<String>(elementValue.Values);
                for (int j = 0; j < transitionKeys.Count; j++)
                {
                    //Format is "State" , "Transition Conditions", "State to transition"
                    output.Write("\t\t{\"" + keys[i] + "\", \"" + transitionKeys[j]
                        + "\", \"" + transitionState[j] + "\" }");
                    if (i != (keys.Count - 1))
                    {
                        output.WriteLine(",");
                    }
                    else
                    {
                        output.WriteLine();
                    }
                }
            }
        }
        
        else
        {
            output.WriteLine("\tprivate String table = null");
        }

        output.WriteLine("\t};");

        if (acceptStates.Count != 0)
        {
            output.Write("\tprivate String[] acceptStates = { ");
            for (int i = 0; i < acceptStates.Count; i++)
            {
                if (i == acceptStates.Count - 1)
                {
                    output.Write("\"" + acceptStates[i] + "\" };");
                }
                else
                {
                    output.Write("\"" + acceptStates[i] + "\" , ");
                }
            }
        }
        
        else
        {
            output.Write("\tprivate String acceptStates = null;");
        }

        //Scan method.
        output.WriteLine("\n\n\tpublic bool scan(String input)\n\t{");
        output.WriteLine("\t\tString currentState = startState;");
        output.WriteLine("\t\tchar[] inputParse = input.ToCharArray();");
        output.WriteLine("\t\tfor(int i = 0; i < inputParse.Length; i++)\n\t\t{");
        output.WriteLine("\t\t\tlog(\"{\" + currentState + \"}\");");
        output.WriteLine("\t\t\tlog(\"\" + inputParse[i]);");
        output.WriteLine("\t\t\tfor(int j = 0; j < table.GetLength(0); j++)\n\t\t\t{");
        output.WriteLine("\t\t\t\tif(table[j, 0].Equals(currentState))\n\t\t\t\t{");
        output.WriteLine("\t\t\t\t\tif(table[j, 1].IndexOf(inputParse[i]) != -1)\n\t\t\t\t\t{");
        output.WriteLine("\t\t\t\t\t\tcurrentState = table[j, 2];");
        output.WriteLine("\t\t\t\t\t\tbreak;");
        output.WriteLine("\t\t\t\t\t}\n\t\t\t\t}\n\n\t\t\t\tif( j == table.GetLength(0) - 1)\n\t\t\t\t{");
        output.WriteLine("\t\t\t\t\tif(debug)\n\t\t\t\t\t{");
        output.WriteLine("\t\t\t\t\t\tConsole.WriteLine();\n\t\t\t\t\t}");
        output.WriteLine("\t\t\t\t\treturn false;\n\t\t\t\t}\n\t\t\t}");
        output.WriteLine("\t\t}\n");
        output.WriteLine("\t\tif(debug)\n\t\t{");
        output.WriteLine("\t\t\tConsole.WriteLine();\n\t\t}");
        output.WriteLine("\n\t\tfor(int i = 0; i < acceptStates.Length; i++)\n\t\t{");
        output.WriteLine("\t\t\tif(acceptStates[i].Equals(currentState))\n\t\t\t{");
        output.WriteLine("\t\t\t\treturn true;\n\t\t\t}\n\t\t}");
        output.WriteLine("\n\t\treturn false;\n\t}");

        //setDebug method.
        output.WriteLine("\n\tpublic void setDebug(bool debug)\n\t{");
        output.WriteLine("\t\tthis.debug = debug;\n\t}");

        //Log method to display output.
        output.WriteLine("\n\tpublic void log(String output)\n\t{");
        output.WriteLine("\t\tif(debug)\n\t\t{");
        output.WriteLine("\t\t\tConsole.Write(output);\n\t\t}\n\t}");
        output.WriteLine("}");
        output.Close();
    }
}
