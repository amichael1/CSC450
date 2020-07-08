package facilities;

import exceptions.DoesNotExistException;
import java.util.Collections;
import java.util.List;

//log to console implementation
public class FacilitiesLogToConsoleImpl implements FacilitiesLoggable {

	private static final int ITEM_NAME_LONG = 8;
	private static final int DOUBLE_DIGIT_DAYS = 10;

	public void facilitiesLog(List<Facility> facilities) throws NullPointerException, DoesNotExistException, Exception {
		
		if(facilities == null) {
			throw new NullPointerException();
		}
		
		for(Facility facility: facilities) {
			//loop through each facility and follow format
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println(facility.getName());
			System.out.println("-----------");
			System.out.println("Rate per Day:\t" + facility.getItemsPerDay());
			System.out.println("Cost per Day:\t" + facility.getRatePerDay());
			
			System.out.println();
			 
			System.out.println("Direct Links:");

			List<Neighbor> neighbors = facility.getNeighbors();
			Collections.sort(neighbors);
			//prints out the neighbor and miles away
			for(Neighbor neighbor : neighbors)
			{
				double days = (double)neighbor.getMiles()/(50*8);
				String daysOutput =  String.format("%.1f",days);
				System.out.print(neighbor.getNeighbor() + " ("+  daysOutput +   "d); "); 
			}
			System.out.println();
			
			System.out.println("Active Inventory:");

			Inventory inventory = facility.getInventory();

			List<String> items = inventory.getItem();

			List<Integer> quantity =  inventory.getQuantity();

			System.out.println("Item ID \t Quantity");
			//prints out the inventory in two columns
			for(int j = 0; j < items.size(); j++) {
				//If item name is too long, add one less tab
				if(items.get(j).length()< ITEM_NAME_LONG) {
					System.out.println(items.get(j)+"\t\t   "+quantity.get(j));
				}
				else {
					System.out.println(items.get(j)+"\t   "+quantity.get(j));
				}
			}
			
			
			System.out.println();

			System.out.print("Depleted (Used-Up) Inventory: ");

			List<String> depletedItem = facility.getDepletedItems();

			for(String item : depletedItem) {
				System.out.print(item + " ");
			}

			System.out.println();
			
			System.out.println("Schedule:");
			
			Schedule schedule = facility.getSchedule();
			
			System.out.print("Day: \t\t");
			for(int i =1; i < schedule.scheduleLength(); i++) {
				int day = i;
				if(i < DOUBLE_DIGIT_DAYS) {
					System.out.print(" " + day + " ");
				}
				else {
					System.out.print(day + " ");
				}
			}
			
			System.out.println();
			//print schedule
			System.out.print("Available: \t");
			List<Integer> availableItems = schedule.getAvailable();
			for(int k =1; k < schedule.scheduleLength(); k++) {
				if(availableItems.get(k)<10) {
					System.out.print(" " +availableItems.get(k) + " ");
				}
				else {
					System.out.print(availableItems.get(k) + " ");
				}
			}
			System.out.println();
			
			System.out.println("---------------------------------------------------------------------------------");
		}

	}

}