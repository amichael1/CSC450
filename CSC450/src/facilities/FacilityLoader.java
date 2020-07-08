package facilities;

import java.util.List;
//Could be facilityLoadable, may rename for next phase
public interface FacilityLoader {
	List<Facility> getFacilities() throws Exception;
}
