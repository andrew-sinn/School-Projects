/*
 * Author: Matthew Talbot
 * 
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

/* TO DO
 * 1) Validate the Login
 * 2) Check to see if login is manger
 */

public class LoginScreen implements ActionListener {

	private JFrame win;
	private JButton logBUT;
	private JTextField idTF;
	private JPasswordField passPF;
	private JLabel logoLBL, passLBL, idLBL;
	private EmployeeStorage em;
	private MenuItemStorage ms;
	private int modeType;

	public LoginScreen( int modeType ) {
		em = EmployeeStorage.getEmployeeStorage();
		if( em.getEmployeeMap().isEmpty() ) {
			Employee e = new Employee("Admin", "Admin", "Admin", "Password", true);
		}
		this.modeType = modeType;
	}

	public void initComponents() {
		/* Establish the Frame for the window */
		win = new JFrame("System Login");
		win.setLayout(null);
		win.setSize(400, 600);
		win.setResizable(false);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/* Set the dimensions and locations of all components */
		logBUT = new JButton("Login");
		logBUT.setSize(200, 50);
		logBUT.setLocation(185, 515);
		logBUT.addActionListener(this);
		idTF = new JTextField("");
		idTF.setSize(200, 50);
		idTF.setLocation(185, 315);
		idLBL = new JLabel("Employee ID:");
		idLBL.setSize(200, 50);
		idLBL.setLocation(15, 315);
		passPF = new JPasswordField("");
		passPF.setSize(200, 50);
		passPF.setLocation(185, 415);
		passLBL = new JLabel("Password:");
		passLBL.setSize(200, 50);
		passLBL.setLocation(15, 415);
		logoLBL = new JLabel("Company Logo");
		logoLBL.setSize(400, 150);
		logoLBL.setLocation(15, 15);
		logoLBL.setForeground(Color.BLUE);
		/* Add all the components to the Frame */
		win.add(idLBL);
		win.add(passLBL);
		win.add(logoLBL);
		win.add(idTF);
		win.add(passPF);
		win.add(logBUT);
		/* Make the Frame visible */
		win.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logBUT) {
			String id = idTF.getText();
			String pass = passPF.getText();
			if( modeType == 0 && Employee.managerLogin(id, pass) != null ) {
				win.dispose();
				ManagerScreen.getScreen( Employee.managerLogin( id, pass ) );
			} else if ( modeType == 1 && Employee.generalLogin(id, pass) != null ) {
				Employee current = EmployeeStorage.getEmployeeStorage().getEmployee(id);
				if(current.getPassword().equals("Password")) {
					String message1 = "Please enter a new password.";
					String message2 = "Your new password is:";
					String [] choices = { "OK" };
					JPasswordField newPass = new JPasswordField(5);
					Object[] array = {message1, message2, newPass};
					JOptionPane option = new JOptionPane (array, JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_OPTION, null, choices, choices[0]);
					JDialog dialog = option.createDialog(win, "Password Reset");
					dialog.show();
					current.setPassword( newPass.getText() );
					EmployeeStorage.getEmployeeStorage().saveSettings();
				}
				win.dispose();
				MainMenu.getNewScreen();
			} else {
				showError();
			}
		}
	}

	public void showError() {
		JOptionPane.showMessageDialog(win,
				"The Employee ID / Password you entered was incorrect.",
				"Invaid Login", JOptionPane.ERROR_MESSAGE);
				idTF.setText("");
				passPF.setText("");
	}

	public static void main(String[] args) {
		PreparationKitchen.getInstance();
		DeliverySystem.getDeliverySystem();
		LoginScreen log = new LoginScreen( 0 );
		log.initComponents();
	}

}
