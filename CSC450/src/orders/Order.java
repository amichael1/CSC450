package orders;

import java.util.List;

/**
 * Created by Adam on 5/30/2016.
 */
public interface Order {
   String getOrderID();
   String getDestination();
   List<String> getItemsOnOrder();
   List<Integer> getQuantityOfItems();
   List<Integer> getOriginalQuantityOfItems();
   Integer getOriginalQuantityForItem(String itemName);
   int getOrderTime();
   void updateQuantity(Integer amountTaken, String itemName);
   boolean itemNotSatisfied(String itemName);
   List<String> itemsOnBackOrder();
}
