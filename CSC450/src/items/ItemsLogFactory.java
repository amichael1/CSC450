package items;

public class ItemsLogFactory {
	
	private ItemsLogFactory() {}
	//Only one way to log as of now
	public static ItemsLoggable createNewItemLog()
	{
		return new ItemsLogToConsoleImpl();
	}
}
