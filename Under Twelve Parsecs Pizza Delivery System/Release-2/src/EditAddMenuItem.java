import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

/**
 * Class to add menu items while editing the order
 * 
 * @author Andrew Sinn
 *
 */

public class EditAddMenuItem implements ActionListener {

	private JFrame win;
	private JPanel buttonPanel;
	private JScrollPane buttonSP;
	private JButton cancelBUT, nextBUT;
	private MenuItemStorage ms = MenuItemStorage.getMenuItemStorage();
	private JList list = null;
	private String itemName;
	private Order current;
	
	/**
	 * Constructor
	 */
	
	public EditAddMenuItem(Order current) {
		this.current = current;
		initComponents();
	}	
	
	/**
	 * Initialize Components
	 */
	
	public void initComponents() {
		win = new JFrame("Menu Items");
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

		buttonPanel.removeAll();
		ArrayList<String> al = new ArrayList<String>(ms.getMenuItemMap().keySet());
		list = new JList(al.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		buttonPanel.add(list);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nextBUT) {
			int select = list.getSelectedIndex();
			if(select == -1) {
				JOptionPane.showMessageDialog(win,
						"No menu items have been selected.",
						"Selection Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			win.dispose();
			itemName = (String) list.getSelectedValue();
			MenuItem item = MenuItemStorage.getMenuItemStorage().getMenuItemMap().get(itemName);
			if(item.getWholeToppingCost() != 0) {
				ToppingSub getToppings = new ToppingSub(MenuItemStorage.getMenuItemStorage().getMenuItemMap().get(itemName), MainMenu.getScreen(), current);
				getToppings.initComponents();
			} else {
				OrderedItem theItem = new OrderedItem(itemName, current, null, null);
				MainMenu.getScreen().receiveOrderedItem(theItem, 1);
			}
		}
		if(e.getSource() == cancelBUT) {
			win.dispose();
		}
	}
}
