import java.io.IOException;
import java.util.ArrayList;

/**
 * This class will parse SQL statements being read in from the command line.
 * 
 * @author Andrew Sinn
 *
 */

public class SQLParser {
	
	//The input scanner.
	private Scanner input;
	
	//The current token.
	private Token current;
	
	//The token type.
	private int tokenType;
	
	//The column names from the select clause.
	private ArrayList<String> columns;
	
	//The table names from the from clause.
	private ArrayList<String> tables;
	
	//The conditions from the where clause.
	private ArrayList<String> conditions;
	
	//The attributes listed in the insert statement (optional).
	private ArrayList<String> attributes;
	
	//The values to be inserted.
	private ArrayList<String> values;
	
	//A table name in a select and an insert statement.
	private String tableName;
	
	//A where condition in a select statement.
	private String whereCond;
	
	//Alias for the table name if possible.
	
	/**
	 * Constructor for the SQL parser. The Scanner reads from the command line.
	 * Initializes all attributes to null or empty.
	 */
	
	public SQLParser() {
		System.out.println("Please enter an SQL Select or Insert Statement:");
		input = new Scanner(System.in);
		current = null;
		columns = new ArrayList<String>();
		tables = new ArrayList<String>();
		conditions = new ArrayList<String>();
		attributes = new ArrayList<String>();
		values = new ArrayList<String>();
		tableName = "";
		whereCond = "";
	}
	
	/**
	 * Gets the next token and the next token type.
	 */
	
	public void updateToken() {
		try {
			current = input.nextToken();
		} catch (IOException e) {
			System.err.println("IOException.");
		}
		tokenType = current.getTokenType();
	}
	
	/**
	 * A sequel (SQL) statement is an select statement, insert statement, 
	 * or an empty line. Anything else throws a syntax error.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void SQL_statement() throws SQLParseException {
		updateToken();
		
		switch(tokenType) {
		
		case Token.SELECT:
			select_statement();
			break;
		
		case Token.INSERT:
			insert_statement();
			break;
		
		case Token.NEWLINE:
			//No SQL query. Exit parsing.
			break;
		
		default:
			signalError();
		}
	}
	
	/**
	 * Check to see if the columns are all columns or column names.
	 * Then check to see if there is a from clause. If yes, then get the
	 * table names. Then check to see if there is a where clause (optional).
	 * Any invalid input will be a syntax error.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void select_statement() throws SQLParseException {
		updateToken();
		
		switch(tokenType) {
		
		//Asterisk, name, or invalid input for the columns.
		case Token.ASTERISK:
			columns.add(current.getInput());
			updateToken();
			break;
		
		case Token.NAME:
			column_name();
			break;
		
		default:
			signalError();
		}
		
		//Checks for the from token.
		if(tokenType != Token.FROM) {
			signalError();
		}
		
		//Get the table names.
		table();
		
		//If there is a where, process the where clause. If there is no
		//newline, throw the error.
		if(tokenType == Token.NEWLINE || tokenType == Token.WHERE) {
			if(tokenType == Token.WHERE) {
				optional_where();
			}
		} else {
			signalError();
		}
	}
	
	/**
	 * Stores a single column name and calls the tail function to see if
	 * there is a comma. Checks to see if there is an alias with the
	 * column name as well.
	 */
	
	public void column_name() {
		String columnName = current.getInput();
		
		updateToken();
		if(tokenType == Token.AS) {
			//Has an alias.
			updateToken();
			columnName = columnName + " AS " + current.getInput();
			updateToken();
		}
		columns.add(columnName);
		column_tail();
	}
	
	/**
	 * If there is a comma, get the next column name.
	 */
	
	public void column_tail() {
		if(tokenType == Token.COMMA) {
			updateToken();
			column_name();
		}
	}
	
