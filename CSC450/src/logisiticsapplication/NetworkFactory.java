package logisiticsapplication;

public class NetworkFactory {

	private NetworkFactory()
	{
		
	}
	//Only one network implementation and that is for finding the shortest path
	public static Network createNewNetwork()
	{
		return new ShortestPathImpl();
	}
	
}
