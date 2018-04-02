/*
 * OrderedItem.java
 * 
 * Version:
 *   $Id: OrderedItem.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: OrderedItem.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

import java.util.*;


/**
 * An item that was ordered 
 * 
 * @author    Andrew James Whitcomb
 * @coauthor  Jeffrey O'Connell
 * 
 */

public class OrderedItem extends Thread {

	//Class variable
	private String name;
	private Order associatedOrder;
	private Oven associatedOven;
	private int percentDone;
	private List<String> leftSideToppings;
	private List<String> rightSideToppings;
	
	/**
	 * Constructor for an OrderedItem that takes the name of an item
	 * 
	 * @param    name    Name of the ordered item
	 * @param    associatedOrder    The order this item belongs to
	 * @param    leftSideToppings    Toppings this item has on it's left side
	 * @param    rightSideToppings    Toppings this item has on it's right side
	 */
	
	public OrderedItem( String name, Order associatedOrder, List<String> leftSideToppings, List<String> rightSideToppings ) {
		if(name != null && MenuItemStorage.getMenuItemStorage().menuItemExists(name)) {
			this.name = name;
			this.associatedOrder = associatedOrder;
			if(leftSideToppings != null)
				this.leftSideToppings = leftSideToppings;
			else
				this.leftSideToppings = new ArrayList<String>();
			if(rightSideToppings != null)
				this.rightSideToppings = rightSideToppings;
			else
				this.rightSideToppings = new ArrayList<String>();
		} else {
			this.name = null;
			this.associatedOrder = null;
			this.leftSideToppings = null;
			this.rightSideToppings = null;
		}		
		
		associatedOven = null;
		percentDone = 0;
		if(associatedOrder != null) {
			associatedOrder.checkComplete();
		}
	}
		
	/**
	 * Gets the name of the item ordered
	 * 
	 * @return    The name of the ordered item
	 */
	
	public String getItemName() {
		return name;
	}
	
	/**
	 * Gets the percent cooked of this item
	 * 
	 * @return    the percent cooked of this item
	 */
	
	public int percentDone() {
		return( percentDone );
	}

	/**
	 * Gets the price of the item
	 * 
	 * @return    the price of the item
	 */	
	
	public float getPriceOfItem() {
		
		float priceOfItem = MenuItemStorage.getMenuItemStorage().getMenuItem( name ).getBasePrice();
		
		if( leftSideToppings != null ) {
			priceOfItem += (MenuItemStorage.getMenuItemStorage().getMenuItem( name ).getWholeToppingCost() / 2) * leftSideToppings.size();
		}
		if( rightSideToppings != null ) {
			priceOfItem += (MenuItemStorage.getMenuItemStorage().getMenuItem( name ).getWholeToppingCost() / 2) * rightSideToppings.size();
		}
		
		//Rounds down to the nearest penny
		priceOfItem = (float) ( ( (float) priceOfItem * 100 ) / 100 );
		return( priceOfItem );
		
	}

	/**
	 * Gets the preparation time of the item
	 * 
	 * @return    the preparation time of the item
	 */
	
	public int getPrepTimeOfItem() {
		
		return( MenuItemStorage.getMenuItemStorage().getMenuItem( name ).getPrepTime() );
		
	}
	
	/**
	 * Gets the cook time of the item
	 * 
	 * @return    the cook time of the item
	 */
	
	public int getCookTimeOfItem() {
		
		return( MenuItemStorage.getMenuItemStorage().getMenuItem( name ).getCookTime() );

	}
	
	/**
	 * Gets the space an item takes in the oven.
	 * 
	 * @return    the space the item takes in the oven
	 */
	
	public int getOvenSpaceRequired() {
		
		return( MenuItemStorage.getMenuItemStorage().getMenuItem( name ).getOvenSpaceTaken() );
		
	}
	
	/**
	 * Sets the associated oven with this item (if appropriate)
	 * 
	 * @param    the oven this item is being put in.
	 */
	
	public void setAssociatedOven( Oven associatedOven ) {
	
		this.associatedOven = associatedOven; 
		
	}
	
