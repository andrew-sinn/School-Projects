/**
 * 
 */

/**
 * @author Matthew Talbot
 *
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ItemWizard implements ActionListener {

	private JFrame win;
	private JLabel nameLBL, priceLBL, topPriceLBL, prepLBL, cookLBL,
			directionsLBL, ovenSpaceLBL;
	private JTextField nameTF, priceTF, topPriceTF, prepTF, cookTF, ovenSpaceTF;
	private JCheckBox toppingsCB, halfableCB;
	private JScrollPane toppingsSP;
	private JPanel leftPanel, rightPanel;
	private JButton cancelBUT, createBUT, checkAllBUT, uncheckAllBUT;
	private MenuItemStorage ms = MenuItemStorage.getMenuItemStorage();
	private JList toppingsList;
	private ArrayList <String> availToppings, associatedToppings;
	private String title;
	private String name;
	
	
	public ItemWizard(){                 // New Mode provides no arguments
		title = "New Menu Item Wizard";
		initComponents();
	}
	
	public ItemWizard(String itemName){  // Edit Mode provides the menu item name
		name = itemName;
		title = "Edit Menu Item Wizard";
		initComponents();
		MenuItem mi = ms.getMenuItem(itemName);
		nameTF.setText(mi.getName());
		priceTF.setText(Float.toString(mi.getBasePrice()));
		topPriceTF.setText(Float.toString(mi.getWholeToppingCost())); 
		prepTF.setText(Integer.toString(mi.getPrepTime())); 
		cookTF.setText(Integer.toString(mi.getCookTime())); 
		ovenSpaceTF.setText(Integer.toString(mi.getOvenSpaceTaken()));
		if(mi.getWholeToppingCost() > 0){
			topPriceTF.setVisible(true);
			topPriceLBL.setVisible(true);
			toppingsCB.setSelected(true);
			halfableCB.setVisible(true);
			rightPanel.setVisible(true);
			halfableCB.setSelected(mi.isCanHaveHalfToppings());		
			associatedToppings = new ArrayList <String>(ms.getAssociatingToppings(mi.getName()));
			ListModel toppings = toppingsList.getModel();
			int[] indices = new int[toppings.getSize()];
			int count = 0;
			for(int i = 0; i < associatedToppings.size(); i ++) {
				for(int j = 0; j < toppings.getSize(); j++) {
					if(toppings.getElementAt(j).equals(associatedToppings.get(i))) {
						indices[count] = j;
						count++;
					}
				}
			}
			toppingsList.setSelectedIndices(indices);
		}
	}
	

	public void initComponents(){
		/* Establish the Frame */
		win = new JFrame(title);
		win.setSize(800, 800);
		win.setResizable(false);
		win.setLayout(null);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/* Initialize the left and right panels */
		leftPanel = new JPanel();
		leftPanel.setSize(400, 750);
		leftPanel.setLayout(null);
		leftPanel.setLocation(0, 0);
		rightPanel = new JPanel();
		rightPanel.setSize(400, 750);
		rightPanel.setLayout(null);
		rightPanel.setLocation(400, 0);
		initLeft();
		initRight();
		rightPanel.setVisible(false);
		/* Place the common function buttons */
		cancelBUT = new JButton("Cancel");
		cancelBUT.setSize(200, 50);
		cancelBUT.setLocation(100, 700);
		cancelBUT.addActionListener(this);
		createBUT = new JButton("Finish");
		createBUT.setSize(200, 50);
		createBUT.setLocation(400, 700);
		createBUT.addActionListener(this);
		/* Add components to the Frame */
		win.add(cancelBUT);
		win.add(createBUT);
		win.add(leftPanel);
		win.add(rightPanel);
		win.setVisible(true);
	}

	public void initRight(){
		/* Initialize Components */
		directionsLBL = new JLabel(
				"Highlight all the toppings available to the new item.");
		availToppings = new ArrayList<String>(ms.getAvailableToppings());
		toppingsList = new JList(availToppings.toArray());
		toppingsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		toppingsSP = new JScrollPane(toppingsList);
		checkAllBUT = new JButton("Select All");
		checkAllBUT.addActionListener(this);
		uncheckAllBUT = new JButton("Unselect All");
		uncheckAllBUT.addActionListener(this);
		/* Size Components */
		directionsLBL.setSize(300, 50);
		toppingsSP.setSize(350, 500);
		checkAllBUT.setSize(165, 50);
		uncheckAllBUT.setSize(165, 50);
		/* Place Components */
		directionsLBL.setLocation(10, 10);
		toppingsSP.setLocation(10, 60);
		checkAllBUT.setLocation(10, 560);
		uncheckAllBUT.setLocation(195, 560);
		/* Add Components to Panel */
		rightPanel.add(directionsLBL);
		rightPanel.add(toppingsSP);
		rightPanel.add(checkAllBUT);
		rightPanel.add(uncheckAllBUT);
		
	}

	public void initLeft(){
		/* Initialize Components */
		nameLBL = new JLabel("Item Name: ");
		nameTF = new JTextField();
		priceLBL = new JLabel("Base Price: $");
		priceTF = new JTextField("0.00");
		prepLBL = new JLabel("Preparation Time (min): ");
		prepTF = new JTextField();
		cookLBL = new JLabel("Cook Time (min): ");
		cookTF = new JTextField();
		topPriceLBL = new JLabel("Toppings Price: $");
		topPriceLBL.setVisible(false);
		topPriceTF = new JTextField("0.00");
		topPriceTF.setVisible(false);
		toppingsCB = new JCheckBox("Has Toppings");
		toppingsCB.setSelected(false);
		toppingsCB.addActionListener(this);
		halfableCB = new JCheckBox("Halfable Toppings");
		halfableCB.setVisible(false);
		halfableCB.setSelected(false);
		ovenSpaceLBL = new JLabel("Oven Space (units): ");
		ovenSpaceTF = new JTextField();
		/* Size the Components */
		nameLBL.setSize(150, 50);
		nameTF.setSize(150, 50);
		priceLBL.setSize(150, 50);
		priceTF.setSize(150, 50);
		prepLBL.setSize(150, 50);
		prepTF.setSize(150, 50);
		cookLBL.setSize(150, 50);
		cookTF.setSize(150, 50);
		topPriceLBL.setSize(150, 50);
		topPriceTF.setSize(150, 50);
		toppingsCB.setSize(150, 50);
		halfableCB.setSize(150, 50);
		ovenSpaceLBL.setSize(150,50);
		ovenSpaceTF.setSize(150,50);
		/* Place the Components */
		nameLBL.setLocation(25, 10);
		nameTF.setLocation(200, 10);
		priceLBL.setLocation(25, 110);
		priceTF.setLocation(200, 110);
		prepLBL.setLocation(25, 210);
		prepTF.setLocation(200, 210);
		cookLBL.setLocation(25, 310);
		cookTF.setLocation(200, 310);
		ovenSpaceLBL.setLocation(25,410);
		ovenSpaceTF.setLocation(200,410);
		topPriceLBL.setLocation(25, 510);
		topPriceTF.setLocation(200, 510);
		toppingsCB.setLocation(25, 610);
		halfableCB.setLocation(200, 610);
		/* Add the Components to the Panel */
		leftPanel.add(nameLBL);
		leftPanel.add(nameTF);
		leftPanel.add(priceLBL);
		leftPanel.add(priceTF);
		leftPanel.add(prepLBL);
		leftPanel.add(prepTF);
		leftPanel.add(cookLBL);
		leftPanel.add(cookTF);
		leftPanel.add(topPriceLBL);
		leftPanel.add(topPriceTF);
		leftPanel.add(toppingsCB);
		leftPanel.add(halfableCB);
		leftPanel.add(ovenSpaceLBL);
		leftPanel.add(ovenSpaceTF);
		toppingsCB.addActionListener(this);
		halfableCB.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == toppingsCB) {
			topPriceLBL.setVisible(toppingsCB.isSelected());
			topPriceTF.setVisible(toppingsCB.isSelected());
			halfableCB.setVisible(toppingsCB.isSelected());
			rightPanel.setVisible(toppingsCB.isSelected());
			if(!toppingsCB.isSelected()) {
				topPriceTF.setText("0.00");
			}
		}

		if (e.getSource() == cancelBUT){
			win.dispose();
		}
		
		if (e.getSource() == checkAllBUT) {
			int[] indices = new int[ms.getAvailableToppings().size()];
			for(int i = 0; i < ms.getAvailableToppings().size(); i++) {
				indices[i] = i;
			}
			toppingsList.setSelectedIndices(indices);
		}
		
		if (e.getSource() == uncheckAllBUT) {
			toppingsList.clearSelection();
		}
		
		if (e.getSource() == createBUT) {
			String itemName = nameTF.getText();
			float basePrice = 0;
			int prepTime = 0;
			int cookTime = 0;
			int ovenSpace = 0;
			float wholeTopping = 0;
			boolean correct = true;
			
			if(itemName.equals("")) {
				JOptionPane.showMessageDialog(win,
					    "Please enter an item name.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);			
				correct = false;				
			}
			
			try {
				basePrice = Float.valueOf(priceTF.getText());
				if(basePrice < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException nfe){
				JOptionPane.showMessageDialog(win,
					    "Please enter a valid numerical value for the base price.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);			
				correct = false;
			}			
			try {
				prepTime = Integer.parseInt(prepTF.getText());
				if(prepTime < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException nfe){
				JOptionPane.showMessageDialog(win,
					    "Please enter a valid numerical value for the prep time.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);	
				correct = false;
			}


			try {
				cookTime = Integer.parseInt(cookTF.getText());
				if(cookTime < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException nfe){
				JOptionPane.showMessageDialog(win,
					    "Please enter a valid numerical value for the cook time.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);	
				correct = false;
			}

			try {
				ovenSpace = Integer.parseInt(ovenSpaceTF.getText());
				if(ovenSpace < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException nfe){
				JOptionPane.showMessageDialog(win,
					    "Please enter a valid numerical value for the oven space.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);	
				correct = false;
			}

			try {
				wholeTopping = Float.valueOf(topPriceTF.getText());
				if(wholeTopping < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException nfe){
				JOptionPane.showMessageDialog(win,
					    "Please enter a valid numerical value for the whole topping price.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);			
				correct = false;
			}
			
			if(wholeTopping == 0 && toppingsCB.isSelected() ) {
				JOptionPane.showMessageDialog(win,
					    "Cannot have whole topping cost of 0 if toppings is selected.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;				
			}			
			
			if(correct) {
				if(title.equals("New Menu Item Wizard")) {
					if(ms.menuItemExists(itemName)) {
						JOptionPane.showMessageDialog(win,
							    "Menu item name already exists.",
							    "Input Error",
							    JOptionPane.ERROR_MESSAGE);
								nameTF.setText("");
						return;
					}
				} else {
					ms.removeMenuItem(name);
				}
				Object[] newToppings = toppingsList.getSelectedValues();
				ArrayList <String> temp = new ArrayList<String>();
				for(int i = 0; i < newToppings.length; i++){
					temp.add(newToppings[i].toString());
				}
				if(toppingsCB.isSelected()) {
					MenuItem mi = new MenuItem(itemName, basePrice, prepTime, cookTime, ovenSpace, wholeTopping, halfableCB.isSelected(), temp);
				} else {
					MenuItem mi = new MenuItem(itemName, basePrice, prepTime, cookTime, ovenSpace, 0, false, null);
				}
				win.dispose();
				ManagerScreen.getNewScreen();
			}
		}
	}
}
