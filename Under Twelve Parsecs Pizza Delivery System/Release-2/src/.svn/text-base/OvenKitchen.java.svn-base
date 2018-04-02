/*
 * OvenKitchen.java
 * 
 * Version:
 *   $Id: OvenKitchen.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: OvenKitchen.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * The kitchen where everything gets cooked
 * 
 * @author Andrew James Whitcomb
 */

public class OvenKitchen extends Thread {

	// Class variables
	private Queue<OrderedItem> itemsWaiting;
	private List<Oven> cookingOvens;
	private boolean running;
	private static OvenKitchen mod = null;
	private final static File SETTINGS = new File( "OvenKitchen.settings" );

	/**
	 * Returns the instance
	 */

	public static OvenKitchen getInstance() {
		if (mod == null){
			mod = new OvenKitchen();
		} 
		return( mod );
	}

	/**
	 * Constructor for the OvenKitchen 
	 */

	private OvenKitchen() {
		running = false;
		itemsWaiting = new LinkedBlockingQueue<OrderedItem>();
		cookingOvens = new ArrayList<Oven>();
		ObjectInputStream ois;
		cookingOvens = new  ArrayList<Oven>(); 
		if(SETTINGS.exists())
		{
			try	{
				ois = new ObjectInputStream( new FileInputStream( SETTINGS ) );
				cookingOvens = ( List<Oven> ) ois.readObject();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
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
	 * Starts the system
	 */
	
	public void startSystem() {
		running = true;
		start();
	}
	
	/**
	 * Adds an oven to the kitchen
	 * 
	 * @param    newOven    Oven to add to the kitchen
	 */
	
	public void addOven( Oven newOven ) {
		cookingOvens.add( newOven );
	}
	
	/**
	 * Removes an oven from the kitchen
	 * 
	 * @param    ovenToRemove    Oven to remove from the kitchen
	 */
	
	public void removeOven( Oven ovenToRemove ) {
		cookingOvens.remove( ovenToRemove );
	}

	/**
	 * Adds an item to the waiting queue
	 * 
	 * @param    itemToCook    Item to add to the cooking list
	 */

	public synchronized void addItem( OrderedItem itemToCook ) {
		itemsWaiting.add( itemToCook );
		if (itemToCook != null)
			this.notify();
	}
	
	/**
 	 * Removes an item from the waiting queue
 	 * 
 	 * @param    itemToRemove    Item to remove from the cooking list
	 */
	
	public synchronized void removeItem( OrderedItem itemToRemove ) {
		itemsWaiting.remove( itemToRemove );
	}

	/**
	 * Gets the ovens that are in operation
	 * 
	 * @return    the list of the ovens in operation
	 */
	
	public List<Oven> getCookingOvens() {
		return( cookingOvens );
	}
	
	/**
	 * Stops thread
	 */
	
	public void kill() {
		running = false;
	}
	
	/**
	 * Saves settings
	 */
	
	public void saveSettings() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream( new File( "OvenKitchen.settings" ) ) );
			oos.writeObject(cookingOvens);
			oos.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException in saveSettings!");
		} catch (IOException e) {
			System.out.println("IOException in saveSettings!");
		}

	}

	/**
	 * Method that dictates how the kitchen works
	 */

	public void run() {
		synchronized (this) {
			while (running) {
				if (!itemsWaiting.isEmpty()) {
					int numItemsWaiting = itemsWaiting.size();
					for( int loop = 0; loop < itemsWaiting.size(); loop++ ) {
						OrderedItem firstItem = itemsWaiting.peek();
						for( int loop2 = 0; loop2 < cookingOvens.size(); loop2++ ) {
							if( firstItem.getOvenSpaceRequired() <= cookingOvens.get( loop2 ).getCurrentOvenSpace() ) {
								cookingOvens.get( loop2 ).addItem( itemsWaiting.remove() );
								loop--;
								loop2 = cookingOvens.size();
							}
						}
						if ( firstItem.equals( itemsWaiting.peek() ) ) {
							itemsWaiting.add( itemsWaiting.remove() );
						}
					}
					if( numItemsWaiting == itemsWaiting.size() ) {
						try {
							wait( 60000 );
						} catch (InterruptedException e) {
							//Might get interrupted; should continue running if interrupted
						}
					}
				} else {
					try {
						wait();
					} catch (InterruptedException e) {
						//Shouldn't have anything happen; meant to be interrupted
					}
				}
			}
		}
	}
}
