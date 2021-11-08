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

	private JTable table_1;

	private DoctorController doctorController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String username = null;
					doctorMain frame = new doctorMain(username);
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
		doctorController = new DoctorController();
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
					ResultSet rs = doctorController.getPatient(textFieldPname.getText());
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

		JButton btnAllPatients = new JButton("All Patients");
		btnAllPatients.setBounds(198, 83, 117, 30);
		contentPane.add(btnAllPatients);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame loginPage = new LoginPage();
					loginPage.setVisible(true);
						
						dispose();
			}
		});
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
				if (selectedRow >= 0) {
					String patientId = table_1.getValueAt(selectedRow, 0).toString();
					doctorInfo doctorInfo = new doctorInfo(Integer.parseInt(patientId), doctorController);
					doctorInfo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //frame will hide on close, it will not terminate the program
					doctorInfo.loadTable();

					// you've passed the user and pass to other frame.
					// then you can make it visible.
					doctorInfo.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Please select a patient first!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnView.setBounds(386, 144, 89, 23);
		contentPane.add(btnView);

		btnAllPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = doctorController.getAllPatients();
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
