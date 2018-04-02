/**
 * 
 */

/**
 * @author Matthew Talbot
 *
 */

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.*;

public class ManagerScreen implements ActionListener{
	
	private static ManagerScreen currentMS = null;
	private JFrame win;
	private JPanel employeePanel, menuPanel, kitchenPanel;
	private JTabbedPane settingsTP;
	private JButton addBUT, editBUT, removeBUT, doneBUT;
	private JComboBox employeeCB, itemCB;
	private JLabel firstLBL, lastLBL, idLBL, itemLBL, priceLBL, prepLBL, cookLBL, driversLBL, cooksLBL;
	private JTextField firstTF, lastTF, idTF, itemTF, priceTF, prepTF, cookTF, numDriversTF, numCooksTF;
	private JCheckBox managerCHB, toppingsCHB, halfCHB;
	private JTextArea toppingsTA, ovensTA, locationsTA;
	private JScrollPane toppingsSP, ovensSP, locationsSP;
	private EmployeeStorage em;
	private MenuItemStorage ms;
	private DeliveryLocationStorage ds;
	private Employee currentLogin;
	
	private ManagerScreen( Employee currentLogin ) {
		this.currentLogin = currentLogin;
		em = EmployeeStorage.getEmployeeStorage();
		ms = MenuItemStorage.getMenuItemStorage();	
		ds = DeliveryLocationStorage.getDeliveryLocationStorage();
	}

	public static ManagerScreen getScreen( Employee currentLogin ) {
		if(currentMS == null) {
			currentMS = new ManagerScreen( currentLogin );
			currentMS.initComponents();
		}
		return currentMS;
	}
	
	public static ManagerScreen getNewScreen() {
		getScreen( null ).win.dispose();
		currentMS = new ManagerScreen( currentMS.currentLogin );
		currentMS.initComponents();
		return currentMS;
	}

	public void initComponents(){
		win = new JFrame("Manager Settings");
		win.setSize(1280,1024);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setLayout(null);
		settingsTP = new JTabbedPane();
		settingsTP.setSize(1250,850);
		settingsTP.setLocation(5,0);
		initEmployee();
		initMenu();
		initKitchen();
		/*Establish Frame*/
		addBUT = new JButton("Add");
		addBUT.setSize(410,50);
		addBUT.setLocation(5,855);
		addBUT.addActionListener(this);
		editBUT = new JButton("Edit");
		editBUT.setSize(410,50);
		editBUT.setLocation(425,855);
		editBUT.addActionListener(this);
		removeBUT = new JButton("Remove");
		removeBUT.setSize(410,50);
		removeBUT.setLocation(845,855);
		removeBUT.addActionListener(this);
		doneBUT = new JButton("Start System");
		doneBUT.setSize(1250,50);
		doneBUT.setLocation(5,910);
		doneBUT.addActionListener(this);
		win.add(addBUT);
		win.add(editBUT);
		win.add(removeBUT);
		win.add(doneBUT);
		win.add(settingsTP);
		win.setVisible(true);
	}
	
