package orders;

import exceptions.NullClassDataValueException;
import facilities.Facility;
import items.Item;
import java.util.ArrayList;

/**
 * Created by Adam on 5/30/2016.
 */

public class OrderFactory {

    private OrderFactory()
    {

    }
    //Only one type of factory
    public static Order createNewOrder(String orderItemID, int orderTime, String destination,
                                       ArrayList<String> items, ArrayList<Integer> quantity, ArrayList<Integer> quantityOriginal)
                    throws NullClassDataValueException
    {

        return new OrderImpl(orderItemID, orderTime, destination, items, quantity, quantityOriginal);
    }

}
