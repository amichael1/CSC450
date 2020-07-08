package orderprocessing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 6/1/2016.
 */
public class OrdersProcessedSummary {

    private List<Integer> firstDeliveryDay;
    private List<Integer> lastDeliveryDay;
    private List<String> items;
    private List<Integer> quantity;
    private List<Integer> costPerItem;
    private List<Integer> sourcesUsed;


    public OrdersProcessedSummary(List<Integer> newFirstDeliveryDay, List<Integer> newLastDeliveryDay,
                                  List<String> newItems, List<Integer> newQuantity,
                                  List<Integer> newCostPerItem, List<Integer> newSourcesUsed) {
        firstDeliveryDay = newFirstDeliveryDay;
        lastDeliveryDay = newLastDeliveryDay;
        items = newItems;
        quantity = newQuantity;
        costPerItem = newCostPerItem;
        sourcesUsed = newSourcesUsed;

    }


    public List<Integer> getFirstDeliveryDay() {
        return new ArrayList<Integer>(firstDeliveryDay);
    }

    public List<Integer> getLastDeliveryDay() {
        return new ArrayList<Integer>(lastDeliveryDay);
    }

    public List<String> getItems() {
        return new ArrayList<String>(items);
    }

    public List<Integer> getQuantity() {
        return new ArrayList<Integer>(quantity);
    }

    public List<Integer> getCostPerItem() {
        return new ArrayList<Integer>(costPerItem);
    }

    public List<Integer> getSourcesUsed() {
        return  new ArrayList<Integer>(sourcesUsed);
    }


}
