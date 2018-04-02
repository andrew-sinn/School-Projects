/**
 * 
 */

/**
 * @author Matthew Talbot
 *
 */

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ToppingSub implements ActionListener{

	private JFrame win;
	private JScrollPane toppingsPane, addedPane;
	private JList toppingsList, addedList;
	private JButton addBUT, removeBUT, nextBUT;
	private JCheckBox wholeCHB, rightCHB, leftCHB;
	private MenuItem mi;
	private ArrayList <String> availableToppings;
	private ArrayList <String> addedToppings;
	private OrderedItem currentItem;
	private MenuItemStorage ms = MenuItemStorage.getMenuItemStorage();
	private List<String> addedLeft, addedRight;
	private MainMenu mm;
	private int toppingsCounter;
	private Order current;
	private int mode = 0;
	
	public ToppingSub(MenuItem mi, MainMenu mm, Order current) {
		this.mi = mi;
		this.mm = mm;
		toppingsCounter = 0;
		availableToppings = ms.getAssociatingToppings(mi.getName());
		addedToppings = new ArrayList<String>();
		addedLeft = new ArrayList<String>();
		addedRight = new ArrayList<String>();
		this.current = current;
	}
	
	public ToppingSub(MenuItem mi, MainMenu mm){
		this.mi = mi;
		this.mm = mm;
		toppingsCounter = 0;
		availableToppings = ms.getAssociatingToppings(mi.getName());
		addedToppings = new ArrayList<String>();
		addedLeft = new ArrayList<String>();
		addedRight = new ArrayList<String>();
	}
	
	public void initComponents(){
		win = new JFrame("Toppings");
		win.setSize(800,800);
		win.setResizable(false);
		win.setLayout(null);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/* Initialize Components */
		toppingsList = new JList(availableToppings.toArray());
		addedList = new JList();
		addBUT = new JButton("Add ->");
		addBUT.addActionListener(this);
		removeBUT = new JButton(" <- Remove");
		removeBUT.addActionListener(this);
		nextBUT = new JButton("Done");
		nextBUT.addActionListener(this);
		wholeCHB = new JCheckBox("Whole");
		wholeCHB.addActionListener(this);
		wholeCHB.setSelected(true);
		leftCHB = new JCheckBox("Left Side");
		leftCHB.addActionListener(this);
		rightCHB = new JCheckBox("Right Side");
		rightCHB.addActionListener(this);
		toppingsPane = new JScrollPane(toppingsList);
		addedPane = new JScrollPane(addedList);
		/* Size Components */
		wholeCHB.setSize(100,25);
		leftCHB.setSize(100,25);
		rightCHB.setSize(100,25);
		addBUT.setSize(100,50);
		removeBUT.setSize(100,50);
		nextBUT.setSize(100,50);
		toppingsPane.setSize(300,700);
		addedPane.setSize(300,700);
		/* Place Components */
		toppingsPane.setLocation(15,50);
		wholeCHB.setLocation(10,10);
		leftCHB.setLocation(210,10);
		rightCHB.setLocation(410,10);
		addedPane.setLocation(475,50);
		addBUT.setLocation(350,200);
		removeBUT.setLocation(350,300);
		nextBUT.setLocation(350,600);
		if(!mi.isCanHaveHalfToppings()){
			wholeCHB.setVisible(false);
			leftCHB.setVisible(false);
			rightCHB.setVisible(false);
		}
		/* Add Components to Window */
		win.add(toppingsPane);
		win.add(addedPane);
		win.add(wholeCHB);
		win.add(leftCHB);
		win.add(rightCHB);
		win.add(addBUT);
		win.add(removeBUT);
		win.add(nextBUT);
		win.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == addBUT){
			String prefix = "(W)";
			if(rightCHB.isSelected())
				prefix = "(R)";
			if(leftCHB.isSelected())
				prefix = "(L)";
			Object temp[] = toppingsList.getSelectedValues();
			for(int i = 0; i < temp.length; i++){
				String tempb = prefix+(String)temp[i];
				addedToppings.add(tempb);
			}
			addedList.setListData(addedToppings.toArray());
			toppingsCounter ++;
		}
		
		if(e.getSource() == removeBUT){
			Object temp[] = addedList.getSelectedValues();
			for(int i = 0; i < temp.length; i++){
				addedToppings.remove((String)temp[i]);
			}
			addedList.setListData(addedToppings.toArray());
			toppingsCounter --;
		}
		
		if(e.getSource() == nextBUT){
			int indecies[] = new int[addedList.getLastVisibleIndex()+1];
			for(int m = 0; m < indecies.length; m++){
				indecies[m] = m;
			}
			addedList.setSelectedIndices(indecies);
			Object[] addedTotal = addedList.getSelectedValues();
			for(int i = 0; i < addedTotal.length; i++){
				String temp = (String) addedTotal[i];
				if(temp.substring(0,3).equals("(L)")){
					addedLeft.add(temp.substring(3,temp.length()));
				}
				else if(temp.substring(0,3).equals("(R)")){
					addedRight.add(temp.substring(3,temp.length()));
				}
				else if(temp.substring(0,3).equals("(W)")){
					addedRight.add(temp.substring(3,temp.length()));
					addedLeft.add(temp.substring(3,temp.length()));
				}
			}
			currentItem = new OrderedItem(mi.getName(), current, addedLeft, addedRight);
			win.dispose();
			if(current != null) {
				mode = 1;
			}
			mm.receiveOrderedItem(currentItem, mode);
		}
		
		if(e.getSource() == wholeCHB){
			if(wholeCHB.isSelected()){
				rightCHB.setSelected(false);
				leftCHB.setSelected(false);
			}
			if(!wholeCHB.isSelected()&&!leftCHB.isSelected()&&!rightCHB.isSelected())
				wholeCHB.setSelected(true);
		}
		
		if(e.getSource() == leftCHB){
			if(leftCHB.isSelected()){
				rightCHB.setSelected(false);
				wholeCHB.setSelected(false);
			}
			if(!wholeCHB.isSelected()&&!leftCHB.isSelected()&&!rightCHB.isSelected())
				leftCHB.setSelected(true);			
		}
		if(e.getSource() == rightCHB){
			if(rightCHB.isSelected()){
				wholeCHB.setSelected(false);
				leftCHB.setSelected(false);
			}
			if(!wholeCHB.isSelected()&&!leftCHB.isSelected()&&!rightCHB.isSelected())
				rightCHB.setSelected(true);
		}
		
	}
	
}
