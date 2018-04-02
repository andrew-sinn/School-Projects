/*
 * Oven.java
 * 
 * Version:
 *   $Id: Oven.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: Oven.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

import java.io.Serializable;
import java.util.*;

/**
 * An oven object that cooks the pizzas
 * 
 * @author    Andrew James Whitcomb
 */

public class Oven implements Comparable<Oven>, Serializable {

	//Class variables
	private static final long serialVersionUID = 1L;
	private List<OrderedItem> itemsCooking;
	private int maxOvenSpace;
	private int currentOvenSpace;
	
	/**
	 * Constructor for the oven object 
	 * 
	 * @param    maxOvenSpace    The amount of food the oven can hold.
	 */
	
	public Oven( int maxOvenSpace ) {
		if( maxOvenSpace > 0 ) {
			this.currentOvenSpace = this.maxOvenSpace = maxOvenSpace;
			itemsCooking = new LinkedList<OrderedItem>();
			OvenKitchen.getInstance().addOven( this );
		} else {
			this.currentOvenSpace = this.maxOvenSpace = 0;
			itemsCooking = null;
		}
	}
	
	/**
	 * Gets the maximum amount of oven space available.
	 * 
	 * @return    the maximum this oven can hold.    
	 */
	
	public int getMaxOvenSpace() {
		return maxOvenSpace;
	}

	/**
	 * Sets the maximum amount of oven space available.
	 * 
	 * @param    maxOvenSpace    the new maximum amount of oven space available 
	 */
	
	public void setMaxOvenSpace( int maxOvenSpace ) {
		if(maxOvenSpace >= 0)	
			this.maxOvenSpace = maxOvenSpace;
	}

	/**
	 * Gets the current amount of oven space available.
	 * 
	 * @return    the amount of oven space available
	 */
	
	public int getCurrentOvenSpace() {
		return currentOvenSpace;
	}

	/**
	 * Sets the current amount of oven space available.
	 * 
	 * @param    currentOvenSpace    the new amount of oven space available.
	 */
	
	public void setCurrentOvenSpace( int currentOvenSpace ) {
		this.currentOvenSpace = currentOvenSpace;
	}
	
	/**
	 * Adds an item to the oven.
	 * 
	 * @param    itemToAdd    Item that needs cooking
	 */
	
	public void addItem( OrderedItem itemToAdd ) {
		itemsCooking.add( itemToAdd );
		currentOvenSpace = currentOvenSpace - itemToAdd.getOvenSpaceRequired();
		itemToAdd.setAssociatedOven( this );
		itemToAdd.start();
	}
	
	/**
	 * Removes an item from the oven.
	 * 
	 * @param    itemToRemove    Item that has finished cooking
	 */
	
	public void removeItem( OrderedItem itemToRemove ) {
		itemsCooking.remove( itemToRemove );
		currentOvenSpace = currentOvenSpace + itemToRemove.getOvenSpaceRequired();
	}
	
	/**
	 * Implementation of the compareTo method in the Comparable interface
	 * 
	 * @param    otherOven    Other oven to compare to
	 */
	
	public int compareTo( Oven otherOven ) {
		return( otherOven.getMaxOvenSpace() - maxOvenSpace );
	}
	
	/**
	 * Override method of the equals method
	 * 
	 * @param    otherOven    The other oven to compare to
	 * 
	 * @return    If the two ovens have the same capacity, then true.
	 */
	
	public boolean equals( Oven otherOven ) {
		return( maxOvenSpace == otherOven.getMaxOvenSpace() );			
	}
	
	/**
	 * Returns the items in the oven
	 * 
	 * @return    The items in the oven
	 */
	public List<OrderedItem> getItemsCooking() {
		return itemsCooking;
	}
}
