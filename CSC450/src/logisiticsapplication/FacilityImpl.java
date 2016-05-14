package logisiticsapplication;

import java.util.ArrayList;

public class FacilityImpl implements Facility{
	//Facility class
	private String name;
	private double ratePerDay;
	private int itemsPerDay;
	private ArrayList<Neighbor> neighbors;
	private ArrayList<Schedule> schedule;
	private ArrayList<Inventory> inventory;
	
	public FacilityImpl()
	{
		name = null;
		ratePerDay = 0;
		itemsPerDay = 0;
		neighbors = null;
		neighbors = null;
		schedule = null;
		inventory = null;
	}
	
	public FacilityImpl(String newName, double newRatePerDay, int newItemsPerDay, 
			ArrayList<Neighbor>newNeighbors, ArrayList<Schedule> newSchedule, ArrayList<Inventory> newInventory) 
	{
		
		name = newName;
		ratePerDay = newRatePerDay;
		itemsPerDay = newItemsPerDay;
		neighbors = newNeighbors;
		schedule = newSchedule;
		inventory = newInventory;	
	}
	//Only need Get methods at the moment, will need to add schedule methods to decrement schedule in phase 2
	public String getName()
	{
		return name;
	}
	
	public double getRatePerDay()
	{
		return ratePerDay;
	}
	
	public int getItemsPerDay()
	{
		return itemsPerDay;
	}

	public ArrayList<Neighbor> getNeighbors()
	{
		return neighbors;
	}
	
	public ArrayList<Schedule> getSchedule()
	{
		return schedule;
	}
	
	public ArrayList<Inventory> getInventory()
	{
		return inventory;
	}

}