	/**
	 * Gets the table names. A table can be joined with another table and
	 * can have an alias associated with it.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void table() throws SQLParseException {
		table_name();
		
		//Checks for join.
		if(tokenType == Token.JOIN) {
			tableName += " JOIN ";
			table_name();
		}
		
		//Checks for alias.
		if(tokenType == Token.AS) {
			tableName += " AS ";
			table_name(); 
		}
		
		table_tail();
		
	}
	
	/**
	 * Gets a table name.
	 * 
	 * @throws SQLParseException Syntax error.
	 * 
	 */
	
	public void table_name() throws SQLParseException {
		updateToken();
		
		if(tokenType != Token.NAME) {
			signalError();
		}
		
		tableName += current.getInput();
		updateToken();
	}
	
	/**
	 * If there is a comma, get another table name.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void table_tail() throws SQLParseException {
		
		tables.add(tableName);
		if(tokenType == Token.COMMA) {
			tableName = "";
			table();
		}
	}
	
	/**
	 * If there is a where clause, get the name, operator and values for 
	 * the condition. Also checks to see if there is an AND clause in which
	 * this method would just be called again for the next where clause.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void optional_where() throws SQLParseException {
		updateToken();
		
		//Get the name
		clause_name();
		
		if(tokenType != Token.OPERATOR) {
			signalError();
		}
		
		//Get the operator.
		whereCond = whereCond + " " + current.getInput() + " ";
		
		//Get the value.
		value();
		
		if(tokenType != Token.AND && tokenType != Token.NEWLINE) {
			signalError();
		}
		
		conditions.add(whereCond);
		
		//Checks for the AND clause.
		if(tokenType == Token.AND) {
			whereCond = "";
			optional_where();
		}
	}
	
	/**
	 * Gets the name from the where statement.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void clause_name() throws SQLParseException {
		if(tokenType == Token.NAME) {
			whereCond = current.getInput();
			updateToken();
			
			//Name could be in a "tableName.attribute" format.
			if(tokenType == Token.PERIOD) {
				updateToken();
				if(tokenType == Token.NAME) {
					whereCond = whereCond + "." + current.getInput() + " ";
					updateToken();
				} else {
					signalError();
				}
			}
		} else {
			signalError();
		}
	}
	
	/**
	 * Get the value to act on.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void value() throws SQLParseException {
		updateToken();
		
		switch(tokenType) {
		
		case Token.NUMBER: case Token.SQLSTRING:
			whereCond += current.getInput();
			updateToken();
			break;
		
		case Token.NAME:
			whereCond = whereCond + current.getInput();
			updateToken();
			
			//"tableName.attribute" format.
			if(tokenType == Token.PERIOD) {
				updateToken();
				if(tokenType == Token.NAME) {
					whereCond = whereCond + "." + current.getInput();
					updateToken();
				} else {
					signalError();
				}
			}
			break;
		
		default:
			signalError();
		}
		
	}
	
	/**
	 * Throws the syntax error exception when called.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void signalError() throws SQLParseException {
		throw new SQLParseException("Error encountered in parsing: syntax error.");
	}
	
	/**
	 * Gets the column names specified in select.
	 * 
	 * @return The column names.
	 */
	
	public ArrayList<String> getColumnNames() {
		return columns;
	}
	
	/**
	 * Gets the table names specified in from.
	 * 
	 * @return The table names.
	 */
	
	public ArrayList<String> getTableNames() {
		return tables;
	}
	
	/**
	 * Gets the where conditions, if any.
	 * 
	 * @return The where conditions.
	 */
	
	public ArrayList<String> getConditions() {
		return conditions;
	}
	
