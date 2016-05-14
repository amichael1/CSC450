package logisiticsapplication;

import java.util.ArrayList;

public class OrderProcessingService {
	
	private volatile static OrderProcessingService orderProcessingManager;
	//Service that communicates with all the other services
	//Manages entire application and is how Main can call methods
	public static OrderProcessingService getInstance() throws Exception
	{
		synchronized(OrderProcessingService.class)
		{
			if (orderProcessingManager == null)
			{
				orderProcessingManager = new OrderProcessingService();
			}
		}
		
		return orderProcessingManager;
	}

	private OrderProcessingService()
	{
		//Could instantiate all services here
	}
	//Load and Log methods
	public void loadFacilities() throws Exception
	{
		FacilityService.getInstance();
	}
	
	public void loadItems() 
			throws Exception
	{
		ItemService.getInstance();
	}
	
	public void logItems() 
			throws Exception
	{
		ItemService.getInstance().logItems();
	}
	
	public void logFacilities() 
			throws NullPointerException, DoesNotExistException, Exception
	{
		FacilityService.getInstance().logItems();
	}
	//Get methods
	public Facility getFacility(String name) 
			throws NullPointerException, DoesNotExistException, Exception
	{
		return FacilityService.getInstance().getFacility(name);
	}
	
	public Neighbor toNeighbor(Facility facility) 
			throws Exception
	{
		return FacilityService.getInstance().toNeighbor(facility);
	}
	//For the hard coding, since passing in string name and not neighbor or facility
	public Neighbor createStartEnd(String name) 
			throws NullPointerException, DoesNotExistException, Exception
	{
		return toNeighbor(getFacility(name));
	}
	//Gets and logs shortest path
	public ArrayList<Neighbor> getShortestPath(Neighbor start, Neighbor end) 
			throws NullPointerException, DoesNotExistException, Exception
	{
		ArrayList<Neighbor> shortestPath = NetworkService.getInstance().getShortestPath(start, end);
		
		return shortestPath;
	}
	
	
	public void logPath(ArrayList<ArrayList<Neighbor>> shortestPaths) 
			throws NullPointerException, Exception
	{
		NetworkService.getInstance().logPath(shortestPaths);
	}
	
	

}
