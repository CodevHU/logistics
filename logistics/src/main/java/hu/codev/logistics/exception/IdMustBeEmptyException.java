package hu.codev.logistics.exception;

public class IdMustBeEmptyException extends IllegalArgumentException {

	public IdMustBeEmptyException() {
		super("ID must be null or empty");
	}
	
}
