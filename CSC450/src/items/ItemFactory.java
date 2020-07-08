package items;

public class ItemFactory {
	private ItemFactory() {}
	//Only one type of factory
	public static Item createNewItem(String name, int price) {
		return new ItemImpl(name, price);
	}
	
}
