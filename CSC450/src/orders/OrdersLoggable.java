package orders;

import java.util.List;

/**
 * Created by Adam on 5/30/2016.
 */
public interface OrdersLoggable {
    void ordersLog(List<Order> orders) throws Exception;
}
