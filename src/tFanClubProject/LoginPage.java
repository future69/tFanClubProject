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
		contentPane.setLayout(null);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(199, 87, 116, 20);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(110, 90, 79, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(110, 131, 79, 14);
		contentPane.add(lblPassword);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(176, 165, 89, 23);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(199, 128, 116, 20);
		contentPane.add(passwordField);
		
		JLabel lblProblem = new JLabel("");
		lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblProblem.setForeground(Color.RED);
		lblProblem.setBounds(110, 43, 205, 35);
		contentPane.add(lblProblem);
		
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
					
					if(loginControl.passUserInfo(username, password) == true) 
					{
						lblProblem.setText("Login Success");
						JFrame homepage = new homePageAdmin(username);
						
						homepage.setVisible(true);
						dispose();
					}
					else {
						lblProblem.setText("Incorrect username or password");
					}
				}
				
			}
		});
	
	}
	
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
