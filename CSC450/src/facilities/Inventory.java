package facilities;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	//Inventory class for inventory data
	//Only need to store data for facilities easily
	private List<String> item;
	private List<Integer> quantity;
	
	public Inventory(List<String> newItem, List<Integer> newQuantity) {
		item = newItem;
		quantity = newQuantity;
	}

	public Inventory(Inventory inventory) {
		item = inventory.getItem();
		quantity = inventory.getQuantity();
	}

	public List<String> getItem()
	{
		return new ArrayList<String>(item);
	}
	
	public List<Integer> getQuantity()
	{
		return new ArrayList<Integer>(quantity);
	}

	public List<String> getDepletedItems() {
		List<String> depletedItems = new ArrayList<String>();

		if(quantity.contains(0)) {
			for(int i=0; i<quantity.size();i++) {
				if(quantity.get(i).equals(0)) {
					depletedItems.add(item.get(i));
				}
			}
		}
		else {
			depletedItems.add("None");
		}

		return depletedItems;
	}


	//Checks to see if item exists and is in stock
	public boolean itemInStock(String itemName) {
		if(itemName==null) {
			throw new NullPointerException();
		}

		return item.contains(itemName);
	}


	//returns amount in stock or order amount if that is less than the amount in stock
	public int checkStock(String itemName) {
		if(!item.contains(itemName)) {
			return 0;
		}

		int index = item.indexOf(itemName);

		return quantity.get(index);
	}

	public Integer checkAmountGiven(String itemName, Integer amountNeeded) {
		if(!item.contains(itemName)) {
			return 0;
		}

		int index = item.indexOf(itemName);

		Integer amountInStock = quantity.get(index);

		Integer amountGiven = 0;

		if(amountInStock>amountNeeded) {
			amountGiven = amountNeeded;
		}
		else {
			amountGiven = amountInStock;
		}

		return amountGiven;
	}

	public void reduceInventory(Integer amountTaken, String itemName) {
		if(item.contains(itemName)) {
			int index = item.indexOf(itemName);

			quantity.set(index, quantity.get(index) - amountTaken);
		}
	}


}
