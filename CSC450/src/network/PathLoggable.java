package network;

import java.util.ArrayList;
import facilities.Neighbor;

public interface PathLoggable
{
	public void logPath(ArrayList<ArrayList<Neighbor>> shortestPath);

}