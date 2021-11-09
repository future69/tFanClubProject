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

public class homePagePharmacist extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearchPrescription;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String username = null;
					homePagePharmacist frame = new homePagePharmacist(username);
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
	public homePagePharmacist(String username) {
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Gets full name
		homePagePharmacistController pharmacistController = new homePagePharmacistController();
		String fullName = pharmacistController.passPharmacistHomepageInfo(username);
		contentPane.setLayout(new MigLayout("", "[][1px][336px,grow][][][4px][60px][20px][100px]", "[1px][30px][35px][20px]"));
		JLabel lblWelcome = new JLabel("New label");
		contentPane.add(lblWelcome, "cell 0 1,growx,aligny top");
		
		//Applies the pharmaName and pharmaAdd
		lblWelcome.setText("Welcome " + fullName);
		
		JLabel lblErrorMsg1 = new JLabel("");
		lblErrorMsg1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblErrorMsg1.setForeground(Color.RED);
		contentPane.add(lblErrorMsg1, "cell 2 2,grow");
		
		JButton btnUpdatePrescription = new JButton("Update Prescription");
		btnUpdatePrescription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStatusPharmacist updateStatus = new updateStatusPharmacist(username);
				updateStatus.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnUpdatePrescription, "cell 8 2");
		
		JLabel lblSearchPres = new JLabel("Search Prescription : ");
		contentPane.add(lblSearchPres, "cell 0 3,growx,aligny center");
		
		JLabel lblErrorMsg = new JLabel("");
		lblErrorMsg.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblErrorMsg.setForeground(Color.RED);
		contentPane.add(lblErrorMsg, "cell 2 2,grow");
		
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame loginPage = new LoginPage();
				loginPage.setVisible(true);
				
				dispose();
			}
		});
		contentPane.add(btnLogOut, "cell 8 1,growx,aligny bottom");
		
		textFieldSearchPrescription = new JTextField();
		contentPane.add(textFieldSearchPrescription, "cell 2 3,growx");
		textFieldSearchPrescription.setColumns(10);
		
		
		
		
	}
		
	
}
