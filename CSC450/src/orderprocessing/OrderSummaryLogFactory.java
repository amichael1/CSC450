package orderprocessing;

/**
 * Created by Adam on 6/1/2016.
 */
public class OrderSummaryLogFactory {
    private OrderSummaryLogFactory() {}
    //facility log factory, in case we want to log to a document or something else later
    public static OrderSummaryLoggable createNewOrdersLog() {
        return new OrderSummaryLogToConsoleImpl();
    }

}
