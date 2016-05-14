package logisiticsapplication;

public class ItemImpl implements Item {
	
	private String name;
	private double price;
	
	public ItemImpl()
	{
		name = null;
		price = 0;
	}
	
	public ItemImpl(String newName, double newPrice)
			throws NullPointerException
	{
		if(newName == null)
		{
			throw new NullPointerException();
		}
		
		name = newName;
		price = newPrice;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getPrice()
	{
		return price;
	}
	

}
