package exceptions;

@SuppressWarnings("serial")
public class NullClassDataValueException extends Exception{
	//Throw exception if a data member of an object is null
	public NullClassDataValueException()
	{
		super("Attempted to create an object with a null data member!");
	}
}
