package orders;

import exceptions.DoesNotExistException;

import java.util.List;

/**
 * Created by Adam on 5/30/2016.
 */

public class OrderService {
    private List<Order> orders;

    private volatile static OrderService orderManager;
    //Item manager singleton design
    //Facade for Items
    public static OrderService getInstance() throws Exception {
        synchronized(OrderService.class) {
            if (orderManager == null) {
                orderManager = new OrderService();
            }
        }

        return orderManager;
    }
    //Loads items into an ArrayList the manager manages
    private OrderService()
            throws Exception {
        OrderLoader orderLoader = OrderLoaderFactory.createNewOrders();
        orders = orderLoader.getOrders();
    }

    public List<Order> sendOrdersToProcess() {

        return orders;

    }

    public String getOrderId(Order order) {
        return order.getOrderID();
    }

    public List<String> getItemsOnOrder(Order order) {
        return order.getItemsOnOrder();
    }

    public String getDestination(Order order) {
        if(order==null) {
            throw new NullPointerException();
        }
        return order.getDestination();
    }

    public Integer getOrderDay(Order order) {
        if(order==null) {
            throw new NullPointerException();
        }

        return order.getOrderTime();
    }

    public Integer getQuantityOfItemOnOrder(Order order, String itemName) {
        if(order==null||itemName==null) {
            throw new NullPointerException();
        }

        return order.getOriginalQuantityForItem(itemName);
    }

    public List<Integer> getOriginalQuantity(Order order) {
        return order.getOriginalQuantityOfItems();
    }

    public Order getOrder(String orderId)
            throws Exception {
        if(orderId==null) {
            throw new NullPointerException();
        }
        if(!orders.contains(orderId)) {
            throw new DoesNotExistException();
        }

        int index = orders.indexOf(orderId);
        return orders.get(index);
    }

    public void logOrders() throws Exception {
        OrdersLoggable logger = OrdersLogFactory.createNewOrdersLog();
        logger.ordersLog(orders);
    }

    public void updateQuantity (Integer numberOfItems, Order order, String itemName)
            throws Exception {

        order.updateQuantity(numberOfItems,itemName);

    }

    public boolean itemNotSatisfied(Order order, String itemName) {
        if(order==null||itemName==null) {
            throw new NullPointerException();
        }

        return order.itemNotSatisfied(itemName);
    }



}
