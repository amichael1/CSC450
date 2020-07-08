package orderprocessing;

/**
 * Created by Adam on 5/30/2016.
 */
public interface OrdersProcessable extends Comparable<OrdersProcessable> {
   String getOrderID();
   void updateItems(Integer takenItems) throws Exception;
   int getArrivalDate();
   String getSource();
   Integer getNumberOfItems();
   Integer getDaysTookToProcess();
   String getItemName();
   void setDaysTaken(Integer days);
   void setEndDay(Integer day);
   void setArrivalDay(Integer day);
   Integer getTravelTime();
}
