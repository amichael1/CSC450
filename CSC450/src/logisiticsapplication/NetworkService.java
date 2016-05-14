package logisiticsapplication;

import java.util.ArrayList;

public class NetworkService {
	
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
	
	
}
