/*
 * MenuItemStorage.java
 * 
 * Version:
 *   $Id$
 *   
 * Revision:
 *   $Log$
 */

import java.io.*;
import java.util.*;

/**
 * Data structure to hold a list of delivery location.
 * 
 * @author    Andrew James Whitcomb
 */

public class MenuItemStorage implements Serializable {

	//Class variables
	private static final long serialVersionUID = 1L;
	private static MenuItemStorage availableStorage;
	private Map< String, MenuItem > menuItemMap;
	private List< String > availableToppings;
	private Map< String, ArrayList<String> > toppingAssociationMap;
	private final static File SETTINGS = new File( "MenuItemList.settings" );
	public ArrayList toppingsAdded = new ArrayList();
	/**
	 * Constructor for the menu item storage
	 */
	
	private MenuItemStorage() {
		if( SETTINGS.exists()) {
			try {
				ObjectInputStream oos = new ObjectInputStream( new FileInputStream( SETTINGS ) );
				//MenuItemStorage other = (MenuItemStorage) oos.readObject();
				menuItemMap = (Map)oos.readObject();
				availableToppings = (List)oos.readObject();
				toppingAssociationMap = (Map)oos.readObject();
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			menuItemMap = new HashMap< String, MenuItem >();
			availableToppings = new ArrayList< String >();
			toppingAssociationMap = new HashMap< String, ArrayList< String > >();
		}
	}
	
	/**
	 * Gets the current CustomerStorage available
	 * 
	 * @return    An available MenuItemStorage
	 */
	
	public static MenuItemStorage getMenuItemStorage() {
		if( availableStorage == null ) {
			availableStorage = new MenuItemStorage();
		}
		return( availableStorage );
	}
	
	/**
	 * Kills the current instance of this class
	 */
	
	public static void killCurrentInstance() {
		availableStorage = null;
	}
	
	/**
	 * Kills all existing instances of this class
	 */
	
	public static void killInstances() {
		SETTINGS.delete();
		availableStorage = null;
	}
	
	/**
	 * Adds to the menu item map
	 * 
	 * @param    newMenuItem    Menu item to add
	 */
	
	public void addMenuItem( MenuItem newMenuItem ) {
		menuItemMap.put( newMenuItem.getName(), newMenuItem );
	}
	
	/**
	 * Removes a menu item from the map
	 * 
	 * @param    menuItemToRemove    Menu item to remove
	 */
	
	public void removeMenuItem( String menuItemToRemove ) {
		removeItemType(menuItemToRemove);
		menuItemMap.remove( menuItemToRemove );
	}
	
	/**
	 * Sees if a menu item exists
	 * 
	 * @param    menuItemName    Name of the menu item in question
	 * 
	 * @return    If there is a menu item with the given name
	 */
	
	public boolean menuItemExists( String menuItemName ) {
		return( menuItemMap.containsKey( menuItemName ) );
	}
	
	/**
	 * Gets a menu item location in the storage
	 * 
	 * @param    menuItemName    Name of the menu item in question
	 * 
	 * @return    The menu item associated with the given name
	 */
	
	public MenuItem getMenuItem( String menuItemName ) {
		return( menuItemMap.get( menuItemName ) );
	}
	
	/**
	 * Adds a topping to the available toppings
	 * 
	 * @param    newTopping    Topping to add
	 */
	
	public void addTopping( String newTopping ) {
		availableToppings.add( newTopping );
	}
	
	/**
	 * Removes a topping from the available toppings
	 * 
	 * @param    toppingToRemove    Topping to remove
	 */
	
	public void removeTopping( String toppingToRemove ) {
		ArrayList<String> menuItems = new ArrayList<String> (getMenuItemMap().keySet());
		for(int i = 0; i < menuItemMap.size(); i++) {
			removeAssociatingTopping( menuItems.get(i), toppingToRemove);
		}
		availableToppings.remove( toppingToRemove );
	}

	/**
	 * Sees if a topping exists in the system
	 * 
	 * @param    toppingName    Topping in question
	 * 
	 * @return    if the topping exists in the system
	 */
	
	public boolean toppingExists( String toppingName ) {
		return( availableToppings.contains( toppingName ) );
	}
	
	/**
	 * Gets available toppings
	 * 
	 * @return    The available toppings
	 */
	
	public List<String> getAvailableToppings() {
		return( availableToppings );
	}
	
	/**
	 * Makes a new item in the topping association
	 * 
	 * @param    newItem    New item to consider
	 * @param    associatedToppings    Toppings to associate with the item
	 */
	
	public void addNewItemAssociation( String newItem, ArrayList<String> associatedToppings ) {
		if( !toppingAssociationMap.containsKey( newItem ) ) {
			if( associatedToppings == null ) {
				toppingAssociationMap.put( newItem, new ArrayList<String>() );
			} else {
				toppingAssociationMap.put( newItem, associatedToppings );
			}
		}
	}
	
	/**
	 * Removes an item from the topping association
	 * 
	 * @param    itemToRemove    Item to remove
	 */
	
	public void removeItemType( String itemToRemove ) {
		toppingAssociationMap.remove( itemToRemove );
	}
	
	/**
	 * Adds a topping to an item
	 * 
	 * @param    itemName    Item in question
	 * @param    newTopping    Topping to associate with type
	 */
	
	public void addAssociatingTopping( String itemName, String newTopping ) {
		toppingAssociationMap.get( itemName ).add( newTopping );
	}
	
	/**
	 * Removes a topping from an item
	 * 
	 * @param    itemName    Item in question
	 * @param    toppingToRemove    Topping to remove from association
	 */
	
	public void removeAssociatingTopping( String itemName, String toppingToRemove ) {
		toppingAssociationMap.get( itemName ).remove( toppingToRemove );
	}
	
	/**
	 * Gets the associating toppings with a given item
	 *
	 * @param    itemName    Item type in question
	 * 
	 * @return    Associating toppings with a given item 
	 */
	
	public ArrayList< String > getAssociatingToppings( String itemName ) {
		return( toppingAssociationMap.get( itemName ) );
	}
	
	/**
	 * Gets the menu item map
	 */
	
	public Map< String, MenuItem > getMenuItemMap() {
		return( menuItemMap );
	}
	
	/**
	 * Gets the topping association map
	 */
	
	public Map< String, ArrayList<String> > getToppingAssociationMap() {
		return( toppingAssociationMap );
	}
	
	/**
	 * Saves the settings
	 */
	
	public void saveSettings() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream( new FileOutputStream( SETTINGS ) );
			oos.writeObject(menuItemMap  );
			oos.writeObject(availableToppings);
			oos.writeObject(toppingAssociationMap);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}

