package tFanClubProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DoctorController {

	/* Connection code */
	private Connection connection;

	public DoctorController() {
		// Establish connection with the database
		try {
			Class.forName("org.sqlite.JDBC");
			// Set this path to where you put your database file in your computer
			connection = DriverManager.getConnection("jdbc:sqlite:DatabaseFiles/userInfo_3.db");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

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
	
	//retrieves the patient info
	public ResultSet getPatient(String username) throws SQLException {
		String query = " SELECT * FROM Patient where username=?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, username);
		return pst.executeQuery();
	}

	//retrieves information of all patients
	public ResultSet getAllPatients() throws SQLException {
		String query = " SELECT * FROM Patient";
		PreparedStatement pst = connection.prepareStatement(query);
		return pst.executeQuery();
	}

	//retrieves the information of patient by patient id
	public ResultSet getPatientInfo(int patientId) throws SQLException {
		String query = " SELECT datePrescribed AS DatePrescribed, medicationName AS Medication FROM Patient "
				+ "INNER JOIN Prescription ON PATIENT.patientID = PRESCRIPTION.patientID "
				+ "where PATIENT.patientID = ?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setInt(1, patientId);
		return pst.executeQuery();
	}
	
	//retrieves the patient's fullname
	public String getPatientName(int patientId) throws SQLException {
		String query = " SELECT patientFName||' '||patientLName AS patientName FROM Patient where patientID=?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setInt(1, patientId);
		ResultSet rst = pst.executeQuery();
		return rst.getString("patientName");
	}

	//retrieves the prescription of the patient by patient id
	public ResultSet getPrescription(int patientId, String prescriptionName) throws SQLException {
		String query = " SELECT dateDispensed AS datePrescribed, medicationName AS medication FROM Patient "
				+ "INNER JOIN Prescription ON PATIENT.patientID = PRESCRIPTION.patientID "
				+ "where PATIENT.patientID = ? AND Prescription.medicationName LIKE ?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setInt(1, patientId);
		pst.setString(2, "%" + prescriptionName + "%");
		return pst.executeQuery();
	}
	
	//adds new prescription of the patient
	public void addPrescription(int patientId, String datePrescribed, String medication) throws SQLException {
		String query = "INSERT INTO Prescription(datePrescribed,patientID,medicationName)"
				+ "VALUES(?,?,?)";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, datePrescribed);
		pst.setInt(2, patientId);
		pst.setString(3, medication);
		pst.executeUpdate();
	}

}
