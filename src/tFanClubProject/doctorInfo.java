package tFanClubProject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;

public class doctorInfo extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrescriptionsName;
	private JLabel lblNewLabel;
	private int patientId;
	private int dosage;
	private JTable table_2;
	private doctorInfoController doctorInfoController;

//	private doctorInfoController doctorInfoController;

	private int rowsCount;

	public void loadTable() {
		try {
//			doctorInfoController doc1 = new doctorInfoController();

			table_2.setModel(doctorInfoController.getPatientInfo(patientId));
			rowsCount = table_2.getRowCount();
		} catch (Exception f) {
			f.printStackTrace();

		}
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public doctorInfo(int patientId, String username, int doctorID) throws SQLException {
		this.patientId = patientId;
		doctorInfoController = new doctorInfoController();
		int docID = doctorInfoController.getDoctorID(username);
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

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table_2.setModel(doctorInfoController.getPrescription(patientId, txtPrescriptionsName.getText()));
					rowsCount = table_2.getRowCount();
				} catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(481, 113, 89, 28);
		contentPane.add(btnSearch);

		String patientName = "";
		patientName = doctorInfoController.getPatientName(patientId);

		JLabel lblPatientName = new JLabel("History of " + patientName);
		lblPatientName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatientName.setBounds(32, 152, 220, 39);
		contentPane.add(lblPatientName);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(36, 224, 534, 191);
		contentPane.add(scrollPane2);
		table_2 = new JTable();
		scrollPane2.setViewportView(table_2);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) table_2.getModel();
				tableModel.addRow(new String[] { "", "", "" });
				table_2.setModel(tableModel);
			}
		});
		btnAdd.setBounds(368, 190, 89, 23);
		contentPane.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(467, 190, 89, 23);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_2.isEditing()) {
					table_2.getCellEditor().stopCellEditing();

				}
				if (rowsCount < table_2.getRowCount()) {
					String[] data = new String[table_2.getRowCount()];
					String datePrescribed = table_2.getValueAt(table_2.getRowCount() - 1, 0).toString();
					String medication = table_2.getValueAt(table_2.getRowCount() - 1, 1).toString();
					String dosage = table_2.getValueAt(table_2.getRowCount() - 1, 2).toString();
					if (!datePrescribed.isEmpty() && !medication.isEmpty() && !dosage.isEmpty()) {
						try {
							String result = doctorInfoController.addPrescription(patientId, datePrescribed, medication,
									doctorID, dosage);

							table_2.setModel(doctorInfoController.getPrescription(patientId, ""));
							JOptionPane.showMessageDialog(null, "Prescription updated successfully", "Message",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {
						JOptionPane.showMessageDialog(null, "Please fill both fields!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please add the row first!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
