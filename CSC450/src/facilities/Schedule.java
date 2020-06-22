package facilities;

import java.util.ArrayList;

public class Schedule {
	//facility will hold a collection of these, each one being one day of the schedule
	//Needed to easily store data about the schedule
	private ArrayList<Integer> available;
	
	public Schedule(ArrayList<Integer> newAvailable)
	{
		available = newAvailable;
	}
	//returns a new schedule object when a previous one is passed in
	//overloaded constructor
	public Schedule(Schedule schedule)
	{
		available = schedule.getAvailable();
	}
	
	public ArrayList<Integer> getAvailable()
	{
		return new ArrayList<>(available);
	}


	//book the days needed
	//quantity represents the amount of items needed to work on that day
	public void bookDays(Integer quantity, int itemsPerDay, Integer startDay)
	{
		if(startDay == null || quantity == null)
		{
			throw new NullPointerException();
		}

		int index = startDay;

			while(quantity > 0)
			{
				if(index>=available.size())
				{
					for(int i= 0; i<=(index-available.size()); i++)
					{
						available.add(itemsPerDay);
					}
				}
				//Only does work when time to schedule is available
				if(!available.get(index).equals(0))
				{
					//if more than enough to schedule in a day, no more is needed
					//set what is available that day to the difference between the availble schedule and the quantity
					if(available.get(index) > quantity)
					{
						quantity = 0;
						available.set(index, available.get(index)-quantity);
					}
					//if that day has just enough or less than the work time available
					//set that day to 0 and subtract the quantity needed by how much work it did
					else
					{
						quantity = quantity - available.get(index);
						available.set(index, 0);
					}

				}

				index++;

			}
	}


	//Get the expected day to ship
	public int expectedShipDate(Integer quantity, int itemsPerDay, Integer startDay)
	{
		if(quantity == null|| startDay==null)
		{
			throw new NullPointerException();
		}

		int dayProcessed = 0;
		int index = startDay;


		while(quantity >= 0)
		{
			if(index>=available.size())
			{
					available.add(itemsPerDay);
			}

			if(!available.get(index).equals(0))
			{
				quantity = quantity - available.get(index);
			}

			dayProcessed = index;
			index++;

		}


		return dayProcessed;

	}


	//get the amount of days it'll take to process the order
	public int daysToProcess(Integer quantity, int itemsPerDay, Integer startDay)
	{

		if(quantity == null|| startDay ==null)
		{
			throw new NullPointerException();
		}

		int index = startDay;

		int daysToProcess = 0;

		while(quantity>=0)
		{

			if(index>=available.size())
			{
					available.add(itemsPerDay);
			}

			if(!(available.get(index)).equals(0))
			{
				quantity = quantity - available.get(index);

				daysToProcess++;
			}

			index++;

		}

		return daysToProcess;

	}

	public int scheduleLength()
	{
		return available.size();
	}


}
