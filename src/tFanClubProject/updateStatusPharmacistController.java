package tFanClubProject;

public class updateStatusPharmacistController {
	
	Pharmacist user = new Pharmacist();

	//Get info to update the pres status
	public String[] passPrescriptionInfo (int presNum) {
		
		String[] result = null;
		
		result  = user.retrievePrescriptionStatus(presNum);
		
		return result;
	}
	
	//Update the prescription table
	public boolean updatePrescriptionInfo(int presNum, String dateDispensed, String presStatus, String pharmaID) {
		if (user.updatePrescriptionInfo(presNum, dateDispensed, presStatus, pharmaID)) {
			return true;
		} else {
			return false;
		}
	}
}
