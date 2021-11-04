package tFanClubProject;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class doctorMain extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPname;
	private JTable table;

	/* Connection code */
	Connection connection = null;
	Connection conn = null;
	private JTable table_1;

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
	public doctorMain(String username) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldPname = new JTextField();
		textFieldPname.setText("Patient Name");
		textFieldPname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					connection = dbConnector();
					Class.forName("org.sqlite.JDBC");
					// Set this path to where you put your database file in your computer
					String query = " SELECT * FROM Patient where username=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldPname.getText());
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		textFieldPname.setFont(new Font("Courier New", Font.ITALIC, 11));
		textFieldPname.setBounds(172, 141, 179, 30);
		contentPane.add(textFieldPname);
		textFieldPname.setColumns(10);

		JLabel lblheader = new JLabel("Doctor");
		lblheader.setBounds(10, 29, 99, 52);
		lblheader.setFont(new Font("Verdana", Font.BOLD, 24));
		contentPane.add(lblheader);

		JLabel lblPname = new JLabel("Search :");
		lblPname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPname.setBounds(92, 141, 79, 30);
		contentPane.add(lblPname);

		JButton btnSearch = new JButton("All Patients");
		btnSearch.setBounds(198, 83, 117, 30);
		contentPane.add(btnSearch);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(498, 35, 89, 30);
		contentPane.add(btnLogout);

		JLabel lblProblem = new JLabel("");
		lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblProblem.setForeground(Color.RED);
		lblProblem.setBounds(110, 60, 205, 35);
		contentPane.add(lblProblem);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 592, 252);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);

		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_1.getSelectedRow();
				table_1.getValueAt(selectedRow, 0);
				String patientId = table_1.getValueAt(selectedRow, 0).toString();
				System.out.println(patientId);
				doctorInfo doctorInfo = new doctorInfo(patientId);

				// you've passed the user and pass to other frame.
				// then you can make it visible.
				doctorInfo.setVisible(true);
			}
		});
		btnView.setBounds(386, 144, 89, 23);
		contentPane.add(btnView);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					connection = dbConnector();
					Class.forName("org.sqlite.JDBC");
					// Set this path to where you put your database file in your computer

					String query = " SELECT * FROM Patient";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception f) {
					f.printStackTrace();
				}

				// Check null
				if (textFieldPname.getText().isEmpty()) {
					lblProblem.setText("Please enter Patient Name");
				}

			}

		});
	}
}
