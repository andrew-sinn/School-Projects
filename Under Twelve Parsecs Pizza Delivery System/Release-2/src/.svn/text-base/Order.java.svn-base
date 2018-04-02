/*
 * Order.java
 * 
 * Version:
 *   $Id: Order.java,v 1.1 2010/01/18 06:18:19 jso5733 Exp $
 *   
 * Revision:
 *   $Log: Order.java,v $
 *   Revision 1.1  2010/01/18 06:18:19  jso5733
 *   Initial revision
 */

import java.sql.*;
import java.util.*;

/**
 * Object to hold the data of an order of pizza
 */
public class Order implements Comparable<Order> {

	private Customer customerInfo;
	private List< OrderedItem > orderContents;
	private float orderCost;
	private Time orderTaken;
	private Time estimatedDelivery;
	private Time orderFinished;
	private boolean orderIsFinished = false;
	
	/**
	 * Constructor for the Order object
	 * 
	 * @param    customerInfo    Object that holds the orderer's information
	 * @param    orderContents    List of items the customer ordered.
	 */
	
	public Order( Customer customerInfo, ArrayList< OrderedItem > orderContents ) {
		if(customerInfo != null && orderContents != null)
		{
			this.customerInfo = customerInfo;
			this.orderContents = orderContents;
			for( int loop = 0; loop < orderContents.size(); loop++ ) {
				orderContents.get( loop ).setAssociatedOrder( this );
				if( orderContents.get( loop ).getPrepTimeOfItem() != 0 ) {
					PreparationKitchen.getInstance().addItem( orderContents.get( loop ) );
				} else if ( orderContents.get( loop ).getCookTimeOfItem() != 0 ) {
					OvenKitchen.getInstance().addItem( orderContents.get( loop ) );
				} else {
					orderContents.get( loop ).start();
				}
			}
		
			orderTaken = new Time( System.currentTimeMillis() );
			//Gets the cost of the order
			orderCost = 0;
			
			for( int loop = 0; loop < orderContents.size(); loop++ ) {
				orderCost += orderContents.get( loop ).getPriceOfItem();
			}
			
			
			
			//Gets the estimated time for delivery.
			int timeForDelivery = 0;
			
			for( int loop = 0; loop < orderContents.size(); loop++ ) {
				if( orderContents.get( loop ).getPrepTimeOfItem() + orderContents.get( loop ).getCookTimeOfItem() > timeForDelivery ) {
					timeForDelivery = orderContents.get( loop ).getPrepTimeOfItem() + orderContents.get( loop ).getCookTimeOfItem();
				}
			}
			
			timeForDelivery += customerInfo.getDelivLocation().getTimeToLocation();
			estimatedDelivery = new Time( System.currentTimeMillis() + 60000 * timeForDelivery );
			orderFinished = null;
			OrderHolder.getCurrentOrderHolder().addOrder( this );
		} else {
			this.customerInfo = null;
			this.orderContents = null;
			this.orderCost = 0;
			this.orderTaken = null;
			this.estimatedDelivery = null;
			this.orderFinished = null;
		}
	}

	/**
	 * Gets the estimated delivery time
	 * 
	 * @return    the estimated delivery time.
	 */
	
	public Time getEstimatedDelivery() {
		return( estimatedDelivery );
	}

	/**consumer should be c
	 * Sets the estimated time of delivery
	 * 
	 * @param    estimatedDelivery    The new estimated time of delivery
	 */
	
	public void setEstimatedDelivery(Time estimatedDelivery) {
		if(estimatedDelivery != null)
			this.estimatedDelivery = estimatedDelivery;
	}

	/**
	 * Gets the time the order was finished
	 * 
	 * @return    The time the order was finished
	 */
	
	public Time getOrderFinished() {
		return orderFinished;
	}

	/**
	 * Sets the time the order was finished
	 * 
	 * @param    orderFinished    The time the order was finished
	 */
	
	public void setOrderFinished(Time orderFinished) {
		if(orderFinished != null)
			this.orderFinished = orderFinished;
	}

	/**
	 * Gets the customer info of the orderer
	 * 
	 * @return    The customer info of the orderer
	 */
	
	public Customer getCustomerInfo() {
		return customerInfo;
	}

	/**
	 * Gets the items ordered
	 * 
	 * @return    The items ordered
	 */
	
	public List<OrderedItem> getOrderContents() {
		return orderContents;
	}

	/**
	 * Gets the cost of this order
	 * 
	 * @return    The cost of this order
	 */
	
	public float getOrderCost() {
		return orderCost;
	}

	/**
	 * Gets the time this order was taken
	 * 
	 * @return    The time this order was taken
	 */
	
	public Time getOrderTaken() {
		return orderTaken;
	}
	
	/**
	 * Gets the number of items in this order
	 * 
	 * @return    The number of items in this order
	 */
	
	public int getNumberOfItems() {
		return( orderContents.size() );
	}
	
	/**
	 * Add an item to the order
	 * 
	 * @param    newItem    New item to add to the order
	 */
	
	public void addItem( OrderedItem newItem ) {
		
		if(newItem != null)
		{
			newItem.setAssociatedOrder( this );
			orderContents.add( newItem );
			orderCost += newItem.getPriceOfItem();
		}
	}
	
	/**
	 * Removes an item from the order
	 * 
	 * @param    itemToRemove    Item to be removed from the order
	 */
	
	public void removeItem( OrderedItem itemToRemove ) {
		if(orderContents.contains(itemToRemove))
		{
			orderContents.remove( itemToRemove );
			orderCost = orderCost - itemToRemove.getPriceOfItem();
		}
		if( getNumberOfItems() == 0 ) {
			cancelOrder();
		}
	}
	
	/**
	 * Checks if the order is complete	
	 */
	
	public void checkComplete() {
		boolean complete = true;
		for( int loop = 0; loop < orderContents.size() && complete; loop++ ) {
			complete = complete && orderContents.get( loop ).percentDone() == 100;
		}
		if( complete ) {
			DeliverySystem.getDeliverySystem().addOrder( this );
			orderIsFinished = true;
		}
	}
	
	public boolean checkIfFinished() {
		checkComplete();
		return orderIsFinished;
	}
	
	/**
	 * Marks the order as completed
	 */
	
	public void finishOrder() {
		orderFinished = new Time( System.currentTimeMillis() );
		OrderHolder.getCurrentOrderHolder().removeOrder( this );
		ManagerReport.getCurrentManagerReport().addOrder( this );
	}
	
	/**
	 * Cancels an order
	 */
	
	public void cancelOrder() {
		OrderHolder.getCurrentOrderHolder().removeOrder( this );
		for( int loop = 0; loop < orderContents.size(); loop++ ) {
			orderContents.get( loop ).setAssociatedOrder( null );
			PreparationKitchen.getInstance().removeItem( orderContents.get( loop ) );
			OvenKitchen.getInstance().removeItem( orderContents.get( loop ) );
		}
	}
	
	/**
	 * Compares two orders
	 * 
	 * @param    otherOrder    The order to compare to
	 */
	
	public int compareTo( Order otherOrder ) {
		if( orderFinished.getTime() - otherOrder.getOrderFinished().getTime() > 0 ) {
			return( 1 );
		} else if( orderFinished.getTime() - otherOrder.getOrderFinished().getTime() < 0 ) {
			return( -1 );
		}
		return( 0 );
	}
}