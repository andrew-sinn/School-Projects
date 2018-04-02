/*
 * PizzaDriver.java
 * 
 * Version:
 *   $Id: PizzaDriver.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: PizzaDriver.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

import java.util.*;

/**
 * A driver that takes orders to their respective location
 * 
 * @author Andrew James Whitcomb
 */

public class PizzaDriver extends Thread {

	// Class variables
	private List<Order> currentOrders;
	private DeliveryLocation currentDestination;
	private boolean exists;
	private int percentDriven;
	
	/**
	 * Creation of a driver
	 */

	public PizzaDriver() {
		currentOrders = null;
		currentDestination = null;
		exists = true;
		percentDriven = 0;
		start();
	}

	/**
	 * Sets the current list of orders to deliver
	 * 
	 * @param    ordersToDeliver    The orders this driver has to deliver
	 */

	public synchronized void setDelivery( List<Order> ordersToDeliver ) {
		currentOrders = ordersToDeliver;
		currentDestination = ordersToDeliver.get(0).getCustomerInfo().getDelivLocation();
		notify();
	}

	/**
	 * Sets the exist flag to false (if driver is unassigned from job)
	 */

	public void stopJob() {
		exists = false;
	}

	/**
	 * Stops this thread
	 */

	public void kill() {
		exists = false;
		currentOrders = null;
	}
	
	public int getPercentDriven() {
		return( percentDriven );
	}
	
	public List<Order> getOrders() {
		return( currentOrders );
	}

	/**
	 * The routine the driver follows
	 */

	public void run() {
		synchronized (this) {
			while( exists ) {
				if( currentOrders != null ) {
					int timeToLocation = currentDestination.getTimeToLocation();
					while( percentDriven < 100 ) {
						try {
							wait( 600 * timeToLocation );
							percentDriven++;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}						
					}
					
					for (int loop = 0; loop < currentOrders.size(); loop++) {
						currentOrders.get(loop).finishOrder();
					}
					currentOrders = null;
					currentDestination = null;
					
					try {
						wait( 120000 );
					} catch ( InterruptedException e ) {
						e.printStackTrace();
					}
					
					while( percentDriven > 0 ) {
						try {
							wait( 600 * timeToLocation );
							percentDriven--;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}						
					}
					DeliverySystem.getDeliverySystem().returnToQueue( this );
				} else {
					try {
						wait();
					} catch (InterruptedException e) {}
				}
			}
		}
	}
}
