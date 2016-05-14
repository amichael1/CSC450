package logisiticsapplication;

public class PathLogFactory {
	
	private PathLogFactory()
	{
		
	}

	public static PathLoggable createNewPathLog()
	{
		return new PathLogToConsoleImpl();
	}
}
