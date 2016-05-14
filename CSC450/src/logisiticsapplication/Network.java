package logisiticsapplication;

import java.util.ArrayList;

public interface Network {
	//Network interface
	public ArrayList<Neighbor> getShortestPath(Neighbor start, Neighbor end) throws NullPointerException, DoesNotExistException, Exception;

}
