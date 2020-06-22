package logisiticsapplication;

import facilities.Facility;
import facilities.Neighbor;
import orderprocessing.OrderProcessingService;
import orderprocessing.OrdersProcessable;
import orders.Order;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class LogisticsApplication {

	public static void main(String[] args)
			throws Exception
	{
		//Load inventory and facilities
		OrderProcessingService.getInstance().loadItems();
		OrderProcessingService.getInstance().loadFacilities();
		OrderProcessingService.getInstance().loadOrders();

		//Log facilities the first time
		OrderProcessingService.getInstance().logFacilities();

		ArrayList<Order> orders = OrderProcessingService.getInstance().retrieveOrders();

		for(Order order:orders)
		{
			ArrayList<Integer> totalCostItem = new ArrayList<Integer>();
			ArrayList<Integer> sources = new ArrayList<Integer>();
			ArrayList<Integer> firstDay = new ArrayList<Integer>();
			ArrayList<Integer> lastDay = new ArrayList<Integer>();
			ArrayList<Integer> costPerItem = new ArrayList<Integer>();

			ArrayList<String> itemsOnOrder = OrderProcessingService.getInstance().getItemsOnOrder(order);
			String orderId = OrderProcessingService.getInstance().getOrderId(order);
			String destination = OrderProcessingService.getInstance().getDestination(order);
			Integer orderDay = OrderProcessingService.getInstance().getOrderDay(order);

			for(String item : itemsOnOrder)
			{
				ArrayList<OrdersProcessable> ordersProcessed = new ArrayList<OrdersProcessable>();

				Integer quantityForItem =
						OrderProcessingService.getInstance().getQuantityForItemInOrder(order,item);

				ArrayList<String> facilitiesWithItem =
						OrderProcessingService.getInstance().facilitiesWithItem(item, destination);

				for(String facility : facilitiesWithItem)
				{
					ArrayList<Neighbor> shortestPath =
							OrderProcessingService.getInstance().getShortestPath(facility,destination);

					Integer miles = OrderProcessingService.getInstance().getMilesFromPath(shortestPath);

					Integer numberOfItems =
							OrderProcessingService.getInstance().itemsInStock(facility,item);

					Integer travelTime = OrderProcessingService.getInstance().travelTime(miles);

					Integer shipDate =
							OrderProcessingService.getInstance().expectedShipDate(facility,numberOfItems,orderDay);


					Integer daysToProcess =
							OrderProcessingService.getInstance().daysToProcess(facility,numberOfItems,orderDay);

					Integer expectedDeliverDay = shipDate + travelTime;

							ordersProcessed.add(
							OrderProcessingService.getInstance().addProcessedOrder(orderId, facility, item,
									numberOfItems, daysToProcess, shipDate,travelTime, expectedDeliverDay));

				}

				OrderProcessingService.getInstance().sortSourcesByDay(ordersProcessed);

				for(int i=0; i<ordersProcessed.size();i++)
				{
					String facility
							= OrderProcessingService.getInstance().getFacilityName(ordersProcessed.get(i));

					Integer quantityNeeded =
							OrderProcessingService.getInstance().getQuantityForItemInOrder(order,item);

					Integer amountToGive =
							OrderProcessingService.getInstance().checkAmountGiven(ordersProcessed.get(i),quantityNeeded);


					//Update Items in order, inventory, and processed order info
					OrderProcessingService.getInstance().updateToItemsTaken(ordersProcessed.get(i),item,amountToGive);

					OrderProcessingService.getInstance().updateInventory(ordersProcessed.get(i),item);

					OrderProcessingService.getInstance().reduceQuantityNeeded(order,item,ordersProcessed.get(i));

					OrderProcessingService.getInstance().bookDays(ordersProcessed.get(i),orderDay);

					OrderProcessingService.getInstance().updateDaysTaken(facility,amountToGive,orderDay,ordersProcessed.get(i));

					OrderProcessingService.getInstance().updateProcessingEndDay(destination,amountToGive,orderDay,ordersProcessed.get(i));

					OrderProcessingService.getInstance().updateArrivalDay(destination,orderDay,ordersProcessed.get(i));

				}

				totalCostItem.add(OrderProcessingService.getInstance().totalCostForItemOnOrder(ordersProcessed));
				sources.add(OrderProcessingService.getInstance().getSourcesUsed(ordersProcessed));
				firstDay.add(OrderProcessingService.getInstance().getFirstDay(ordersProcessed));
				lastDay.add(OrderProcessingService.getInstance().getLastDay(ordersProcessed));
			}

			OrderProcessingService.getInstance().createSummary(firstDay,lastDay,order,totalCostItem,sources);
		}

		OrderProcessingService.getInstance().logSummary(orders);
		OrderProcessingService.getInstance().logFacilities();
		
	}

}
