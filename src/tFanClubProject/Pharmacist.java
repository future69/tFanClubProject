package tFanClubProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class Pharmacist 
{
	/*Connection code*/
	Connection connection = null;
	Connection conn = null;
	
	//Establish connection with the database
	public static Connection dbConnector() 
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			//Set this path to where  you put your database file in your computer
			Connection conn = DriverManager.getConnection("jdbc:sqlite:DatabaseFiles/userInfo_3.db");
			return conn;
		}
		catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	//For homepagePharmacist
	public String getHomepageInfo(String username) {
		String fullName = null;
	try {

		connection = dbConnector();
		
		//hi
		String query = "SELECT pharmaName , pharmaAdd FROM Pharmacist where username = ?";
		PreparedStatement pst = connection.prepareStatement(query);
		pst.setString(1, username);
		
		//This gets the values back one by one from the database
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			fullName = rs.getString("pharmaName") + (" at ")+ rs.getString("pharmaAdd");
		}
	}
	catch(Exception f) {
		JOptionPane.showMessageDialog(null, f);
	}
	return fullName;
	}
	
	//Get prescription info
	public String[] retrievePrescriptionStatus (int presNum) {
		String prescriptionInfo[] = new String[5];
		try {
			connection = dbConnector();
			String query2 = "SELECT dateDispensed , presStatus, medicationName, dosage FROM Prescription where presNum=?";
			PreparedStatement pst2 = connection.prepareStatement(query2);
			pst2.setInt(1, presNum);
			//This gets the values back one by one from the database
			ResultSet rs2 = pst2.executeQuery();
			while(rs2.next()) {
				prescriptionInfo[1] = rs2.getString("dateDispensed");
				prescriptionInfo[2] = rs2.getString("presStatus");
				prescriptionInfo[3] = rs2.getString("medicationName");
				prescriptionInfo[4] = rs2.getString("dosage");
			}
			
			if (prescriptionInfo[2] == null || prescriptionInfo[3] == null || prescriptionInfo[4] == null) {
				return null;
			}	
			
			else {
				return prescriptionInfo;
			}
		}
		catch(Exception f) {
			JOptionPane.showMessageDialog(null, f);
			return null;
		}
	}
	
	//Updates Prescription table
	public boolean updatePrescriptionInfo (int presNum, String dateDispensed, String presStatus) {
		try {
		connection = dbConnector();

		//Updates data in Pharmacist
		String query2 = "UPDATE Prescription SET dateDispensed=? , presStatus =? WHERE presNum=?";
		PreparedStatement pst2 = connection.prepareStatement(query2);
		
		pst2.setString(1, dateDispensed);
		pst2.setString(2, presStatus);
		pst2.setInt(3, presNum);
		pst2.executeUpdate();
		pst2.close();
		
		return true;
		} 
		catch(Exception f){
			JOptionPane.showMessageDialog(null, f);
			return false;
		}
	}
	
}