	public void initEmployee(){
		/*Employee Tab Components*/
		employeePanel = new JPanel();
		employeePanel.setLayout(null);		
		employeeCB = new JComboBox();
		employeeCB.setSize(300, 50);
		employeeCB.setLocation(50, 100);
		employeeCB.addItem("-Employee Name-");
		ArrayList<Employee> theEmployees = new ArrayList<Employee> (EmployeeStorage.getEmployeeStorage().getEmployeeMap().values());
		for(int i = 0; i < theEmployees.size(); i++) {
			employeeCB.addItem(theEmployees.get(i).getFirstName() + " " + theEmployees.get(i).getLastName());
		}
		employeeCB.addActionListener(this);
		employeePanel.add(employeeCB);
		firstLBL = new JLabel("First Name:");
		firstLBL.setSize(300,50);
		firstLBL.setLocation(50, 200);
		lastLBL = new JLabel("Last Name:");
		lastLBL.setSize(300,50);
		lastLBL.setLocation(50,400);
		idLBL = new JLabel("Employee ID:");
		idLBL.setSize(300,50);
		idLBL.setLocation(50,600);
		firstTF = new JTextField("");
		firstTF.setSize(300,50);
		firstTF.setLocation(550,200);
		firstTF.setEditable(false);
		lastTF = new JTextField("");
		lastTF.setSize(300,50);
		lastTF.setLocation(550,400);
		lastTF.setEditable(false);
		idTF = new JTextField("");
		idTF.setSize(300,50);
		idTF.setLocation(550,600);
		idTF.setEditable(false);
		managerCHB = new JCheckBox("Manager");
		managerCHB.setSize(300,100);
		managerCHB.setLocation(50,700);
		managerCHB.setEnabled(false);
		employeePanel.add(idTF);
		employeePanel.add(lastTF);
		employeePanel.add(firstTF);
		employeePanel.add(managerCHB);
		employeePanel.add(idLBL);
		employeePanel.add(lastLBL);
		employeePanel.add(firstLBL);
		settingsTP.addTab("Employee", employeePanel);
	}
	
	public void initMenu() {
		menuPanel = new JPanel();
		menuPanel.setLayout(null);
		itemCB = new JComboBox();
		itemCB.setSize(300, 50);
		itemCB.setLocation(50, 100);
		itemCB.addItem("-Menu Items-");
		for (int i = 0; i < ms.getMenuItemMap().size(); i++) {
			ArrayList<String> al = new ArrayList<String>(ms.getMenuItemMap().keySet());
			itemCB.addItem(al.get(i));
		}
		itemTF = new JTextField("");
		itemTF.setSize(300,50);
		itemTF.setLocation(400,200);
		itemTF.setEditable(false);
		itemLBL = new JLabel("Item:");
		itemLBL.setSize(300,50);
		itemLBL.setLocation(50,200);
		priceTF = new JTextField("");
		priceTF.setSize(300,50);
		priceTF.setLocation(400,300);
		priceTF.setEditable(false);
		priceLBL = new JLabel("Base Price: $");
		priceLBL.setSize(300,50);
		priceLBL.setLocation(50,300);
		prepTF = new JTextField("");
		prepTF.setSize(300,50);
		prepTF.setLocation(400,400);
		prepTF.setEditable(false);
		prepLBL = new JLabel("Preparation Time:");
		prepLBL.setSize(300,50);
		prepLBL.setLocation(50,400);
		cookTF = new JTextField("");
		cookTF.setSize(300,50);
		cookTF.setLocation(400,500);
		cookTF.setEditable(false);
		cookLBL = new JLabel("Cook Time:");
		cookLBL.setSize(300,50);
		cookLBL.setLocation(50,500);
		toppingsCHB = new JCheckBox("Can have Toppings");
		toppingsCHB.setSize(300,50);
		toppingsCHB.setLocation(50,600);
		toppingsCHB.setEnabled(false);
		halfCHB = new JCheckBox("Can have toppings on half");
		halfCHB.setSize(300,50);
		halfCHB.setLocation(50,700);
		halfCHB.setEnabled(false);
		toppingsTA = new JTextArea();
		toppingsTA.setEditable(false);
		toppingsSP = new JScrollPane(toppingsTA);
		toppingsSP.setLocation(800,100);
		toppingsSP.setSize(400,600);
		menuPanel.add(toppingsSP);
		menuPanel.add(halfCHB);
		menuPanel.add(toppingsCHB);
		menuPanel.add(cookTF);
		menuPanel.add(prepTF);
		menuPanel.add(priceTF);
		menuPanel.add(cookLBL);
		menuPanel.add(prepLBL);
		menuPanel.add(priceLBL);
		menuPanel.add(itemLBL);
		menuPanel.add(itemTF);
		menuPanel.add(itemCB);
		settingsTP.addTab("Menu", menuPanel);
	}
	
