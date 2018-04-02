import junit.framework.TestCase;
import java.io.*;
import java.util.*;

public class PizzaDeliverySystemTest extends TestCase {

	public void testDeliveryLocationCreation() {
		DeliveryLocationStorage.killInstances();
		DeliveryLocation dl = new DeliveryLocation( "RIT", 26 );
		assertTrue( "The DeliveryLocationStorage does not have the RIT delivery location.", DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocationMap().values().contains( dl ) );
	}
	
	public void testDeliveryLocationBadCreation() {
		testDeliveryLocationCreation();
		DeliveryLocation dl = new DeliveryLocation( null, 26 );
		DeliveryLocation dl2 = new DeliveryLocation( "RPI", -1 );
		DeliveryLocation dl3 = new DeliveryLocation( "RIT", 28 );
		assertFalse( "The bad created delivery locations should not be in the DeliveryLocationStorage", DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocationMap().values().contains( dl ) );
		assertFalse( "The bad created delivery locations should not be in the DeliveryLocationStorage", DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocationMap().values().contains( dl2 ) );
		assertFalse( "The bad created delivery locations should not be in the DeliveryLocationStorage", DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocationMap().values().contains( dl3 ) );
	}
	
	public void testDeliveryLocationExists() {
		testDeliveryLocationCreation();
		DeliveryLocation dl = DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocation( "RIT" );
		assertTrue( "The DeliveryLocationStorage does not have the RIT delivery location.", dl != null );
		assertTrue( "dl should be named RIT and have a tmie fo 26 minutes", dl.getLocationName().equals( "RIT" ) && dl.getTimeToLocation() == 26 );
	}
	
	public void testDeliveryLocationDoesNotExist() {
		assertFalse( "The DeliveryLocationStorage has a location called RPI", DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocation( "RPI" ) != null );
	}
	
	public void testContainsDeliveryLocation() {
		testDeliveryLocationCreation();
		assertTrue( "RIT should be contained in the map", DeliveryLocationStorage.getDeliveryLocationStorage().deliveryLocationExists( "RIT" ) );
	}
	
	public void testDoesNotContainDeliveryLocation() {
		assertFalse( "RPI should not be contained in the map", DeliveryLocationStorage.getDeliveryLocationStorage().deliveryLocationExists( "RPI" ) );
	}
	
	public void testRemoveALocation() {
		testDeliveryLocationCreation();
		DeliveryLocationStorage.getDeliveryLocationStorage().removeDeliveryLocation( "RIT" );
		assertTrue( "The DeliveryLocationStorage map should be empty", DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocationMap().isEmpty() );
	}
	
	public void testSavingDeliverySettings() {
		testDeliveryLocationCreation();
		DeliveryLocationStorage.getDeliveryLocationStorage().saveSettings();
		File fileThatShouldExist = new File( "DeliveryLocationList.settings" );
		assertTrue( "There should be a file that exists called DeliveryLocationList.settings", fileThatShouldExist.exists() );
	}
	
	public void testLoadingDeliverySettings() {
		testSavingDeliverySettings();
		DeliveryLocationStorage.killCurrentInstance();
		assertTrue( "After reloading the settings, the RIT location should still exist", DeliveryLocationStorage.getDeliveryLocationStorage().deliveryLocationExists( "RIT" ) );
	}
	
	public void testSetDrivers() {
		DeliverySystem.killInstances();
		DeliverySystem.getDeliverySystem().setDrivers( 2 );
		assertTrue( "The number of drivers should be 2", DeliverySystem.getDeliverySystem().getNumDrivers() == 2 );
	}
	
	public void testSetBadDrivers() {
		testSetDrivers();
		DeliverySystem.getDeliverySystem().setDrivers( -1 );
		assertTrue( "The number of drivers should be 2", DeliverySystem.getDeliverySystem().getNumDrivers() == 2 );
	}
	
	public void testDriverSaving() {
		testSetDrivers();
		DeliverySystem.getDeliverySystem().saveSettings();
		File fileThatShouldExist = new File( "DeliverySystem.settings" );
		assertTrue( "There should be a file that exists called DeliverySystem.settings", fileThatShouldExist.exists() );
	}
	
	public void testDriverLoading() {
		testDriverSaving();
		DeliverySystem.killCurrentInstance();
		assertTrue( "The number of drivers should be 2", DeliverySystem.getDeliverySystem().getNumDrivers() == 2 );
	}
	
