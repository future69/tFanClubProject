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

	private JPanel contentPane;
	private JTextField txtPrescriptionsName;
	private JLabel lblNewLabel;
	private String patientId;

	/* Connection code */
	Connection connection = null;
	Connection conn = null;
	private JScrollPane scrollPane2;

	// Establish connection with the database
	public static Connection dbConnector() {

		try {
			Class.forName("org.sqlite.JDBC");
			// Set this path to where you put your database file in your computer
			Connection conn = DriverManager.getConnection("jdbc:sqlite:DatabaseFiles/userInfo_3.db");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

	public void loadTable() {
		try {
			connection = dbConnector();
			Class.forName("org.sqlite.JDBC");
			// Set this path to where you put your database file in your computer
			String query = " SELECT dateDispensed,medicationName FROM Patient INNER JOIN Prescription on PATIENT.patientID = PRESCRIPTION.patientID where PATIENT.patientID=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, patientId);
			ResultSet rs = pst.executeQuery();
			//table_2.setModel(DbUtils.resultSetToTableModel(rs));
			while (rs.next()) {

				System.out.print(rs.getString("medicationName"));
				System.out.println(rs.getDate("dateDispensed"));
			}
		} catch (Exception f) {
			f.printStackTrace();

		}
	}
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					doctorInfo frame = new doctorInfo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public doctorInfo(String patientId) {
		// TODO Auto-generated constructor stub
		this.patientId = patientId;
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

			}
		});
		btnNewButton.setBounds(481, 113, 89, 28);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("History of <Patient Name>");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(32, 152, 220, 39);
		contentPane.add(lblNewLabel_1);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(36, 224, 534, 191);
		contentPane.add(scrollPane2);
		JTable table_2 = new JTable();
		scrollPane2.setViewportView(table_2);
		try {
			connection = dbConnector();
			Class.forName("org.sqlite.JDBC");
			// Set this path to where you put your database file in your computer
			String query = " SELECT dateDispensed,medicationName FROM Patient INNER JOIN Prescription on PATIENT.patientID = PRESCRIPTION.patientID where PATIENT.patientID=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, patientId);
			ResultSet rs = pst.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(rs));
//			while (rs.next()) {
//
//				System.out.print(rs.getString("medicationName"));
//				System.out.println(rs.getDate("dateDispensed"));
//			}
		} catch (Exception f) {
			f.printStackTrace();

		}
	}
}
