package orderprocessing;

import orders.Order;

import java.util.List;

/**
 * Created by Adam on 6/1/2016.
 */
public interface OrderSummaryLoggable {
    void orderSummary(List<OrdersProcessedSummary> summary, List<Order> orders) throws Exception;
}

