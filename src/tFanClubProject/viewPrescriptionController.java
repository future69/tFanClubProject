package tFanClubProject;

public class viewPrescriptionController 
{
	UserInfo user = new UserInfo();
	public String passPatientHomepageInfo (String username) 
	{
		return user.getHomepageInfo(username);
	}
	public String [][] getPrescription()
	{
		String [][] prescriptions = new String [4][4];
		return prescriptions;
	}
}
