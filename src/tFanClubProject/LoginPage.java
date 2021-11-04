package tFanClubProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

		
	}
	
	private JPasswordField passwordField;
	

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[79px][10px][][116px][][][]", "[35px][20px][20px][23px][][]"));
		
		JLabel lblProblem = new JLabel("");
		lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblProblem.setForeground(Color.RED);
		contentPane.add(lblProblem, "cell 3 1,grow");
		
		JLabel lblUsername = new JLabel("Username :");
		contentPane.add(lblUsername, "cell 2 2,growx,aligny center");
		
		textFieldUsername = new JTextField();
		contentPane.add(textFieldUsername, "cell 3 2,growx,aligny top");
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		contentPane.add(lblPassword, "cell 2 3,growx,aligny center");
		
		passwordField = new JPasswordField();
		contentPane.add(passwordField, "cell 3 3,growx,aligny top");
		
		
		JButton btnLogin = new JButton("Login");
		contentPane.add(btnLogin, "cell 3 4,alignx center,aligny top");
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				String username = textFieldUsername.getText();
				char[] password = passwordField.getPassword();
				
				//Check null
				if (CheckNull(username, password) == true) {
					lblProblem.setText("Please enter both username and password");
				}
				
				else {
					//Calling method in controller class
					LoginPageController loginControl = new LoginPageController();
					String role = loginControl.passUserInfo(username, password).trim();
					if(role != null){
						switch(role) {
						case "Admin":
							JFrame homePageAdmin = new doctorMain(username);
							homePageAdmin.setVisible(true);
							dispose();
						case "Doctor":
							JFrame doctorMain = new doctorMain(username);
							doctorMain.setVisible(true);
							dispose();
						case "Patient":
							JFrame homePagePatient = new homePagePatient(username);
							homePagePatient.setVisible(true);
							dispose();
						}
					}
					else {
						lblProblem.setText("Incorrect username or password");
					}
				}
				
			}
		});
	
	}
	
	//JFrame doctorMain = new doctorMain(username);
	//doctorMain.setVisible(true);
	//dispose();
	
	//Method to check null
	public boolean CheckNull(String username, char[] password) {
		if (username.isBlank() || String.valueOf(password).isBlank()) {
			return true;
		}
		else {
			return false;
		}
	}
	

	
	
	
	
}
