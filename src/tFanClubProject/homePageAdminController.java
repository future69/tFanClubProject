package tFanClubProject;

public class homePageAdminController {

	UserInfo user = new UserInfo();

	public String passAdminHomepageInfo (String fullname) {
		
		String result = null;
		
		result  = user.getHomepageInfo(fullname);
		
		return result;
	}
}