	public void initKitchen(){
		kitchenPanel = new JPanel();
		kitchenPanel.setLayout(null);
		cooksLBL = new JLabel("Set Cooks:");
		cooksLBL.setSize(200,50);
		cooksLBL.setLocation(150,150);
		driversLBL = new JLabel("Set Drivers:\n");
		driversLBL.setSize(200,50);
		driversLBL.setLocation(675,150);
		numCooksTF = new JTextField();
		numCooksTF.setSize(100,50);
		numCooksTF.setLocation(355,150);
		numCooksTF.setText(""+PreparationKitchen.getInstance().getNumCooks());
		numDriversTF = new JTextField();
		numDriversTF.setSize(100,50);
		numDriversTF.setLocation(890,150);
		numDriversTF.setText(""+DeliverySystem.getDeliverySystem().getNumDrivers());
		ovensTA = new JTextArea("- Ovens -\n");
		ovensTA.setEditable(false);
		//Change made here
		if(OvenKitchen.getInstance().getCookingOvens().size() == 0) {
			ovensTA.append("There are no ovens currently");
		}
		for(int i = 0; i < OvenKitchen.getInstance().getCookingOvens().size(); i++)
			ovensTA.append("Oven #" + (i+1) + ": " + OvenKitchen.getInstance().getCookingOvens().get(i).getMaxOvenSpace() + " oven space units\n");
		locationsTA = new JTextArea("- Delivery Locations -\n");
		locationsTA.setEditable(false);
		if(ds.getDeliveryLocationMap().size() == 0) {
			locationsTA.append("There are no delivery locations currently");
		}
		ArrayList<DeliveryLocation> al = new ArrayList<DeliveryLocation>(ds.getDeliveryLocationMap().values());
		for(int i = 0; i < ds.getDeliveryLocationMap().size(); i++)
		{
			locationsTA.append("Delivery Location #" + (i+1) + "\n");
			locationsTA.append("Name: " + al.get(i).getLocationName() + "\n");
			locationsTA.append("Time to Location: " + al.get(i).getTimeToLocation() + " minutes" + "\n\n");
		}
		ovensSP = new JScrollPane(ovensTA);
		ovensSP.setSize(305,400);
		ovensSP.setLocation(150,300);
		locationsSP = new JScrollPane(locationsTA);
		locationsSP.setSize(305,400);
		locationsSP.setLocation(675,300);
		kitchenPanel.add(ovensSP);
		kitchenPanel.add(locationsSP);
		kitchenPanel.add(numCooksTF);
		kitchenPanel.add(numDriversTF);
		kitchenPanel.add(driversLBL);
		kitchenPanel.add(cooksLBL);
		settingsTP.addTab("Kitchen", kitchenPanel);
		numDriversTF.setText(Integer.toString(DeliverySystem.getDeliverySystem().getNumDrivers())); 
		numCooksTF.setText(Integer.toString(PreparationKitchen.getInstance().getNumCooks()));
		
		itemCB.addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == addBUT){
			String[] choices = {"Menu Item", "Topping", "Employee", "Delivery Location", "Oven"};
			String input = " ";
			
			input = (String) JOptionPane.showInputDialog(win,"What would you like to create?",
					"Add Wizard",JOptionPane.PLAIN_MESSAGE,null,choices,choices[0]);
			if(input == null) {
				return;
			}
			if(input.equals("Menu Item")){
				ItemWizard niw = new ItemWizard();
			}
			if(input.equals("Delivery Location")){
				LocationWizard lw = new LocationWizard("");
			}
			if(input.equals("Oven")){
				//Change made here
				OvenWizard ow = new OvenWizard(-1);
			}
			if(input.equals("Employee")){
				//Change made here
				EmployeeWizard ew = new EmployeeWizard("");
			}
			if(input.equals("Topping")){
				ToppingWizard tw = new ToppingWizard();
				tw.initComponents();
			}
		}
		if(e.getSource() == editBUT){
			
			SelectionScreen s;
			String[] choices = {"Menu Item", "Employee", "Delivery Location", "Oven"};
			String input = " ";
			input = (String) JOptionPane.showInputDialog(win,"What would you like to edit?",
					"Edit Wizard",JOptionPane.PLAIN_MESSAGE,null,choices,choices[0]);	
			if(input == null) {
				return;
			}
			if(input.equals("Menu Item"))
			{
				if(ms.getMenuItemMap().size() == 0) {
					JOptionPane.showMessageDialog(win,
						    "There are no menu items to edit.",
						    "Menu item error",
						    JOptionPane.ERROR_MESSAGE);
				} else {
					s = new SelectionScreen(2,0);
					s.initComponents();
				}
			}
			if(input.equals("Employee"))
			{
				s = new SelectionScreen(2,1);
				s.initComponents();
			}
			if(input.equals("Delivery Location"))
			{
				if(ds.getDeliveryLocationMap().size() == 0) {
					JOptionPane.showMessageDialog(win,
						    "There are delivery locations to edit.",
						    "Delivery location error",
						    JOptionPane.ERROR_MESSAGE);
				} else {
					s = new SelectionScreen(2,2);
					s.initComponents();
				}
			}
			if(input.equals("Oven"))
			{
				if(OvenKitchen.getInstance().getCookingOvens().size() == 0) {
					JOptionPane.showMessageDialog(win,
						    "There are no ovens to remove.",
						    "Oven error",
						    JOptionPane.ERROR_MESSAGE);
				} else {
					s = new SelectionScreen(2,3);
					s.initComponents();
				}
			}
			
		}
		if(e.getSource() == removeBUT){
			SelectionScreen s;
			String[] choices = {"Menu Item", "Topping", "Employee", "Delivery Location", "Oven"};
			String input = " ";
			input = (String) JOptionPane.showInputDialog(win,"What would you like to remove?",
					"Removal Wizard",JOptionPane.PLAIN_MESSAGE,null,choices,choices[0]);
			if(input == null) {
				return;
			}
			if(input.equals("Menu Item"))
			{
				if(ms.getMenuItemMap().size() == 0) {
					JOptionPane.showMessageDialog(win,
						    "There are no menu items to remove.",
						    "Menu item error",
						    JOptionPane.ERROR_MESSAGE);
				} else {
					s = new SelectionScreen(1,0);
					s.initComponents();
				}
			}
			if(input.equals("Topping"))
			{
				if(ms.getAvailableToppings().size()==0) {
					JOptionPane.showMessageDialog(win,
							"There are toppings to remove.",
							"Topping error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					s = new SelectionScreen(1,1);
					s.initComponents();
				}
			}
			if(input.equals("Employee"))
			{
				if(em.getEmployeeMap().size() == 1) {
					JOptionPane.showMessageDialog(win,
						    "You cannot delete the last employee.",
						    "Employee error",
						    JOptionPane.ERROR_MESSAGE);
				} else {
					s = new SelectionScreen(1,2);
					s.initComponents();
				}
			}
			if(input.equals("Delivery Location"))
			{
				if(ds.getDeliveryLocationMap().size()==0) {
					JOptionPane.showMessageDialog(win,
						    "There are no delivery locations to remove.",
						    "Delivery Locaion error",
						    JOptionPane.ERROR_MESSAGE);
				} else {
					s = new SelectionScreen(1,3);
					s.initComponents();
				}
			}
			if(input.equals("Oven"))
			{
				if(OvenKitchen.getInstance().getCookingOvens().size() == 0) {
					JOptionPane.showMessageDialog(win,
						    "There are no ovens to remove.",
						    "Oven error",
						    JOptionPane.ERROR_MESSAGE);
				} else {
					s = new SelectionScreen(1,4);
					s.initComponents();
				}
			}
		}
		if(e.getSource() == doneBUT){
			boolean correct = true;
			int numCooks = 0;
			int numDrivers = 0;
			
			try {
				numCooks = Integer.parseInt(numCooksTF.getText());
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(win,
					    "Please enter a numerical value for the number of cooks.",
					    "Number of cooks error",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				numDrivers = Integer.parseInt(numDriversTF.getText());
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(win,
					    "Please enter a numerical value for the number of drivers.",
					    "Number of drivers error",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if(ms.getMenuItemMap().size() == 0) {
				JOptionPane.showMessageDialog(win,
					    "There are no menu items currently.",
					    "Number of menu items error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}
			
			if(ds.getDeliveryLocationMap().size() == 0) {
				JOptionPane.showMessageDialog(win,
					    "There are no delivery locations currently.",
					    "Number of delivery locations error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}			

			if(OvenKitchen.getInstance().getCookingOvens().size() == 0) {
				JOptionPane.showMessageDialog(win,
					    "There are no ovens currently.",
					    "Number of ovens error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}		
			
			if(numCooks <= 0) {
				JOptionPane.showMessageDialog(win,
					    "Invalid number of cooks entered.",
					    "Number of cooks error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}

			if(numDrivers <= 0) {
				JOptionPane.showMessageDialog(win,
					    "Invalid number of drivers entered.",
					    "Number of drivers error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}
			
			if(correct) {
				PreparationKitchen.getInstance().setCooks(numCooks);
				DeliverySystem.getDeliverySystem().setDrivers(numDrivers);
				em.saveSettings();
				ms.saveSettings();
				CustomerStorage.getCustomerStorage().saveSettings();
				DeliveryLocationStorage.getDeliveryLocationStorage().saveSettings();
				PreparationKitchen.getInstance().saveSettings();
				DeliverySystem.getDeliverySystem().saveSettings();
				OvenKitchen.getInstance().saveSettings();
				PreparationKitchen.getInstance().startSystem();
				OvenKitchen.getInstance().startSystem();
				DeliverySystem.getDeliverySystem().startSystem();
				MainMenu.getNewScreen();
				win.dispose();
			}	
		}
		
		if(e.getSource() == itemCB) {
			String selected = (String) itemCB.getSelectedItem();
			if (selected.equals("-Menu Items-")) {
				itemTF.setText("");
				priceTF.setText("");
				prepTF.setText("");
				cookTF.setText("");
				toppingsTA.setText("");
			} else {
				MenuItem anItem = ms.getMenuItem(selected);
				itemTF.setText(anItem.getName());
				priceTF.setText(Float.toString(anItem.getBasePrice()));
				prepTF.setText(Integer.toString(anItem.getPrepTime()));
				cookTF.setText(Integer.toString(anItem.getCookTime()));
				toppingsCHB.setSelected(anItem.getWholeToppingCost() != 0);
				halfCHB.setSelected(anItem.isCanHaveHalfToppings()); 
				ArrayList<String> associated = ms.getAssociatingToppings(selected);
				toppingsTA.setText("List of toppings on this menu item:\n");
				toppingsTA.append(" ----------------------------------\n");
				if(associated == null || associated.size() == 0) {
					toppingsTA.append("No current toppings are available");
					return;
				}
				for(int i =0; i < associated.size(); i++) {
					toppingsTA.append(associated.get(i) +"\n");
				}							
			}			
		}
		
		if(e.getSource() == employeeCB) {
			String name = (String) employeeCB.getSelectedItem();
			if(name.equals("-Employee Name-")) {
				firstTF.setText("");
				lastTF.setText("");
				idTF.setText("");
				managerCHB.setSelected(false);
				return;
			}
		
			firstTF.setText(name.substring(0, name.indexOf(" ")));
			lastTF.setText(name.substring(name.indexOf(" ")));
			EmployeeStorage storage = EmployeeStorage.getEmployeeStorage();
			Map<String, Employee> employeeMap = storage.getEmployeeMap();
			Set<String> keys = employeeMap.keySet();
			Iterator iter = keys.iterator();
			while(iter.hasNext()) {
				String key = (String) iter.next();
				Employee aEmployee = employeeMap.get(key);
				String mapName = aEmployee.getFirstName()+ " " + aEmployee.getLastName();
				if(name.equals(mapName)) {
					idTF.setText(key);
					managerCHB.setSelected(aEmployee.isManagerStatus());
					return;
				}
			}
		}
	}
}
