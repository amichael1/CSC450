package orders;

/**
 * Created by Adam on 5/30/2016.
 */
public class OrdersLogFactory
{

    private OrdersLogFactory()
    {

    }
    //facility log factory, in case we want to log to a document or something else later
    public static OrdersLoggable createNewOrdersLog()
    {
        return new OrdersLogToConsoleImpl();
    }


}
