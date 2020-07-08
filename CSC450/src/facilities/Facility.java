package facilities;

import java.util.List;

public interface Facility {
	//Facility Interface
	String getName();
	int getRatePerDay();
	int getItemsPerDay();
	List<Neighbor> getNeighbors();
	Schedule getSchedule();
	Inventory getInventory();
	List<String> getDepletedItems();
	boolean itemInStock(String itemName);
	int expectedShipDate(Integer quantity, Integer startDay);
	void bookDays(Integer quantity, Integer startDay);
	int daysToProcess(Integer quantity, Integer startDay);
	void reduceInventory(Integer quantity, String itemName);
	Integer checkStock(String itemName);
	Integer checkAmountGiven(String itemName, Integer amountNeeded);
}
