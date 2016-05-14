package logisiticsapplication;

import java.util.ArrayList;
//log to console implementation
public class FacilitiesLogToConsoleImpl implements FacilitiesLoggable {

	public void facilitiesLog(ArrayList<Facility> facilities) throws NullPointerException, DoesNotExistException, Exception {
		
		if(facilities == null)
		{
			throw new NullPointerException();
		}
		
		for(Facility facility: facilities)
		{
			//loop through each facility and follow format
			System.out.println("---------------------------------------------------------------------------------");
			System.out.println(facility.getName());
			System.out.println("-----------");
			System.out.println("Rate per Day:\t" + facility.getItemsPerDay());
			System.out.println("Cost per Day:\t" + facility.getRatePerDay());
			
			System.out.println();
			 
			System.out.println("Direct Links:");
			
			ArrayList<Neighbor> neighbors = facility.getNeighbors();
			//prints out the neighbor and miles away
			for(Neighbor neighbor : neighbors)
			{
				double days = (double)neighbor.getMiles()/(50*8);
				String daysOutput =  String.format("%.1f",days);
				System.out.print(neighbor.getNeighbor() + " ("+  daysOutput +   "d); "); 
			}
			System.out.println();
			
			System.out.println("Active Inventory:");
			
			ArrayList<Inventory> inventory = facility.getInventory();
			
			System.out.println("Item ID \t Quanity");
			//prints out the inventory in two columns
			for(Inventory item : inventory)
			{
				//If item name is too long, add one less tab
				if(item.getItem().length() < 8)
				{
					System.out.println(item.getItem()+"\t\t   "+item.getQuanity());
				}
				else
				{
					System.out.println(item.getItem()+"\t   "+item.getQuanity());
				}
			}
			
			
			System.out.println();
			System.out.println("Depleted (Used-Up) Inventory: None" );
			System.out.println();
			
			System.out.println("Schedule:");
			
			ArrayList<Schedule> schedule = facility.getSchedule();
			
			System.out.print("Day: \t\t");
			for(Schedule day : schedule)
			{
				if(day.getDay()<10)
				{
					System.out.print(" " +day.getDay() + " ");
				}
				else
				{
					System.out.print(day.getDay() + " ");
				}
			}
			
			System.out.println();
			//print schedule
			System.out.print("Available: \t");
			for(Schedule day : schedule)
			{
				if(day.getAvailable()<10)
				{
					System.out.print(" " +day.getAvailable() + " ");
				}
				else
				{
					System.out.print(day.getAvailable() + " ");
				}
			}
			System.out.println();
			
			System.out.println("---------------------------------------------------------------------------------");
		}

	}

}