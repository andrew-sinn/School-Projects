/*
 * DeliveryLocationStorage.java
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
 * Data structure to hold a list of delivery locations.
 * 
 * @author    Andrew James Whitcomb
 */

public class DeliveryLocationStorage implements Serializable {

	//Class variables
	private static final long serialVersionUID = 1L;
	private static DeliveryLocationStorage availableStorage;
	private Map< String, DeliveryLocation > deliveryLocationMap = null;
	private final static File SETTINGS = new File( "DeliveryLocationList.settings" );
	
	/**
	 * Constructor for the customer storage
	 */
	
	private DeliveryLocationStorage() {
		if( SETTINGS.exists() ) {
			try {
				ObjectInputStream oos = new ObjectInputStream( new FileInputStream( SETTINGS ) );
				deliveryLocationMap = ((DeliveryLocationStorage) oos.readObject()).getDeliveryLocationMap();
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			deliveryLocationMap = new HashMap< String, DeliveryLocation >();
		}
	}
	
	/**
	 * Gets the current DeliveryLocationStorage available
	 * 
	 * @return    An available CustomerStorage
	 */
	
	public static DeliveryLocationStorage getDeliveryLocationStorage() {
		if( availableStorage == null ) {
			availableStorage = new DeliveryLocationStorage();
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
	 * Adds to the delivery location map
	 * 
	 * @param    newLocation    Location to add
	 */
	
	public void addDeliveryLocation( DeliveryLocation newLocation ) {
		deliveryLocationMap.put( newLocation.getLocationName(), newLocation );
	}
	
	/* Removes a location from the map
	* 
	* @param    locationToRemove    Delivery location to remove
	*/

	public void removeDeliveryLocation( String locationToRemove ) {
		deliveryLocationMap.remove( locationToRemove );
	}
	
	/**
	 * Sees if a delivery location exists
	 * 
	 * @param    locationName    Name of the location in question
	 * 
	 * @return    If there is a location with the given name
	 */
	
	public boolean deliveryLocationExists( String locationName ) {
		return( deliveryLocationMap.containsKey( locationName ) );
	}
	
	/**
	 * Gets a delivery location in the storage
	 * 
	 * @param    locationName    Name of the location in question
	 * 
	 * @return    The location associated with the given name
	 */
	
	public DeliveryLocation getDeliveryLocation( String locationName ) {
		return( deliveryLocationMap.get( locationName ) );
	}
	
	/**
	 * Gets the delivery location map
	 */
	
	public Map< String, DeliveryLocation > getDeliveryLocationMap() {
		return( deliveryLocationMap );
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

