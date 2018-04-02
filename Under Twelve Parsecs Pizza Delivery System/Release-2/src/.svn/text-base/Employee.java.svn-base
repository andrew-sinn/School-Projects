/*
 * Employee.java
 * 
 * Version:
 *   $Id$
 *   
 * Revision:
 *   $Log$
 */

import java.io.*;

/**
 * Data structure to hold customer information
 * 
 * @author    Andrew James Whitcomb
 */

public class Employee implements Serializable {

	//Class variables
	private static final long serialVersionUID = 1L;
	private String userID;
	private String lastName;
	private String firstName;
	private String password;
	private boolean managerStatus;
	private int numOrdersTaken = 0;
	
	/**
	 * Constructs an employee object
	 * 
	 * @param    userID    The ID for the employee
	 * @param    lastName    Last name of the customer
	 * @param    firstName    First name of the customer
	 * @param    password    Password for this account
	 * @param    managerStatus    Says if this employee has manager status
	 */
	
	public Employee( String userID, String lastName, String firstName, String password, boolean managerStatus ) {
		if( userID != null && lastName != null && firstName != null && password != null ) {
			if( !EmployeeStorage.getEmployeeStorage().employeeExists( userID ) ) {
				this.userID = userID;
				this.lastName = lastName;
				this.firstName = firstName;
				this.password = password;
				this.managerStatus = managerStatus;
				EmployeeStorage.getEmployeeStorage().addEmployee( this );
			} else {
				this.userID = null;
				this.lastName = null;
				this.firstName = null;
				this.password = null;
				this.managerStatus = false;
			}
		} else {
			this.userID = null;
			this.lastName = null;
			this.firstName = null;
			this.password = null;
			this.managerStatus = false;
		}
	}
	
	/**
	 * Gets the ID of this employee
	 * 
	 * @return    the ID of this employee
	 */

	public String getUserID() {
		return userID;
	}

	/**
	 * Gets the last name of this employee
	 * 
	 * @return    the last name of this employee 
	 */

	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of this employee
	 * 
	 * @param     lastName    The new last name of this employee
	 */
	
	public void setLastName(String lastName) {
		if(lastName != null)
			this.lastName = lastName;
	}
	
	/**
	 * Sets the userID of this employee
	 * 
	 * @param     userID    The new userID of this employee
	 */
	
	public void setId(String userID) {
		if(userID != null)
			this.userID = userID;
	}
	
	/**
	 * Gets the number of orders taken
	 * 
	 * @return    the number of orders taken
	 */
	
	public int getNumOrdersTaken() {
		return( numOrdersTaken );
	}

	/**
	 * Gets the first name of this employee
	 * 
	 * @return    the first name of this employee
	 */
	
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of this employee
	 * 
	 * @param    firstName    the new first name of this employee
	 */
	
	public void setFirstName(String firstName) {
		if(firstName != null)
			this.firstName = firstName;
	}

	/**
	 * Gets the password of this employee
	 * 
	 * @return    the password of this employee
	 */
	
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of this employee
	 * 
	 * @param    password    the new password of this employee
	 */
	
	public void setPassword(String password) {
		if(password != null)
			this.password = password;
	}

	/**
	 * Sees if this employee has manager status
	 * 
	 * @return    if this employee has manager status
	 */
	
	public boolean isManagerStatus() {
		return managerStatus;
	}

	/**
	 * Sets if this employee has manager status
	 * 
	 * @param    managerStatus    the new management status of this employee
	 */
	
	public void setManagerStatus(boolean managerStatus) {
		this.managerStatus = managerStatus;
	}

	/**
	 * Saves the employee information
	 */
	
	public void saveEmployee() {
		ObjectOutputStream oos;
		try {
			if( managerStatus ) {
				oos = new ObjectOutputStream( new FileOutputStream( new File( userID + ".manager" ) ) );
				oos.writeObject( this );
			} else {
				oos = new ObjectOutputStream( new FileOutputStream( new File( userID + ".employee" ) ) );
				oos.writeObject( this );
			}
		} catch (FileNotFoundException e) {
			//Should not be thrown
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Attempts to make a manager login
	 * 
	 * @param    userID    The ID of the person
	 * @param    password    The password inputted
	 * 
	 * @return    The manager's data if successful login, else, null
	 */
	
	public static Employee managerLogin( String userID, String password ) {
		Employee e = EmployeeStorage.getEmployeeStorage().getEmployee( userID );
		if( e != null ) {
			if( e.getPassword().equals( password ) && e.isManagerStatus() ) { 
				return( e );
			}
		}
		return( null );
	}
	
	/**
	 * Attempts to make a general login
	 * 
	 * @param    userID    The ID of the person
	 * @param    password    The password inputted
	 * 
	 * @return    The manager's data if successful login, else, null
	 */
	
	public static Employee generalLogin( String userID, String password ) {
		Employee e = EmployeeStorage.getEmployeeStorage().getEmployee( userID );
		if( e != null ) {
			if( e.getPassword().equals( password ) ) {
				return( e );
			}
		}
		return( null );
	}
}
