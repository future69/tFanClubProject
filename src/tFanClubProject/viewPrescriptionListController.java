package tFanClubProject;

public class viewPrescriptionListController
{
	public String [][] getPrescriptions(String accountUsername)
	{
		Patient patientData = new Patient ();
		int patientID = patientData.getPatientID(accountUsername);
	
		return patientData.getPres(26);
	}
	
	UserInfo user = new UserInfo();

	public String passPatientHomepageInfo (String username) 
	{
		return user.getHomepageInfo(username);
	}
	
}
