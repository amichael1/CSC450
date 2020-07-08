package items;

import java.util.List;

public class ItemsLogToConsoleImpl implements ItemsLoggable {
	private static final int ITEMS_PER_LINE = 4;
	private static final int LENGTH_OF_STRING_IN_TAB = 7;

	public ItemsLogToConsoleImpl() {}

	public void itemsLog(List<Item> items)
		throws NullPointerException {
		
		if(items == null) {
			throw new NullPointerException();
		}
		
		System.out.println("Item Catalog:");
		//Log item and price as specified
			for(int j=0; j < items.size(); j+=ITEMS_PER_LINE) {
				for(int i=j; i<j+ITEMS_PER_LINE; i++) {
					String itemPriceOutput = String.format("%d", items.get(i).getPrice());
					
					if(items.get(i).getName().length()<LENGTH_OF_STRING_IN_TAB) {
						System.out.print(items.get(i).getName()+":\t\t $" + itemPriceOutput + "\t\t");		
					}
					else {
						System.out.print(items.get(i).getName()+":\t $" + itemPriceOutput + "\t\t");								
					}
				}

				System.out.println();
			}
	}
}

