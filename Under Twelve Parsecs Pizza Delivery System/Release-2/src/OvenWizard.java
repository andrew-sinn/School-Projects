/**
 * author: Matthew Talbot
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class OvenWizard implements ActionListener{

	private JFrame win;
	private JButton cancelBUT, createBUT;
	private JLabel capLBL;
	private JTextField capTF;
	private OvenKitchen oks = OvenKitchen.getInstance();
	private String title;
	private int edit;
	
	public OvenWizard(int number) {
		if(number == -1) {
			title = "New Oven Wizard";
		} else {
			title = "Edit Oven Wizard";
		}
		edit = number;
		initComponents();
		if(number != -1) {
			capTF.setText(Integer.toString(oks.getCookingOvens().get(number).getMaxOvenSpace()));
		}
	}

	public void initComponents(){
		win = new JFrame(title);
		win.setResizable(false);
		win.setLayout(null);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setSize(400,200);
		/* Initialize Components */
		capLBL = new JLabel("Capacity (units): ");
		capTF = new JTextField();
		cancelBUT = new JButton("Cancel");
		cancelBUT.addActionListener(this);
		createBUT = new JButton("Finish");
		createBUT.addActionListener(this);
		/* Size Components */
		capLBL.setSize(150,50);
		capTF.setSize(150,50);
		cancelBUT.setSize(150,50);
		createBUT.setSize(150,50);
		/* Place Components */
		capLBL.setLocation(10,10);
		capTF.setLocation(200,10);
		cancelBUT.setLocation(10,110);
		createBUT.setLocation(200,110);
		/* Add Components to Frame */
		win.add(capLBL);
		win.add(capTF);
		win.add(cancelBUT);
		win.add(createBUT);
		win.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == cancelBUT){
			win.dispose();
		}
		if(e.getSource() == createBUT){
			String temp = capTF.getText();
			int cap = 0;
			boolean correct = true;
			try{
				cap = Integer.parseInt(temp);
				if(cap <= 0) {
					throw new NumberFormatException();
				}
			}
			catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(win,
					    "Please enter a valid numerical value for Capacity.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}
			if(correct) {
				if(edit != -1)
					oks.removeOven(oks.getCookingOvens().get(edit));
				Oven o = new Oven(cap);
				win.dispose();
				ManagerScreen.getNewScreen();
			}
		}
	}

}

