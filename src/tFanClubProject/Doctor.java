package tFanClubProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Doctor {
	Connection conn;
	public Doctor() {

		try {
			Class.forName("org.sqlite.JDBC");
			// Set this path to where you put your database file in your computer
			conn = DriverManager.getConnection("jdbc:sqlite:DatabaseFiles/userInfo_3.db");
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			conn = null;
		}

	}
	//retrieves the patient info
	public ResultSet getPatient(String username) throws SQLException {
		String query = " SELECT * FROM Patient where username=?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, username);
		return pst.executeQuery();
	}

	//retrieves information of all patients
	public ResultSet getAllPatients() throws SQLException {
		String query = " SELECT * FROM Patient";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.executeQuery();
		return pst.executeQuery();
	}

	//retrieves the information of patient by patient id
	public ResultSet getPatientInfo(int patientId) throws SQLException {
		String query = " SELECT datePrescribed AS DatePrescribed, medicationName AS Medication, dosage AS dosage FROM Patient "
				+ "INNER JOIN Prescription ON PATIENT.patientID = PRESCRIPTION.patientID "
				+ "where PATIENT.patientID = ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, patientId);
		return pst.executeQuery();
	}
	
	//retrieves the patient's fullname
	public String getPatientName(int patientId) throws SQLException {
		String query = " SELECT patientFName||' '||patientLName AS patientName FROM Patient where patientID=?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, patientId);
		ResultSet rst = pst.executeQuery();
		return rst.getString("patientName");
	}

	//retrieves the prescription of the patient by patient id
	public ResultSet getPrescription(int patientId, String prescriptionName) throws SQLException {
		String query = " SELECT datePrescribed AS datePrescribed, medicationName AS medication, dosage AS dosage FROM Patient "
				+ "INNER JOIN Prescription ON PATIENT.patientID = PRESCRIPTION.patientID "
				+ "where PATIENT.patientID = ? AND Prescription.medicationName LIKE ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, patientId);
		pst.setString(2, "%" + prescriptionName + "%");
		return pst.executeQuery();
	}
	
	//adds new prescription of the patient
	public void addPrescription(int patientId, String datePrescribed, String medication, int doctorID, String dosage) throws SQLException {
		String query = "INSERT INTO Prescription(datePrescribed,patientID,medicationName,presStatus,doctorID,dosage)"
				+ "VALUES(?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, datePrescribed);
		pst.setInt(2, patientId);
		pst.setString(3, medication);
		pst.setString(4, "Pending");
		pst.setInt(5, doctorID);
		pst.setString(6, dosage);
		pst.executeUpdate();
		pst.close();
		conn.close();
	}
	//For homepagePharmacist
    public int getDoctorID(String username) throws SQLException {
        int doctorID= 0;

        //hi
        String query = "SELECT doctorID FROM Doctor where username = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, username);

        //This gets the values back one by one from the database
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            doctorID= rs.getInt("doctorID");
        }
        return doctorID;
  
    }

}
