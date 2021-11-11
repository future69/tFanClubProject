package tFanClubProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

public class doctorInfoController {

	public String addPrescription(int patientId, String datePrescribed, String medication, int docID, String dosage)
			throws SQLException {
		Doctor doc1 = new Doctor();
		String token = doc1.addPrescription(patientId, datePrescribed, medication, docID, dosage);

		// getpatientinfo(patientid)
		// get email address
		// send email

		return null;

	}

	public TableModel getPatientInfo(int patientId) throws SQLException {
		Doctor doc1 = new Doctor();
		TableModel username = doc1.getPatientInfo(patientId);
		return username;
	}

	public int getDoctorID(String username) throws SQLException {
		Doctor doc1 = new Doctor();
		int doctorId = doc1.getDoctorID(username);
		return doctorId;

	}

	public TableModel getPrescription(int patientId, String text) throws SQLException {
		Doctor doc1 = new Doctor();
		TableModel pres = doc1.getPrescription(patientId, text);
		return pres;
	}

	public static String getPatientName(int patientId) throws SQLException {
		Doctor doc1 = new Doctor();
		String doctorId = doc1.getPatientName(patientId);
		return doctorId;
	}

	public int updateToken(int patientId) throws SQLException {
		Doctor doc1 = new Doctor();
		int response = doc1.updateToken(patientId);
		return response;

	}
}
