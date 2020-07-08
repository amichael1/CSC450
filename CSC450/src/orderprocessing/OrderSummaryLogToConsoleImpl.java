package orderprocessing;

import orders.Order;

import java.util.List;

/**
 * Created by Adam on 6/2/2016.
 */
public class OrderSummaryLogToConsoleImpl implements OrderSummaryLoggable {
    private static final int LENGTH_OF_STRING_IN_TAB = 7;

    public void orderSummary(List<OrdersProcessedSummary> summaries, List<Order> orders)
            throws Exception {
        if(summaries==null||orders==null) {
            throw new NullPointerException();
        }

        int orderNumber = 1;

        for(int i = 0; i<orders.size();i++) {
            //will be in the same order as processed
            Order order = orders.get(i);
            OrdersProcessedSummary summary = summaries.get(i);

            System.out.println("---------------------------------------------------------------------------------- ");
            System.out.println("Order #" + orderNumber);
            System.out.println("\tOrder Id:\t" + order.getOrderID());
            System.out.println("\tOrder time:\tDay " + order.getOrderTime());
            System.out.println("\tOrder Destination:\t" + order.getDestination());
            System.out.println("\tList of Order Items:");

            List<String> items = order.getItemsOnOrder();
            List<Integer> quantityOfItems = order.getOriginalQuantityOfItems();

            int printOrder=1;
            for (int j = 0; j < items.size(); j++) {
                System.out.println("\t\t" + printOrder + ") Item ID:\t" + items.get(j) + ",\tQuantity:\t" + quantityOfItems.get(j));
                printOrder++;
            }

            orderNumber++;
            List<String> itemsOnBackOrder = order.itemsOnBackOrder();
            System.out.println();

            System.out.print("Items on back order:\t");
            for (String item : itemsOnBackOrder) {
                System.out.print(item + " ");
            }

            System.out.println();

            int totalCost = OrderProcessingService.getInstance().totalCostOfOrder(summary);
            int firstDeliveryDay = OrderProcessingService.getInstance().summaryFirstDeliveryDay(summary);
            int lastDeliveryDay = OrderProcessingService.getInstance().summaryLastDeliveryDay(summary);


            //Order Summary Info
            System.out.println("Processing Solution:");
            System.out.println("\tTotal Cost:\t\t" + totalCost);
            System.out.println("\t1st Delivery Day:\t" + firstDeliveryDay);
            System.out.println("\tLast Delivery Day:\t" + lastDeliveryDay);
            System.out.println("\tOrder Items:");

            List<String> itemsProcessed = summary.getItems();
            List<Integer> quantity = summary.getQuantity();
            List<Integer> cost = summary.getCostPerItem();
            List<Integer> sources = summary.getSourcesUsed();
            List<Integer> firstDay = summary.getFirstDeliveryDay();
            List<Integer> lastDay = summary.getLastDeliveryDay();

            System.out.println("Item ID\t\tQuantity\tCost\t# Sources Used\tFirst Day\tLast Day");
            int index = 0;
            for (String item : itemsProcessed) {
                if (item.length() < LENGTH_OF_STRING_IN_TAB) {
                    System.out.println(item + "\t\t\t" + quantity.get(index) + "\t\t" + cost.get(index) + "\t\t\t" + sources.get(index) + "\t\t\t"
                            + firstDay.get(index) + "\t\t" + lastDay.get(index));
                } else {
                    System.out.println(item + "\t\t" + quantity.get(index) + "\t\t" + cost.get(index) + "\t\t\t" + sources.get(index) + "\t\t\t"
                            + firstDay.get(index) + "\t\t\t" + lastDay.get(index));
                }
                index++;
            }
            System.out.println("---------------------------------------------------------------------------------- ");

        }

    }

}
