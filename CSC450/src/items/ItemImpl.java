package items;

public class ItemImpl implements Item {
	private String name;
	private int price;
	
	public ItemImpl(String newName, int newPrice)
			throws NullPointerException {
		if(newName == null) {
			throw new NullPointerException();
		}
		
		name = newName;
		price = newPrice;
	}
	
	public String getName() {
		String returnedName = new String(name);
		return returnedName;
	}
	
	public int getPrice() {
		int returnedPrice = price;
		return returnedPrice;
	}
	

}
