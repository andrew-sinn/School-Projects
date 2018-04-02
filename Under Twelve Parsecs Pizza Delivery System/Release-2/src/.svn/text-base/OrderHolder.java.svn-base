/*
 * OrderHolder.java
 * 
 * Version:
 *   $Id$
 *   
 * Revision:
 *   $Log$
 */

import java.util.*;

public class OrderHolder {

	//Class variables
	private static OrderHolder currentOrderHolder = null;
	private ArrayList<Order> currentOrders;
	
	/**
	 * Constructor for the OrderHolder class
	 */
	
	private OrderHolder() {
		currentOrders = new ArrayList<Order>();
	}
	
	/**
	 * Gets the current instance of the order holder
	 * 
	 * @return    the current instance of the order holder
	 */
	
	public static OrderHolder getCurrentOrderHolder() {
		if( currentOrderHolder == null ) {
			currentOrderHolder = new OrderHolder();
		}
		return( currentOrderHolder );
	}
	
	/**
	 * Adds an order to the order holder
	 * 
	 * @param    newOrder    Order to add
	 */
	
	public void addOrder( Order newOrder ) {
		currentOrders.add( newOrder );
	}
	
	/**
	 * Removes an order to the order holder
	 * 
	 * @param    orderToRemove    Order to remove
	 */
	
	public void removeOrder( Order orderToRemove ) {
		currentOrders.remove( orderToRemove );
	}
	
	/**
	 * Gets the list of current orders not in the delivery system
	 */
	
	public ArrayList<Order> getCurrentOrders() {
		return( currentOrders );
	}
	
}
