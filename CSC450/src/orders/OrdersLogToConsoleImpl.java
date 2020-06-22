package orders;

import orderprocessing.OrdersProcessedSummary;

import java.util.ArrayList;

/**
 * Created by Adam on 5/30/2016.
 */

public class OrdersLogToConsoleImpl implements OrdersLoggable
{
    private static final int LENGTH_OF_STRING_IN_TAB = 7;

    public void ordersLog(ArrayList<Order> orders)
            throws Exception
    {
        int orderNumber = 1;
        for(Order order : orders)
        {
            System.out.println("---------------------------------------------------------------------------------- ");
            System.out.println("Order #" + orderNumber);
            System.out.println("\tOrder Id:\t" + order.getOrderID());
            System.out.println("\tOrder time:\tDay " + order.getOrderID());
            System.out.println("\tOrder Destination:\t" + order.getDestination());
            System.out.println("\tList of Order Items:");

            ArrayList<String> items = order.getItemsOnOrder();
            ArrayList<Integer> quantityOfItems = order.getQuantityOfItems();

            int itemNumber = 1;

            for(int i=0; i<items.size();i++)
            {
                System.out.println("\t\t"+itemNumber+") Item ID:\t"+items.get(i)+",\tQuantity:\t"+quantityOfItems.get(i));
            }

            orderNumber++;
            ArrayList<String> itemsOnBackOrder=order.itemsOnBackOrder();
            System.out.println();

            System.out.print("Items on back order:\t");
            for(String item : itemsOnBackOrder)
            {
                System.out.print(item + " ");
            }

            System.out.println();
        }

    }

}
