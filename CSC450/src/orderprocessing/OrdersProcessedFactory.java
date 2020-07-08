package orderprocessing;

/**
 * Created by Adam on 5/30/2016.
 */
public class OrdersProcessedFactory {

    private OrdersProcessedFactory() {}
    //Only one way to load
    //Needed in case other file formats are specified
    public static OrdersProcessable createNewOrders(String orderID,String newSource, String newItemName,
                                                    int newNumberOfItems, int newDaysTookToProcess, int newProcessingEndDate,
                                                    int newTravelTime, int newArrivalDate)
            throws Exception {
        if(orderID==null||newSource==null||newItemName==null) {
            throw new NullPointerException();
        }


        return new OrderProcessedImpl(orderID, newSource, newItemName, newNumberOfItems, newDaysTookToProcess, newProcessingEndDate, newTravelTime, newArrivalDate);
    }

}
