/*
 * MenuItem.java
 * 
 * Version:
 *   $Id: MenuItem.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: MenuItem.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

import java.io.*;
import java.util.*;

/**
 * An item that is possible to order on the menu.
 * 
 * @author    Andrew James Whitcomb
 */

public class MenuItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private float basePrice;
	private int prepTime;
	private int cookTime;
	private int ovenSpaceTaken;
	private float wholeToppingCost;
	private boolean canHaveHalfToppings;
	
	/**
	 * Creation of a menu item
	 * 
	 * @param    name    Name of the item
	 * @param    basePrice    Price item is sold at.
	 * @param    prepTime    Time it takes an item to prepare.
	 * @param    cookTime    Time it takes an item to cook in an oven of cooking speed 1.
	 * @param    ovenSpaceTaken    Amount of space it takes in the oven.
	 * @param    wholeToppingCost    Cost to put a topping over the whole item
	 * @param    canHaveHalfToppings    Sees if the item can be put over the whole item
	 * @param    associatedToppings    Says what toppings can go on this item
	 */
	
	MenuItem( String name, float basePrice, int prepTime, int cookTime, int ovenSpaceTaken, float wholeToppingCost, boolean canHaveHalfToppings, ArrayList<String> associatedToppings ) {
		if( name != null && basePrice >= 0 && prepTime >=0 && cookTime >= 0 && ovenSpaceTaken >= 0 && wholeToppingCost >= 0 ) {
			if( name != null ) {
				this.name = name;
				this.basePrice = basePrice;
				this.prepTime = prepTime;
				this.cookTime = cookTime;
				this.ovenSpaceTaken = ovenSpaceTaken;
				this.wholeToppingCost = wholeToppingCost;
				this.canHaveHalfToppings = canHaveHalfToppings;
				MenuItemStorage.getMenuItemStorage().addMenuItem( this );
				MenuItemStorage.getMenuItemStorage().addNewItemAssociation( name, associatedToppings );
			} else {
				this.name = null;
				this.basePrice = (float)-1;
				this.prepTime = -1;
				this.cookTime = -1;
				this.ovenSpaceTaken = -1;
				this.wholeToppingCost = (float)-1;
			}
		} else {
			this.name = null;
			this.basePrice = (float)-1;
			this.prepTime = -1;
			this.cookTime = -1;
			this.ovenSpaceTaken = -1;
			this.wholeToppingCost = (float)-1;
		}
	}

	/**
	 * Returns the name of the item.
	 * 
	 * @return    the name of the item.
	 */
	
	public String getName() {
		return name;
	}

	/**
	 * Gets the price of the item
	 * 
	 * @return    the price of the item
	 */
	
	public float getBasePrice() {
		return basePrice;
	}

	/**
	 * Sets the price of the item
	 * 
	 * @param    basePrice    the new price of the item 
	 */
	
	public void setBasePrice( float basePrice ) {
		if(basePrice > 0)
			this.basePrice = basePrice;
	}

	/**
	 * Gets the amount of time it takes to prepare the item (in minutes)
	 * 
	 * @return    the amount of time it takes to cook the item.
	 */
	
	public int getPrepTime() {
		return prepTime;
	}
	
	/**
	 * Sets the amount of time it takes to prepare the item (in minutes)
	 * 
	 * @param    prepTime    The new amount of time it takes to prepare the item.
	 */

	public void setPrepTime( int prepTime ) {
		if(prepTime >= 0)
			this.prepTime = prepTime;
	}
	
	/**
	 * Gets the amount of time the item takes to cook in an oven of cooking speed 1 (in minutes)
	 * 
	 * @return    the amount of time the item takes to cook in an oven of cooking speed 1 (in minutes)
	 */

	public int getCookTime() {
		return cookTime;
	}

	/**
	 * Sets the amount of time the item takes to cook in an oven of cooking speed 1 (in minutes)
	 * 
	 * @param    cookTime    the new amount of time the item takes to cook in an oven of cooking speed 1 (in minutes)
	 */
	
	public void setCookTime( int cookTime ) {
		if(cookTime >= 0)	
			this.cookTime = cookTime;
	}

	/**
	 * Gets the amount of space in an oven an item takes up
	 * 
	 * @return    the amount of space in an oven the item takes up 
	 */
	
	public int getOvenSpaceTaken() {
		return ovenSpaceTaken;
	}

	/**
	 * Sets the amount of space in an oven an item takes up
	 * 
	 * @param    ovenSpaceTaken    the new amount of space in an oven the item 
	 */
	
	public void setOvenSpaceTaken( int ovenSpaceTaken ) {
		if(ovenSpaceTaken >= 0)
			this.ovenSpaceTaken = ovenSpaceTaken;
	}
	
	/**
	 * Gets the cost to put a topping over the whole item
	 * 
	 * @return    the cost to put a topping over the whole item
	 */
	
	public float getWholeToppingCost() {
		return wholeToppingCost;
	}
	
	/**
	 * Sets the cost to put a whole topping over the whole item
	 * 
	 * @param    wholeToppingCost    the new cost to put a whole topping over the whole item
	 */

	public void setWholeToppingCost(float wholeToppingCost) {
		if(wholeToppingCost >= 0)	
			this.wholeToppingCost = wholeToppingCost;
	}

	/**
	 * Sees if the item can have toppings covering half the item
	 * 
	 * @return    if the item can have toppings covering half the item
	 */
	
	public boolean isCanHaveHalfToppings() {
		return canHaveHalfToppings;
	}

	/**
	 * Sets if the item can have toppings covering half the item
	 * 
	 * @param    canHaveHalfToppings    if the item can have toppings covering half the item now
	 */
	
	public void setCanHaveHalfToppings(boolean canHaveHalfToppings) {
		this.canHaveHalfToppings = canHaveHalfToppings;
	}

}
