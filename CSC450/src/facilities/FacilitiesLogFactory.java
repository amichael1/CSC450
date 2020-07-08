package facilities;

public class FacilitiesLogFactory {
	private FacilitiesLogFactory() {}
//facility log factory, in case we want to log to a document or something else later
	public static FacilitiesLoggable createNewFacilitiesLog()
	{
		return new FacilitiesLogToConsoleImpl();
	}
}
