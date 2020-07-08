package items;

public class ItemLoaderFactory {
	private ItemLoaderFactory() {
		
	}
//Only one way to load
//Needed in case other file formats are specified
	public static ItemLoader createNewItem()
	{
		return new ItemXmlLoaderImpl();
	}
}
