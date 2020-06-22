package orderprocessing;

import orders.Order;

import java.util.ArrayList;

/**
 * Created by Adam on 6/1/2016.
 */
public interface OrderSummaryLoggable {

    public void orderSummary(ArrayList<OrdersProcessedSummary> summary, ArrayList<Order> orders) throws Exception;
}

