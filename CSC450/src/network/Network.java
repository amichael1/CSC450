package network;

import java.util.ArrayList;
import exceptions.DoesNotExistException;
import facilities.Neighbor;

public interface Network
{
	//Network interface
	public ArrayList<Neighbor> getShortestPath(Neighbor start, Neighbor end) throws NullPointerException, DoesNotExistException, Exception;

}
