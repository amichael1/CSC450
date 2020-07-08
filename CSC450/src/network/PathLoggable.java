package network;

import java.util.List;

import facilities.Neighbor;

public interface PathLoggable {
	void logPath(List<List<Neighbor>> shortestPath);
}