	/**
	 * Gets the associated order with this item
	 * 
	 * @return    the associated order with this item
	 */
	
	public Order getAssociatedOrder() {
		return( associatedOrder );
	}
	
	/**
	 * Sets the associated order with this item
	 * 
	 * @param    the associated order with this item
	 */
	
	public void setAssociatedOrder(Order associatedOrder) {
		this.associatedOrder = associatedOrder;
		//associatedOrder.checkComplete();
	}
	
	/**
	 * Stops the thread
	 */
	
	public void setLeftToppings(ArrayList<String> leftToppings){
		leftSideToppings = leftToppings;
	}
	
	public void setRightToppings(ArrayList<String> rightToppings){
		rightSideToppings = rightToppings;
	}
	
	public void kill()
	{
		percentDone = 101;
	}
	
	/**
	 * Run operation of this thread
	 */
	
	public void run() {
		synchronized( this ) {
			if( getCookTimeOfItem() != 0 ) {
				while( percentDone < 100 ) {
					try {
						wait( (int) ( 600 * getCookTimeOfItem() ) );
					} catch (InterruptedException e) {} 
					percentDone++;
				}
				associatedOven.removeItem( this );
				associatedOven = null;
			} else {
				percentDone = 100;
			}
			
			if( associatedOrder != null ) {
				associatedOrder.checkComplete();
			}
		}
	}
	
	/**
	 * Add list of toppings to the left side of the item
	 * 
	 * @param    lsToppings    Toppings to add to the left side
	 */
	
	public void addLeftToppings( List<String> lsToppings ) {
		List<String> legals = new ArrayList<String>(); 
		for(int i = 0; i < lsToppings.size(); i++)
		{
			if(lsToppings != null && MenuItemStorage.getMenuItemStorage().getAssociatingToppings( name ).contains(lsToppings.get(i)))
				legals.add(lsToppings.get(i));
		}	
		if(!legals.isEmpty())
			leftSideToppings.addAll(legals);
	}
	
	/**
	 * Add list of toppings to the right side of the item
	 * 
	 * @param    rsToppings    Toppings to add to the right side
	 */
	
	public void addRightToppings( List<String> rsToppings ) {
		List<String> legals = new ArrayList<String>(); 
		for(int i = 0; i < rsToppings.size(); i++)
		{
			if(rsToppings != null && MenuItemStorage.getMenuItemStorage().getAssociatingToppings( name ).contains(rsToppings.get(i)))
				legals.add(rsToppings.get(i));
		}	
		if(!legals.isEmpty())
			rightSideToppings.addAll(legals);
	}
	
	/**
	 * Add a topping to the left side of the item
	 * 
	 * @param    topping Topping to add to the left side
	 */
	
	public void addLeftTopping( String topping ) {
		if(topping != null && MenuItemStorage.getMenuItemStorage().getAssociatingToppings( name ).contains(topping))
			leftSideToppings.add( topping );
	}
	
	/**
	 * Add a topping to the right side of the item
	 * 
	 * @param    topping    Topping to add to the right side
	 */
	
	public void addRightTopping( String topping) {
		if(topping != null && MenuItemStorage.getMenuItemStorage().getAssociatingToppings( name ).contains(topping))
			rightSideToppings.add( topping );
	}
	
	
	/**
	 * Get list of toppings to the left side of the item
	 * 
	 * @return    Toppings on the left side
	 */
	
	public List<String> getLeftToppings() {
		return leftSideToppings;
	}
	
	/**
	 * Get list of toppings to the Right side of the item
	 * 
	 * @return    Toppings on the Right side
	 */
	
	public List<String> getRightToppings() {
		return rightSideToppings;
	}
	
	/**
	 * Add list of toppings to the whole item
	 * 
	 * @param    wholeToppings    Toppings to add to the whole item
	 */
	
	public void addWholeToppings( List<String> wholeToppings ) {	
		addLeftToppings( wholeToppings );
		addRightToppings( wholeToppings );
	}

}
