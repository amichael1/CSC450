package items;

import java.util.List;

//Loader interface in case new ways to load are needed
public interface ItemLoader {
	List<Item> getItems() throws Exception;
}
