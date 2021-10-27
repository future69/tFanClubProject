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
import java.awt.Window;

public class doctorMain extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPname;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctorMain frame = new doctorMain();
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
	public doctorMain() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldPname = new JTextField();
		textFieldPname.setFont(new Font("Courier New", Font.ITALIC, 11));
		textFieldPname.setText("Patient's Name");
		textFieldPname.setBounds(128, 150, 143, 20);
		contentPane.add(textFieldPname);
		textFieldPname.setColumns(10);
		
		JLabel lblheader = new JLabel("Doctor");
		lblheader.setBounds(10, 29, 99, 52);
		lblheader.setFont(new Font("Verdana", Font.BOLD, 24));
		contentPane.add(lblheader);
		
		JLabel lblPname = new JLabel("Search :");
		lblPname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPname.setBounds(47, 141, 79, 30);
		contentPane.add(lblPname);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(281, 150, 70, 21);
		contentPane.add(btnSearch);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(320, 29, 89, 30);
		contentPane.add(btnLogout);
		
		JLabel lblProblem = new JLabel("");
		lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblProblem.setForeground(Color.RED);
		lblProblem.setBounds(110, 60, 205, 35);
		contentPane.add(lblProblem);
		
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				String patients = textFieldPname.getText();
				
				//Check null
				if (textFieldPname.getText().isEmpty()) {
					lblProblem.setText("Please enter Patient Name");
				}
				
			}
			
			
		});
	}
	
}			

		
		

				
				
	

	
	
	
	
