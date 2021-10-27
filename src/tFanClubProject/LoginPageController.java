package tFanClubProject;

public class LoginPageController {
	
	UserInfo user = new UserInfo();

	public boolean passUserInfo (String username, char[] password) {
		
		if(user.validateInfo(username, password) == true) {
			return true;
		}
		
		else {
			return false;
		}

		
		
	}
	
}
