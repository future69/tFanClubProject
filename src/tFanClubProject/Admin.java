package tFanClubProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Admin {
	
	/*Connection code*/
	Connection connection = null;
	Connection conn = null;
	
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
	
	//Verify existing record
	public boolean checkDuplicate (String username) {
	try {

		connection = dbConnector();
		
		//userInfo is the name of the SQLite database, username and password are the fields
		String query = "SELECT * FROM userInfo where Username=?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, username);
		
		//This gets the values back one by one from the database
		ResultSet rs = pst.executeQuery();
		int count = 0;
		while(rs.next()) {
			count = count + 1;
		}
		
		if (count >= 1) {
			connection.close();
			rs.close();
			return true;
			
		}
		else {
			connection.close();
			rs.close();
			return false;
		}
		
	}
	catch(Exception f) {
		JOptionPane.showMessageDialog(null, f);
		return true;
	}
	}
	
	//Gets the row count in admin table
	public int getCountAdmin() {
		int count = 0;
		try {
			connection = dbConnector();
			String query = "SELECT * FROM Admin";
			PreparedStatement pst = connection.prepareStatement(query);
			
			//This gets the values back one by one from the database
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				count = count + 1;
			}
			connection.close();
			rs.close();
			return count;
		}
		catch(Exception f) {
			JOptionPane.showMessageDialog(null, f);
			return count;
		}
	}
	
	//Add account to admin database
	public void addAdmin (String username, String FName, String LName) {
	try {
		int numOfRows = getCountAdmin() + 1;
		connection = dbConnector();

		//Inserts the data
		String query = "INSERT INTO Admin(adminID, username, adminFName, adminLName) VALUES (?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setInt(1, numOfRows);
		pst.setString(2, username);
		pst.setString(3, FName);
		pst.setString(4, LName);

		pst.executeUpdate();
		pst.close();
	}
	catch(Exception f) {
		JOptionPane.showMessageDialog(null, f);
	}
	}
	
	//Gets the row count in patient table
	public int getCountPatient() {
		int count = 0;
		try {
			connection = dbConnector();
			String query = "SELECT * FROM Patient";
			PreparedStatement pst = connection.prepareStatement(query);
			
			//This gets the values back one by one from the database
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				count = count + 1;
			}
			connection.close();
			rs.close();
			return count;
		}
		catch(Exception f) {
			JOptionPane.showMessageDialog(null, f);
			return count;
		}
	}
	
	//Add account to patient database
	public void addPatient (String username, String FName, String LName, String DOB, String email) {
	try {
		int numOfRows = getCountPatient() + 1;
		connection = dbConnector();
		
		//Inserts the data
		String query = "INSERT INTO Patient(patientID, username, patientFName, patientLName, patientDOB, patientEmail) VALUES (?,?,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setInt(1, numOfRows);
		pst.setString(2, username);
		pst.setString(3, FName);
		pst.setString(4, LName);
		pst.setString(5, DOB);
		pst.setString(6, email);

		pst.executeUpdate();
		pst.close();
	}
	catch(Exception f) {
		JOptionPane.showMessageDialog(null, f);
	}
	}
	
	//Gets the row count in patient table
	public int getCountPharmacist() {
		int count = 0;
		try {
			connection = dbConnector();
			String query = "SELECT * FROM Pharmacist";
			PreparedStatement pst = connection.prepareStatement(query);
			
			//This gets the values back one by one from the database
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				count = count + 1;
			}
			connection.close();
			rs.close();
			return count;
		}
		catch(Exception f) {
			JOptionPane.showMessageDialog(null, f);
			return count;
		}
	}
	
	//Add account to pharmacist database
	public void addPharmacist (String username, String FName, String pharmacyAddress) {
	try {
		int numOfRows = getCountPharmacist() + 1;
		connection = dbConnector();
		
		//Inserts the data
		String query = "INSERT INTO Pharmacist(pharamcistID, username, pharmaName, pharmaAdd) VALUES (?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setInt(1, numOfRows);
		pst.setString(2, username);
		pst.setString(3, FName);
		pst.setString(4, pharmacyAddress);

		pst.executeUpdate();
		pst.close();
	}
	catch(Exception f) {
		JOptionPane.showMessageDialog(null, f);
	}
	}
	
	//Gets the row count in doctor table
	public int getCountDoctor() {
		int count = 0;
		try {
			connection = dbConnector();
			String query = "SELECT * FROM Doctor";
			PreparedStatement pst = connection.prepareStatement(query);
			
			//This gets the values back one by one from the database
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				count = count + 1;
			}
			connection.close();
			rs.close();
			return count;
		}
		catch(Exception f) {
			JOptionPane.showMessageDialog(null, f);
			return count;
		}
	}
	
	//Add account to doctor database
	public void addDoctor (String username, String FName, String LName, int patientID) {
	try {
		int numOfRows = getCountDoctor() + 1;
		connection = dbConnector();
		
		//Inserts the data
		String query = "INSERT INTO Doctor(doctorID, username, doctorFName, doctorLName, patientID) VALUES (?,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setInt(1, numOfRows);
		pst.setString(2, username);
		pst.setString(3, FName);
		pst.setString(4, LName);
		pst.setLong(5, patientID);

		pst.executeUpdate();
		pst.close();
	}
	catch(Exception f) {
		JOptionPane.showMessageDialog(null, f);
		}
	}
	
	//Check if theres a patient with this patient ID (When adding doctor)
	public boolean checkPatientID(int patientID) {
		try {
			connection = dbConnector();
			String query = "SELECT * FROM Patient where patientID=?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setLong(1, patientID);
			//This gets the values back one by one from the database
			ResultSet rs = pst.executeQuery();
			int count = 0;
			while(rs.next()) {
				count = count + 1;
			}
			if (count >= 1) {
				connection.close();
				rs.close();
				return true;
			}
			else {
				connection.close();
				rs.close();
				return false;
			}
		}
		catch(Exception f) {
			JOptionPane.showMessageDialog(null, f);
			return true;
		}
	}

	
	//Adds account into userInfo database so they can login
	public void addUserInfo (String username, char[] password, String role) {
		try {
		connection = dbConnector();
		String pass = String.valueOf(password);
		
		//Inserts the data
		String query = "INSERT INTO userInfo(username, password, role) VALUES (?,?,?)";
		PreparedStatement pst = connection.prepareStatement(query);

		pst.setString(1, username);
		pst.setString(2, pass);
		pst.setString(3, role);

		pst.executeUpdate();
		} 
		catch(Exception f){
			JOptionPane.showMessageDialog(null, f);
		}
	}

}
