package logisiticsapplication;

import java.util.ArrayList;

public final class ItemService {
	
	private ArrayList<Item> items;
	
	private volatile static ItemService itemManager;
	//Item manager singleton design
	//Facade for Items
	public static ItemService getInstance() throws Exception
	{
		synchronized(ItemService.class)
		{
			if (itemManager == null)
			{
				itemManager = new ItemService();
			}
		}
		
		return itemManager;
	}
	//Loads items into an ArrayList the manager manages
	private ItemService() 
			throws Exception
	{
		ItemLoader itemLoader = ItemLoaderFactory.createNewItem();
		items = itemLoader.getItems();
	}
	//Checks to see if Item exists
	public boolean itemExists(Item item)
		throws NullPointerException
	{
		if(item == null)
		{
			throw new NullPointerException();
		}
		
		return items.contains(item);
	}
	//Gets the items price
	public double getItemPrice(Item itemNeeded) 
			throws NullPointerException, DoesNotExistException
	{
		//Do not want to check null items
		if(itemNeeded == null)
		{
			throw new NullPointerException();
		}
		
		//If item doesn't exist, do not want to return 0
		if(!items.contains(itemNeeded))
		{
			throw new DoesNotExistException();
		}
		
		double price = 0;
		
		price = items.get(items.indexOf(itemNeeded)).getPrice();
		
		return price;
	}
	
	public Item getItem(String itemNeeded) 
			throws NullPointerException, DoesNotExistException
	{
		//Do not want to check null items
		if(itemNeeded == null)
		{
			throw new NullPointerException();
		}
		
		Item foundItem = null;
		
		for(Item item : items)
		{
			if (item.getName().equals(foundItem))
			{
				foundItem = item;
			}
		}
		
		if(foundItem==null)
		{
			throw new DoesNotExistException();
		}
		
		return foundItem;
		
	}
	//Logs
	public void logItems()
	{
		ItemsLoggable logger = ItemsLogFactory.createNewItemLog();
		
		logger.itemsLog(items);
	}
	
}
