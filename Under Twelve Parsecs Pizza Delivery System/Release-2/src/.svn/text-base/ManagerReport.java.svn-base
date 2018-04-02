/*
 * ManagerReport.java
 * 
 * Version:
 *   $Id$
 *   
 * Revision:
 *   $Log$
 */

import java.util.*;

/**
 * Data structure to hold the completed orders of the day
 * 
 * @author    Andrew Sinn
 */

public class ManagerReport {
	
	//Class variables
	private static ManagerReport currentReport = null;
	private ArrayList<Order> ordersToday;
	
	/**
	 * Constructor for the manager report.
	 */
	
	private ManagerReport() {
		ordersToday = new ArrayList<Order>();
	}
	
	/**
	 * Gets the current manager report
	 * 
	 * @return    The current manager report
	 */
	
	public static ManagerReport getCurrentManagerReport() {
		if( currentReport == null ) {
			currentReport = new ManagerReport();
		}
		return( currentReport );
	}
	
	/**
	 * Add an order to the report
	 * 
	 * @param    newOrder    Order to add to the report
	 */
	
	public void addOrder( Order newOrder ) {
		ordersToday.add( newOrder );
	}
	
	public ArrayList<Order> getOrders() {
		return( ordersToday );
	}
	
	/**
	 * Gets the number of orders made today.
	 * 
	 * @return  The number of orders
	 */
	
	public int getNumberOfOrdersToday() {
		return ordersToday.size();
	}
	
	/**
	 * Gets the average cost of orders for today.
	 * 
	 * @return  The average cost of orders for today.
	 */
	
	public double getAverageCost() {
		if( ordersToday.size() == 0 ) {
			return( 0 );
		} else {
			double sum = 0;
			Iterator<Order> iter = ordersToday.iterator();
			while(iter.hasNext()) {
				sum = iter.next().getOrderCost();
			}
			return (sum/getNumberOfOrdersToday());
		}
	}
}
