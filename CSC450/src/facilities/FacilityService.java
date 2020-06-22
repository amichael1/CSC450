package facilities;

import java.util.ArrayList;
import exceptions.DoesNotExistException;

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
	
	
	public Schedule getFacilitySchedule(Facility facilityNeeded)
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
		
		Schedule schedule = null;
		
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
	
	public Inventory getInventory(Facility facilityNeeded)
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
		
		Inventory inventory = null;
		
		inventory = facilities.get(facilities.indexOf(facilityNeeded)).getInventory();
		
		return inventory;
		
	}
	
	public Neighbor toNeighbor(Facility facility)
	{
		if(facility == null)
		{
			throw new NullPointerException();
		}

		int start = 0;
		Neighbor startNeighbor = new Neighbor(facility.getName(), start);
		
		return startNeighbor;
	}
	
	
	public void logItems() throws NullPointerException, DoesNotExistException, Exception
	{
		FacilitiesLoggable logger = FacilitiesLogFactory.createNewFacilitiesLog();
		
		logger.facilitiesLog(facilities);
	}

	public boolean hasItem(String itemName, String facilityName)
			throws Exception
	{

		if(facilityName == null||itemName == null)
		{
			throw new NullPointerException();
		}

		Facility facility = getFacility(facilityName);

		return facilities.get(facilities.indexOf(facility)).itemInStock(itemName);
	}

	public Integer expectedShipDate(Integer quantity, String facility, Integer startDay)
			throws Exception
	{
		if(facility == null || quantity == null)
		{
			throw new NullPointerException();
		}

		return getFacility(facility).expectedShipDate(quantity, startDay);
	}

	public int daysToProcess(String facility, Integer quantity, Integer startDay)
			throws Exception
	{
		if(facility == null || quantity==null)
		{
			throw new NullPointerException();
		}

		return getFacility(facility).daysToProcess(quantity, startDay);

	}

	public void updateAvailability(Integer quantity, String facility, Integer startDay)
			throws Exception
	{
		if(facility == null || quantity==null)
		{
			throw new NullPointerException();
		}

		getFacility(facility).bookDays(quantity, startDay);
	}

	public ArrayList<String> facilitiesWithItem(String item, String destination)
	{
		if(item==null)
		{
			throw new NullPointerException();
		}

		ArrayList<String> facilitiesWithItem = new ArrayList<String>();

		for(Facility facility : facilities)
		{
			if(facility.itemInStock(item) && !facility.getName().equals(destination))
			{
				facilitiesWithItem.add(facility.getName());
			}
		}

		if(facilitiesWithItem==null)
		{
			throw new NullPointerException();
		}

		return facilitiesWithItem;
	}

	public Integer checkAmountGiven(String facilityName, String itemName, Integer amountNeeded)
			throws Exception
	{
		if(facilityName==null||itemName==null||amountNeeded==null)
		{
			throw new NullPointerException();
		}

		Facility facility = getFacility(facilityName);

		return facility.checkAmountGiven(itemName,amountNeeded);
	}

	public void updateInventory(String facilityName, String itemName, int quantityOfItems)
			throws Exception
	{
		if(facilityName==null||itemName==null)
		{
			throw new NullPointerException();
		}

		Facility facility = getFacility(facilityName);

		facility.reduceInventory(quantityOfItems,itemName);
	}

	public void bookDays(String facilityName, Integer numberOfItems, Integer startDay)
			throws Exception
	{
		Facility facility = getFacility(facilityName);

		facility.bookDays(numberOfItems, startDay);
	}

	public int costToProcessPerDay(String facilityName)
			throws Exception
	{
		if(facilityName==null)
		{
			throw new NullPointerException();
		}

		Facility facility = getFacility(facilityName);

		return facility.getRatePerDay();
	}


}
