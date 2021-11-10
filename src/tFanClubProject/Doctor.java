package tFanClubProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

public class Doctor {

//	Connection conn;
//
//	public Doctor() {
//
//		try {
//			Class.forName("org.sqlite.JDBC");
//			// Set this path to where you put your database file in your computer
//			conn = DriverManager.getConnection("jdbc:sqlite:DatabaseFiles/userInfo_3.db");
//
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e);
//			conn = null;
//		}
//	}
//	public static Connection dbConnector() {
//		 
//
//		try {
//			Class.forName("org.sqlite.JDBC");
//			// Set this path to where you put your database file in your computer
//			Connection conn = DriverManager.getConnection("jdbc:sqlite:DatabaseFiles/userInfo_3.db");
//			return conn;
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e);
//		}
//		return null;
//	}

	// retrieves the patient info
	public TableModel getPatient(String username) throws SQLException {
		Connection con = ConnectDatabase.getConnection();
		if (con != null) {
			PreparedStatement pst = null;
			ResultSet response = null;
			try {
				String query = " SELECT * FROM Patient where username=?";
				pst = con.prepareStatement(query);
				pst.setString(1, username);
				response = pst.executeQuery();
				return DbUtils.resultSetToTableModel(response);
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				if (pst != null) {
					pst.close();

				}
			}

		}
		return null;
	}

	// retrieves information of all patients
	public TableModel getAllPatients() throws SQLException {
		Connection con = ConnectDatabase.getConnection();
		if (con != null) {
			PreparedStatement pst = null;
			ResultSet response = null;
			try {
				String query = " SELECT * FROM Patient";
				pst = con.prepareStatement(query);
				response = pst.executeQuery();
				return DbUtils.resultSetToTableModel(response);
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				if (pst != null) {
					pst.close();

				}

			}
		}
		return null;
	}

	// retrieves the information of patient by patient id
	public TableModel getPatientInfo(int patientId) throws SQLException {
		Connection con = ConnectDatabase.getConnection();
		if (con != null) {
			PreparedStatement pst = null;
			ResultSet response = null;
			try {
				String query = " SELECT datePrescribed AS DatePrescribed, medicationName AS Medication, dosage AS dosage FROM Patient "
						+ "INNER JOIN Prescription ON PATIENT.patientID = PRESCRIPTION.patientID "
						+ "where PATIENT.patientID = ?";
				pst = con.prepareStatement(query);
				pst.setInt(1, patientId);
				response = pst.executeQuery();
				return DbUtils.resultSetToTableModel(response);
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				if (pst != null) {
					pst.close();
				}
			}
		}
		return null;
	}

	// retrieves the patient's fullname
	public String getPatientName(int patientId) throws SQLException {
		Connection con = ConnectDatabase.getConnection();
		if (con != null) {
			PreparedStatement pst = null;
			ResultSet response = null;
			try {

				String query = " SELECT patientFName||' '||patientLName AS patientName FROM Patient where patientID=?";
				pst = con.prepareStatement(query);
				pst.setInt(1, patientId);
				response = pst.executeQuery();
				String patientName = "";
				while (response.next()) {
					patientName = response.getString("patientName");
				}
				return patientName;

			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				if (pst != null) {
					pst.close();
				}

			}
		}
		return null;
	}

	// retrieves the prescription of the patient by patient id
	public TableModel getPrescription(int patientId, String prescriptionName) throws SQLException {
		Connection con = ConnectDatabase.getConnection();
		if (con != null) {
			PreparedStatement pst = null;
			ResultSet response = null;
			try {
				String query = " SELECT datePrescribed AS datePrescribed, medicationName AS medication, dosage AS dosage FROM Patient "
						+ "INNER JOIN Prescription ON PATIENT.patientID = PRESCRIPTION.patientID "
						+ "where PATIENT.patientID = ? AND Prescription.medicationName LIKE ?";
				pst = con.prepareStatement(query);
				pst.setInt(1, patientId);
				pst.setString(2, "%" + prescriptionName + "%");
				response = pst.executeQuery();
				return DbUtils.resultSetToTableModel(response);

			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				if (pst != null) {
					pst.close();
				}

			}

		}
		return null;
	}

	// adds new prescription of the patient
	public int addPrescription(int patientId, String datePrescribed, String medication, int doctorID, String dosage)
			throws SQLException {
		Connection con = ConnectDatabase.getConnection();
		if (con != null) {
			String query = "INSERT INTO Prescription(datePrescribed,patientID,medicationName,presStatus,doctorID,dosage)"
					+ "VALUES(?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, datePrescribed);
			pst.setInt(2, patientId);
			pst.setString(3, medication);
			pst.setString(4, "Pending");
			pst.setInt(5, doctorID);
			pst.setString(6, dosage);
			int response = pst.executeUpdate();
			pst.close();
			return response;
		}
		return -1;
	}

	// For homepagePharmacist
	public int getDoctorID(String username) throws SQLException {

		int doctorID = 0;
		Connection con = ConnectDatabase.getConnection();
		if (con != null) {
			String query = "SELECT doctorID FROM Doctor where username = ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, username);

			// This gets the values back one by one from the database
			ResultSet response = pst.executeQuery();

			while (response.next()) {
				doctorID = response.getInt("doctorID");

			}
			pst.close();

		}
		return doctorID;

	}

}