	public void testEmployeeCreation() {
		EmployeeStorage.killInstances();
		Employee e = new Employee( "Case", "Test", "Employee", "Password", false );
		assertTrue( "The EmployeeStorage does not have the current employee", EmployeeStorage.getEmployeeStorage().getEmployeeMap().values().contains( e ) );
	}
	
	public void testBadEmployeeCreation() {
		testEmployeeCreation();
		Employee e = new Employee( "Case", "Test", "Employee", "Password", false );
		Employee e2 = new Employee( null, "Test", "Employee", "Password", false );
		Employee e3 = new Employee( "Case1", null, "Employee", "Password", false );
		Employee e4 = new Employee( "Case2", "Test", null, "Password", false );
		Employee e5 = new Employee( "Case3", "Test", "Employee", null , false );
		assertFalse( "The bad created employees should not be in the EmployeeStorage", EmployeeStorage.getEmployeeStorage().getEmployeeMap().values().contains( e ) );
		assertFalse( "The bad created employees should not be in the EmployeeStorage", EmployeeStorage.getEmployeeStorage().getEmployeeMap().values().contains( e2 ) );
		assertFalse( "The bad created employees should not be in the EmployeeStorage", EmployeeStorage.getEmployeeStorage().getEmployeeMap().values().contains( e3 ) );
		assertFalse( "The bad created employees should not be in the EmployeeStorage", EmployeeStorage.getEmployeeStorage().getEmployeeMap().values().contains( e4 ) );
		assertFalse( "The bad created employees should not be in the EmployeeStorage", EmployeeStorage.getEmployeeStorage().getEmployeeMap().values().contains( e5 ) );
	}
	
	public void testEmployeeExists() {
		testEmployeeCreation();
		Employee e = EmployeeStorage.getEmployeeStorage().getEmployee( "Case" );
		assertTrue( "The already existing employee was not in the storage", e != null );
		assertTrue( "e should have a last name \"Test\"", e.getLastName().equals( "Test" ) );
		assertTrue( "e should have a first name \"Employee\"", e.getFirstName().equals( "Employee" ) );
		assertTrue( "e should have a password \"Password\"", e.getPassword().equals( "Password" ) );
		assertTrue( "e should have not have manager status", !e.isManagerStatus() );
	}
	
	public void testEmployeeDoesNotExist() {
		Employee e = EmployeeStorage.getEmployeeStorage().getEmployee( "Case10" );
		assertFalse( "e should be null", e != null );
	}
	
	public void testContainsEmployee() {
		testEmployeeCreation();
		assertTrue( "The employee storage should contain an employee with the user ID \"Case\"", EmployeeStorage.getEmployeeStorage().employeeExists( "Case" ) );
	}
	
	public void testDoesNotContainEmployee() {
		assertFalse( "The employee storage should not contain an employee with the user ID \"Case10\"", EmployeeStorage.getEmployeeStorage().employeeExists( "Case10" ) );
	}
	
	public void testRemoveEmployee() {
		testEmployeeCreation();
		EmployeeStorage.getEmployeeStorage().removeEmployee( "Case" );
		assertTrue( "The employee storage map should be empty", EmployeeStorage.getEmployeeStorage().getEmployeeMap().isEmpty() );
	}
	
	public void testSaveEmployeeSettings() {
		testEmployeeCreation();
		EmployeeStorage.getEmployeeStorage().saveSettings();
		File fileThatShouldExist = new File( "EmployeeList.settings" );
		assertTrue( "The settings file was not saved for the EmployeeStorage", fileThatShouldExist.exists() );
	}
	
	public void testLoadEmployeeSettings() {
		testSaveEmployeeSettings();
		EmployeeStorage.killCurrentInstance();
		assertTrue( "The loaded Employee Settings should contain an employee user ID of \"Case\"", EmployeeStorage.getEmployeeStorage().employeeExists( "Case" ) );
	}
	
	public void testEmployeeChanges() {
		testEmployeeCreation();
		Employee e = EmployeeStorage.getEmployeeStorage().getEmployee( "Case" );
		e.setFirstName( "Testing" );
		e.setLastName( "Testing" );
		e.setPassword( "Testing" );
		e.setManagerStatus( true );
		assertTrue( "e has a first name of \"Testing\"", e.getFirstName().equals( "Testing" ) );
		assertTrue( "e has a last name of \"Testing\"", e.getLastName().equals( "Testing" ) );
		assertTrue( "e has a password of \"Testing\"", e.getPassword().equals( "Testing" ) );
		assertTrue( "e has manager status", e.isManagerStatus() );
	}
	
