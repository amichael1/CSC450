package logisiticsapplication;

import java.util.ArrayList;

public class LogisiticsApplication {

	public static void main(String[] args) throws Exception {
		//Load inventory and facilities
		OrderProcessingService.getInstance().loadItems();
		OrderProcessingService.getInstance().loadFacilities();
		
		ArrayList<ArrayList<Neighbor>> allPathsTaken = new ArrayList<ArrayList<Neighbor>>();
		//Log them
		OrderProcessingService.getInstance().logFacilities();
		OrderProcessingService.getInstance().logItems();
		
		//Hard coding until orders are implemented
		Neighbor start = OrderProcessingService.getInstance().createStartEnd("Santa Fe, NM");
		Neighbor end = OrderProcessingService.getInstance().createStartEnd("Chicago, IL");
		
		allPathsTaken.add(OrderProcessingService.getInstance().getShortestPath(start, end));

		start = OrderProcessingService.getInstance().createStartEnd("Atlanta, GA");
		end = OrderProcessingService.getInstance().createStartEnd("St. Louis, MO");
		
		allPathsTaken.add(OrderProcessingService.getInstance().getShortestPath(start, end));

		start = OrderProcessingService.getInstance().createStartEnd("Seattle, WA");
		end = OrderProcessingService.getInstance().createStartEnd("Nashville, TN");
		
		allPathsTaken.add(OrderProcessingService.getInstance().getShortestPath(start, end));
		
		start = OrderProcessingService.getInstance().createStartEnd("New York City, NY");
		end = OrderProcessingService.getInstance().createStartEnd("Phoenix, AZ");
		
		allPathsTaken.add(OrderProcessingService.getInstance().getShortestPath(start, end));
		
		start = OrderProcessingService.getInstance().createStartEnd("Fargo, ND");
		end = OrderProcessingService.getInstance().createStartEnd("Austin, TX");
		
		allPathsTaken.add(OrderProcessingService.getInstance().getShortestPath(start, end));
		
		start = OrderProcessingService.getInstance().createStartEnd("Denver, CO");
		end = OrderProcessingService.getInstance().createStartEnd("Miami, FL");
		
		allPathsTaken.add(OrderProcessingService.getInstance().getShortestPath(start, end));
		
		
		start = OrderProcessingService.getInstance().createStartEnd("Austin, TX");
		end = OrderProcessingService.getInstance().createStartEnd("Norfolk, VA");
		
		allPathsTaken.add(OrderProcessingService.getInstance().getShortestPath(start, end));
		
		start = OrderProcessingService.getInstance().createStartEnd("Miami, FL");
		end = OrderProcessingService.getInstance().createStartEnd("Seattle, WA");
		
		allPathsTaken.add(OrderProcessingService.getInstance().getShortestPath(start, end));
		
		start = OrderProcessingService.getInstance().createStartEnd("Los Angeles, CA");
		end = OrderProcessingService.getInstance().createStartEnd("Chicago, IL");
		
		allPathsTaken.add(OrderProcessingService.getInstance().getShortestPath(start, end));
		
		start = OrderProcessingService.getInstance().createStartEnd("Detroit, MI");
		end = OrderProcessingService.getInstance().createStartEnd("Nashville, TN");
		
		allPathsTaken.add(OrderProcessingService.getInstance().getShortestPath(start, end));
		
		OrderProcessingService.getInstance().logPath(allPathsTaken);
		
	}

}