	/**
	 * An insert statement has optional attributes in parentheses,
	 * and then the actual attributes in parentheses.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void insert_statement() throws SQLParseException {
		updateToken();
		if(tokenType != Token.NAME) {
			signalError();
		}
		
		//Gets the table name.
		tableName = current.getInput();
		updateToken();
		
		//Optional attributes
		if(tokenType == Token.LEFTPAREN) {
			attribute();
			updateToken();
		}
		
		//Values token.
		if(tokenType != Token.VALUES) {
			signalError();
		}
		
		updateToken();
		
		//Gets the values enclosed in parentheses
		if(tokenType == Token.LEFTPAREN) {
			insert_value();
			if(tokenType != Token.RIGHTPAREN) {
				signalError();
			}
		} else {
			signalError();
		}
		
	}
	
	/**
	 * Gets a single attribute name.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void attribute() throws SQLParseException {
		updateToken();
		if(tokenType != Token.NAME) {
			signalError();
		}
		attributes.add(current.getInput());
		updateToken();
		attributes_tail();
	}
	
	/**
	 * If there is a comma, get the next attribute name.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void attributes_tail() throws SQLParseException {
		if(tokenType == Token.COMMA) {
			attribute();
		} else if(tokenType != Token.RIGHTPAREN) {
			signalError();
		}
	}
	
	/**
	 * Gets a single value to be inserted.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void insert_value() throws SQLParseException {
		updateToken();
		switch(tokenType) {
		
		case Token.SQLSTRING: case Token.NUMBER:
			values.add(current.getInput());
			insert_value_tail();
			break;
		
		default:
			signalError();
		}
		
	}
	
	/**
	 * Checks for a comma, if yes, then get the next value.
	 * 
	 * @throws SQLParseException Syntax error.
	 */
	
	public void insert_value_tail() throws SQLParseException {
		updateToken();
		if(tokenType == Token.COMMA) {
			insert_value();
		}
	}
	
	/**
	 * Get the table name to be inserted.
	 * 
	 * @return The table name.
	 */
	
	public String getTableName() {
		return tableName;
	}
	
	/**
	 * Get the attribute names in a list.
	 * 
	 * @return The attribute names.
	 */
	
	public ArrayList<String> getAttributes() {
		return attributes;
	}
	
	/**
	 * Get the values in a list.
	 * 
	 * @return The values to be inserted.
	 */
	
	public ArrayList<String> getValues() {
		return values;
	}
	
	/**
	 * The main method (test).
	 * 
	 * @param args no command line arguments.
	 */
	
	public static void main(String[]args) {
		
		//To validate SQL queries, declare an object of this class and
		//call the SQL_statement method. It will throw a SQLParseException
		//when there is a syntax error (but it is caught here).
		//SQL_statement starts the parsing.
		
		SQLParser parser = new SQLParser();
		
		try {
			parser.SQL_statement();
		} catch (SQLParseException e) {
			System.err.println(e.getMessage());
			return;
		}
		
		//The results of parsing are in here.
		
		//Remove comments to display output of parsing.
		
		//Select query results
		ArrayList<String> select = parser.getColumnNames();
		ArrayList<String> from = parser.getTableNames();
		ArrayList<String> where = parser.getConditions();
		
		/*
		//Insert query results
		String tableName = parser.getTableName();
		ArrayList<String> attributes = parser.getAttributes();
		ArrayList<String> allValues = parser.getValues();
		
		//Display insert results.
		if(parser.getTableName() !=null) {
			System.out.println("\nTable Name: " + tableName);
		}
		
		if(attributes.size() !=0) {
			System.out.println("\nAttributes");
			for (String s: attributes) {
				System.out.println(s);
			}
		}
		
		if(allValues.size() !=0) {
			System.out.println("\nValues");
			for (String s: allValues) {
				System.out.println(s);
			}
		}
		//Display select results.
		if(select.size() !=0) {
			System.out.println("\nSelect");
			for (String s: select) {
				System.out.println(s);
			}
		}
		
		if(from.size() !=0) {
			System.out.println("\nFrom");
			for (String t: from) {
				System.out.println(t);
			}
		}
		
		if(where.size() !=0) {
			System.out.println("\nWhere");
			for (String u: where) {
				System.out.println(u);
			}
		}
		*/
	}
}