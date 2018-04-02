/**
 * 
 */

/**
 * @author Admin
 *
 */

import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
import java.util.*;

public class ToppingWizard implements ActionListener{

	private JFrame win;
	private JButton uncheckAllBUT, checkAllBUT, cancelBUT, createBUT;
	private JLabel nameLBL, directionsLBL;
	private JTextField nameTF;
	private JScrollPane itemsSP;
	private JPanel itemsPanel;
	private JList list;
	private MenuItemStorage ms = MenuItemStorage.getMenuItemStorage();
	
	public ToppingWizard(){
		
	}
	
	public void initComponents(){
		win = new JFrame("Topping WIzard");
		win.setSize(400,600);
		win.setResizable(false);
		win.setLayout(null);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/* Initialize Components */
		nameLBL = new JLabel("Topping: ");
		nameTF = new JTextField("");
		directionsLBL = new JLabel("Please highlight all items that this topping can be applied to.");
		itemsPanel = new JPanel();
		itemsSP = new JScrollPane(itemsPanel);
		ArrayList<String> menuItems = new ArrayList<String>(ms.getMenuItemMap().keySet());
		for(int i = 0; i < menuItems.size(); i++){
			MenuItem temp = (MenuItem)ms.getMenuItem(menuItems.get(i));
			if(temp.getWholeToppingCost() == 0.00){
				menuItems.remove(i);
				i--;
			}
		}
		list = new JList(menuItems.toArray());
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setSelectionForeground(Color.BLUE);
		itemsPanel.add(list);
		uncheckAllBUT = new JButton("Unselect All");
		uncheckAllBUT.addActionListener(this);
		checkAllBUT = new JButton("Select All");
		checkAllBUT.addActionListener(this);
		cancelBUT = new JButton("Cancel");
		cancelBUT.addActionListener(this);
		createBUT = new JButton("Finish");
		createBUT.addActionListener(this);
		/* Size Components */
		nameLBL.setSize(150,50);
		nameTF.setSize(150,50);
		directionsLBL.setSize(380,50);
		itemsSP.setSize(375,300);
		checkAllBUT.setSize(175,50);
		uncheckAllBUT.setSize(175,50);
		cancelBUT.setSize(175,50);
		createBUT.setSize(175,50);
		/* Place Components */
		nameLBL.setLocation(10,10);
		nameTF.setLocation(200,10);
		directionsLBL.setLocation(10,60);
		itemsSP.setLocation(10,110);
		checkAllBUT.setLocation(10,425);
		uncheckAllBUT.setLocation(210,425);
		cancelBUT.setLocation(10,515);
		createBUT.setLocation(210,515);
		/* Add Components to Frame */
		win.add(nameLBL);
		win.add(nameTF);
		win.add(directionsLBL);
		win.add(itemsSP);
		win.add(checkAllBUT);
		win.add(uncheckAllBUT);
		win.add(cancelBUT);
		win.add(createBUT);
		win.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == cancelBUT){
			win.dispose();
		}
		if(e.getSource() == checkAllBUT){
			list.setSelectionInterval(0, list.getLastVisibleIndex());
		}
		if(e.getSource() == uncheckAllBUT){
			list.clearSelection();
		}
		if(e.getSource() == createBUT){
			String name = nameTF.getText();
			if(ms.toppingExists(name)) {
				JOptionPane.showMessageDialog(win,
					    "Topping already exists",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
				nameTF.setText("");				
			}
			ms.addTopping(name);
			Object[] selectedItems = list.getSelectedValues();
			for(int i = 0; i < selectedItems.length; i++){
				String item = (String)selectedItems[i];
				ms.addAssociatingTopping(item, name);
			}
			boolean correct = true;
			if(name.equals("")){
				JOptionPane.showMessageDialog(win,
					    "Please enter a topping name.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}
			if(correct){
				ManagerScreen.getNewScreen();
				win.dispose();
			}
		}
		
	}
}
