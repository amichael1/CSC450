package items;

import java.util.List;

import exceptions.DoesNotExistException;

public final class ItemService {
	private List<Item> items;
	
	private volatile static ItemService itemManager;
	//Item manager singleton design
	//Facade for Items
	public static ItemService getInstance() throws Exception {
		synchronized(ItemService.class) {
			if (itemManager == null) {
				itemManager = new ItemService();
			}
		}
		return itemManager;
	}

	//Loads items into an ArrayList the manager manages
	private ItemService() 
			throws Exception {
		ItemLoader itemLoader = ItemLoaderFactory.createNewItem();
		items = itemLoader.getItems();
	}

	//Checks to see if Item exists
	public boolean itemExists(Item item)
		throws NullPointerException {
		if(item == null) {
			throw new NullPointerException();
		}
		
		return items.contains(item);
	}

	//Gets the items price
	public int getItemPrice(String itemNeededName)
			throws NullPointerException, DoesNotExistException {
		//Do not want to check null items
		if(itemNeededName == null) {
			throw new NullPointerException();
		}

		Item itemNeeded = getItem(itemNeededName);

		//If item doesn't exist, do not want to return 0
		if(!items.contains(itemNeeded)) {
			throw new DoesNotExistException();
		}

		return items.get(items.indexOf(itemNeeded)).getPrice();
	}
	
	public Item getItem(String itemNeeded) 
			throws NullPointerException, DoesNotExistException {
		//Do not want to check null items
		if(itemNeeded == null) {
			throw new NullPointerException();
		}
		
		Item foundItem = null;
		
		for(Item item : items) {
			if (item.getName().equals(itemNeeded)) {
				foundItem = item;
			}
		}
		
		return foundItem;
	}
	//Logs
	public void logItems() {
		ItemsLoggable logger = ItemsLogFactory.createNewItemLog();
		
		logger.itemsLog(items);
	}
	
}
