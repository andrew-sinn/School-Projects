/**
 * SQLParseException - Exception when there is an error in parsing.
 * 
 * @author Andrew Sinn
 *
 */
public class SQLParseException extends Exception {

	//The error message.
	private String message;
	
	/**
	 * Constructor for the SQLParseException
	 * 
	 * @arg message - The error message.
	 */
	
	public SQLParseException(String message) {
		super(message);
		this.message = message;
	}
}
