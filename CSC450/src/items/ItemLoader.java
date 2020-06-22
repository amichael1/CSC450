package items;

import java.util.ArrayList;
//Loader interface in case new ways to load are needed
public interface ItemLoader {
	
	public ArrayList<Item> getItems() throws Exception;

}
