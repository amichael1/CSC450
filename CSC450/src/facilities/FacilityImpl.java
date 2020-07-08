package facilities;

import exceptions.NullClassDataValueException;

import java.util.ArrayList;
import java.util.List;

public class FacilityImpl implements Facility{
	//Facility class
	private String name;
	private int ratePerDay;
	private int itemsPerDay;
	private List<Neighbor> neighbors;
	private Schedule schedule;
	private Inventory inventory;
	
	public FacilityImpl(String newName, int newRatePerDay, int newItemsPerDay,
						List<Neighbor>newNeighbors, Schedule newSchedule, Inventory newInventory)
			throws NullClassDataValueException {
		if(newName == null || newNeighbors == null || newSchedule == null || newInventory == null) {
			throw new NullClassDataValueException();
		}

		name = newName;
		ratePerDay = newRatePerDay;
		itemsPerDay = newItemsPerDay;
		neighbors = newNeighbors;
		schedule = newSchedule;
		inventory = newInventory;	
	}


	//Only need Get methods at the moment, will need to add schedule methods to decrement schedule in phase 2
	public String getName() {
		String returnedName = new String(name);
		return returnedName;
	}
	
	public int getRatePerDay() {
		int returnRatePerDay = ratePerDay;
		return returnRatePerDay;
	}
	
	public int getItemsPerDay() {
		int returnItemsPerDay = itemsPerDay;
		return returnItemsPerDay;
	}

	public List<Neighbor> getNeighbors()
	{
		return new ArrayList<Neighbor>(neighbors);
	}
	
	public Schedule getSchedule()
	{
		return new Schedule(schedule);
	}
	
	public Inventory getInventory()
	{
		return new Inventory(inventory);
	}


	public List<String> getDepletedItems()
	{
		return inventory.getDepletedItems();
	}

	public boolean itemInStock(String itemName) {

		if(itemName == null) {
			throw new NullPointerException();
		}

		return inventory.itemInStock(itemName);
	}

	public int expectedShipDate(Integer quantity, Integer startDay) {
		if(quantity == null || startDay == null) {
			throw new NullPointerException();
		}

		return schedule.expectedShipDate(quantity, itemsPerDay, startDay);
	}

	public void bookDays(Integer quantity, Integer startDay) {
		if(quantity == null) {
			throw new NullPointerException();
		}
		schedule.bookDays(quantity, itemsPerDay, startDay);
	}

	public int daysToProcess(Integer quantity, Integer startDay) {
		if(quantity == null|| startDay == null) {
			throw new NullPointerException();
		}
		return schedule.daysToProcess(quantity, itemsPerDay, startDay);
	}

	public Integer checkStock(String itemName)
	{
		return inventory.checkStock(itemName);
	}

	public Integer checkAmountGiven(String itemName, Integer amountNeeded) {
		return inventory.checkAmountGiven(itemName, amountNeeded);
	}

	public void reduceInventory(Integer quantity, String itemName)
	{
		inventory.reduceInventory(quantity, itemName);
	}


}
