package tFanClubProject;

public class homePagePharmacistController {

	Pharmacist user = new Pharmacist();

	public String passPharmacistHomepageInfo (String fullname) {
		
		String result = null;
		
		result  = user.getHomepageInfo(fullname);
		
		return result;
	}
}
