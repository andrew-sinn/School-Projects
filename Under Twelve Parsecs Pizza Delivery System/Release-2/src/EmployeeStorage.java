/*
 * EmployeeStorage.java
 * 
 * Version:
 *   $Id$
 *   
 * Revision:
 *   $Log$
 */

import java.io.*;
import java.util.*;

/**
 * Data structure to hold a list of available employees.
 * 
 * @author    Andrew James Whitcomb
 */

public class EmployeeStorage implements Serializable {

	//Class variables
	private static final long serialVersionUID = 1L;
	private static EmployeeStorage availableStorage;
	private Map< String, Employee > employeeMap = null;
	private final static File SETTINGS = new File( "EmployeeList.settings" );
	
	/**
	 * Constructor for the employee storage
	 */
	
	private EmployeeStorage() {
		if( SETTINGS.exists() ) {
			try {
				ObjectInputStream oos = new ObjectInputStream( new FileInputStream( SETTINGS ) );
				employeeMap = ((EmployeeStorage) oos.readObject()).getEmployeeMap();
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			employeeMap = new HashMap< String, Employee >();
		}
	}
	
	/**
	 * Gets the current EmployeeStorage available
	 * 
	 * @return    An available EmployeeStorage
	 */
	
	public static EmployeeStorage getEmployeeStorage() {
		if( availableStorage == null ) {
			availableStorage = new EmployeeStorage();
		}
		return( availableStorage );
	}
	
	/**
	 * Kills the current instance of this class
	 */
	
	public static void killCurrentInstance() {
		availableStorage = null;
	}
	
	/**
	 * Kills all existing instances of this class
	 */
	
	public static void killInstances() {
		SETTINGS.delete();
		availableStorage = null;
	}
	
	/**
	 * Adds to the employee map
	 * 
	 * @param    newEmployee    employee to add
	 */
	
	public void addEmployee( Employee newEmployee ) {
		employeeMap.put( newEmployee.getUserID(), newEmployee );
	}
	
	/**
	 * Removes an employee from the map
	 * 
	 * @param    employeeToRemove    Employee to remove
	 */
	
	public void removeEmployee( String employeeToRemove ) {
		employeeMap.remove( employeeToRemove );
	}
	
	/**
	 * Sees if a customer exists in the map
	 * 
	 * @param    employeeID    userID of the employee in question
	 * 
	 * @return    If the employee exists in the list
	 */
	
	public boolean employeeExists( String employeeID ) {
		return( employeeMap.containsKey( employeeID ) );
	}
	
	/**
	 * Gets an employee from the list
	 * 
	 * @param    employeeID    userID of the employee in question
	 * 
	 * @return    The employee to get
	 */
	
	public Employee getEmployee( String employeeID ) {
		return( employeeMap.get( employeeID ) );
	}
	
	/**
	 * Gets the employee map
	 */
	
	Map< String, Employee > getEmployeeMap() {
		return( employeeMap );
	}
	
	/**
	 * Saves the settings
	 */
	
	public void saveSettings() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream( new FileOutputStream( SETTINGS ) );
			oos.writeObject( this );
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
