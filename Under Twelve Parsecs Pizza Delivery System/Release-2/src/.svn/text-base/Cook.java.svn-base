/*
 * Cook.java
 * 
 * Version:
 *   $Id: Cook.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: Cook.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

/**
 * Cook that prepares an item (for the oven or for delivery)
 * 
 * @author Andrew James Whitcomb
 * @coauthor Jeffrey O'Connell
 */

public class Cook extends Thread {

	// Class variable
	private boolean exists;
	private OrderedItem itemToPrep;
	private int percentPrepared;

	/**
	 * Constructor for the cook object, which prepares items for the oven or
	 * delivery
	 */

	public Cook() {
		exists = true;
		itemToPrep = null;
		percentPrepared = 0;
		start();
	}

	/**
	 * Sets the item the cook is working on
	 * 
	 * @param itemToPrep
	 *            Item that needs preparing
	 */

	public synchronized void setItemToPrepare( OrderedItem itemToPrep ) {
		this.itemToPrep = itemToPrep;
		if(itemToPrep != null)
			this.notify();
	}

	/**
	 * Gets the item the cook is currently preparing
	 * 
	 * @return the item the cook is currently preparing
	 */

	public OrderedItem getItemToPrepare() {
		return( itemToPrep );
	}

	/**
	 * Gets the percent that the current item is prepared
	 * 
	 * @return int percent that the current item is prepared
	 */

	public int getPercentPrepared() {
		return( percentPrepared );
	}

	/*
	 * Stops cook thread
	 */

	public void kill() {
		exists = false;
		itemToPrep = null;
		percentPrepared = 101;
	}

	/**
	 * The run process of the cook
	 */
	public void run() {
		synchronized (this) {
			// Continues while the cook still has an item
			while (exists) {
				if(itemToPrep == null)
				{
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// "Prepares" the item one percent at a time
				while (percentPrepared < 100) {
					try {
						wait( (int) (600 * itemToPrep.getPrepTimeOfItem() ) );
						percentPrepared++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
						percentPrepared = 101;
					}
				}
				percentPrepared = 0;
				PreparationKitchen.getInstance().returnToQueue(this);
				itemToPrep = null;	
			}
		}
	}
}
