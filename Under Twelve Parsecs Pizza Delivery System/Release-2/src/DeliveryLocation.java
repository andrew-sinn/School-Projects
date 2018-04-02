/*
 * DeliveryLocation.java
 * 
 * Version:
 *   $Id: DeliveryLocation.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: DeliveryLocation.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

import java.io.*;

/**
 * Description of a given delivery location
 * 
 * @author    Andrew James Whitcomb
 */

public class DeliveryLocation implements Serializable {

	//Class variables
	private static final long serialVersionUID = 1L;
	private String locationName;
	private int timeToLocation;

	/**
	 * Constructor for DeliveryLocation
	 * 
	 * @param    locationName    The name of the location to deliver to
	 * @param    timeToLocation    The amount of time it takes to drive to the location (in minutes)
	 */
	
	public DeliveryLocation( String locationName, int timeToLocation ) {
		if( locationName != null && timeToLocation >= 0 ) {
			if( !DeliveryLocationStorage.getDeliveryLocationStorage().deliveryLocationExists( locationName ) ) {
				this.locationName = locationName;
				this.timeToLocation = timeToLocation;
				DeliveryLocationStorage.getDeliveryLocationStorage().addDeliveryLocation( this );
			} else {
				this.locationName = null;
				this.timeToLocation = -1;
			}
		} else {
			this.locationName = null;
			this.timeToLocation = -1;
		}
	}

	/**
	 * Gets the full name of the location
	 * 
	 * @return    the name of the location
	 */
	
	public String getLocationName() {
		return locationName;
	}

	/**
	 * Gets the amount of time it takes to drive to the location (in minutes)
	 * 
	 * @return    the amount of time it takes to drive to the location (in minutes)
	 */
	
	public int getTimeToLocation() {
		return timeToLocation;
	}	
	
}
