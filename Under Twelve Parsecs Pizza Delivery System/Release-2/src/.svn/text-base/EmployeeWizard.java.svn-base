/**
 * 
 */

/**
 * @author Matthew Talbot
 *
 */

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class EmployeeWizard implements ActionListener{

	private JFrame win;
	private JLabel firstLBL, lastLBL, idLBL;
	private JTextField firstTF, lastTF, idTF;
	private JCheckBox managerCHB;
	private JButton cancelBUT, resetBUT, createBUT;
	private EmployeeStorage es = EmployeeStorage.getEmployeeStorage();
	private boolean managerStatus = false;
	private String employeeS = null;
	private String title;
	private int mode = 0;
	private Employee employee;
	public EmployeeWizard() {
		
	}
	
	public EmployeeWizard(String string) {
		//Change made here
		if(!string.equals("")) {
			title = "Edit Employee Wizard";
			mode = 1;
		} else {
			title = "New Employee Wizard";
			mode = 0;
		}
		initComponents();
		if(!string.equals("")) {
			employeeS = string;
			employee = es.getEmployee(employeeS);
			firstTF.setText(employee.getFirstName()); 
			lastTF.setText(employee.getLastName()); 
			idTF.setText(employee.getUserID());
			managerCHB.setSelected(employee.isManagerStatus());
		}
	}

	public void initComponents(){
		win = new JFrame(title);
		if(mode == 1) {
			win.setSize(400,650);
		} else {
			win.setSize(400,500);
		}
		win.setResizable(false);
		win.setLayout(null);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/* Initialize Components */
		firstLBL = new JLabel("First Name: ");
		firstTF = new JTextField();
		lastLBL = new JLabel("Last Name: ");
		lastTF = new JTextField();
		idLBL = new JLabel("Employee ID: ");
		idTF = new JTextField();
		managerCHB = new JCheckBox("Manager");
		managerCHB.addActionListener(this);
		resetBUT = new JButton("Reset Password");
		resetBUT.addActionListener(this);
		cancelBUT = new JButton("Cancel");
		cancelBUT.addActionListener(this);
		createBUT = new JButton("Finish");
		createBUT.addActionListener(this);
		/* Size Components */
		firstLBL.setSize(150,50);
		firstTF.setSize(150,50);
		lastLBL.setSize(150,50);
		lastTF.setSize(150,50);
		idLBL.setSize(150,50);
		idTF.setSize(150,50);
		managerCHB.setSize(150,50);
		resetBUT.setSize(150, 50);
		cancelBUT.setSize(150,50);
		createBUT.setSize(150,50);
		/* Place Components */
		firstLBL.setLocation(10,10);
		firstTF.setLocation(200,10);
		lastLBL.setLocation(10,110);
		lastTF.setLocation(200,110);
		idLBL.setLocation(10,210);
		idTF.setLocation(200,210);
		managerCHB.setLocation(150,310);
		if(mode == 1) {
			resetBUT.setLocation(110,410);
			cancelBUT.setLocation(10,510);
			createBUT.setLocation(200,510);
		} else {
			resetBUT.setLocation(110,310);
			cancelBUT.setLocation(10,410);
			createBUT.setLocation(200,410);
		}
		/* Add Components to Frame */
		win.add(firstLBL);
		win.add(firstTF);
		win.add(lastLBL);
		win.add(lastTF);
		win.add(idLBL);
		win.add(idTF);
		win.add(managerCHB);
		if (mode == 1) {
			win.add(resetBUT);
		}
		win.add(cancelBUT);
		win.add(createBUT);
		win.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == cancelBUT){
			win.dispose();
		}
		
		if(e.getSource() == resetBUT) {
			employee.setPassword("Password");
			JOptionPane.showMessageDialog(win, "Password has been reset.", "Password Reset", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(employee.getPassword());
		}
		if(e.getSource() == createBUT){
			boolean correct = true;
			String first = firstTF.getText();
			String last = lastTF.getText();
			String id = idTF.getText();
			
			if(first.equals("")){
				JOptionPane.showMessageDialog(win,
					    "Please enter a first name.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}
			if(last.equals("")){
				JOptionPane.showMessageDialog(win,
					    "Please enter a last name.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}
			if(id.equals("")){
				JOptionPane.showMessageDialog(win,
					    "Please enter an employee ID.",
					    "Input Error",
					    JOptionPane.ERROR_MESSAGE);
				correct = false;
			}
			if(correct){	
				if(employeeS != null)
					es.removeEmployee(employeeS);
				if(es.employeeExists(id)) {
					JOptionPane.showMessageDialog(win,
						    "Employee ID already exists. Enter a new Employee ID.",
						    "Input Error",
						    JOptionPane.ERROR_MESSAGE);
					idTF.setText("");
					correct = false;
				} else {
					if(employee != null && !employee.getPassword().equals("Password")) {
						Employee e2 = new Employee( idTF.getText(),lastTF.getText(), firstTF.getText(), employee.getPassword(), managerStatus );
					} else {
						Employee e2 = new Employee( idTF.getText(),lastTF.getText(), firstTF.getText(), "Password", managerStatus );
					}
					this.win.dispose();
					ManagerScreen.getNewScreen();
				}
			}
		}
		if(e.getSource() == managerCHB) {
			managerStatus = !managerStatus;
		}		
	}	
}
