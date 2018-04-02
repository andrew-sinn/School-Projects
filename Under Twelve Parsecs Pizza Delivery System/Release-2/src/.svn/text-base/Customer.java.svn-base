/*
 * Customer.java
 * 
 * Version:
 *   $Id: Customer.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: Customer.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

import java.io.*;

/**
 * Object to hold customer information
 * 
 * @author    Andrew James Whitcomb
 */

public class Customer implements Serializable {

	//Class variables
	private static final long serialVersionUID = 1L;
	private long orderID;
	private String lastName;
	private String firstName;
	private DeliveryLocation delivLocation;
	
	/**
	 * Constructor for the Customer object
	 * 
	 * @param    orderID    The phone number of the customer
	 * @param    lastName    The last name of the customer
	 * @param    firstName    The first name of the customer
	 * @param    delivLocation    The delivery location enum for this customer
	 */
	
	public Customer( long orderID, String lastName, String firstName, DeliveryLocation delivLocation ) {
		
		if( !CustomerStorage.getCustomerStorage().customerExists( orderID ) && orderID > 0 && lastName != null && firstName != null && delivLocation != null ) {	
			this.orderID = orderID;
			this.lastName = lastName;
			this.firstName = firstName;		
			this.delivLocation = delivLocation;
			CustomerStorage.getCustomerStorage().addCustomer( this );
		} else {
			this.orderID = -1;
			this.lastName = null;
			this.firstName = null;		
			this.delivLocation = null;
		}
	}

	/**
	 * Gets the delivery location for this customer
	 * 
	 * @return    The delivery location of this customer
	 */
	
	public DeliveryLocation getDelivLocation() {
		return delivLocation;
	}

	/**
	 * Sets the delivery location for this customer
	 * 
	 * @param    delivLocation    The new delivery location for the customer
	 */
	
	public void setDelivLocation( DeliveryLocation delivLocation) {
		if( delivLocation != null )
			this.delivLocation = delivLocation;
	}

	/**
	 * Gets the order ID of this customer
	 * 
	 * @return    The order ID of this customer
	 */
	
	public long getOrderID() {
		return orderID;
	}

	/**
	 * Gets the last name of the customer
	 * 
	 * @return    The last name of the customer
	 */
	
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the first name of the customer
	 * 
	 * @return    The first name of the customer
	 */
	
	public String getFirstName() {
		return firstName;
	}
	
}
