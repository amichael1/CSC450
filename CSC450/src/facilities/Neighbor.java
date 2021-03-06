package facilities;

import exceptions.DoesNotExistException;

public class Neighbor implements Comparable<Neighbor> {
	//Facility will hold a collection of these to state which nodes are connected
	//Needed to easily store data
	private String neighbor;
	private int miles;
	
	public Neighbor() {
		neighbor = null;
		miles = 0;
	}
	
	public Neighbor(String newNeighbor, int newMiles) {
		neighbor = newNeighbor;
		miles = newMiles;
	}
	
	public String getNeighbor()
	{
		return new String(neighbor);
	}
	
	public int getMiles()
	{
		return miles;
	}
	
	public Facility getFacility() throws NullPointerException, DoesNotExistException, Exception {
		return FacilityService.getInstance().getFacility(neighbor);
	}
	
	public boolean equals(Neighbor neighborChecked) {
		if(neighborChecked==null)
		{
			throw new NullPointerException();
		}
		return (neighborChecked.getNeighbor().equals(neighbor));
	}

	public int compareTo(Neighbor neighborComparedTo)
	{
		return neighbor.compareTo(neighborComparedTo.getNeighbor());
	}

}
