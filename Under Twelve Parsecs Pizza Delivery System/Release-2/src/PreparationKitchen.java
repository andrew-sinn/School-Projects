/*
 * PreparationKitchen.java
 * 
 * Version:
 *   $Id: PreparationKitchen.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: PreparationKitchen.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Object that helps the handling the preparation of the food.
 * 
 * @author    Andrew James Whitcomb
 * @coauthor    Jeffrey O'Connell
 */

public class PreparationKitchen extends Thread implements Serializable {

	// Class variables
	private static final long serialVersionUID = 1L;
	private Queue<OrderedItem> itemsToBePrepared;
	private Queue<Cook> theCooks;
	private List<Cook> busyCooks;
	private boolean running;
	private static PreparationKitchen mod = null;
	private int numCooks = 0;
	private final static File SETTINGS = new File( "PreparationKitchen.settings" );

	/**
	 * Gets the instance of the preparation kitchen
	 * 
	 * @return    the instance of preparation kitchen
	 */
	
	public static PreparationKitchen getInstance() {
		if (mod == null)
			mod = new PreparationKitchen();
		return mod;
	}
	
	/**
	 * Constructor for the Kitchen object
	 */

	private PreparationKitchen() {
		if( SETTINGS.exists() ) {
			try	{
				ObjectInputStream ois = new ObjectInputStream( new FileInputStream( SETTINGS ) );
				numCooks = (Integer) ois.readObject();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}		
		} else {
			numCooks = 0;
		}
		itemsToBePrepared = new LinkedBlockingQueue<OrderedItem>();
		theCooks = new LinkedBlockingQueue<Cook>();
		busyCooks = new ArrayList<Cook>();
		running = false;
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
	 * Starts this system up
	 */
	
	public void startSystem() {
		running = true;
		for( int loop = 0; loop < numCooks; loop++ ) {
			theCooks.add( new Cook() );
		}
		start();
	}

	/**
	 * Adds a list of items to the OrderedItem queue
	 * 
	 * @param    itemsToBePrep    List of items that need to be prepared
	 */

	public synchronized void addItem( OrderedItem itemToBePrep ) {
		itemsToBePrepared.add( itemToBePrep );
		notify();
	}
	
	/**
	 * Removes an item from either the preparation kitchen or the cook that has the item
	 * 
	 * @param    itemToRemove    Item to be removed from the kitchen
	 */
	
	public synchronized void removeItem( OrderedItem itemToRemove ) {
		if( !itemsToBePrepared.remove( itemToRemove ) ) {
			for( int loop = 0; loop < busyCooks.size(); loop++ ) {
				if( busyCooks.get( loop ).getItemToPrepare().equals( itemToRemove ) ) {
					busyCooks.get( loop ).setItemToPrepare( null );
				}
			}
		}
	}

	/**
	 * Moves a cook from the busy list to the working list
	 * 
	 * @param    cookFreed    The cook that can take another item
	 */

	public synchronized void returnToQueue( Cook cookFreed ) {

		OrderedItem finishedItem = cookFreed.getItemToPrepare();
		if( finishedItem != null ) {
			if (finishedItem.getCookTimeOfItem() == 0) {
				finishedItem.start();
			} else {
				OvenKitchen.getInstance().addItem(finishedItem);
			}
		}
		busyCooks.remove(cookFreed);
		theCooks.add(cookFreed);
		notify();
	}
	
	/**
	 * Stops the thread from running
	 */
	
	public void kill() {
		running = false;
		while (!theCooks.isEmpty()) {
			theCooks.peek().kill();
			theCooks.remove();
		}
	}

	/**
	 * Method that dictates how the kitchen works
	 */

	public void run() {
		synchronized (this) {
			while( running ) {
				// Checks if there an item needing to be prepared
				if (!itemsToBePrepared.isEmpty() && !theCooks.isEmpty()) {
					theCooks.peek().setItemToPrepare(itemsToBePrepared.remove());
					busyCooks.add(theCooks.remove());
				} else { 	
					try {
						wait();
					} catch (InterruptedException e) {
						//Should not do anything; meant to be interrupted
					}
				}
			}
		}
	}
	
	/**
	 * Get numCooks
	 */
	public int getNumCooks()
	{
		return numCooks;
	}
	
	public List<Cook> getBusyCooks(){
		return busyCooks;
	}
	
	/**
	 * Set the number of cooks
	 * 
	 * @param number  The number of cooks
	 */
	
	public void setCooks(int number) {
		numCooks = number;
	}
	
	/**
	 * Saves settings
	 */
	public void saveSettings() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( SETTINGS ) );
			oos.writeObject( new Integer( numCooks ) );
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
