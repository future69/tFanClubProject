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
	
	public String getPrescriptionID(String SearchPres)
	{
        SearchPres = null;
		try 
		{
			connection = dbConnector();
			String query = "SELECT * FROM Prescription WHERE presNum = ?";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, SearchPres);
			//This gets the values back one by one from the database
			ResultSet rs = pst.executeQuery();
			SearchPres = rs.getString("SearchPres");
			connection.close();
			rs.close();
			return SearchPres;
		}
		catch(Exception f) 
		{
			JOptionPane.showMessageDialog(null, f);
			return SearchPres;
		}
		
	}
	
}
