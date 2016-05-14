package logisiticsapplication;

public class Inventory {
	//Inventory class for inventory data
	//Only need to store data for facilities easily
	private String item;
	private int quanity;
	
	public Inventory()
	{
		item = null;
		quanity = 0;
	}
	
	public Inventory(String newItem, int newQuanity)
	{
		item = newItem;
		quanity = newQuanity;
	}
	
	public String getItem()
	{
		return item;
	}
	
	public int getQuanity()
	{
		return quanity;
	}


}
