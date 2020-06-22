package orders;

import java.util.ArrayList;

/**
 * Created by Adam on 5/30/2016.
 */
public interface Order {

    public String getOrderID();
    public String getDestination();
    public ArrayList<String> getItemsOnOrder();
    public ArrayList<Integer> getQuantityOfItems();
    public ArrayList<Integer> getOriginalQuantityOfItems();
    public Integer getOriginalQuantityForItem(String itemName);
    public int getOrderTime();
    public void updateQuantity(Integer amountTaken, String itemName);
    public boolean itemNotSatisfied(String itemName);
    public ArrayList<String> itemsOnBackOrder();


}
