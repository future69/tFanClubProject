package tFanClubProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JFormattedTextField;

public class addUserAdmin extends JFrame {
	

	// For the confirm button to know which method and SQL statement to run  
	int whichPanel = 1;
	
	private JPanel contentPane;
	private JTextField textFieldUsernameAdd;
	private JTextField textFieldFirstNameAdd;
	private JTextField textFieldLastNameAdd;
	private JPasswordField passwordFieldAdd;
	private JTextField textFieldDOB;
	private JTextField textFieldEmail;
	private JTextField textFieldPatientID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String username = null;
					addUserAdmin frame = new addUserAdmin(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public addUserAdmin(String username) {
		//The username is passed here so that when the user goes back to homepage we know 
		//who this user is
		String accountUsername = username;
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelAdd = new JPanel();

		tabbedPane.addTab("Admin", null, panelAdd, null);
		panelAdd.setLayout(new MigLayout("", "[][][][][][][grow][][grow]", "[][][][][][][]"));
		
		JLabel lblUserType = new JLabel("User Type : ");
		panelAdd.add(lblUserType, "cell 5 1,alignx trailing");
		
		JLabel lblUsername = new JLabel("Username : ");
		panelAdd.add(lblUsername, "cell 5 2,alignx trailing");
		
		textFieldUsernameAdd = new JTextField();
		panelAdd.add(textFieldUsernameAdd, "cell 6 2,growx");
		textFieldUsernameAdd.setColumns(10);
		
		JLabel lblDOB = new JLabel("Date of birth : ");
		panelAdd.add(lblDOB, "cell 7 2,alignx trailing");
		
		textFieldDOB = new JTextField();
		textFieldDOB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.print(textFieldDOB.getText());
				if (textFieldDOB.getText() == "DDMMYYYY"){
						textFieldDOB.setText(null);
				}
			}
		});
		textFieldDOB.setForeground(Color.BLACK);
		textFieldDOB.setText("DDMMYYYY");
		panelAdd.add(textFieldDOB, "cell 8 2,growx");
		textFieldDOB.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password : ");
		panelAdd.add(lblPassword, "cell 5 3,alignx trailing");
		
		passwordFieldAdd = new JPasswordField();
		panelAdd.add(passwordFieldAdd, "cell 6 3,growx");
		
		JLabel lblEmail = new JLabel("Email : ");
		panelAdd.add(lblEmail, "cell 7 3,alignx trailing");
		
		textFieldEmail = new JTextField();
		panelAdd.add(textFieldEmail, "cell 8 3,growx");
		textFieldEmail.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name : ");
		panelAdd.add(lblFirstName, "cell 5 4,alignx trailing");
		
		textFieldFirstNameAdd = new JTextField();
		panelAdd.add(textFieldFirstNameAdd, "cell 6 4,growx");
		textFieldFirstNameAdd.setColumns(10);
		
		JLabel lblPatientID = new JLabel("PatientID : ");
		lblPatientID.setEnabled(false);
		panelAdd.add(lblPatientID, "cell 7 4,alignx trailing");
		
		textFieldPatientID = new JTextField();
		textFieldPatientID.setEnabled(false);
		panelAdd.add(textFieldPatientID, "cell 8 4,growx");
		textFieldPatientID.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name : ");
		panelAdd.add(lblLastName, "cell 5 5,alignx trailing");
		
		textFieldLastNameAdd = new JTextField();
		panelAdd.add(textFieldLastNameAdd, "cell 6 5,growx");
		textFieldLastNameAdd.setColumns(10);
		
		JLabel lblErrorMessage = new JLabel("");
		lblErrorMessage.setForeground(Color.RED);
		panelAdd.add(lblErrorMessage, "cell 6 6");
		contentPane.setLayout(new MigLayout("", "[593px]", "[285px][23px][23px]"));
		contentPane.add(tabbedPane, "cell 0 0,grow");
		
		JPanel panelUpdate = new JPanel();
		tabbedPane.addTab("Update", null, panelUpdate, null);
		panelUpdate.setLayout(new MigLayout("", "[7px]", "[8px]"));
		
		//Back button to return to homepage
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame homepage = new homePageAdmin(accountUsername);
				homepage.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnBack, "flowx,cell 0 1,alignx right");
		
		JComboBox comboBoxAdd = new JComboBox();
		comboBoxAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userType = comboBoxAdd.getSelectedIndex();
				switch(userType) {
					case 0:
						lblDOB.setEnabled(false);
						lblEmail.setEnabled(false);
						textFieldDOB.setEnabled(false);
						textFieldEmail.setEnabled(false);
						lblFirstName.setText("First Name : ");
						lblLastName.setText("Last Name : ");
						lblPatientID.setEnabled(false);
						textFieldPatientID.setEnabled(false);
						break;
					case 1:
						lblDOB.setEnabled(false);
						lblEmail.setEnabled(false);
						textFieldDOB.setEnabled(false);
						textFieldEmail.setEnabled(false);
						lblFirstName.setText("First Name : ");
						lblLastName.setText("Last Name : ");
						lblPatientID.setEnabled(true);
						textFieldPatientID.setEnabled(true);
						break;
					case 2:
						lblDOB.setEnabled(true);
						lblEmail.setEnabled(true);
						textFieldDOB.setEnabled(true);
						textFieldEmail.setEnabled(true);
						lblFirstName.setText("First Name : ");
						lblLastName.setText("Last Name : ");
						lblPatientID.setEnabled(false);
						textFieldPatientID.setEnabled(false);
						break;
					case 3:
						lblDOB.setEnabled(false);
						lblEmail.setEnabled(false);
						textFieldDOB.setEnabled(false);
						textFieldEmail.setEnabled(false);
						lblFirstName.setText("Pharmacy Name : ");
						lblLastName.setText("Pharmacy Address : ");
						lblPatientID.setEnabled(false);
						textFieldPatientID.setEnabled(false);
						break;
				}
			}
		});
		comboBoxAdd.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Doctor", "Patient", "Pharmacist"}));
		comboBoxAdd.setSelectedIndex(0);
		panelAdd.add(comboBoxAdd, "cell 6 1,alignx right");
		
		
		//Confirm button
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblErrorMessage.setText("");
				
				
				int userType = comboBoxAdd.getSelectedIndex();
				String username = textFieldUsernameAdd.getText();
				char[] password = passwordFieldAdd.getPassword();
				String fName = textFieldFirstNameAdd.getText();
				String lName = textFieldLastNameAdd.getText();
				String DOB = textFieldDOB.getText();
				String email = textFieldEmail.getText();
				
				if(checkNull(username, password, fName, lName) == true) {
					lblErrorMessage.setText("Please key in all required fields");
				}
				
				else {
					
					addUserAdminController adminCon = new addUserAdminController();
					
				switch(userType) {
				
				//Add admin
				case 0:
					if(adminCon.addUserAdmin(username, password, fName, lName) == true) {
						lblErrorMessage.setText("Success!");
						successReset();
						break;
					}
				case 1:
					//int patientID = Integer.valueOf(textFieldPatientID.getText());
					int patientID = 0;
					try {
						patientID = Integer.parseInt(textFieldPatientID.getText());
					} catch(NumberFormatException r) {
						lblErrorMessage.setText("Please enter patient ID (2 digit number)");
						break;
					}
					if(adminCon.addUserDoctor(username, password, fName, lName, patientID) == true) {
						lblErrorMessage.setText("Success!");
						successReset();
						break;
					}
				case 2:
					if(adminCon.addUserPatient(username, password, fName, lName, DOB, email)) {
						lblErrorMessage.setText("Success!");
						successReset();
						break;
					}
				case 3:
					if(adminCon.addUserPharmacist(username, password, fName, lName)) {
						lblErrorMessage.setText("Success!");
						successReset();
						break;
					}
				}
				}

			}
		});
		contentPane.add(btnConfirm, "cell 0 1,alignx right");
		
	}
	
	//Check if the values are empty
	public boolean checkNull(String username, char[] password, String fName, String lName) {
		
		if(username.isBlank() || String.valueOf(password).isBlank() || fName.isBlank() || lName.isBlank()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//Just refreshes all the textField
	public void successReset() {
		textFieldUsernameAdd.setText(null);
		passwordFieldAdd.setText(null);
		textFieldFirstNameAdd.setText(null);
		textFieldLastNameAdd.setText(null);
		textFieldDOB.setText(null);
		textFieldEmail.setText(null);
		textFieldPatientID.setText(null);
	}
	
}
