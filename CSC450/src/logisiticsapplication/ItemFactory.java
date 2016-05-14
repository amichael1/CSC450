package logisiticsapplication;

public class ItemFactory {
	
	private ItemFactory()
	{
		
	}
	//Only one type of factory
	public static Item createNewItem(String name, double price)
	{
		
		return new ItemImpl(name, price);
	}
	
}
