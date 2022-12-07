package hu.codev.logistics.exception;

@SuppressWarnings("serial")
public class IdMustBeEmptyException extends IllegalArgumentException {

	public IdMustBeEmptyException() {
		super("ID must be null or empty");
	}
	
}
