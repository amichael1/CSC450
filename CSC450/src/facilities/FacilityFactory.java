package facilities;

import java.util.List;

public class FacilityFactory {
	
	private FacilityFactory() {}
	//Creates only the FacilityImpl class at the moment
	public static Facility createNewFacility(String name, int ratePerDay, int itemsPerDay,
											 List<Neighbor> neighbors, Schedule schedule, Inventory inventory)
		throws Exception {
		if(name == null||neighbors == null ||schedule == null ||inventory == null) {
			throw new NullPointerException();
		}
		
		return new FacilityImpl(name, ratePerDay, itemsPerDay, neighbors, schedule, inventory);
	}

}
