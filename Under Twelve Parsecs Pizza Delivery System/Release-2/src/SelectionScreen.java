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

import javax.swing.*;

public class SelectionScreen implements ActionListener {

	private JFrame win;
	private JPanel buttonPanel;
	private JScrollPane buttonSP;
	private JButton cancelBUT, nextBUT;
	private int type;
	private int mode;
	private JToggleButton foo;
	private EmployeeStorage es = EmployeeStorage.getEmployeeStorage();
	private MenuItemStorage ms = MenuItemStorage.getMenuItemStorage();
	private DeliveryLocationStorage ds = DeliveryLocationStorage
			.getDeliveryLocationStorage();
	private OvenKitchen ok = OvenKitchen.getInstance();
	private JList list = null;

	public SelectionScreen(int type, int mode) {
		this.type = type;
		this.mode = mode;
	}

	public void initComponents() {
		win = new JFrame("Selection");
		win.setLayout(null);
		win.setSize(400, 600);
		win.setResizable(false);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/* Initialize Components */
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		buttonSP = new JScrollPane(buttonPanel);
		cancelBUT = new JButton("Cancel");
		cancelBUT.addActionListener(this);
		nextBUT = new JButton("Next");
		nextBUT.addActionListener(this);
		/* Size Components */
		buttonSP.setSize(375, 450);
		cancelBUT.setSize(175, 50);
		nextBUT.setSize(175, 50);
		/* Place Components */
		buttonSP.setLocation(10, 10);
		cancelBUT.setLocation(10, 500);
		nextBUT.setLocation(200, 500);
		/* Add Components to Frame */
		win.add(buttonSP);
		win.add(cancelBUT);
		win.add(nextBUT);
		win.setVisible(true);

		if (type == 1) {// Remove
			buttonPanel.removeAll();
			if (mode == 0)// Item
			{
				ArrayList<String> al = new ArrayList<String>(ms.getMenuItemMap().keySet());
				list = new JList(al.toArray());
				list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				buttonPanel.add(list);
			} else if (mode == 1)// Topping
			{
				ArrayList<String> toppings = new ArrayList<String>(ms.getAvailableToppings());
				list = new JList(toppings.toArray());
				list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				buttonPanel.add(list);
			//Change made here
			} else if (mode == 2)// Employee
			{
				ArrayList<Employee> al = new ArrayList<Employee>(es.getEmployeeMap().values());
				String[] names = new String[al.size()];
				for(int j =0; j< al.size(); j++) {
					names[j] = al.get(j).getFirstName() + " " + al.get(j).getLastName();
				}
				list = new JList(names);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				buttonPanel.add(list);

			} else if (mode == 3)// Delivery Location
			{
				ArrayList<DeliveryLocation> al = new ArrayList<DeliveryLocation>(ds.getDeliveryLocationMap().values());
				String[] locations = new String[al.size()];
				for(int i = 0; i < locations.length; i++) {
					locations[i] = al.get(i).getLocationName();
				}
				list = new JList(locations);
				list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				buttonPanel.add(list);
			}
			if (mode == 4)// Oven
			{
				String[] names = new String[ok.getCookingOvens().size()];
				for(int i = 0; i < names.length; i++) {
					names[i] = "Oven #" + (i+1);
				}
				list = new JList(names);
				list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				buttonPanel.add(list);			
			}
		}

		if (type == 2) {// Edit
			buttonPanel.removeAll();
			if (mode == 0)// Item
			{
				ArrayList<String> al = new ArrayList<String>(ms.getMenuItemMap().keySet());
				list = new JList(al.toArray());
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				buttonPanel.add(list); 
			} else if (mode == 1)// Employee
			{
				//Change made here.
				ArrayList<Employee> al = new ArrayList<Employee>(es.getEmployeeMap().values());
				String[] names = new String[al.size()];
				for(int j =0; j< al.size(); j++) {
					names[j] = al.get(j).getFirstName() + " " + al.get(j).getLastName();
				}
				list = new JList(names);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				buttonPanel.add(list);

			} else if (mode == 2)// Delivery Location
			{
				ArrayList<DeliveryLocation> al = new ArrayList<DeliveryLocation>(ds.getDeliveryLocationMap().values());
				String[] locations = new String[al.size()];
				for(int i = 0; i < locations.length; i++) {
					locations[i] = al.get(i).getLocationName();
				}
				list = new JList(locations);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				buttonPanel.add(list);
			}
			if (mode == 3)// Oven
			{
				String[] names = new String[ok.getCookingOvens().size()];
				for(int i = 0; i < names.length; i++) {
					names[i] = "Oven #" + (i+1);
				}
				list = new JList(names);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				buttonPanel.add(list);
			}
		}
	}

