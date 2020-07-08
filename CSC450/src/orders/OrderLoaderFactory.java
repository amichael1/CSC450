package orders;

/**
 * Created by Adam on 5/30/2016.
 */
public class OrderLoaderFactory {
    private OrderLoaderFactory() {}
    //Only one way to load
    //Needed in case other file formats are specified
    public static OrderLoader createNewOrders()
    {
        return new OrderXmlLoaderImpl();
    }
}
