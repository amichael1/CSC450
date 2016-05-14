package logisiticsapplication;

import java.util.ArrayList;
import java.util.Iterator;


public class PathLogToConsoleImpl implements PathLoggable {

	
	private static final int HOURS_IN_DAY = 8;
	private static final int MILES_PER_HOUR = 50;
	private static final int LETTERS_IN_ALPHABET = 26;
	
	public void logPath(ArrayList<ArrayList<Neighbor>> shortestPath) {
		
		if(shortestPath == null)
		{
			throw new NullPointerException();
		}
		
		char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
		Iterator<ArrayList<Neighbor>> logIterator = shortestPath.iterator();
		int secondLetter = 0;
		int thirdLetter = 0;
		int letter = 0;
		//log each trip
		while(logIterator.hasNext())
		{
			if(letter < LETTERS_IN_ALPHABET)
			{
				ArrayList<Neighbor> neighbors = logIterator.next();
			
				Neighbor firstNode = neighbors.get(0);
				Neighbor lastNode = neighbors.get(neighbors.size()-1);
			
				int miles = 0;
			
			
				System.out.println(letters[letter]+") \t" + firstNode.getNeighbor()+ " to "+lastNode.getNeighbor()+":");
			

				System.out.print("\t - ");
				//Need to know if the last one in there so no arrow
				for(Neighbor neighbor : neighbors)
				{
					miles += neighbor.getMiles();
					if(!neighbor.equals(lastNode))
					{
						System.out.print(neighbor.getNeighbor() + "->");
					}
					else
					{
						System.out.print(neighbor.getNeighbor() + " = " + miles + " mi");
					}
				}
			
				System.out.println();
				System.out.print("\t - ");
			
				double daysDriven = (double) miles/(HOURS_IN_DAY*MILES_PER_HOUR);
				String daysDrivenOutput = String.format("%.2f", daysDriven);
			
				System.out.println(miles + " mi / ("+HOURS_IN_DAY+ " hours per day * "+MILES_PER_HOUR+" mph) = " +daysDrivenOutput+ " days");
				letter++;
			}
			else
			{
				ArrayList<Neighbor> neighbors = logIterator.next();
				
				Neighbor firstNode = neighbors.get(0);
				Neighbor lastNode = neighbors.get(neighbors.size()-1);
			
				int miles = 0;
			
			
				System.out.println(letters[secondLetter]+""+letters[thirdLetter]+") \t" + firstNode.getNeighbor()+ " to "+lastNode.getNeighbor()+":");
			

				System.out.print("\t - ");
				//Need to know if the last one in there so no arrow
				for(Neighbor neighbor : neighbors)
				{
					miles += neighbor.getMiles();
					if(!neighbor.equals(lastNode))
					{
						System.out.print(neighbor.getNeighbor() + "->");
					}
					else
					{
						System.out.print(neighbor.getNeighbor() + " = " + miles + " mi");
					}
				}
			
				System.out.println();
				System.out.print("\t - ");
			
				double daysDriven = (double) miles/(HOURS_IN_DAY*MILES_PER_HOUR);
				String daysDrivenOutput = String.format("%.2f", daysDriven);
			
				System.out.println(miles + " mi / ("+HOURS_IN_DAY+ " hours per day * "+MILES_PER_HOUR+" mph) = " +daysDrivenOutput+ " days");
				thirdLetter++;
				if(thirdLetter == LETTERS_IN_ALPHABET)
				{
					secondLetter++;
					thirdLetter = 0;
				}
				if(secondLetter == LETTERS_IN_ALPHABET)
				{
					letter=0;
				}
			}
		}	
		
		
	}

}
