package network;

import exceptions.DoesNotExistException;
import facilities.Neighbor;

import java.util.ArrayList;


public class NetworkService {

	private final static int HOURS_PER_DAY = 8;
	private final static int MILES_PER_HOUR = 50;
	private static final int DAILY_TRAVEL_COST = 500;
	
	private volatile static NetworkService networkManager;
	private Network network;
	//Network singleton and facade
	//manages all the components to the network
	public static NetworkService getInstance() throws Exception
	{
		synchronized(NetworkService.class)
		{
			if (networkManager == null)
			{
				networkManager = new NetworkService();
			}
		}
		
		return networkManager;
	}
	//Creates a new network class
	private NetworkService()
	{
		network = NetworkFactory.createNewNetwork();
	}
	//Finds the shortest path
	//Maybe it should be renamed to getPath in case new types of paths are needed
	//Might do on Phase 2
	public ArrayList<Neighbor> getShortestPath(Neighbor start, Neighbor end)
			throws NullPointerException, DoesNotExistException, Exception
	{
		if(start==null || end == null )
		{
			throw new NullPointerException();
		}
		ArrayList<Neighbor> shortestPath = network.getShortestPath(start, end);
		return shortestPath;
		
	}

	public int lengthOfPath(ArrayList<Neighbor> path)
			throws Exception
	{
		if(path==null)
		{
			throw new NullPointerException();
		}

		int miles = 0;

		for (Neighbor neighbor : path)
		{
			miles += neighbor.getMiles();
		}

		return miles;
	}

	//Logs the path
	public void logPath(ArrayList<ArrayList<Neighbor>> shortestPath)
		throws NullPointerException
	{
		if(shortestPath == null)
		{
			throw new NullPointerException();
		}
		
		PathLoggable logger = PathLogFactory.createNewPathLog();
		
		logger.logPath(shortestPath);
	}

	public int costToTravel(int travelTime)
	{

		return travelTime * DAILY_TRAVEL_COST;

	}

	public int travelTime(int miles)
	{
		return (miles/(HOURS_PER_DAY*MILES_PER_HOUR));
	}

}