	public void testAddTopping() {
		MenuItemStorage.killInstances();
		MenuItemStorage.getMenuItemStorage().addTopping( "Bacon" );
		assertTrue( "The menu item storage does not have Bacon in it", MenuItemStorage.getMenuItemStorage().getAvailableToppings().contains( "Bacon" ) );
	}
	
	public void testAddMenuItem() { 
		testAddTopping();
		ArrayList<String> associatedToppings = new ArrayList<String>();
		associatedToppings.add( "Bacon" );
		MenuItem mi = new MenuItem( "Pizza", (float)10, 5, 10, 4, (float)1.5, true, associatedToppings );
		assertTrue( "Pizza does not exist in the Menu Item Storage", MenuItemStorage.getMenuItemStorage().getMenuItemMap().values().contains( mi ) );
		assertTrue( "Pizza does not have an association with bacon", MenuItemStorage.getMenuItemStorage().getAssociatingToppings("Pizza").contains( "Bacon" ) );
	}
	
	public void testAddBadMenuItem() {
		testAddMenuItem();
		MenuItem mi = new MenuItem( "Pizza", (float)11, 6, 11, 5, (float)2.5, true, null );
		MenuItem mi2 = new MenuItem( "Pizza2", (float)-10, 5, 10, 4, (float)1.5, true, null );
		MenuItem mi3 = new MenuItem( "Pizza2", (float)10, -5, 10, 4, (float)1.5, true, null );
		MenuItem mi4 = new MenuItem( "Pizza2", (float)10, 5, -10, 4, (float)1.5, true, null );
		MenuItem mi5 = new MenuItem( "Pizza2", (float)10, 5, 10, -4, (float)1.5, true, null );
		MenuItem mi6 = new MenuItem( "Pizza2", (float)10, 5, 10, 4, (float)-1.5, true, null );
		assertFalse( "The bad created menu items should not be in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().getMenuItemMap().values().contains( mi ) );
		assertFalse( "The bad created menu items should not be in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().getMenuItemMap().values().contains( mi2 ) );
		assertFalse( "The bad created menu items should not be in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().getMenuItemMap().values().contains( mi3 ) );
		assertFalse( "The bad created menu items should not be in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().getMenuItemMap().values().contains( mi4 ) );
		assertFalse( "The bad created menu items should not be in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().getMenuItemMap().values().contains( mi5 ) );
		assertFalse( "The bad created menu items should not be in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().getMenuItemMap().values().contains( mi6 ) );
	}
	
	public void testAddNewTopping() {
		testAddMenuItem();
		MenuItemStorage.getMenuItemStorage().addTopping( "Pepperoni" );
		MenuItemStorage.getMenuItemStorage().addAssociatingTopping( "Pizza", "Pepperoni" );
		assertTrue( "Pepperoni was not added to the Menu Item Storage", MenuItemStorage.getMenuItemStorage().getAvailableToppings().contains( "Pepperoni" ) );
		assertTrue( "Pepperoni is not associated with Pizza", MenuItemStorage.getMenuItemStorage().getAssociatingToppings( "Pizza" ).contains( "Pepperoni" ) );
	}
	
	public void testRemoveMenuItem() {
		testAddMenuItem();
		MenuItemStorage.getMenuItemStorage().removeMenuItem( "Pizza" );
		assertFalse( "Pizza still exists in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().getMenuItemMap().keySet().contains( "Pizza" ) );
		assertFalse( "Pizza's Associated Topping List still exists", MenuItemStorage.getMenuItemStorage().getAssociatingToppings( "Pizza" ) != null );
	}
	
	public void testRemoveTopping() {
		testAddNewTopping();
		MenuItemStorage.getMenuItemStorage().removeTopping( "Pepperoni" );
		assertFalse( "Pepperoni still exists as a topping", MenuItemStorage.getMenuItemStorage().getAvailableToppings().contains( "Pepperoni" ) );
		assertFalse( "Pepperoni is still associated with Pizza", MenuItemStorage.getMenuItemStorage().getAssociatingToppings( "Pizza" ).contains( "Pepperoni" ) );
	}
	
	public void testContainsMenuItem() {
		testAddMenuItem();
		assertTrue( "Pizza does not exist in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().menuItemExists( "Pizza" ) );
	}
	
	public void testDoesNotContainMenuItem() {
		assertFalse( "Pizza Logs does not exist in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().menuItemExists( "Pizza Logs" ) );
	}
	
	public void testContainsTopping() {
		testAddTopping();
		assertTrue( "Bacon does not exist in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().toppingExists( "Bacon" ) );
	}
	
	public void testDoesNotContainTopping() {
		assertFalse( "Pepperoni does not exist in the MenuItemStorage", MenuItemStorage.getMenuItemStorage().toppingExists( "Pepperoni" ) );
	}
	
	public void testIfAssociatedTopping() {
		testAddMenuItem();
		assertTrue( "Bacon is not associated with Pizza", MenuItemStorage.getMenuItemStorage().getAssociatingToppings( "Pizza" ).contains( "Bacon" ) );
	}
	
	public void testIfNotAssociatedTopping() {
		assertFalse( "Sausage is not associated with Pizza", MenuItemStorage.getMenuItemStorage().getAssociatingToppings( "Pizza" ).contains( "Sausage" ) );
	}
	
	public void testSavingMenuItemSettings() {
		testAddMenuItem();
		MenuItemStorage.getMenuItemStorage().saveSettings();
		File fileThatShouldExist = new File( "MenuItemList.settings" );
		assertTrue( "The settings file was not saved for the MenuItemStorage", fileThatShouldExist.exists() );
	}
	
	public void testLoadingMenuItemSettings() {
		testSavingMenuItemSettings();
		MenuItemStorage.killCurrentInstance();
		assertTrue( "The loaded menu item settings should contain a menu item with name Pizza", MenuItemStorage.getMenuItemStorage().menuItemExists( "Pizza" ) );
		assertTrue( "The loaded menu item settings should contain a topping with the name Bacon", MenuItemStorage.getMenuItemStorage().toppingExists( "Bacon" ) );
		assertTrue( "The loaded menu item settings should know Bacon is associated with Pizza", MenuItemStorage.getMenuItemStorage().getAssociatingToppings( "Pizza" ).contains( "Bacon" ) );
	}
	
	public void testOvenCreation() {
		OvenKitchen.killInstances();
		Oven o = new Oven( 10 );
		assertTrue( "The oven kitchen does not have the new oven", OvenKitchen.getInstance().getCookingOvens().contains( o ) );
	}
	
	public void testSaveOvenKitchenSettings() {
		testOvenCreation();
		OvenKitchen.getInstance().saveSettings();
		File fileThatShouldExist = new File( "OvenKitchen.settings" );
		assertTrue( "The settings file was not saved for the OvenKitchen", fileThatShouldExist.exists() );
	}
	
	public void testLoadOvenKitchenSettings() {
		testSaveOvenKitchenSettings();
		OvenKitchen.killCurrentInstance();
		assertTrue( "The loaded oven kitchen settings should contain an oven of size 10", OvenKitchen.getInstance().getCookingOvens().get( 0 ).getMaxOvenSpace() == 10 );
	}
	
	public void testOvenRemoval() {
		testOvenCreation();
		OvenKitchen.getInstance().removeOven( OvenKitchen.getInstance().getCookingOvens().get( 0 ) );
		assertTrue( "The Oven Kitchen still has ovens", OvenKitchen.getInstance().getCookingOvens().isEmpty() );
	}
	
	public void testSetNumberCooks() {
		PreparationKitchen.killInstances();
		PreparationKitchen.getInstance().setCooks( 2 );
		assertTrue( "The number of cooks is not equal to 2", PreparationKitchen.getInstance().getNumCooks() == 2 );
	}
	
	public void testSavePrepKitchenSettings() {
		testSetNumberCooks();
		PreparationKitchen.getInstance().saveSettings();
		File fileThatShouldExist = new File( "PreparationKitchen.settings" );
		assertTrue( "The settings file was not saved for the PreparationKitchen", fileThatShouldExist.exists() );
	}
	
	public void testLoadPrepKitchenSettings() {
		testSavePrepKitchenSettings();
		PreparationKitchen.killCurrentInstance();
		assertTrue( "The loaded prep kitchen settings should contain 2 cooks", PreparationKitchen.getInstance().getNumCooks() == 2 );
	}
	
	public void testCustomerCreation() {
		CustomerStorage.killInstances();
		testDeliveryLocationCreation();
		Customer c = new Customer( 5558675309L, "Rivings", "Rose", DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocation( "RIT" ) );
		assertTrue( "The CustomerStorage does not contain the newly created customer", CustomerStorage.getCustomerStorage().getCustomer( 5558675309L ).equals( c ) );
	}
	
	public void testBadCustomerCreation() {
		testCustomerCreation();
		Customer c = new Customer( 5558675309L, "Dodaga", "Tanya", DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocation( "RIT" ) );
		Customer c2 = new Customer( -1, "Rivings", "Rose", DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocation( "RIT" ) );
		Customer c3 = new Customer( 5559675319L, null, "Rose", DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocation( "RIT" ) );
		Customer c4 = new Customer( 5559675329L, "Rivings", null, DeliveryLocationStorage.getDeliveryLocationStorage().getDeliveryLocation( "RIT" ) );
		Customer c5 = new Customer( 5559675339L, "Rivings", "Rose", null );
		assertFalse( "The bad customers were stored in the CustomerStorage", CustomerStorage.getCustomerStorage().getCustomerMap().containsValue( c ) );
		assertFalse( "The bad customers were stored in the CustomerStorage", CustomerStorage.getCustomerStorage().getCustomerMap().containsValue( c2 ) );
		assertFalse( "The bad customers were stored in the CustomerStorage", CustomerStorage.getCustomerStorage().getCustomerMap().containsValue( c3 ) );
		assertFalse( "The bad customers were stored in the CustomerStorage", CustomerStorage.getCustomerStorage().getCustomerMap().containsValue( c4 ) );
		assertFalse( "The bad customers were stored in the CustomerStorage", CustomerStorage.getCustomerStorage().getCustomerMap().containsValue( c5 ) );
	}
	
	public void testCustomerExists() {
		testCustomerCreation();
		assertTrue( "The CustomerStorage does not properly return a customer", CustomerStorage.getCustomerStorage().getCustomer( 5558675309L ) != null );
	}
	
	public void testCustomerDoesNotExist() {
		testCustomerCreation();
		assertFalse( "The CustomerStorage is running a customer that is not null", CustomerStorage.getCustomerStorage().getCustomer( 5558675319L ) != null );
	}
	
	public void testContainsCustomer() {
		testCustomerCreation();
		assertTrue( "The CustomerStorage is saying the customer does not exist", CustomerStorage.getCustomerStorage().customerExists( 5558675309L ) );
	}
	
	public void testDoesNotContainCustomer() {
		testCustomerCreation();
		assertFalse( "The CustomerStorage is claiming that a customer that does not exist exists", CustomerStorage.getCustomerStorage().customerExists( 5558675319L ) );
	}
	
	public void testSaveCustomerSettings() {
		testCustomerCreation();
		CustomerStorage.getCustomerStorage().saveSettings();
		File fileThatShouldExist = new File( "CustomerList.settings" );
		assertTrue( "The settings file was not saved for the CustomerStorage", fileThatShouldExist.exists() );
	}
	
	public void testLoadCustomerSettings() {
		testSaveCustomerSettings();
		CustomerStorage.killCurrentInstance();
		assertTrue( "The CustomerSettings did not save the customer", CustomerStorage.getCustomerStorage().getCustomer( 5558675309L ) != null );
	}
	
	public void testOrderedItemCreation() {
		testAddMenuItem();
		ArrayList<String> lhs = new ArrayList<String>();
		lhs.add( "Bacon" );
		OrderedItem oi = new OrderedItem( "Pizza", null, lhs, null );
		assertTrue( "The OrderedItem has an incorrect parameter", oi.getItemName().equals( "Pizza" ) );
		assertTrue( "The OrderedItem has an incorrect parameter", oi.getAssociatedOrder() == null );
		assertTrue( "The OrderedItem has an incorrect parameter", oi.getLeftToppings().equals( lhs ) );
		assertTrue( "The OrderedItem has an incorrect parameter", oi.getRightToppings().isEmpty() );
	}
	
	public void testBadOrderedItemCreation() {
		testAddMenuItem();
		OrderedItem oi = new OrderedItem( null, null, null, null );
		OrderedItem oi2 = new OrderedItem( "Bacon", null, null, null );
		assertTrue( "The OrderedItems initalized properly", oi.getLeftToppings() == null );
		assertTrue( "The OrderedItems initalized properly", oi2.getLeftToppings() == null );
	}
	
	public void testCreateOrder() {
		testCustomerCreation();
		testAddMenuItem();
		ArrayList<String> lhs = new ArrayList<String>();
		ArrayList<OrderedItem> orderContents = new ArrayList<OrderedItem>();
		lhs.add( "Bacon" );
		OrderedItem oi = new OrderedItem( "Pizza", null, lhs, null );
		orderContents.add( oi );
		Order o = new Order( CustomerStorage.getCustomerStorage().getCustomer( 5558675309L ), orderContents );		
		assertTrue( "The Order was not properly created", OrderHolder.getCurrentOrderHolder().getCurrentOrders().contains( o ) );
	}
}
