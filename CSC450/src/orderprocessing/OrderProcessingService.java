package orderprocessing;

import exceptions.DoesNotExistException;
import facilities.Facility;
import facilities.FacilityService;
import facilities.Neighbor;
import items.ItemService;
import network.NetworkService;
import orders.Order;
import orders.OrderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OrderProcessingService {

	private List<OrdersProcessedSummary> orderInformation;


	private static final int TRAVEL_COST_PER_DAY = 300;

	private volatile static OrderProcessingService orderProcessingManager;
	//Service that communicates with all the other services
	//Manages entire application and is how Main can call methods
	public static OrderProcessingService getInstance() throws Exception {
		synchronized(OrderProcessingService.class) {
			if (orderProcessingManager == null) {
				orderProcessingManager = new OrderProcessingService();
			}
		}
		
		return orderProcessingManager;
	}

	private OrderProcessingService() {
		orderInformation=new ArrayList<OrdersProcessedSummary>();
		//Could instantiate all services here
	}


	//Load and Log methods
	public void loadFacilities() throws Exception {
		FacilityService.getInstance();
	}


	public void loadItems() 
			throws Exception {
		ItemService.getInstance();
	}

	public void loadOrders()
			throws Exception {
		OrderService.getInstance();
	}

	public List<Order> retrieveOrders()
			throws Exception {
		return OrderService.getInstance().sendOrdersToProcess();
	}

	public List<String> getItemsOnOrder(Order order)
			throws Exception {
		if(order==null) {
			throw new NullPointerException();
		}

		return OrderService.getInstance().getItemsOnOrder(order);
	}

	public String getOrderId(Order order)
			throws Exception {
		if(order == null) {
			throw new NullPointerException();
		}


		return OrderService.getInstance().getOrderId(order);
	}

	public String getDestination(Order order)
			throws Exception {
		if(order == null) {
			throw new NullPointerException();
		}

		return OrderService.getInstance().getDestination(order);
	}

	public int getOrderDay(Order order)
			throws Exception {
		if(order==null) {
			throw new NullPointerException();
		}

		return OrderService.getInstance().getOrderDay(order);
	}

	public Integer getQuantityForItemInOrder(Order order, String itemName)
			throws Exception {
		if(order==null||itemName==null) {
			throw new NullPointerException();
		}

		return OrderService.getInstance().getQuantityOfItemOnOrder(order, itemName);
	}


	public void logItems() 
			throws Exception {
		ItemService.getInstance().logItems();
	}


	public void logFacilities() 
			throws NullPointerException, DoesNotExistException, Exception {
		FacilityService.getInstance().logItems();
	}


	//Get methods
	public Facility getFacility(String name)
			throws NullPointerException, DoesNotExistException, Exception {
		return FacilityService.getInstance().getFacility(name);
	}

	public String getFacilityName(OrdersProcessable orderProcessed)
			throws NullPointerException, DoesNotExistException, Exception {
		return orderProcessed.getSource();
	}


	public Neighbor toNeighbor(Facility facility)
			throws Exception {
		return FacilityService.getInstance().toNeighbor(facility);
	}


	//For the hard coding, since passing in string name and not neighbor or facility
	public Neighbor createStartEnd(String name) 
			throws NullPointerException, DoesNotExistException, Exception {
		return toNeighbor(getFacility(name));
	}


	//Gets and logs shortest path
	public List<Neighbor> getShortestPath(Neighbor start, Neighbor end)
			throws NullPointerException, DoesNotExistException, Exception {
		return NetworkService.getInstance().getShortestPath(start, end);
	}

	public List<Neighbor> getShortestPath(String facility, String destination)
			throws NullPointerException, DoesNotExistException, Exception {
		Neighbor start = toNeighbor(getFacility(facility));
		Neighbor end = toNeighbor(getFacility(destination));

		return NetworkService.getInstance().getShortestPath(start, end);
	}



	public List<Neighbor> getDestinationPath(String startFacilityName, String destination)
			throws NullPointerException, DoesNotExistException, Exception {
		if(startFacilityName==null||destination==null) {
			throw new NullPointerException();
		}

		Neighbor end = createStartEnd(destination);
		Neighbor start = createStartEnd(startFacilityName);

		return getShortestPath(start,end);
	}


	public void logPath(List<List<Neighbor>> shortestPaths)
			throws NullPointerException, Exception {
		NetworkService.getInstance().logPath(shortestPaths);
	}


	public List<String> facilitiesWithItem(String itemName, String destination)
			throws Exception {
		if(itemName==null) {
			throw new NullPointerException();
		}

		return FacilityService.getInstance().facilitiesWithItem(itemName,destination);
	}

	//Helper method for expectedDeliveryDay, gets miles so arrivalDay can be called
	public int getMilesFromPath (List<Neighbor> pathsFromSources)
			throws Exception {
		if(pathsFromSources==null) {
			throw new NullPointerException();
		}

		return NetworkService.getInstance().lengthOfPath(pathsFromSources);

	}


	public Integer itemsInStock(String facilityName, String itemName)
			throws Exception {
		if(facilityName==null||itemName==null) {
			throw new NullPointerException();
		}

		Facility facility = getFacility(facilityName);

		return facility.checkStock(itemName);
	}

	public Integer daysToProcess(String facilityName, Integer quantity, Integer startDate)
			throws Exception {
		if(facilityName==null||quantity==null||startDate==null) {
			throw new NullPointerException();
		}

		return 	FacilityService.getInstance().daysToProcess(facilityName,quantity,startDate);
	}

	public int travelTime(Integer miles)
			throws Exception {
		if(miles == null) {
			throw new NullPointerException();
		}

		return NetworkService.getInstance().travelTime(miles);
	}

	public int expectedShipDate(String facilityName, Integer quantity, Integer startDay)
			throws Exception {
		if(facilityName==null || quantity==null) {
			throw new NullPointerException();
		}

		return FacilityService.getInstance().expectedShipDate(quantity, facilityName, startDay);
	}

	//Finds the delivery day
	public int expectedDeliveryDate(String facility, int quantity,
													String destination, Integer startDay)
			throws Exception {

		if(facility==null || destination==null || startDay==null) {
			throw new NullPointerException();
		}


		int miles = getMilesFromPath(getDestinationPath(facility,destination));

		int expectedShipDate = expectedShipDate(facility, quantity, startDay);
		int expectedTravelTime = travelTime(miles);

		return expectedShipDate + expectedTravelTime;
	}

	public OrdersProcessable addProcessedOrder(String newOrderID, String newSource, String newItemName,
								  int newNumberOfItems, int newProcessingEndDate, int newDaysTookToProcess,
								  int newTravelTime, int newArrivalDate)
			throws Exception {
		OrdersProcessable newOrder = OrdersProcessedFactory.createNewOrders(newOrderID, newSource, newItemName, newNumberOfItems,
				newDaysTookToProcess, newProcessingEndDate, newTravelTime, newArrivalDate);

		return newOrder;

	}


	public List<OrdersProcessable> sortSourcesByDay (List<OrdersProcessable> ordersProcessed)
			throws Exception {

		Collections.sort(ordersProcessed);

		return new ArrayList<OrdersProcessable>(ordersProcessed);
	}


	public void updateToItemsTaken(OrdersProcessable ordersProcessed, String itemName, Integer quantityNeeded)
			throws Exception {
		if(ordersProcessed == null||itemName==null||quantityNeeded==null) {
			throw new NullPointerException();
		}


			ordersProcessed.updateItems(quantityNeeded);

	}

	public void updateDaysTaken(String facilityName, Integer quantity, Integer startDate, OrdersProcessable ordersProcessed)
			throws Exception {
		if(facilityName==null||quantity==null||startDate==null || ordersProcessed==null) {
			throw new NullPointerException();
		}

		ordersProcessed.setDaysTaken(daysToProcess(facilityName, quantity, startDate));
	}

	public void updateProcessingEndDay(String Destination, Integer quantity, Integer startDate, OrdersProcessable ordersProcessed)
		throws Exception {

		if(quantity==null||startDate==null||ordersProcessed==null) {
			throw new NullPointerException();
		}

		ordersProcessed.setEndDay(expectedShipDate(ordersProcessed.getSource(),ordersProcessed.getNumberOfItems(), startDate));
	}

	public void updateArrivalDay(String destination, Integer startDate, OrdersProcessable ordersProcessed)
			throws Exception {
		if(startDate==null||ordersProcessed==null) {
			throw new NullPointerException();
		}

		ordersProcessed.setArrivalDay(expectedDeliveryDate(ordersProcessed.getSource(),ordersProcessed.getNumberOfItems(),destination,startDate));
	}


	public Integer checkAmountGiven(OrdersProcessable orderProcessed, Integer amountNeeded)
			throws Exception {
		return FacilityService.getInstance().checkAmountGiven(orderProcessed.getSource(),orderProcessed.getItemName(),amountNeeded);
	}

	public void updateInventory (OrdersProcessable ordersProcessed, String itemName)
			throws Exception {
		if(ordersProcessed == null||itemName==null) {
			throw new NullPointerException();
		}

		FacilityService.getInstance().updateInventory(ordersProcessed.getSource(),itemName, ordersProcessed.getNumberOfItems());

	}


	public void reduceQuantityNeeded(Order order, String itemName, OrdersProcessable ordersProcessed)
			throws Exception {
		if(order==null||itemName==null||ordersProcessed==null) {
			throw new NullPointerException();
		}

		OrderService.getInstance().updateQuantity(ordersProcessed.getNumberOfItems(),order,itemName);

	}

	public void bookDays (OrdersProcessable ordersProcessed, Integer startDay)
			throws Exception {
		if(ordersProcessed == null) {
			throw new NullPointerException();
		}

		FacilityService.getInstance().bookDays(ordersProcessed.getSource(),ordersProcessed.getNumberOfItems(), startDay);

	}

	public boolean backOrdered(Order order, String itemName)
			throws Exception {
		if(order==null||itemName==null) {
			throw new NullPointerException();
		}

		return OrderService.getInstance().itemNotSatisfied(order, itemName);
	}

	public Integer getItemCost(String itemName)
			throws Exception {
		if(itemName==null) {
			throw new NullPointerException();
		}
		return ItemService.getInstance().getItemPrice(itemName);
	}

	public int itemCostPerFacility(OrdersProcessable processedItem)
			throws Exception {
		if(processedItem==null) {
			throw new NullPointerException();
		}

		String itemName = processedItem.getItemName();
		int itemCostPerUnit = getItemCost(itemName);
		int quantityOfItems = processedItem.getNumberOfItems();
		int costOfItemForRecord = quantityOfItems*itemCostPerUnit;

		int costToProcessPerDay = FacilityService.getInstance().costToProcessPerDay(processedItem.getSource());
		int daysProcessed = processedItem.getDaysTookToProcess();

		int costToProcess = daysProcessed*costToProcessPerDay;

		int travelCost =0;

		if(quantityOfItems!=0) {
			int travelTime = processedItem.getTravelTime();
			travelCost = travelTime * TRAVEL_COST_PER_DAY;
		}

		int totalCost = costOfItemForRecord + costToProcess + travelCost;

		return totalCost;
	}

	public Integer totalCostForItemOnOrder(List<OrdersProcessable> ordersProcessed)
			throws Exception {
		if(ordersProcessed==null) {
			throw new NullPointerException();
		}


		int totalCost= 0;

		for(OrdersProcessable processedItem : ordersProcessed) {
			totalCost += itemCostPerFacility(processedItem);
		}

		return totalCost;
	}

	public Integer getSourcesUsed(List<OrdersProcessable> ordersProcessed)
			throws Exception {

		if(ordersProcessed==null) {
			throw new NullPointerException();
		}


		int sourcesUsed=0;
		for(OrdersProcessable ordersAtFacility : ordersProcessed) {
			if(!ordersAtFacility.getNumberOfItems().equals(0)) {
				sourcesUsed++;
			}
		}

		return sourcesUsed;

	}

	public Integer getFirstDay(List<OrdersProcessable> ordersProcessed)
			throws Exception {
		if(ordersProcessed==null) {
			throw new NullPointerException();
		}

		int firstDay = ordersProcessed.get(0).getArrivalDate();

		for(int i=1;i< ordersProcessed.size();i++) {
			if(ordersProcessed.get(i).getArrivalDate()<firstDay) {
				firstDay=ordersProcessed.get(i).getArrivalDate();
			}
		}

		return firstDay;

	}

	public Integer getLastDay(List<OrdersProcessable> ordersProcessed)
			throws Exception {
		if(ordersProcessed==null) {
			throw new NullPointerException();
		}

		int lastDay = ordersProcessed.get(0).getArrivalDate();

		for(int i=1;i< ordersProcessed.size();i++) {
			if(ordersProcessed.get(i).getArrivalDate()>lastDay) {
				lastDay=ordersProcessed.get(i).getArrivalDate();
			}
		}
		return lastDay;

	}

	public List<Integer> getOriginalOrderQuantity(Order order)
			throws Exception {
		return OrderService.getInstance().getOriginalQuantity(order);
	}


	public Integer totalCostOfOrder(OrdersProcessedSummary summary)
			throws Exception {
		if(summary == null) {
			throw new NullPointerException();
		}

		Integer totalCost = 0;
		List<Integer> costsPerItem = summary.getCostPerItem();
		for(Integer itemCost : costsPerItem) {
			totalCost+=itemCost;
		}

		return totalCost;
	}

	public void createSummary(List<Integer> firstDay, List<Integer> lastDay, Order order,
							  List<Integer> costPerItem, List<Integer> sourcesUsed)
			throws Exception {
		if(firstDay==null||order==null||lastDay==null||costPerItem==null||sourcesUsed==null) {
			throw new NullPointerException();
		}


		OrdersProcessedSummary summary = new OrdersProcessedSummary(firstDay, lastDay, order.getItemsOnOrder() ,getOriginalOrderQuantity(order), costPerItem, sourcesUsed);

		orderInformation.add(summary);
	}

	public int summaryFirstDeliveryDay(OrdersProcessedSummary summary)
			throws Exception {
		if(summary==null) {
			throw new NullPointerException();
		}

		List<Integer> firstDays = summary.getFirstDeliveryDay();

		int firstDay = firstDays.get(0);

		for(int i=1;i< firstDays.size();i++) {
			if(firstDays.get(i)<firstDay) {
				firstDay=firstDays.get(i);
			}
		}

		return firstDay;
	}

	public int summaryLastDeliveryDay(OrdersProcessedSummary summary)
			throws Exception {
		if(summary==null) {
			throw new NullPointerException();
		}

		List<Integer> lastDays = summary.getLastDeliveryDay();

		int lastDay = lastDays.get(0);

		for(int i=1;i< lastDays.size();i++) {
			if(lastDays.get(i)>lastDay) {
				lastDay=lastDays.get(i);
			}
		}

		return lastDay;
	}


	public void logSummary(List<Order> orders)
			throws Exception {
		OrderSummaryLoggable logger = OrderSummaryLogFactory.createNewOrdersLog();
		logger.orderSummary(orderInformation, orders);
	}



}
