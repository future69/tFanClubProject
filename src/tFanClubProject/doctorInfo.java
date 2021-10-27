package tFanClubProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;

public class doctorInfo extends JFrame {
	
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
	private JTextField txtPrescriptionsName;
	private JLabel lblNewLabel;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctorInfo frame = new doctorInfo();
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
	public doctorInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblheader = new JLabel("Doctor");
		lblheader.setFont(new Font("Verdana", Font.BOLD, 24));
		lblheader.setBounds(32, 49, 99, 52);
		contentPane.add(lblheader);
		
		txtPrescriptionsName = new JTextField();
		txtPrescriptionsName.setFont(new Font("Tahoma", Font.ITALIC, 11));
		txtPrescriptionsName.setText("Prescriptions Name");
		txtPrescriptionsName.setBounds(296, 115, 175, 26);
		contentPane.add(txtPrescriptionsName);
		txtPrescriptionsName.setColumns(10);
		
		lblNewLabel = new JLabel("Search Prescriptions:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(128, 112, 165, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String query="select * from Prescription";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(481, 113, 89, 28);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("History of <Patient Name>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(32, 152, 220, 39);
		contentPane.add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 229, 516, 183);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
