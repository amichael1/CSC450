package logisiticsapplication;

import java.util.ArrayList;

public interface Facility {
	
	//Facility Interface
	public String getName();
	public double getRatePerDay();
	public int getItemsPerDay();
	public ArrayList<Neighbor> getNeighbors();
	public ArrayList<Schedule> getSchedule();
	public ArrayList<Inventory> getInventory();

}
