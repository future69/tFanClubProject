package tFanClubProject;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class doctorMain extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPname;
	private JTable table;
	
	/*Connection code*/
	Connection connection = null;
	Connection conn = null;
	private JTable table_1;
	
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
		setBounds(100, 100, 626, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldPname = new JTextField();
		textFieldPname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					connection = dbConnector();
					Class.forName("org.sqlite.JDBC");
					//Set this path to where  you put your database file in your computer
					Connection conn = DriverManager.getConnection("jdbc:sqlite:DatabaseFiles/userInfo_3.db");
					String query=" SELECT * FROM Patient where username=?";
					PreparedStatement pst= connection.prepareStatement(query);
					pst.setString(1,textFieldPname.getText() );
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
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
		btnSearch.setBounds(281, 144, 79, 30);
		contentPane.add(btnSearch);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(320, 29, 89, 30);
		contentPane.add(btnLogout);
		
		JLabel lblProblem = new JLabel("");
		lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblProblem.setForeground(Color.RED);
		lblProblem.setBounds(110, 60, 205, 35);
		contentPane.add(lblProblem);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 592, 166);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	

				try {
					connection = dbConnector();
					Class.forName("org.sqlite.JDBC");
					//Set this path to where  you put your database file in your computer
					Connection conn = DriverManager.getConnection("jdbc:sqlite:DatabaseFiles/userInfo_3.db");
					String query=" SELECT * FROM Patient";
					PreparedStatement pst= connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch (Exception f) {
					f.printStackTrace();
				}
				
				
				String patients = textFieldPname.getText();
				
				//Check null
				if (textFieldPname.getText().isEmpty()) {
					lblProblem.setText("Please enter Patient Name");
				}
				
			}
			
			
		});
	}
}			

		
		

				
				
	

	
	
	
	
