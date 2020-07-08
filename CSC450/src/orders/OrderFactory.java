package orders;

import exceptions.NullClassDataValueException;
import java.util.List;

/**
 * Created by Adam on 5/30/2016.
 */

public class OrderFactory {
    private OrderFactory() {}
    //Only one type of factory
    public static Order createNewOrder(String orderItemID, int orderTime, String destination,
                                       List<String> items, List<Integer> quantity, List<Integer> quantityOriginal)
                    throws NullClassDataValueException {
        return new OrderImpl(orderItemID, orderTime, destination, items, quantity, quantityOriginal);
    }

}
