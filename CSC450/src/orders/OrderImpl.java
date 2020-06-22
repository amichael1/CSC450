package orders;

import exceptions.NullClassDataValueException;
import facilities.Facility;
import items.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Adam on 5/30/2016.
 */
public class OrderImpl implements Order {

    private String orderID;
    private int orderTime;
    private String destination;
    private ArrayList<String> itemsOnOrder;
    private ArrayList<Integer> quantityOfItems;
    private ArrayList<Integer> originalQuantityOfItems;


    public OrderImpl(String newOrderID, int newOrderTime, String newDestination,
                     ArrayList<String> newItemsOnOrder, ArrayList<Integer> newQuantityOfItems,  ArrayList<Integer> newOriginalQuantityOfItems)
            throws NullClassDataValueException
    {
        if(newOrderID == null ||newDestination == null ||
                newItemsOnOrder == null || newQuantityOfItems == null)
        {
            throw new NullClassDataValueException();
        }
        orderID = newOrderID;
        orderTime = newOrderTime;
        destination = newDestination;
        itemsOnOrder = newItemsOnOrder;
        quantityOfItems = newQuantityOfItems;
        originalQuantityOfItems = newOriginalQuantityOfItems;
    }

    public String getOrderID()
    {
        return new String(orderID);
    }

    public int getOrderTime()
    {
        int returnedOrderTime = orderTime;

        return returnedOrderTime;
    }

    public String getDestination()
    {
        return new String(destination);
    }

    public ArrayList<String> getItemsOnOrder()
    {
        return new ArrayList<String>(itemsOnOrder);
    }

    public ArrayList<Integer> getQuantityOfItems()
    {
        return new ArrayList<Integer> (quantityOfItems);
    }


    public ArrayList<Integer> getOriginalQuantityOfItems()
    {
        return new ArrayList<Integer>(originalQuantityOfItems);
    }

    public Integer getOriginalQuantityForItem(String itemName)
    {
        if(itemName==null)
        {
            throw new NullPointerException();
        }

        int index = itemsOnOrder.indexOf(itemName);
        return new Integer(quantityOfItems.get(index));
    }

    public void updateQuantity(Integer amountTaken, String itemName)
    {
        if(amountTaken==null||itemName==null)
        {
            throw new NullPointerException();
        }

        int index = itemsOnOrder.indexOf(itemName);

        quantityOfItems.set(index, quantityOfItems.get(index) - amountTaken);
    }

    public boolean itemNotSatisfied(String itemName)
    {

        if(itemName==null)
        {
            throw new NullPointerException();
        }

        if(!itemsOnOrder.contains(itemName))
        {
            return false;
        }
        boolean backOrdered = false;
        int index = itemsOnOrder.indexOf(itemName);

        if(!quantityOfItems.get(index).equals(0))
        {
            backOrdered=true;
        }

        return backOrdered;
    }

    public ArrayList<String> itemsOnBackOrder()
    {
        ArrayList<String> itemsOnBackOrder = new ArrayList<>();
        for(String item: itemsOnOrder)
        {
            if(itemNotSatisfied(item))
            {
                itemsOnBackOrder.add(item);
            }
        }

        if(itemsOnBackOrder==null)
        {
            itemsOnBackOrder.add("None");
        }

        return itemsOnBackOrder;
    }


}
