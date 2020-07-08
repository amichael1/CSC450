package facilities;

import exceptions.DoesNotExistException;
import java.util.List;

//log interface
public interface FacilitiesLoggable {
	void facilitiesLog(List<Facility> facilities) throws NullPointerException, DoesNotExistException, Exception;
}