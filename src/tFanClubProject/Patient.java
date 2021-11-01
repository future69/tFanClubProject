package tFanClubProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class Patient 
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
	
	public int getPatientID(String username)
	{
		int patientID = 0;
		try 
		{
			connection = dbConnector();
			String query = "SELECT patientID FROM Patient WHERE username = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, username);
			//This gets the values back one by one from the database
			ResultSet rs = pst.executeQuery();
			patientID = rs.getInt("patientID");
			connection.close();
			rs.close();
			return patientID;
		}
		catch(Exception f) 
		{
			JOptionPane.showMessageDialog(null, f);
			return patientID;
		}
		
	}
	
	public int getCountPres(int patientID) 
	{
		int counter = 0;
		try 
		{
			connection = dbConnector();
			String query = "SELECT * FROM Prescription WHERE PatientID = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, patientID);
			//This gets the values back one by one from the database
			ResultSet rs = pst.executeQuery();
			while(rs.next()) 
			{
				counter = counter + 1;
			}
			connection.close();
			rs.close();
			return counter;
		}
		catch(Exception f) 
		{
			JOptionPane.showMessageDialog(null, f);
			return counter;
		}
	}
	public String [][] getPres(int patientID)
	{
		int counter = getCountPres(patientID);
		String [][] presList= new String [counter][3];
		counter = 0; // reset to use to store data in 2Darray
		try 
		{
			connection = dbConnector();
			String query = "SELECT presNum, presStatus FROM Prescription WHERE PatientID = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, patientID);
			//This gets the values back one by one from the database
			ResultSet rs = pst.executeQuery();
			while(rs.next()) 
			{
				String presNum = String.valueOf(rs.getInt("presNum"));
				String presStatus = rs.getString("presStatus");
				presList [counter][0] = presNum;
				presList [counter][1] = presStatus;
				counter += 1;
				System.out.println("presNum:"+ presNum +"  pressStatus" + presStatus);
			}
			connection.close();
			rs.close();
			return presList;
		}
		catch(Exception f) 
		{
			JOptionPane.showMessageDialog(null, f);
			return presList;
		}
	}
}
