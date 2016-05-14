package logisiticsapplication;

public class Schedule {
	//facility will hold a collection of these, each one being one day of the schedule
	//Needed to easily store data about the schedule
	private int day;
	private int available;
	
	public Schedule()
	{
		day=0;
		available=0;
	}
	
	public Schedule(int newDay, int newAvailable)
	{
		day = newDay;
		available = newAvailable;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public int getAvailable()
	{
		return available;
	}
	//To be used later
	public void subtractAvailable(int itemsDone)
	{
		available = available - itemsDone;
	}

}
