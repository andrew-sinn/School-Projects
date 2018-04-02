/*
 * CustomerStorage.java
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
 * Data structure to hold a list of customers.
 * 
 * @author    Andrew James Whitcomb
 */

public class CustomerStorage implements Serializable {

	//Class variables
	private static final long serialVersionUID = 1L;
	private static CustomerStorage availableStorage;
	private Map< Long, Customer > customerMap = null;
	private final static File SETTINGS = new File( "CustomerList.settings" );
	
	/**
	 * Constructor for the customer storage
	 */
	
	private CustomerStorage() {
		if( SETTINGS.exists() ) {
			try {
				ObjectInputStream oos = new ObjectInputStream( new FileInputStream( SETTINGS ) );
				customerMap = ((CustomerStorage) oos.readObject()).getCustomerMap();
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			customerMap = new HashMap< Long, Customer >();
		}
	}
	
	/**
	 * Gets the current CustomerStorage available
	 * 
	 * @return    An available CustomerStorage
	 */
	
	public static CustomerStorage getCustomerStorage() {
		if( availableStorage == null ) {
			availableStorage = new CustomerStorage();
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
	 * Adds to the customer map
	 * 
	 * @param    newCustomer    Customer to add
	 */
	
	public void addCustomer( Customer newCustomer ) {
		customerMap.put( newCustomer.getOrderID(), newCustomer );
	}
	
	/**
	 * Removes a customer from the map
	 * 
	 * @param    customerToRemove    Customer to remove
	 */
	
	public void removeCustomer( Long customerToRemove ) {
		customerMap.remove( customerToRemove );
	}
	
	/**
	 * Sees if a customer exists in the database
	 * 
	 * @param    customerID    ID of the customer in question
	 * 
	 * @return    If the customer's ID is in the database
	 */
	
	public boolean customerExists( Long customerID ) {
		return( customerMap.containsKey( customerID ) );
	}
	
	/**
	 * Gets a customer in the storage
	 * 
	 * @param    customerID    ID of the customer in question
	 * 
	 * @return    The customer associated with the ID
	 */
	
	public Customer getCustomer( Long customerID ) {
		return( customerMap.get( customerID ) );
	}
	
	/**
	 * Gets the customer map
	 */
	
	public Map< Long, Customer > getCustomerMap() {
		return( customerMap );
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
