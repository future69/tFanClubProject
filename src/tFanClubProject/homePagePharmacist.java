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

public class homePagePharmacist extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearchPres;
	private JTextField textFieldSearchPati;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homePagePharmacist frame = new homePagePharmacist();
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
	public homePagePharmacist() {
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPharmacist = new JLabel("Pharmacist");
		lblPharmacist.setBounds(10, 10, 300, 14);
		contentPane.add(lblPharmacist);
		
		JLabel lblSearchPres = new JLabel("Search Prescription :");
		lblSearchPres.setBounds(110, 90, 300, 14);
		contentPane.add(lblSearchPres);
		
		JLabel lblSearchPati = new JLabel("Search Patient :");
		lblSearchPati.setBounds(110, 131, 300, 14);
		contentPane.add(lblSearchPati);
		
		textFieldSearchPres = new JTextField();
		textFieldSearchPres.setBounds(230, 87, 116, 20);
		contentPane.add(textFieldSearchPres);
		
		textFieldSearchPati = new JTextField();
		textFieldSearchPati.setBounds(230, 128, 116, 20);
		contentPane.add(textFieldSearchPati);
		
		JLabel lblErrorMsg1 = new JLabel("");
		lblErrorMsg1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblErrorMsg1.setForeground(Color.RED);
		lblErrorMsg1.setBounds(110, 43, 205, 35);
		contentPane.add(lblErrorMsg1);
		
		JButton btnSearchPres = new JButton("Search Prescription");
		btnSearchPres.setBounds(350, 87, 150, 20);
		btnSearchPres.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
				String SearchPres = textFieldSearchPres.getText();
				
				if (SearchPres == null )
				{
					lblErrorMsg1.setText("Please fill in Prescription ID");
				}
				else 
				{
					JFrame ViewPresPharmacist = new ViewPresPharmacist(SearchPres);
					
					ViewPresPharmacist.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnSearchPres);
		
		JLabel lblErrorMsg = new JLabel("");
		lblErrorMsg.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblErrorMsg.setForeground(Color.RED);
		lblErrorMsg.setBounds(110, 43, 205, 35);
		contentPane.add(lblErrorMsg);
		
		JButton btnSearchPati = new JButton("Search Patient");
		btnSearchPati.setBounds(350, 128, 150, 20);
		btnSearchPati.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
				String SearchPati = textFieldSearchPati.getText();
				
				if (SearchPati == null)
				{
					lblErrorMsg.setText("Please fill in Patient ID");
				}
				else 
				{
					
				}
				
				
			
			}
		});
		contentPane.add(btnSearchPati);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(430, 20, 100, 20);
		contentPane.add(btnLogOut);
		
		
		
		
	}
		
	
}
