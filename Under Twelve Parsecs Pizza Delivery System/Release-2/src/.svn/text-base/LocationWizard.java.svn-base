/**
 * 
 */

/**
 * @author Matthew Talbot
 *
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class LocationWizard implements ActionListener{

	private JFrame win;
	private JButton cancelBUT, createBUT;
	private JLabel nameLBL, timeLBL;
	private JTextField nameTF, timeTF;
	private String locationS = null;
	private String title;
	private DeliveryLocationStorage dls =  DeliveryLocationStorage.getDeliveryLocationStorage();
	
	public LocationWizard(String string) {
		if(!string.equals("")) {
			title = "Edit Delivery Location Wizard";
		} else {
			title = "New Delivery Location Wizard";
		}		
		initComponents();
		if(!string.equals("")) {
			locationS = string;
			nameTF.setText(dls.getDeliveryLocation(locationS).getLocationName());
			timeTF.setText(Integer.toString(dls.getDeliveryLocation(locationS).getTimeToLocation()));
		}
	}

	public void initComponents(){
		win = new JFrame(title);
		win.setLayout(null);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setSize(400,300);
		win.setResizable(false);
		/* Initialize Components */
		nameLBL = new JLabel("Location: ");
		nameTF = new JTextField();
		timeLBL = new JLabel("Delivery Time (minutes): ");
		timeTF = new JTextField();
		cancelBUT = new JButton("Cancel");
		cancelBUT.addActionListener(this);
		createBUT = new JButton("Finish");
		createBUT.addActionListener(this);
		/* Size Components */
		nameLBL.setSize(150,50);
		nameTF.setSize(150,50);
		timeLBL.setSize(150,50);
		timeTF.setSize(150,50);
		cancelBUT.setSize(150,50);
		createBUT.setSize(150,50);
		/* Place Components */
		nameLBL.setLocation(10,10);
		nameTF.setLocation(200,10);
		timeLBL.setLocation(10,110);
		timeTF.setLocation(200,110);
		cancelBUT.setLocation(10,210);
		createBUT.setLocation(200,210);
		/* Add Components to Frame */
		win.add(nameLBL);
		win.add(nameTF);
		win.add(timeLBL);
		win.add(timeTF);
		win.add(cancelBUT);
		win.add(createBUT);
		win.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == cancelBUT){
			win.dispose();
		}
		if(e.getSource() == createBUT){
			String name = nameTF.getText();
			String temp = timeTF.getText();
			int time = 0;
			boolean correct = true;
			try{
				time = Integer.parseInt(temp);
			}
			catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(win,
					    "Please enter a numerical value for delivery time.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}
			
			if(name.equals("")){
				JOptionPane.showMessageDialog(win,
					    "Please enter a location name.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}
			if(correct){
				if(locationS != null)
					dls.removeDeliveryLocation(locationS);
				if(dls.deliveryLocationExists(name)) {
					JOptionPane.showMessageDialog(win,
						    "Location name already exists.",
						    "Input Error",
						    JOptionPane.ERROR_MESSAGE);
					nameTF.setText("");
					correct = false;
				} else {
					DeliveryLocation l = new DeliveryLocation(name, Integer.parseInt(timeTF.getText()));
					ManagerScreen.getNewScreen();
					win.dispose();
				}
			}
		}
	}

}
