package facilities;

import java.util.ArrayList;

public interface Facility {
	
	//Facility Interface
	public String getName();
	public int getRatePerDay();
	public int getItemsPerDay();
	public ArrayList<Neighbor> getNeighbors();
	public Schedule getSchedule();
	public Inventory getInventory();
	public ArrayList<String> getDepletedItems();
	public boolean itemInStock(String itemName);
	public int expectedShipDate(Integer quantity, Integer startDay);
	public void bookDays(Integer quantity, Integer startDay);
	public int daysToProcess(Integer quantity, Integer startDay);
	public void reduceInventory(Integer quantity, String itemName);
	public Integer checkStock(String itemName);
	public Integer checkAmountGiven(String itemName, Integer amountNeeded);

}
