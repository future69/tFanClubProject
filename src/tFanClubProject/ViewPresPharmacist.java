package tFanClubProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class ViewPresPharmacist extends JFrame {
	
	/*Connection code*/
	Connection connection = null;
	Connection conn = null;
	
	//Establish connection with the database
	public static Connection dbConnector() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			//Set this path to where  you put your database file in your computer
			Connection conn = DriverManager.getConnection("jdbc:sqlite:DatabaseFiles/userInfo_3.db");
			return conn;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	

	private JPanel contentPane;
	private JTextField textFieldSearchPres;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String SearchPres = null;
					ViewPresPharmacist frame = new ViewPresPharmacist(SearchPres);
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
	public ViewPresPharmacist(String SearchPres) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][114px][10px][153px][][][][][127px][][][]", "[35px][23px][23px][][][][][][][]"));
				
		viewPresPharmacistController vPPC = new viewPresPharmacistController(); 

		String [] columnNames = {"Prescription ID", "Medication Name", "Dosage"};
		String [][] data = vPPC.getPrescriptions(SearchPres);;
		
		table = new JTable(data, columnNames);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add (scrollPane, "flowx,cell 9 10");
		
		
		JButton btnUpdateStatus = new JButton("Update Status");
		btnUpdateStatus.setBounds(350, 87, 150, 20);
		btnUpdateStatus.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{				
					JFrame UpdatePresStatus = new UpdatePresStatus();
					UpdatePresStatus.setVisible(true);
					dispose();	
			}
		});
		contentPane.add(btnUpdateStatus);
		
	}
		
	
	
	
}
