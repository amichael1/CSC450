package logisiticsapplication;

import java.util.ArrayList;

public class ItemsLogToConsoleImpl implements ItemsLoggable {
	
	private static final int ITEMS_PER_LINE = 4;

	public ItemsLogToConsoleImpl()
	{
		
	}

	public void itemsLog(ArrayList<Item> items) 
		throws NullPointerException
	{
		
		if(items == null)
		{
			throw new NullPointerException();
		}
		
		System.out.println("Item Catalog:");
		//Log item and price as specified
			for(int j=0; j < items.size(); j+=ITEMS_PER_LINE)
			{
				for(int i=j; i<j+ITEMS_PER_LINE; i++)
				{
					String itemPriceOutput = String.format("%.0f", items.get(i).getPrice());
					
					if(items.get(i).getName().length()<7)
					{
						System.out.print(items.get(i).getName()+":\t\t $" + itemPriceOutput + "\t\t");		
					}
					else
					{
						System.out.print(items.get(i).getName()+":\t $" + itemPriceOutput + "\t\t");								
					}
				}
				System.out.println();
			}
		
	}
	

}

