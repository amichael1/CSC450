package logisiticsapplication;

import java.util.ArrayList;
//log interface
public interface FacilitiesLoggable {
	
	public void facilitiesLog(ArrayList<Facility> facilities) throws NullPointerException, DoesNotExistException, Exception;

}