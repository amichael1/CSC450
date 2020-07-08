package network;

import exceptions.DoesNotExistException;
import facilities.Facility;
import facilities.Neighbor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class ShortestPathImpl implements Network
{
	private List<Neighbor> lowPath;
	private HashMap<Integer, List<Neighbor>> pairs;
	private HashSet<Neighbor> seen;
	private int key;
	
	
	public ShortestPathImpl() {}
	
	public List<Neighbor> getShortestPath(Neighbor start, Neighbor end)
			throws NullPointerException, DoesNotExistException, Exception {
		if(start==null || end == null) {
			throw new NullPointerException();
		}
		
		lowPath = new ArrayList<Neighbor>();
		pairs = new HashMap<Integer, List<Neighbor>>();
		seen = new HashSet<Neighbor>();
		key = 0;
		
		mapPairs(start);
		
		seen = new HashSet<Neighbor>();

		List<Neighbor> pathList = new ArrayList<Neighbor>();
		pathList.add(start);
		
		findPath(start, end, pathList);
		
		return lowPath;
	}
	
	public void mapPairs(Neighbor start) throws NullPointerException, DoesNotExistException, Exception {
		seen.add(start);
		Facility facility = start.getFacility();
		List<Neighbor> allNeighbors = facility.getNeighbors();
		
		for(Neighbor neighbor : allNeighbors) {
			List<Neighbor> pairing = new ArrayList<Neighbor>();
			pairing.add(start);
			pairing.add(neighbor);
			pairs.put(key, pairing);
			key++;
			
			if(!existsHashSet(seen, neighbor)) {
				mapPairs(neighbor);
			}
		}
		
	}
	
	public void findPath(Neighbor start, Neighbor end, List<Neighbor> pathList)
			throws NullPointerException, DoesNotExistException, Exception {
		if(start.getFacility().equals(end.getFacility())) {
			int pathListSum = 0;
			for(Neighbor neighbor : pathList) {
				pathListSum += neighbor.getMiles();
			}
			
			int lowPathSum = 0;
			for(Neighbor neighbor : lowPath) {
				lowPathSum += neighbor.getMiles();
			}
			
			if(pathListSum < lowPathSum || lowPathSum == 0) {
				lowPath = pathList;
				return;
			}
			else {
				return;
			}
			
		}
		else {
			HashSet<List<Neighbor>> fromHere = new HashSet<List<Neighbor>>();
		
			for(int i = 0; i<pairs.size();i++) {
				List<Neighbor> neighbors = pairs.get(i);
				Neighbor firstNode = neighbors.get(0);
			
				if(firstNode.getFacility().equals(start.getFacility())) {
				
					fromHere.add(neighbors);
		
				}	
			}	
		
		
			for(List<Neighbor> neighbors : fromHere) {
				Neighbor secondNode = neighbors.get(1);
			
				if(!existsArrayList(pathList, secondNode)) {
					List<Neighbor> newPath = new ArrayList<Neighbor>();
					
					for(Neighbor neighbor:pathList) {
						newPath.add(neighbor);
					}
					
					newPath.add(secondNode);
					findPath(secondNode, end, newPath);
				
				}
			}
		}
	}
	//Maybe making a comparer class
	public boolean existsHashSet(HashSet<Neighbor> neighborsToCheck, Neighbor neighborCheckedAgainst)
			throws NullPointerException, DoesNotExistException, Exception {
		//Since Miles will be different with end node, cannot use ArrayList.contains()
		//Need to check if it exists another way
		boolean contains = false;
		
		//Change to facility because for some reason checking names of neighbors returns false when true
		//Will fix for second phase
		for(Neighbor neighbor : neighborsToCheck) {
			if(neighborCheckedAgainst.equals(neighbor)) {
				contains = true;
			}
		}
		
		return contains;
	}
	
	public boolean existsArrayList(List<Neighbor> neighborsToCheck, Neighbor neighborCheckedAgainst)
			throws NullPointerException, DoesNotExistException, Exception {
		//Since Miles will be different with end node, cannot use ArrayList.contains()
		//Need to check if it exists another way
		boolean contains = false;
		
		
		for(Neighbor neighbor : neighborsToCheck) {
			if(neighborCheckedAgainst.equals(neighbor)) {
				contains = true;
			}
		}
		
		return contains;
	}
}
