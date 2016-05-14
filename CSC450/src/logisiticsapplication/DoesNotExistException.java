package logisiticsapplication;

@SuppressWarnings("serial")
public class DoesNotExistException extends Exception {

	//If the item cannot be null, do not want to return null
	public DoesNotExistException()
	{
		super("Item passed does not exist!");
	}
	
}
