package tFanClubProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class doctorInfoController {
	/* Connection code */


	public void addPrescription(int patientId, String datePrescribed, String medication, int docID, String dosage) throws SQLException {
		Doctor doc1 = new Doctor();
		doc1.addPrescription(patientId, datePrescribed, medication, docID, dosage);
		return;
		
		
	}

	public ResultSet getPatientInfo(int patientId) throws SQLException {
		Doctor doc1 = new Doctor();
		ResultSet username = doc1.getPatientInfo(patientId);
		return username;
	}

	public int getDoctorID(String username) throws SQLException {
		Doctor doc1 = new Doctor();
		int doctorId = doc1.getDoctorID(username);
		return doctorId;
	
	}

public ResultSet getPrescription(int patientId, String text) throws SQLException {
	Doctor doc1 = new Doctor();
	ResultSet pres = doc1.getPrescription(patientId,text);
	return pres;
}

public static String getPatientName(int patientId) throws SQLException {
	Doctor doc1 = new Doctor();
	String doctorId = doc1.getPatientName(patientId);
	return doctorId;
}

}
