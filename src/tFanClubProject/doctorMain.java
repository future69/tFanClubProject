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
	
	private JTextField textFieldPres;
	

	/**
	 * Create the frame.
	 */
	public doctorMain() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldPname = new JTextField();
		textFieldPname.setBounds(199, 87, 116, 20);
		contentPane.add(textFieldPname);
		textFieldPname.setColumns(10);
		
		JLabel lblheader = new JLabel("Doctor");
		lblheader.setBounds(70, 30, 79, 20);
		lblheader.setFont(new Font("Verdana", Font.PLAIN, 18));
		contentPane.add(lblheader);
		
		JLabel lblPname = new JLabel("Search :");
		lblPname.setBounds(147, 90, 79, 14);
		contentPane.add(lblPname);
		
		JLabel lblPrescription = new JLabel("Search Prescriptions:");
		lblPrescription.setBounds(70, 131, 200, 15);
		contentPane.add(lblPrescription);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(320, 86, 89, 20);
		contentPane.add(btnSearch);
		
		JButton btnSearch2 = new JButton("Search");
		btnSearch2.setBounds(320, 128, 89, 20);
		contentPane.add(btnSearch2);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(320, 40, 89, 30);
		contentPane.add(btnLogout);
		
		textFieldPres = new JTextField();
		textFieldPres.setBounds(199, 128, 116, 20);
		contentPane.add(textFieldPres);
		textFieldPres.setColumns(10);
		
		JLabel lblProblem = new JLabel("");
		lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblProblem.setForeground(Color.RED);
		lblProblem.setBounds(110, 60, 205, 35);
		contentPane.add(lblProblem);
		
	
		btnSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String prescriptions = textFieldPres.getText();
				if (textFieldPres.getText().isEmpty()) {
					lblProblem.setText("Please enter Prescription Name");
				}
			}
		});
		
		
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

		
		

				
				
	

	
	
	
	
