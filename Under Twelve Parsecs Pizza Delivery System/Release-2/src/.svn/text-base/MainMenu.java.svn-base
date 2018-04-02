/**
 * 
 */

/**
 * @author Matthew Talbot
 *
 */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MainMenu implements ActionListener, ListSelectionListener{
	
	/* GUI Components */
	private JFrame win;
	private JTabbedPane tabPane;
	private JPanel createPanel, editPanel, kitchenPanel, reportPanel;
	private JLabel customerLBL, firstLBL, lastLBL, prepLBL, ovenLBL, deliveryLBL, priceLBL, topCostLBL, totalLBL, 
		prepViewLBL, averageLBL, averageTimeLBL, maxTimeLBL, totalReportLBL;
	private JButton okBUT, doneBUT, addBUT, createRemoveItemBUT, createRemoveToppingBUT, saveBUT, logoutBUT,
		cancelOrderBUT, addItemBUT, editRemoveItemBUT, addToppingBUT, refreshBUT, refreshReportBUT;
	private JComboBox locationCB, itemCB, cooksCB, ovensCB, driversCB;
	private JTextField customerTF, firstTF, lastTF, priceTF, topCostTF, totalTF, averageTF, averageTimeTF, maxTF,
		totalReportTF;
	private JTextArea employeesTA;
	private JScrollPane orderItemSP, orderToppingSP, ordersSP,itemsSP,toppingsSP,ovenSP,driversSP;
	private JList orderItemList, orderToppingList, currentOrderList, itemsList, toppingsList, ovenList, driverList;
	private JProgressBar prepProgress, ovenProgress, deliveryProgress;
	/* Storage Objects */
	private CustomerStorage cs = CustomerStorage.getCustomerStorage();
	private DeliveryLocationStorage ds = DeliveryLocationStorage.getDeliveryLocationStorage();
	private MenuItemStorage is = MenuItemStorage.getMenuItemStorage();
	private PreparationKitchen pk = PreparationKitchen.getInstance();
	private OvenKitchen ok = OvenKitchen.getInstance();
	private DeliverySystem dls = DeliverySystem.getDeliverySystem();
	private static MainMenu currentMM = null;
	/* Functionality Objects */
	private ArrayList<String> al;
	private ArrayList<String> displayOrderedItems= new ArrayList<String>();
	private ArrayList<String> displayItemToppings = new ArrayList<String>();
	private ArrayList<OrderedItem> currentOrderedItems = new ArrayList<OrderedItem>();
	private ArrayList<Long> orderNumbers = new ArrayList<Long>();
	private Order currentOrder;
	private ArrayList<OrderedItem> contents;
	private List<String> left, right;
	private boolean customerLoaded = false;
	private MenuItem selectedItem;
	private OrderedItem addedItem;
	private Customer theCustomer;
	private Oven selectedOven;
	private PizzaDriver selectedDriver;
	private ArrayList<String> itemNames = new ArrayList<String> ();
	
	public MainMenu() {

	}
	
	public static MainMenu getScreen() {
		if(currentMM == null) {
			currentMM = new MainMenu();
			currentMM.initComponents();
		}
		return currentMM;
	}
	
	public static MainMenu getNewScreen() {
		getScreen().win.dispose();
		currentMM = new MainMenu();
		currentMM.initComponents();
		return currentMM;
	}
	
	public void initComponents(){
		win = new JFrame("Main Menu");
		win.setSize(1280,1024);
		win.setResizable(false);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setLayout(null);
		tabPane = new JTabbedPane();
		tabPane.setSize(1272,927);
		tabPane.setLocation(1,1);
		initCreateOrder();
		initEditOrder();
		initKitchen();
		initReport();
		tabPane.addTab("Create Order", createPanel);
		tabPane.addTab("Edit Order", editPanel);
		tabPane.addTab("View Kitchen", kitchenPanel);
		tabPane.addTab("Manager Report", reportPanel);
		logoutBUT = new JButton("Logout");
		logoutBUT.addActionListener(this);
		logoutBUT.setSize(1272,65);
		logoutBUT.setLocation(1,930);
		win.add(tabPane);
		win.add(logoutBUT);
		win.setVisible(true);
	}
	
	public void initCreateOrder(){
		createPanel = new JPanel();
		createPanel.setLayout(null);
		/* Initialize all components */
		customerLBL = new JLabel("Customer: ");
		customerTF = new JTextField();
		okBUT = new JButton("OK");
		okBUT.addActionListener(this);
		firstLBL = new JLabel("First Name: ");
		firstTF = new JTextField();
		lastLBL = new JLabel("Last Name: ");
		lastTF = new JTextField();
		locationCB = new JComboBox();
		saveBUT = new JButton("Save");
		saveBUT.addActionListener(this);
		itemCB = new JComboBox();
		itemCB.addActionListener(this);
		priceLBL = new JLabel("Base Price: $");
		priceTF = new JTextField();
		priceTF.setEditable(false);
		topCostLBL = new JLabel("Topping Cost: $");
		topCostTF = new JTextField();
		topCostTF.setEditable(false);
		totalLBL = new JLabel("Subtotal: $");
		totalTF = new JTextField("0.00");
		totalTF.setEditable(false);
		addBUT = new JButton("Add");
		addBUT.addActionListener(this);
		orderItemList = new JList();
		orderItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderItemList.addListSelectionListener(this);
		orderToppingList = new JList();
		orderItemSP = new JScrollPane(orderItemList);
		orderToppingSP = new JScrollPane(orderToppingList);
		createRemoveItemBUT = new JButton("Remove Item");
		createRemoveItemBUT.addActionListener(this);
		createRemoveToppingBUT = new JButton("Remove Topping");
		createRemoveToppingBUT.addActionListener(this);
		doneBUT = new JButton("Create Order");
		doneBUT.addActionListener(this);
		/* Size all Components */
		customerLBL.setSize(100,50);
		customerTF.setSize(150,50);
		okBUT.setSize(75,50);
		firstLBL.setSize(100,50);
		firstTF.setSize(150,50);
		lastLBL.setSize(100,50);
		lastTF.setSize(150,50);
		locationCB.setSize(290,50);
		saveBUT.setSize(150,50);
		itemCB.setSize(300,50);
		priceLBL.setSize(100,50);
		priceTF.setSize(100,50);
		topCostLBL.setSize(100,50);
		topCostTF.setSize(100,50);
		totalLBL.setSize(100,50);
		totalTF.setSize(100,50);
		addBUT.setSize(150,50);
		orderItemSP.setSize(500,350);
		orderToppingSP.setSize(500,350);
		createRemoveItemBUT.setSize(500,50);
		createRemoveToppingBUT.setSize(500,50);
		doneBUT.setSize(150,50);
		/* Place all components */
		customerLBL.setLocation(75,10);
		customerTF.setLocation(250,10);
		okBUT.setLocation(525,10);
		firstLBL.setLocation(10,200);
		firstTF.setLocation(150,200);
		lastLBL.setLocation(325,200);
		lastTF.setLocation(450,200);
		locationCB.setLocation(10,375);
		saveBUT.setLocation(450,375);
		itemCB.setLocation(10,600);
		priceLBL.setLocation(10,675);
		priceTF.setLocation(135,675);
		topCostLBL.setLocation(10,750);
		topCostTF.setLocation(135,750);
		totalLBL.setLocation(10,825);
		totalTF.setLocation(135,825);
		addBUT.setLocation(450,600);
		orderItemSP.setLocation(675,10);
		orderToppingSP.setLocation(675,450);
		createRemoveItemBUT.setLocation(675,360);
		createRemoveToppingBUT.setLocation(675,800);
		doneBUT.setLocation(450,825);
		locationCB.addItem("-Locations-");
		for (int i = 0; i < ds.getDeliveryLocationMap().size(); i++) {
			al = new ArrayList<String>(ds.getDeliveryLocationMap().keySet());
			locationCB.addItem(al.get(i));
		}
		
		itemCB.addItem("-Menu Items-");
		for (int i = 0; i < is.getMenuItemMap().size(); i++) {
			al = new ArrayList<String>(is.getMenuItemMap().keySet());
			itemCB.addItem(al.get(i));
		}
		/* Add components to Panel */
		createPanel.add(customerLBL);
		createPanel.add(customerTF);
		createPanel.add(okBUT);
		createPanel.add(firstLBL);
		createPanel.add(firstTF);
		createPanel.add(lastLBL);
		createPanel.add(lastTF);
		createPanel.add(locationCB);
		createPanel.add(saveBUT);
		createPanel.add(itemCB);
		createPanel.add(priceLBL);
		createPanel.add(priceTF);
		createPanel.add(topCostLBL);
		createPanel.add(topCostTF);
		createPanel.add(totalLBL);
		createPanel.add(totalTF);
		createPanel.add(addBUT);
		createPanel.add(orderItemSP);
		createPanel.add(orderToppingSP);
		createPanel.add(createRemoveItemBUT);
		createPanel.add(createRemoveToppingBUT);
		createPanel.add(doneBUT);
	}

	public void initEditOrder(){
		editPanel = new JPanel();
		editPanel.setLayout(null);
		/* Initialize all components */
		currentOrderList = new JList();
		currentOrderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		currentOrderList.addListSelectionListener(this);
		itemsList = new JList();
		itemsList.addListSelectionListener(this);
		itemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		toppingsList = new JList();
		toppingsList.setEnabled(false);
		ordersSP = new JScrollPane(currentOrderList);
		itemsSP = new JScrollPane(itemsList);
		toppingsSP = new JScrollPane(toppingsList);
		cancelOrderBUT = new JButton("Cancel Order");
		cancelOrderBUT.addActionListener(this);
		addItemBUT = new JButton("Add Item");
		addItemBUT.addActionListener(this);
		addItemBUT.setVisible(false);
		editRemoveItemBUT = new JButton("Remove Item");
		editRemoveItemBUT.addActionListener(this);
		editRemoveItemBUT.setVisible(false);
		addToppingBUT = new JButton("Add Topping");
		addToppingBUT.addActionListener(this);
		addToppingBUT.setVisible(false);
		/* Size components */
		ordersSP.setSize(300,700);
		itemsSP.setSize(300,700);
		toppingsSP.setSize(300,700);
		cancelOrderBUT.setSize(300,50);
		addItemBUT.setSize(135,50);
		editRemoveItemBUT.setSize(135,50);
		addToppingBUT.setSize(300,50);
		/* Place Components */
		ordersSP.setLocation(40,25);
		itemsSP.setLocation(500,25);
		toppingsSP.setLocation(940,25);
		cancelOrderBUT.setLocation(40,725);
		addItemBUT.setLocation(500,725);
		editRemoveItemBUT.setLocation(665,725);
		addToppingBUT.setLocation(940,725);
		/* Add Components to Panel */
		editPanel.add(ordersSP);
		editPanel.add(itemsSP);
		editPanel.add(toppingsSP);
		editPanel.add(cancelOrderBUT);
		editPanel.add(addItemBUT);
		editPanel.add(editRemoveItemBUT);
		editPanel.add(addToppingBUT);
	}
	
	public void initKitchen(){
		kitchenPanel = new JPanel();
		kitchenPanel.setLayout(null);
		/* Initialize Components */
		prepLBL = new JLabel("Preparation Kitchen");
		prepViewLBL = new JLabel("");
		cooksCB = new JComboBox();
		cooksCB.addActionListener(this);
		prepProgress = new JProgressBar(0,100);
		prepProgress.setStringPainted(true);
		ovenLBL = new JLabel("Ovens");
		ovensCB = new JComboBox();
		ovensCB.addActionListener(this);
		ovenList = new JList();
		ovenList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ovenList.addListSelectionListener(this);
		ovenSP = new JScrollPane(ovenList);
		ovenProgress = new JProgressBar(0,100);
		ovenProgress.setStringPainted(true);
		deliveryLBL = new JLabel("Deliveries");
		driversCB = new JComboBox();
		driversCB.addActionListener(this);
		driverList = new JList();
		driverList.addListSelectionListener(this);
		driversSP = new JScrollPane(driverList);
		deliveryProgress = new JProgressBar(0,100);
		deliveryProgress.setStringPainted(true);
		refreshBUT = new JButton("Refresh");
		refreshBUT.addActionListener(this);
		/* Size Components */
		prepLBL.setSize(426,50);
		prepViewLBL.setSize(400,50);
		ovenLBL.setSize(426,50);
		deliveryLBL.setSize(426,50);
		cooksCB.setSize(300,50);
		ovensCB.setSize(300,50);
		driversCB.setSize(300,50);
		ovenSP.setSize(300,600);
		driversSP.setSize(300,600);
		prepProgress.setSize(300,50);
		ovenProgress.setSize(300,50);
		deliveryProgress.setSize(300,50);
		refreshBUT.setSize(300,50);
		/* Place Components */
		prepLBL.setLocation(0,0);
		prepViewLBL.setLocation(10,600);
		ovenLBL.setLocation(426,0);
		deliveryLBL.setLocation(852,0);
		cooksCB.setLocation(63,75);
		ovensCB.setLocation(489,75);
		driversCB.setLocation(915,75);
		ovenSP.setLocation(489,150);
		driversSP.setLocation(915,150);
		prepProgress.setLocation(63,765);
		ovenProgress.setLocation(489,765);
		deliveryProgress.setLocation(915,765);
		refreshBUT.setLocation(489,835);
		/* Add Components to Panel */
		kitchenPanel.add(prepLBL);
		kitchenPanel.add(ovenLBL);
		kitchenPanel.add(deliveryLBL);
		kitchenPanel.add(cooksCB);
		kitchenPanel.add(ovensCB);
		kitchenPanel.add(driversCB);
		kitchenPanel.add(ovenSP);
		kitchenPanel.add(driversSP);
		kitchenPanel.add(prepProgress);
		kitchenPanel.add(ovenProgress);
		kitchenPanel.add(deliveryProgress);
		kitchenPanel.add(refreshBUT);
		kitchenPanel.add(prepViewLBL);

	}
	
	public void initReport() {
        reportPanel = new JPanel();
        reportPanel.setLayout(null);
        averageLBL = new JLabel("Average Cost:");
        averageLBL.setSize(200,50);
        averageLBL.setLocation(150,200);
        averageTimeLBL = new JLabel("Average Time for a Order:");
        averageTimeLBL.setSize(200,50);
        averageTimeLBL.setLocation(150,100);
        maxTimeLBL = new JLabel("Max Time for a Order:");
        maxTimeLBL.setSize(200,50);
        maxTimeLBL.setLocation(675,100);
        totalReportLBL = new JLabel("Number Of Orders Today:");
        totalReportLBL.setSize(200,50);
        totalReportLBL.setLocation(675,200);
        averageTF = new JTextField();
        averageTF.setSize(100,50);
        averageTF.setLocation(355,200);
        averageTimeTF = new JTextField();
        averageTimeTF.setSize(100,50);
        averageTimeTF.setLocation(355,100);
        maxTF = new JTextField();
        maxTF.setSize(100,50);
        maxTF.setLocation(890,100);
        totalReportTF = new JTextField();
        totalReportTF.setSize(100,50);
        totalReportTF.setLocation(890,200);
        employeesTA = new JTextArea("- Number Of Sales by Employee -\n");
        employeesTA.setEditable(false);
        //Change made here
        EmployeeStorage es = EmployeeStorage.getEmployeeStorage();
        java.util.Iterator<Employee> it = es.getEmployeeMap().values().iterator();
        for(int i = 0; i < es.getEmployeeMap().size(); i++)
        {
            Employee e = it.next();
            employeesTA.append(e.getFirstName() + " " + e.getLastName() +  " : " + e.getNumOrdersTaken() + "\n");
        }
        JScrollPane employesSP = new JScrollPane(employeesTA);
        employesSP.setSize(400,400);
        employesSP.setLocation(435,330);
        employesSP.setEnabled(false);
        refreshReportBUT = new JButton( "Refresh" );
        refreshReportBUT.setSize(300, 50);
        refreshReportBUT.setLocation(485, 750);
        refreshReportBUT.addActionListener( this );
        averageTF.setEditable(false);
        totalReportTF.setEditable(false);
        averageTimeTF.setEditable(false);
        maxTF.setEditable(false);
        reportPanel.add(employesSP);
        reportPanel.add(averageTF);
        reportPanel.add(totalReportTF);
        reportPanel.add(totalReportLBL);
        reportPanel.add(averageLBL);
        reportPanel.add(averageTimeLBL);
        reportPanel.add(averageTimeTF);
        reportPanel.add(maxTF);
        reportPanel.add(maxTimeLBL);
        reportPanel.add(refreshReportBUT);
        totalReportTF.setText("0");
        averageTF.setText("0");
        averageTimeTF.setText("0");
        maxTF.setText("0");
    }
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == logoutBUT){
			win.dispose();
			LoginScreen ls = new LoginScreen(1);
			ls.initComponents();
		}
		
		if(e.getSource() == okBUT){
			if(customerTF.getText().length() != 10){
				JOptionPane.showMessageDialog(win, "Please use 10 digit phone numbers.");
			}
			else{
				Customer cust = null;
				//Test for correct input
				try{
					cust = cs.getCustomer(Long.valueOf(customerTF.getText()));
				}
				catch(NumberFormatException ee){
					JOptionPane.showMessageDialog(win, "The customer ID must be a number.");
				}
				//If the customer does not exist
				if(cust == null){
					JOptionPane.showMessageDialog(win, "This customer ID does not exist.\nPlese put in new customer information, then click save.");
					firstTF.setText("");
					lastTF.setText("");
					locationCB.setSelectedIndex(0);
				}
				//If the customer does exist we better set the fields	
				else {
					customerLoaded = true;
					lastTF.setText(cust.getLastName());
					firstTF.setText(cust.getFirstName());
					al = new ArrayList<String>(ds.getDeliveryLocationMap().keySet());
					if(cust.getDelivLocation().getLocationName() != null){
						locationCB.setSelectedIndex(al.indexOf(cust.getDelivLocation().getLocationName())+1);
						cs.saveSettings();
				}
				theCustomer = cust;
			}
		}
	}

		if(e.getSource() == saveBUT){
			String sel = (String) locationCB.getSelectedItem();
			//If the field is not 10 digits
			if(customerTF.getText().length() != 10 ){
				JOptionPane.showMessageDialog(win, "Please make sure the telephone number is 10 digits long.");
			}
			//If the fields are not set
			else if(firstTF.getText().isEmpty() || lastTF.getText().isEmpty() || ds.getDeliveryLocation(sel) == null)
				JOptionPane.showMessageDialog(win, "Customer information missing\nPlese put in new customer information, then click save.");
			//If the fields are set then make the customer
			else{
				cs.removeCustomer( Long.valueOf( customerTF.getText() ) );
				Customer cust2 = new Customer(Long.valueOf(customerTF.getText()),lastTF.getText(),firstTF.getText(),ds.getDeliveryLocation(sel));
				cs.saveSettings();
				theCustomer = cust2;
				JOptionPane.showMessageDialog(win, "Save success.");
				customerLoaded = true;
			}
		}
		if(e.getSource() == createRemoveItemBUT){
			int index = orderItemList.getSelectedIndex();
			OrderedItem temp = currentOrderedItems.get(index);
			float currentTotal = Float.parseFloat(totalTF.getText());
			float itemPrice = temp.getPriceOfItem();
			totalTF.setText(Float.toString(currentTotal - itemPrice));
			currentOrderedItems.remove(index);
			displayOrderedItems.remove(index);
			orderItemList.setListData(displayOrderedItems.toArray());
			if(orderItemList.getLastVisibleIndex() >= 0){
				orderItemList.setSelectedIndex(0);
			}
			else{
				displayItemToppings = new ArrayList<String>();
				orderToppingList.setListData(displayItemToppings.toArray());
			}
		}
		if(e.getSource() == createRemoveToppingBUT){
			Object temp[] = orderToppingList.getSelectedValues();
			for(int i = 0; i < temp.length; i++){
				displayItemToppings.remove((String)temp[i]);
			}
			orderToppingList.setListData(displayItemToppings.toArray());
			ArrayList <String>left = new ArrayList<String>();
			ArrayList <String>right = new ArrayList<String>();
			for(int i = 0; i < displayItemToppings.size(); i++){
				String tempb = displayItemToppings.get(i);
				if(tempb.substring(0,3).equals("(L)")){
					left.add(tempb.substring(3));
				}
				if(tempb.substring(0,3).equals("(R)")){
					right.add(tempb.substring(3));
				}
			}
			int index = orderItemList.getSelectedIndex();
			currentOrderedItems.get(index).setLeftToppings(left);
			currentOrderedItems.get(index).setRightToppings(right);
			totalTF.setText( Float.toString( Float.parseFloat( totalTF.getText() ) - temp.length * MenuItemStorage.getMenuItemStorage().getMenuItem( currentOrderedItems.get(index).getItemName() ).getWholeToppingCost() / 2 ) ); 
		}
		
		if(e.getSource() == doneBUT){
			if(!(currentOrderedItems != null) || !((theCustomer !=null)) || currentOrderedItems.size() == 0) {
				JOptionPane.showMessageDialog(win,
					    "You cannot create an order with no ordered items.",
					    "Create Order Error",
					    JOptionPane.ERROR_MESSAGE);				
				return;
			}
			int sure = JOptionPane.showConfirmDialog(win,
					"The subtotal for this order is: $" + totalTF.getText() +
					" Are you sure you want to create this order?",
					"Confirm Action", JOptionPane.YES_NO_OPTION);
			if(sure != 1){
				/* Create the Order */
				Order newOrder = new Order(theCustomer,currentOrderedItems);
				JOptionPane.showMessageDialog(win, "Order created. Estimated delivery time is: " + newOrder.getEstimatedDelivery());
				/* Reset the tab for next Order */
				customerLoaded = false;
				orderNumbers.add(newOrder.getCustomerInfo().getOrderID());
				displayOrderedItems= new ArrayList<String>();
				displayItemToppings = new ArrayList<String>();
				currentOrderedItems = new ArrayList<OrderedItem>();
				currentOrderList.setListData(orderNumbers.toArray());
				orderItemList.setListData(displayOrderedItems.toArray());
				orderToppingList.setListData(displayItemToppings.toArray());
				theCustomer = null;
				customerTF.setText("");
				firstTF.setText("");
				lastTF.setText("");
				totalTF.setText("0.00");
				locationCB.setSelectedIndex(0);
				itemCB.setSelectedIndex(0);
				orderItemList.setListData(currentOrderedItems.toArray());
			}
		}
		if(e.getSource() == addBUT){
			if(customerLoaded == false){
				JOptionPane.showMessageDialog(win,"Please ensure that you have a customer loaded from the databse.");
			}
			else{
				String selected = (String)itemCB.getSelectedItem();
				if(is.menuItemExists(selected)){
					selectedItem = is.getMenuItem(selected);
					if(selectedItem.getWholeToppingCost() > 0){
						ToppingSub ts = new ToppingSub(selectedItem,this);
						ts.initComponents();
					}
					else{
						OrderedItem temp = new OrderedItem(selected,null,null,null);
						currentOrderedItems.add(temp);
						float oldTotal = Float.parseFloat(totalTF.getText());
						float itemCost = Float.parseFloat(priceTF.getText());
						float newTotal = oldTotal+itemCost;
						totalTF.setText(Float.toString(newTotal));
						displayOrderedItems.add(selected);
						orderItemList.setListData(displayOrderedItems.toArray());
						orderItemList.setSelectedIndex(orderItemList.getLastVisibleIndex());
					}
				}
			}
		}
		
		if(e.getSource() == itemCB){
			String selected = (String)itemCB.getSelectedItem();
			if(is.menuItemExists(selected)){
				MenuItem temp = is.getMenuItem(selected);
				priceTF.setText(Float.toString(temp.getBasePrice()));
				topCostTF.setText(Float.toString(temp.getWholeToppingCost()));
			}
			else{
				priceTF.setText("0.00");
				topCostTF.setText("0.00");
			}
		}
		
		if(e.getSource() == cancelOrderBUT) {
			int index = currentOrderList.getSelectedIndex();
			if( index == -1) {
				JOptionPane.showMessageDialog(win,
					    "An order has not been selected.",
					    "Remove Order Error",
					    JOptionPane.ERROR_MESSAGE);		
				return;
			}
			if(OrderHolder.getCurrentOrderHolder().getCurrentOrders().get(index).checkIfFinished()) {
				JOptionPane.showMessageDialog(win,
					    "The order has been finished.",
					    "Remove Order Error",
					    JOptionPane.ERROR_MESSAGE);	
				return;
			}
			int sure = JOptionPane.showConfirmDialog(win,
					"Are you sure you want to delete this order?",
					"Confirm Action", JOptionPane.YES_NO_OPTION);
			if(sure == 0) {
				OrderHolder.getCurrentOrderHolder().getCurrentOrders().get(index).cancelOrder();
				orderNumbers.remove(index);
				currentOrderList.setListData(orderNumbers.toArray());
				Object[] blank = new Object[0];
				itemsList.setListData(blank);
				toppingsList.setListData(blank);
				addItemBUT.setVisible(false);
				editRemoveItemBUT.setVisible(false);
				addToppingBUT.setVisible(false);
			}
		}
		
		if(e.getSource() == addItemBUT) {
			EditAddMenuItem items = new EditAddMenuItem(OrderHolder.getCurrentOrderHolder().getCurrentOrders().get(currentOrderList.getSelectedIndex()));
		}
		
		if(e.getSource() == editRemoveItemBUT) {
			int index = itemsList.getSelectedIndex();
			int index2 = currentOrderList.getSelectedIndex();
			if( index == -1 || index2 == -1 ) {
				return;
			}
			Order temp = OrderHolder.getCurrentOrderHolder().getCurrentOrders().get( index2 );
			boolean oneItemLeft = temp.getNumberOfItems() == 1;
			temp.removeItem( temp.getOrderContents().get( index ) );
			if( oneItemLeft ) {
				orderNumbers.remove(index2);
				currentOrderList.setListData(orderNumbers.toArray());
				itemsList.setListData( new String[0] );
				addItemBUT.setVisible(false);
				editRemoveItemBUT.setVisible(false);
				addToppingBUT.setVisible(false);
				
			} else {
				contents.remove( index );
				itemsList.setListData( contents.toArray() );
			}
		}
		
		if(e.getSource() == refreshBUT){
			String temp = "";
			int counter = 0;
			/*Update Cooks*/
			cooksCB.removeAllItems();
			for(int i = 0; i < pk.getBusyCooks().size(); i++){
				temp = ("Cook #"+(i+1));
				cooksCB.addItem(temp);
				counter++;
			}
			if(counter != 0)
				cooksCB.setSelectedIndex(0);
			/* Update Ovens*/
			counter = 0;
			ovensCB.removeAllItems();
			for(int j = 0; j < ok.getCookingOvens().size(); j++){
				temp = ("Oven #"+(j+1));
				ovensCB.addItem(temp);
				counter ++;
			}
			if(counter != 0){
				ovensCB.setSelectedIndex(0);
				ovenList.setSelectedIndex(0);
			}
			/* Update Drivers*/
			counter = 0;
			driversCB.removeAllItems();
			for(int k = 0; k < dls.getBusyDrivers().size(); k++){
				temp = ("Driver #"+(k+1));
				driversCB.addItem(temp);
			}
			if(counter != 0){
				driversCB.setSelectedIndex(0);
				driverList.setSelectedIndex(0);
			}
		}
		
		if(e.getSource() == cooksCB){
			String temp = (String)cooksCB.getSelectedItem();
			if(temp != null){
				int cookIndex = Integer.parseInt(temp.substring(6));
				cookIndex--;
				Cook selectedCook = (Cook)pk.getBusyCooks().get(cookIndex);
				prepViewLBL.setText("Cook is currently making "+selectedCook.getItemToPrepare().getItemName()+" for order # " + 
						selectedCook.getItemToPrepare().getAssociatedOrder().getCustomerInfo().getOrderID());
				int percentDone = selectedCook.getPercentPrepared();
				prepProgress.setValue(percentDone);
			}
			if(temp == null){
				prepProgress.setValue(0);
			}
		}
		
		if(e.getSource() == ovensCB){
			String temp = (String)ovensCB.getSelectedItem();
			if(temp != null){
				int ovenIndex = Integer.parseInt(temp.substring(6));
				ovenIndex--;
				selectedOven = (Oven)ok.getCookingOvens().get(ovenIndex);
				LinkedList<OrderedItem> ovenContents = (LinkedList<OrderedItem>)selectedOven.getItemsCooking();
				ArrayList<String> displayOvenContents = new ArrayList<String>();
				for(int i = 0; i < ovenContents.size(); i++){
					displayOvenContents.add(ovenContents.get(i).getItemName());
				}
				ovenList.setListData(displayOvenContents.toArray());
			}
			if(temp == null){
				ovenProgress.setValue(0);
			}
		}
		
		if(e.getSource() == driversCB){
			String temp = (String)driversCB.getSelectedItem();
			if(temp != null){
				int driverIndex = Integer.parseInt(temp.substring(8));
				driverIndex--;
				selectedDriver = (PizzaDriver)dls.getBusyDrivers().get(driverIndex);
				ArrayList<Order> driverContents = (ArrayList<Order>)selectedDriver.getOrders();
				ArrayList<String> displayDriverContents = new ArrayList<String>();
				if(driverContents != null){
					for(int i = 0; i < driverContents.size(); i++){
						displayDriverContents.add(Long.toString(driverContents.get(i).getCustomerInfo().getOrderID()));
					}
				}
				driverList.setListData(displayDriverContents.toArray());
				deliveryProgress.setValue(selectedDriver.getPercentDriven());
			}
			if(temp == null){
				deliveryProgress.setValue(0);
			}
		}
		
		if(e.getSource() == refreshReportBUT) {
			employeesTA.setText("");
	        EmployeeStorage es = EmployeeStorage.getEmployeeStorage();
	        java.util.Iterator<Employee> it = es.getEmployeeMap().values().iterator();
	        for(int i = 0; i < es.getEmployeeMap().size(); i++)
	        {
	            Employee e2 = it.next();
	            employeesTA.append(e2.getFirstName() + " " + e2.getLastName() +  " : " + e2.getNumOrdersTaken() + "\n");
	        }
	        totalReportTF.setText( "" + ManagerReport.getCurrentManagerReport().getNumberOfOrdersToday() );
	        averageTF.setText( "" + ManagerReport.getCurrentManagerReport().getAverageCost() );
	        Iterator<Order> theOrders = ManagerReport.getCurrentManagerReport().getOrders().iterator();
	        long totalTime = 0;
	        long maxTime = 0;
	        while( theOrders.hasNext() ) {
	        	Order nextOrder = theOrders.next();
	        	long timeToAdd = nextOrder.getOrderFinished().getTime() - nextOrder.getOrderTaken().getTime();
	        	if( timeToAdd > maxTime ) {
	        		maxTime = timeToAdd;
	        	}
	        	totalTime += timeToAdd;
	        }
			if( ManagerReport.getCurrentManagerReport().getNumberOfOrdersToday() == 0 ) {
		averageTimeTF.setText( "0" );
} else {
	        int averageTimeMinutes = (int)( totalTime / 60000 ) / ManagerReport.getCurrentManagerReport().getNumberOfOrdersToday();
	        averageTimeTF.setText( "" + averageTimeMinutes );
}
	        maxTF.setText( "" + maxTime / 60000 );
		}
	}
	public void receiveOrderedItem(OrderedItem x, int y){
		if(y == 0) {
			addedItem = x;
			currentOrderedItems.add(addedItem);
			left = addedItem.getLeftToppings();
			right = addedItem.getRightToppings();
			float currentCost = Float.parseFloat(totalTF.getText()) + Float.parseFloat(priceTF.getText());
			float topCost = Float.parseFloat(topCostTF.getText());
			float totalCost = ( ( addedItem.getLeftToppings().size() + addedItem.getRightToppings().size() ) * topCost / 2 ) + currentCost;
			totalTF.setText(Float.toString(totalCost));
			displayOrderedItems.add(addedItem.getItemName());
			orderItemList.setListData(displayOrderedItems.toArray());
			orderItemList.setSelectedIndex(orderItemList.getLastVisibleIndex());
		} else {
			Order beforeOrder = x.getAssociatedOrder();
			OrderHolder.getCurrentOrderHolder().removeOrder(beforeOrder);
			beforeOrder.addItem(x);
			OrderHolder.getCurrentOrderHolder().addOrder(beforeOrder);
			contents.add(x);
			itemNames.add(x.getItemName());
			itemsList.setListData(itemNames.toArray());
			Object[] blank = new Object[0];
			toppingsList.setListData(blank);
		}
	}

	
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == ovenList){
			int index = ovenList.getSelectedIndex();
			if( index != -1){
				LinkedList<OrderedItem> ovenContents = (LinkedList<OrderedItem>)selectedOven.getItemsCooking();
				OrderedItem viewedItem = ovenContents.get(index);
				ovenProgress.setValue(viewedItem.percentDone());
			}
		}
		
		if(e.getSource() == orderItemList){
			int index = orderItemList.getSelectedIndex();
			if(index >= 0){
				OrderedItem temp = currentOrderedItems.get(index);
				displayItemToppings = new ArrayList<String>();
				String tempb = " ";
				if(temp.getLeftToppings() != null){
					for(int i = 0; i < temp.getLeftToppings().size();i++){
						tempb = ("(L)") + temp.getLeftToppings().get(i);
						displayItemToppings.add(tempb);
					}
				}
				if(temp.getRightToppings() != null){
					for(int j = 0; j < temp.getRightToppings().size(); j++){
						tempb = "(R)" + temp.getRightToppings().get(j);
						displayItemToppings.add(tempb);
					}
				}
				orderToppingList.setListData(displayItemToppings.toArray());
			}
		}
		
		if(e.getSource() == currentOrderList) {
			addItemBUT.setVisible(true);
			editRemoveItemBUT.setVisible(true);
			int index = currentOrderList.getSelectedIndex();
			if(index == -1) {
				return;
			}
			currentOrder = OrderHolder.getCurrentOrderHolder().getCurrentOrders().get(index);
			contents = new ArrayList<OrderedItem> (currentOrder.getOrderContents());
			ArrayList<String> itemNames = new ArrayList<String> ();
			for(int i = 0; i < contents.size(); i++) {
				itemNames.add(contents.get(i).getItemName());
			}
			itemsList.setListData(itemNames.toArray());
		}
		
		if(e.getSource() == itemsList) {
			int index = itemsList.getSelectedIndex();
			if(index == -1) {
				return;
			}
			ArrayList<String> lsToppings = new ArrayList<String>(contents.get(index).getLeftToppings());
			ArrayList<String> rsToppings = new ArrayList<String>(contents.get(index).getLeftToppings());
			ArrayList<String> allToppings = new ArrayList<String>();
			for(int i = 0; i < lsToppings.size(); i++) {
				allToppings.add("(L)"+lsToppings.get(i));
			}
			for(int j = 0; j < rsToppings.size(); j++) {
				allToppings.add("(R)"+rsToppings.get(j));
			}
			toppingsList.setListData(allToppings.toArray());
		}
	}
}
