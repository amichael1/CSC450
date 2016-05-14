package logisiticsapplication;

import java.util.ArrayList;

public class FacilityService {
	
	private ArrayList<Facility> facilities;
	
	private volatile static FacilityService facilityManager;
	//Singleton to create a facility manager
	public static FacilityService getInstance() throws Exception
	{
		synchronized(FacilityService.class)
		{
			if (facilityManager == null)
			{
				facilityManager = new FacilityService();
			}
		}
		
		return facilityManager;
	}

	//creates new facilities when called, calls factory to parse and instantiate
	private FacilityService() throws Exception
	{
		FacilityLoader facilityLoader = FacilityLoaderFactory.createNewFacilities();
		facilities = facilityLoader.getFacilities();
	}
	//Checks to see if facility exists
	public boolean facilityExists(Facility facility)
			throws NullPointerException
	{
		if(facility == null)
		{
			throw new NullPointerException();
		}
		
		return facilities.contains(facility);
	}
	//Easy way to go from neighbor to facility
	public Facility getFacility(String name)
		throws NullPointerException, DoesNotExistException
	{
		if(name == null)
		{
			throw new NullPointerException();
		}
		
		Facility foundFacility = null;
		
		for(Facility facility : facilities)
		{
			if (facility.getName().equals(name))
			{
				foundFacility = facility;
			}

		}
		
		if(foundFacility==null)
		{
			throw new DoesNotExistException();
		}
		
		
		return foundFacility;
	}
	
	
	public ArrayList<Schedule> getFacilitySchedule(Facility facilityNeeded)
			throws NullPointerException, DoesNotExistException
	{
		if(facilityNeeded == null)
		{
			throw new NullPointerException();
		}
		
		if(!facilities.contains(facilityNeeded))
		{
			throw new DoesNotExistException();
		}
		
		ArrayList<Schedule> schedule = null;
		
		schedule = facilities.get(facilities.indexOf(facilityNeeded)).getSchedule();
		
		return schedule;
		
	}
	
	public ArrayList<Neighbor> getNeighbors(Facility facilityNeeded)
			throws NullPointerException, DoesNotExistException
	{
		if(facilityNeeded == null)
		{
			throw new NullPointerException();
		}
		
		if(!facilities.contains(facilityNeeded))
		{
			throw new DoesNotExistException();
		}
		
		ArrayList<Neighbor> neighbors = null;
		
		neighbors = facilities.get(facilities.indexOf(facilityNeeded)).getNeighbors();
		
		return neighbors;
		
	}
	
	public ArrayList<Inventory> getInventory(Facility facilityNeeded)
			throws NullPointerException, DoesNotExistException
	{
		if(facilityNeeded == null)
		{
			throw new NullPointerException();
		}
		
		if(!facilities.contains(facilityNeeded))
		{
			throw new DoesNotExistException();
		}
		
		ArrayList<Inventory> inventory = null;
		
		inventory = facilities.get(facilities.indexOf(facilityNeeded)).getInventory();
		
		return inventory;
		
	}
	
	public Neighbor toNeighbor(Facility facility)
	{
		int start = 0;
		Neighbor startNeighbor = new Neighbor(facility.getName(), start);
		
		return startNeighbor;
	}
	
	
	public void logItems() throws NullPointerException, DoesNotExistException, Exception
	{
		FacilitiesLoggable logger = FacilitiesLogFactory.createNewFacilitiesLog();
		
		logger.facilitiesLog(facilities);
	}
	
	
	

}
