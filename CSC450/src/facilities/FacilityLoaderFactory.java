package facilities;

public class FacilityLoaderFactory {
	private FacilityLoaderFactory() {}
	//only one type of facility, no need for conditional
	public static FacilityLoader createNewFacilities()
	{
		return new FacilityXmlLoaderImpl();
	}
}