	// "Item", "Topping", "Employee", "Delivery Location", "Oven"
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelBUT) {
			win.dispose();
		}
		if (e.getSource() == nextBUT) {
			int[] select = list.getSelectedIndices();
			//Change made here
			if(select.length == 0) {
				switch (mode) {
				case 0:
					JOptionPane.showMessageDialog(win,
						    "No menu items have been selected.",
						    "Selection Error",
						    JOptionPane.ERROR_MESSAGE);	
					break;
				case 1:
					JOptionPane.showMessageDialog(win,
						    "No toppings have been selected.",
						    "Selection Error",
						    JOptionPane.ERROR_MESSAGE);	
					break;
				case 2:
					JOptionPane.showMessageDialog(win,
						    "No employees have been selected.",
						    "Selection Error",
						    JOptionPane.ERROR_MESSAGE);	
					break;
					
				case 3:
					JOptionPane.showMessageDialog(win,
						    "No delivery locations have been selected.",
						    "Selection Error",
						    JOptionPane.ERROR_MESSAGE);
					break;
				case 4:
					JOptionPane.showMessageDialog(win,
						    "No ovens have been selected.",
						    "Selection Error",
						    JOptionPane.ERROR_MESSAGE);	
					break;
				}
				
				return;
			}
			if (type == 1) { // Remove Function
				int sure = 0;
				sure = JOptionPane.showConfirmDialog(win,
						"Are you sure you want to delete the selected item(s)?",
						"Confirm Action", JOptionPane.YES_NO_OPTION);
				if (sure == 0) { // User is sure
					if (mode == 0)// Item
					{
						Object[] values = list.getSelectedValues();
						try {
							for (int i = 0; i < values.length; i++)
								ms.removeMenuItem((String) values[i]);
						} catch (Exception e3) {

						}

					} else if (mode == 1)// Topping
					{
						Object[] values = list.getSelectedValues();
						try {
							for (int i = 0; i < values.length; i++) {
								ms.removeTopping((String) values[i]);
							}
						} catch (Exception e3) {
						}
					} else if (mode == 2)// Employee
					{
						//Change made here.
						ArrayList<String> al = new ArrayList<String>(es.getEmployeeMap().keySet());
						try {
							es.removeEmployee(al.get(select[0]));
						} catch (Exception e3) {
						}
					} else if (mode == 3)// Delivery Location
					{
						Object[] values = list.getSelectedValues();
						try {
							for (int i = 0; i < select.length; i++) {
								ds.removeDeliveryLocation((String) values[i]);
							}
						} catch (Exception e3) {
						}
					}
					if (mode == 4)// Oven
					{
						try {
							for (int i = 0; i < select.length; i++) {
								ok.removeOven(ok.getCookingOvens().get(select[i]-i));
							}
						} catch (Exception e3) {
						}
					}
					ManagerScreen.getNewScreen();
					win.dispose();
				}
			}
			if (type == 2) { // Edit function
				
				if (mode == 0)// Item
				{
					ArrayList<String> al = new ArrayList<String>(ms.getMenuItemMap().keySet());
					ItemWizard w = new ItemWizard(al.get(select[0]));
					
					
				} 
				//Made a change here
				else if (mode == 1)// Employee
				{
					ArrayList<String> al = new ArrayList<String>(es.getEmployeeMap().keySet());
					EmployeeWizard w = new EmployeeWizard(al.get(select[0]));
				} else if (mode == 2)// Delivery Location
				{
					ArrayList<String> al = new ArrayList<String>(ds.getDeliveryLocationMap().keySet());
					LocationWizard w = new LocationWizard(al.get(select[0]));
				}
				if (mode == 3)// Oven
				{	
					OvenWizard w = new OvenWizard(select[0]);
				}
				
				//ManagerScreen.getNewScreen();
				win.dispose();
			}
				
				
				//ManagerScreen.getNewScreen();
				win.dispose();
			}
	}
}

