package tFanClubProject;

public class homePagePatientController {

	UserInfo user = new UserInfo();

	public String passAdminHomepageInfo (String fullname) {
		
		String result = null;
		
		result  = user.getHomepageInfo(fullname);
		
		return result;
	}
}
