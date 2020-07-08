package network;

import java.util.List;

import exceptions.DoesNotExistException;
import facilities.Neighbor;

public interface Network {
	//Network interface
	List<Neighbor> getShortestPath(Neighbor start, Neighbor end) throws NullPointerException, DoesNotExistException, Exception;
}
