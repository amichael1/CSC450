package logisiticsapplication;

import java.util.ArrayList;

public class FacilityFactory {
	
	private FacilityFactory()
	{
		
	}
	//Creates only the FacilityImpl class at the moment
	public static Facility createNewFacility(String name, double ratePerDay, int itemsPerDay, 
			ArrayList<Neighbor> neighbors, ArrayList<Schedule> schedule, ArrayList<Inventory> inventory)
		throws Exception
	{
		if(name == null||neighbors == null ||schedule == null ||inventory == null)
		{
			throw new NullPointerException();
		}
		
		return new FacilityImpl(name, ratePerDay, itemsPerDay, neighbors, schedule, inventory);
	}

}
