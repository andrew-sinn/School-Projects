/*
 * DeliverySystem.java
 * 
 * Version:
 *   $Id: DeliverySystem.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: DeliverySystem.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * The system that keeps track of the drivers
 * 
 * @author    Andrew James Whitcomb
 */

public class DeliverySystem extends Thread implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Class variables
	private Queue<PizzaDriver> theDrivers;
	private List<PizzaDriver> busyDrivers;
	private List<Order> readyOrders;
	private static DeliverySystem mod = null;
	private boolean running;
	private int numDrivers;
	private final static File SETTINGS = new File( "DeliverySystem.settings" );

	/**
	 * Gets the current instance of the DeliverySystem
	 * 
	 * @return    the current instance of the DeliverySystem
	 */
	
	public static DeliverySystem getDeliverySystem() {
		if (mod == null)
			mod = new DeliverySystem();
		return mod;
	}

	/**
	 * Constructor for the DeliverySystem
	 */

	private DeliverySystem() {
		if( SETTINGS.exists() ) {
			try {
				ObjectInputStream ois = new ObjectInputStream( new FileInputStream( SETTINGS ) );
				numDrivers = (Integer) ois.readObject();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}	
		} else {
			numDrivers = 0;
		}
		theDrivers = new LinkedBlockingQueue<PizzaDriver>();
		readyOrders = new ArrayList<Order>();
		busyDrivers = new ArrayList<PizzaDriver>();
		running = false;
	}
	
	/**
	 * Starts this system up
	 */
	
	public void startSystem() {
		running = true;
		for( int loop = 0; loop < numDrivers; loop++ ) {
			theDrivers.add( new PizzaDriver() );
		}
		start();
	}
	
	/**
	 * Kills the current instance of this class
	 */
	
	public static void killCurrentInstance() {
		mod = null;
	}
	
	/**
	 * Kills all existing instances of this class
	 */
	
	public static void killInstances() {
		SETTINGS.delete();
		mod = null;
	}
	
	/**
	 * Sets the number of drivers
	 * 
	 * @param    numDrivers    the new number of drivers
	 */
	
	public void setDrivers( int numDrivers ) {
		this.numDrivers = numDrivers;
	}

	/**
	 * Adds an order to the delivery system
	 * 
	 * @param    newOrder    Order to add to the delivery system
	 */

	public synchronized void addOrder( Order newOrder ) {
		readyOrders.add(newOrder);
		if(newOrder != null)
			this.notify();
	}
	
	/**
	 * Removes an order from the delivery system
	 * 
	 * @param    orderToRemove    Order to remove from the delivery system
	 */
	
	public synchronized void removeOrder( Order orderToRemove ) {
		readyOrders.remove( orderToRemove );
	}
	
	/**
	 * Checks to see if an order is in queue
	 * 
	 * @param    orderToCheck    Order to see if it is in the delivery system
	 * 
	 * @return    if the order is in the delivery system
	 */
	
	public synchronized boolean checkOrder( Order orderToCheck ) {
		return( readyOrders.contains( orderToCheck ) );
	}

	/**
	 * Returns a driver to the queue
	 * 
	 * @param    returnedDriver    Driver that is returning to queue
	 */

	public synchronized void returnToQueue( PizzaDriver returnedDriver ) {
		busyDrivers.remove(returnedDriver);
		theDrivers.add(returnedDriver);
	}

	/*
	 * Stops thread
	 */

	public void kill() {
		running = false;
		while (!theDrivers.isEmpty()) {
			theDrivers.peek().kill();
			theDrivers.remove();
		}

		readyOrders.clear();
	}
	
	public List getBusyDrivers(){
		return busyDrivers;
	}	
	
	/**
	 * Saves settings
	 */
	public void saveSettings() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( SETTINGS ) );
			oos.writeObject(new Integer(numDrivers));
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Gets the number of drivers
	 */
	public int getNumDrivers()
	{
		return numDrivers;
	}

	/**
	 * Run routine for the delivery system
	 */

	public void run() {
		synchronized( this ) {
			while( running ) {
				// Merely for initial purposes
				if (!theDrivers.isEmpty() && !readyOrders.isEmpty()) {
					// Collections.sort( readyOrders );
					while (!theDrivers.isEmpty() && !readyOrders.isEmpty()) {
						List<Order> nextDriverOrders = new ArrayList<Order>();
						Order firstOrder = readyOrders.remove(0);
						nextDriverOrders.add(firstOrder);
						for (int loop = 0; loop < readyOrders.size(); loop++) {
							if (readyOrders.get(loop).getCustomerInfo()
									.getDelivLocation() == firstOrder
									.getCustomerInfo().getDelivLocation()) {
								nextDriverOrders.add(readyOrders.remove(loop));
								loop--;
							}
						}
						theDrivers.peek().setDelivery(nextDriverOrders);
						busyDrivers.add(theDrivers.remove());
					}
				} else {
					try {
						wait();
					} catch (InterruptedException e) {
						// Shouldn't be interrupted
					}
				}
			}
		}
	}
}
